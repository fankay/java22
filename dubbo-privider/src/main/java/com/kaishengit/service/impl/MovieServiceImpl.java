package com.kaishengit.service.impl;

import com.kaishengit.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class MovieServiceImpl implements MovieService {

    public List<String> queryMovieNames() {
        List<String> nameList = Arrays.asList("A","B","Hello");
        return nameList;
    }
}
