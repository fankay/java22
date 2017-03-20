package com.kaishengit.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class HelloAction extends ActionSupport
        implements SessionAware,ServletRequestAware,ServletResponseAware,ApplicationAware,ServletContextAware {

    private String code;
    private Map<String,Object> session;

    public String execute() throws Exception {
        /*ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
        session.put("s1","k1");*/

        //session.put("s1","kk1");

        /*HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        ServletContext application = session.getServletContext();*/
        //HttpServletResponse response = ServletActionContext.getResponse();

        //Map<String,Object> application = ActionContext.getContext().getApplication();
        //ServletContext servletContext = ServletActionContext.getServletContext();

        System.out.println("Hello,Struts2");
        return SUCCESS;
    }

    public String list() {
        code = "1009";
        System.out.println("List page");
        return "success";
    }

    //get set
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {

    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {

    }

    @Override
    public void setApplication(Map<String, Object> map) {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {

    }
}
