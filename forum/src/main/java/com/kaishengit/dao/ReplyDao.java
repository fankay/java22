package com.kaishengit.dao;

import com.kaishengit.entity.Reply;
import com.kaishengit.util.DbHelp;

/**
 * Created by jimi_jin on 2016-12-21.
 */
public class ReplyDao {

    public void addReply(Reply reply){
        String sql = "insert into t_reply (content,userid,topicid) values (?,?,?)";
        DbHelp.update(sql,reply.getContent(),reply.getUserid(),reply.getTopicid());


    }
}
