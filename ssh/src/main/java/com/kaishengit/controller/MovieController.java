package com.kaishengit.controller;

import com.kaishengit.pojo.Movie;
import com.kaishengit.service.MovieService;
import com.kaishengit.util.Page;
import com.kaishengit.util.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String list(Model model,
                       @RequestParam(required = false,defaultValue = "1",name = "p") Integer pageNo,
                       HttpServletRequest request) {


        List<QueryParam> queryParamList = QueryParam.builderQueryParamByRequest(request);
        Page<Movie> moviePage = movieService.findByPage(pageNo,queryParamList);
        model.addAttribute("page",moviePage);
        return "movie/list";
    }

    @GetMapping("/{id:\\d+}")
    public String show(@PathVariable Integer id,Model model) {
        Movie movie = movieService.findById(id);
        model.addAttribute("movie",movie);
        return "movie/movie";
    }

}
