package com.kaishengit;

import com.kaishengit.service.MovieService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MovieService movieService = (MovieService) context.getBean("movieService");
        List<String> names = movieService.queryMovieNames();
        for(String name : names) {
            System.out.println(name);
        }
    }
}
