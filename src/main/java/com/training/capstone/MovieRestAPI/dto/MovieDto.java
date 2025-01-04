package com.training.capstone.MovieRestAPI.dto;

import com.training.capstone.MovieRestAPI.model.Actor;
import com.training.capstone.MovieRestAPI.model.Genre;
import com.training.capstone.MovieRestAPI.model.Review;
import jakarta.persistence.*;

import java.util.Set;

public class MovieDto {

    private int movieId;
    private String title;
    private String status;
    private String overview;
    private String tagline;
    private double revenue;
    private Set<ActorDto> actorsDto;
    private Set<ReviewDto> reviewsDto;
    private Set<GenreDto> genresDto;

    public MovieDto(){}

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public Set<ActorDto> getActorsDto() {
        return actorsDto;
    }

    public void setActorsDto(Set<ActorDto> actorsDto) {
        this.actorsDto = actorsDto;
    }

    public Set<ReviewDto> getReviewsDto() {
        return reviewsDto;
    }

    public void setReviewsDto(Set<ReviewDto> reviewsDto) {
        this.reviewsDto = reviewsDto;
    }

    public Set<GenreDto> getGenresDto() {
        return genresDto;
    }

    public void setGenresDto(Set<GenreDto> genresDto) {
        this.genresDto = genresDto;
    }
}
