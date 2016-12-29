package com.kaishengit.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kaishengit.dao.LoginLogDao;
import com.kaishengit.dao.NotifyDao;
import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.LoginLog;
import com.kaishengit.entity.Notify;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.EmailUtil;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.vo.UserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);


    private UserDao userDao = new UserDao();
    private LoginLogDao loginLogDao = new LoginLogDao();
    private NotifyDao notifyDao = new NotifyDao();
    //发送激活邮件的TOKEN缓存
    private static Cache<String,String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(6, TimeUnit.HOURS)
            .build();
    //发送找回密码邮件的Token缓存
    private static Cache<String,String> passwordCache = CacheBuilder.newBuilder()
            .expireAfterWrite(30,TimeUnit.MINUTES)
            .build();
    //限制操作频率的缓存
    private static Cache<String,String> activeCache = CacheBuilder.newBuilder()
            .expireAfterWrite(60,TimeUnit.SECONDS)
            .build();



    /**
     * 校验用户名是否被占用
     * @param username
     * @return
     */
    public boolean validateUserName(String username) {
        //注意保留用户名(名人或其他的名字)
        String name = Config.get("no.signup.usernames");
        List<String> nameList = Arrays.asList(name.split(","));
        if(nameList.contains(username)) {
            return false;
        }
        return  userDao.findByUserName(username) == null;
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * 新用户注册
     * @param username
     * @param password
     * @param email
     * @param phone
     */
    public void saveNewUser(String username, String password, String email, String phone) {
        User user = new User();
        user.setUsername(username);
        user.setAvatar(User.DEFAULT_AVATAR_NAME);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(DigestUtils.md5Hex(Config.get("user.password.salt") + password));
        user.setState(User.USERSTATE_UNACTIVE);

        userDao.save(user);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //给用户发送激活邮件
                String uuid = UUID.randomUUID().toString();
                String url = "http://localhost/user/active?_="+uuid;
//                String url = "http://bbs.kaishengit.com/user/active?_="+uuid;
                //放入缓存等待6个小时
                cache.put(uuid,username);

                String html ="<h3>Dear "+username+":</h3>请点击<a href='"+url+"'>该链接</a>去激活你的账号. <br> 凯盛软件";

                EmailUtil.sendHtmlEmail(email,"用户激活邮件",html);
            }
        });
        thread.start();
    }

    /**
     * 根据Token激活对应的用户
     * @param token
     */
    public void activeUser(String token) {
        String userName = cache.getIfPresent(token);
        if(userName == null) {
            throw new ServiceException("token无效或已过期");
        } else {
            User user = userDao.findByUserName(userName);
            if(user == null) {
                throw new ServiceException("无法找到对应的账号");
            } else {
                user.setState(User.USERSTATE_ACTIVE);
                userDao.update(user);

                //将缓存中的键值对删除
                cache.invalidate(token);
            }
        }
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @param ip
     */
    public User login(String username, String password, String ip) {
        User user = userDao.findByUserName(username);
        if(user != null && DigestUtils.md5Hex(Config.get("user.password.salt") + password).equals(user.getPassword())) {
            if(user.getState().equals(User.USERSTATE_ACTIVE)) {
                //记录登录日志
                LoginLog log = new LoginLog();
                log.setIp(ip);
                log.setUserId(user.getId());

                loginLogDao.save(log);
                //拼装avatar的完整路径
                user.setAvatar(Config.get("qiniu.domain") + user.getAvatar());
                logger.info("{}登录了系统，IP：{}",username,ip);
                return user;

            } else if(User.USERSTATE_UNACTIVE.equals(user.getState())) {
                throw new ServiceException("该账号未激活");
            } else {
                throw new ServiceException("该账号已被禁用");
            }
        } else {
            throw new ServiceException("账号或密码错误");
        }
    }

    /**
     * 用户找回密码
     * @param sessionId 客户端的sessionID,限制客户端的操作频率
     * @param type 找回密码方式 email | phone
     * @param value 电子邮件地址 | 手机号码
     */
    public void foundPassword(String sessionId, String type, String value) {
        if(activeCache.getIfPresent(sessionId) == null) {
            if("phone".equals(type)) {
                //TODO 根据手机号码找回密码
            } else if("email".equals(type)) {
                User user = userDao.findByEmail(value);
                if(user != null) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String uuid = UUID.randomUUID().toString();
                            String url = "http://bbs.kaishengit.com/foundpassword/newpassword?token=" + uuid;

                            passwordCache.put(uuid,user.getUsername());
                            String html = user.getUsername()+"<br>请点击该<a href='"+url+"'>链接</a>进行找回密码操作，链接在30分钟内有效";
                            EmailUtil.sendHtmlEmail(value,"密码找回邮件",html);
                        }
                    });
                    thread.start();
                }
            }

            activeCache.put(sessionId,"xxx");
        } else {
            throw new ServiceException("操作频率过快");
        }
    }

    /**
     * 根据找回密码的链接获取找回密码的用户
     * @param token
     * @return
     */
    public User foundPasswordGetUserByToken(String token) {
        String username = passwordCache.getIfPresent(token);
        if(StringUtils.isEmpty(username)) {
            throw new ServiceException("token过期或错误");
        } else {
            User user = userDao.findByUserName(username);
            if(user == null) {
                throw new ServiceException("未找到对应账号");
            } else {
                return user;
            }
        }

    }

    /**
     * 重置用户的密码
     * @param id 用户ID
     * @param token 找回密码的TOken
     * @param password 新密码
     */
    public void resetPassword(String id, String token, String password) {
        if(passwordCache.getIfPresent(token) == null) {
            throw new ServiceException("token过期或错误");
        } else {
            String username = passwordCache.getIfPresent(token);
            User user = userDao.findByUserName(username); //userDao.findById(Integer.valueOf(id));
            user.setPassword(DigestUtils.md5Hex(Config.get("user.password.salt")+password));
            userDao.update(user);

            //删除token
            passwordCache.invalidate(token);

            logger.info("{} 重置了密码",user.getUsername());
        }
    }

    /**
     * 修改用户的电子邮件
     * @param user
     * @param email
     */
    public void updateEmail(User user, String email) {
        user.setEmail(email);
        userDao.update(user);
    }

    /**
     * 修改用户的密码
     * @param user
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    public void updatePassword(User user, String oldPassword, String newPassword) {
        String salt = Config.get("user.password.salt");
        if(DigestUtils.md5Hex(salt + oldPassword).equals(user.getPassword())) {
            newPassword = DigestUtils.md5Hex(salt + newPassword);
            user.setPassword(newPassword);
            userDao.update(user);
        } else {
            throw new ServiceException("原始密码错误");
        }
    }

    /**
     * 设置用户的头像
     * @param user
     * @param fileKey
     */
    public void updateAvatar(User user, String fileKey) {
        user.setAvatar(fileKey);
        userDao.update(user);
    }

    public List<Notify> findNotifyListByUser(User user) {
       return notifyDao.findByUserId(user.getId());

    }

    public void updateNotifyStateByIds(String ids) {
        String idArray[] = ids.split(",");
        for (int i= 0 ;i <idArray.length;i++ ){
            Notify notify = notifyDao.findById(idArray[i]);
            notify.setState(Notify.NOTIFY_STATE_READ);
            notify.setReadtime(new Timestamp(DateTime.now().getMillis()));
            notifyDao.update(notify);
        }

    }

    public Page<UserVo> findUserList(Integer pageNo) {
        Integer count = userDao.count();
        Page<UserVo> page = new Page<>(count,pageNo);
        List<User> userList =  userDao.findAllUsers(page);
        List<UserVo> userVoList = new ArrayList<>();

       /* for (User user:userList){
            UserVo vo = new UserVo();
            vo.setUserId(user.getId());
            vo.setUsername(user.getUsername());
            vo.setUserState(String.valueOf(user.getState()));
            vo.setCreatetime(String.valueOf(user.getCreateTime()));
            LoginLog loginLog = loginLogDao.findLastLogin(user.getId());
            if(loginLog != null){
                vo.setLoginIP(loginLog.getIp());
                vo.setLastLoginTime(String.valueOf(loginLog.getLoginTime()));
            }
            userVoList.add(vo);
        }*/
       for (User user:userList){
           UserVo userVo = userDao.findUserVo(user.getId());
           userVoList.add(userVo);
       }
        page.setItems(userVoList);
        return page;
    }

    public void updateUserState(String userid, Integer userState) {
        if(StringUtils.isNumeric(userid)){
            User user = userDao.findById(Integer.valueOf(userid));
            user.setState(userState);
            userDao.update(user);
        }else{
            throw new ServiceException("参数异常");
        }
        }
}
