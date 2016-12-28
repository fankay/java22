package com.kaishengit.web.admin;

import com.kaishengit.service.NodeService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jimi_jin on 2016-12-28.
 */
@WebServlet("/admin/nodeValidate")
public class NodeVliadateServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nodeId = req.getParameter("nodeid");
        String nodeName = req.getParameter("nodename");
        nodeName = StringUtils.isoToUtf8(nodeName);
        //nodeName = new String(nodeName.getBytes("IOS8859-1"),"UTF-8");
        NodeService nodeService = new NodeService();
        String res = nodeService.validateNodeName(nodeId,nodeName);
        renderText(res,resp);
    }
}
