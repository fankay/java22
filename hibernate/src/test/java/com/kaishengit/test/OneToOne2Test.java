package com.kaishengit.test;

import com.kaishengit.pojo.Topic;
import com.kaishengit.pojo.TopicContent;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class OneToOne2Test {

    @Test
    public void save() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Topic topic = new Topic();
        topic.setTitle("如何使用Vue.js");

        TopicContent topicContent = new TopicContent();
        topicContent.setContent("zzzzzzzzzzzzzzzzzzzz");

        topic.setTopicContent(topicContent);
        topicContent.setTopic(topic);

        session.save(topic);
        session.save(topicContent);


        session.getTransaction().commit();
    }

    @Test
    public void find() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Topic topic = (Topic) session.get(Topic.class,2);
        System.out.println(topic.getTitle());
        System.out.println(topic.getTopicContent().getContent());

        session.getTransaction().commit();
    }
}
