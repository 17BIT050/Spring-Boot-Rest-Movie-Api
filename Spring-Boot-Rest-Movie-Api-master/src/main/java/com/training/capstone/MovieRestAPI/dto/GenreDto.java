package com.training.capstone.MovieRestAPI.dto;


import java.util.Set;

public class GenreDto {

    private int genreId;
    private String name;
    private Set<MovieDto> moviesDto;

    public GenreDto(){}

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MovieDto> getMovies() {
        return moviesDto;
    }

    public void setMovies(Set<MovieDto> moviesDto) {
        this.moviesDto = moviesDto;
    }
}
