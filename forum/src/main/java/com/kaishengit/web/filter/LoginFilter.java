package com.kaishengit.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class LoginFilter extends AbstractFilter {

    private List<String> urlList = null;
    private List<String> urlAdminList = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       String validateUrl = filterConfig.getInitParameter("validateUrl");
       urlList = Arrays.asList(validateUrl.split(","));
       String validateAdminUrl = filterConfig.getInitParameter("validateAdminUrl");
       urlAdminList = Arrays.asList(validateAdminUrl.split(","));


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取用户要访问的URL
        String requestUrl = request.getRequestURI();
        if(requestUrl.startsWith("/admin")){
            if(urlAdminList != null  && urlAdminList.contains(requestUrl)){
                if(request.getSession().getAttribute("curr_admin") == null){

                    //去管理员登录页面
                    response.sendRedirect("/admin/login?redirect="+requestUrl);

                }else{
                    filterChain.doFilter(request,response);
                }
            }else{
                filterChain.doFilter(request,response);
            }
        }else{
            if(urlList != null && urlList.contains(requestUrl)) {
                if(request.getSession().getAttribute("curr_user") == null) {
                    Map map = request.getParameterMap();
                    Set paramSet = map.entrySet();
                    Iterator it = paramSet.iterator();
                    if (it.hasNext()){
                        requestUrl += "?";

                        while(it.hasNext()){
                            Map.Entry me = (Map.Entry) it.next();
                            Object key = me.getKey();
                            Object value = me.getValue();
                            String[] valString = new String[1];
                            if(value instanceof String[]){
                                valString=(String[])value;
                            }else{
                                valString[0]=value.toString();
                            }
                            String param = "";
                            for(int i=0;i<valString.length;i++){
                                param = key + "=" + valString[i] +"&";
                                requestUrl += param;
                             }
                        }
                    requestUrl = requestUrl.substring(0,requestUrl.length()-1);
                    System.out.println("requestUrl = " + requestUrl);
                    }
                     //去登录页面
                    response.sendRedirect("/login?redirect="+requestUrl);
                } else {
                    filterChain.doFilter(request,response);
                }
            } else {
                filterChain.doFilter(request,response);
            }
        }

    }
}
