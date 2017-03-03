package com.kaishengit.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash
public class User {

    @Id
    private Integer id;
    @Indexed
    private String userName;
    private float socre;

    public User() {
    }

    public User(Integer id, String userName, float socre) {
        this.id = id;
        this.userName = userName;
        this.socre = socre;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", socre=" + socre +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getSocre() {
        return socre;
    }

    public void setSocre(float socre) {
        this.socre = socre;
    }
}
