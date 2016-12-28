package com.kaishengit.service;

import com.kaishengit.dao.NodeDao;
import com.kaishengit.entity.Node;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.StringUtils;

/**
 * Created by jimi_jin on 2016-12-28.
 */
public class NodeService {

    NodeDao nodeDao = new NodeDao();

    public void updateNode(String nodeId, String nodeName) {
        if (StringUtils.isNumeric(nodeId) && StringUtils.isNotEmpty(nodeName)) {
            Node node = nodeDao.findNodeById(Integer.valueOf(nodeId));
            node.setNodename(nodeName);
            nodeDao.update(node);
        } else {
            throw new ServiceException("参数异常");
        }

    }

    public String validateNodeName(String nodeId, String nodeName) {
        // 根据nodeid查询node,并判断nodeName是否等于node的nodename
        Node node = nodeDao.findNodeById(Integer.valueOf(nodeId));
        if (node.getNodename().equals(nodeName)) {
            return "true";
        } else {
            Node nodeIsIn = nodeDao.findNodeByName(nodeName);
            if (nodeIsIn == null) {
                return "true";
            }
        }
        return "false";
    }

    public void delNodeById(String id) {
        Node node = nodeDao.findNodeById(Integer.valueOf(id));
        if (node.getTopicnum() > 0){
            throw  new ServiceException("该节点下已有主题,不可删除");
        }else{
            nodeDao.del(id);
        }
    }
}
