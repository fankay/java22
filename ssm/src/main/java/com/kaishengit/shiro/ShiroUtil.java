package com.kaishengit.shiro;

import com.kaishengit.pojo.User;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static User getCurrentUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getCurrentUserName() {
        return getCurrentUser().getUserName();
    }

    public static Integer getCurrentUserId() {
        return getCurrentUser().getId();
    }

    /**
     * 判断当前登录对象是否为市场部员工
     * @return
     */
    public static boolean isSales() {
        return SecurityUtils.getSubject().hasRole("role_sales");
    }

}
