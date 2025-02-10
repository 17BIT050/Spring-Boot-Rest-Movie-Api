package com.training.capstone.MovieRestAPI.controller;

import com.training.capstone.MovieRestAPI.dto.GenreDto;
import com.training.capstone.MovieRestAPI.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/movies/genres")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping("/all")
    public List<GenreDto> getAllGenres(){
        return genreService.getAllGenres();
    }

    @GetMapping("/{genreId}")
    public Set<String> getMoviesByGenreId(@PathVariable int genreId){
        return genreService.getMoviesByGenreId(genreId);
    }

    @GetMapping("genre/{genreName}")
    public Set<String> getMoviesByGenreName(@Valid @PathVariable String genreName){
        return genreService.getMoviesByGenreName(genreName);
    }

    @PostMapping("/genre")
    public GenreDto createGenre(@Valid @RequestBody GenreDto genreDto){
        return genreService.saveGenre(genreDto);
    }
}
