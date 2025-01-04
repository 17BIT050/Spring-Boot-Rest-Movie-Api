package com.training.capstone.MovieRestAPI.controller;

import com.training.capstone.MovieRestAPI.dto.ActorDto;
import com.training.capstone.MovieRestAPI.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/movies/actors")
public class ActionController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private MovieController movieController;

    @GetMapping("/all")
    private Set<ActorDto> getAllActors() {
        return actorService.getAllActors();
    }

}
