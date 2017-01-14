package com.kaishengit.shiro;

import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShrioDbRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //返回当前登录的对象
        User user = (User) principalCollection.getPrimaryPrincipal();
        //获取当前对象拥有的角色
        List<Role> roleList = roleMapper.findByUserId(user.getId());
        if(!roleList.isEmpty()) {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            for(Role role : roleList) {
                authorizationInfo.addRole(role.getRoleName());
            }
            return authorizationInfo;
        }
        return null;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userName = usernamePasswordToken.getUsername();
        User user = userMapper.findByUserName(userName);
        if(user != null) {
            return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        }
        return null;
    }
}
