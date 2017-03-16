package com.kaishengit.test;

import com.kaishengit.pojo.Movie;

public class Son extends Father<Movie> {

    public Son() {
        System.out.println("Create son :" + this);
    }

}
