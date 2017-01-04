package com.kaishengit.dao;

import com.kaishengit.entity.Reply;
import com.kaishengit.entity.User;
import com.kaishengit.util.Config;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.handlers.AbstractListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jimi_jin on 2016-12-21.
 */
public class ReplyDao {

    public void addReply(Reply reply){
        String sql = "insert into t_reply (content,userid,topicid) values (?,?,?)";
        DbHelp.update(sql,reply.getContent(),reply.getUserid(),reply.getTopicid());


    }

    public List<Reply> findListByTopicId(String topicId) {
        String sql = "SELECT tu.id,tu.avatar,tu.username,tr.* FROM t_reply tr ,t_user tu WHERE tr.userid = tu.id AND topicid = ?";
        return DbHelp.query(sql, new AbstractListHandler<Reply>() {
            @Override
            protected Reply handleRow(ResultSet rs) throws SQLException {
                Reply reply = new BasicRowProcessor().toBean(rs,Reply.class);
                User user = new User();
                user.setAvatar(Config.get("qiniu.domain") + rs.getString("avatar"));
                user.setUsername(rs.getString("username"));
                user.setId(rs.getInt("id"));
                reply.setUser(user);
                return reply;
            }
        },topicId);
    }

    public void delByTopicId(String id) {
        String sql = "delete from t_reply where topicid = ?";
        DbHelp.update(sql,id);
    }
}
