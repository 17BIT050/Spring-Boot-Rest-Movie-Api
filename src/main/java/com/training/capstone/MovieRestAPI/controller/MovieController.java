package com.training.capstone.MovieRestAPI.controller;

import com.training.capstone.MovieRestAPI.dto.MovieDto;
import com.training.capstone.MovieRestAPI.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/movie")
    public MovieDto createMovie(@Valid@RequestBody MovieDto movieDto){
        Logger logger = Logger.getLogger(MovieController.class.getName());
        logger.info("Movie title >>> " + movieDto.getTitle());
        return movieService.saveMovie(movieDto);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity getMovieByID(@PathVariable int movieId) {
        try {
            MovieDto result = movieService.getMovieByID(movieId);
            if (result == null) {
                return ResponseEntity.ok("Not found");
            }
            else{
                return ResponseEntity.ok(result);
            }
        } catch (Exception e) {
            return ResponseEntity.ok("" + e.getStackTrace());
        }

    }
}
