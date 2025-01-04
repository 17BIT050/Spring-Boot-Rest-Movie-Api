package com.training.capstone.MovieRestAPI.controller;

import com.training.capstone.MovieRestAPI.dto.MovieDto;
import com.training.capstone.MovieRestAPI.dto.ReviewDto;
import com.training.capstone.MovieRestAPI.model.Movie;
import com.training.capstone.MovieRestAPI.model.Review;
import com.training.capstone.MovieRestAPI.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @GetMapping("/all")
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping
    public MovieDto createMovie(@Valid@RequestBody MovieDto movieDto){
        Logger logger = Logger.getLogger(MovieController.class.getName());
        logger.info("Movie title >>> " + movieDto.getTitle());
        /*for(ReviewDto reviewDto : movieDto.getReviewsDto()){
           // logger.info(rev);
            logger.info(reviewDto.getTitle());
            logger.info(reviewDto.getContent());
            logger.info(reviewDto.getAuthor());
            LocalDateTime now = LocalDateTime.now();
            reviewDto.setCreatedAt(now);
            reviewDto.setUpdatedAt(now);
            logger.info("Create time " +reviewDto.getCreatedAt());
            logger.info("update time" + reviewDto.getUpdatedAt());
        }*/
        return movieService.saveMovie(movieDto);
    }
}
