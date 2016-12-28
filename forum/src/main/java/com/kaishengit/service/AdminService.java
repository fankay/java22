package com.kaishengit.service;

import com.kaishengit.dao.AdminDao;
import com.kaishengit.dao.NodeDao;
import com.kaishengit.dao.ReplyDao;
import com.kaishengit.dao.TopicDao;
import com.kaishengit.entity.Admin;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Topic;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jimi_jin on 2016-12-28.
 */
public class AdminService {
    Logger logger = LoggerFactory.getLogger(AdminService.class);
    AdminDao admindao = new AdminDao();
    TopicDao topicDao = new TopicDao();
    ReplyDao replyDao = new ReplyDao();
    NodeDao nodeDao = new NodeDao();
    public Admin login(String adminName, String password, String ip) {
        Admin admin = admindao.findAdminByName(adminName);
        if(admin != null && admin.getPassword().equals(DigestUtils.md5Hex(Config.get("user.password.salt") + password))){
            logger.debug("管理员{}登录了后台管理系统,IP为:{}",adminName,ip);
            return admin;
        }else{
            throw new ServiceException("帐号密码错误");
        }


    }

    public void deleteTopicById(String id) {
        //删除主题的回复
        replyDao.delByTopicId(id);
        //更新节点下的主题数量
        //1.根据topicId 获取 nodeId
        Topic topic = topicDao.findTopicById(id);
        if(topic != null ){
            //2.根据nodeid 获取node
            Node node = null;//nodeDao.findNodeById(topic.getNodeid());
            //3.更新node
            node.setTopicnum(node.getTopicnum() - 1);
            nodeDao.update(node);
            //删除主题
            topicDao.delById(id);
        }else {
            throw  new ServiceException("该主题不存在或已删除");
        }


    }
}
