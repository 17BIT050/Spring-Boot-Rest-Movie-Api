package com.training.capstone.MovieRestAPI.dto;

import java.util.Set;

public class ActorDto {


    private int actorId;
    private String name;
    private String gender;
    private int popularity;
    private Set<MovieDto> moviesDto;

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public Set<MovieDto> getMovies() {
        return moviesDto;
    }

    public void setMovies(Set<MovieDto> moviesDto) {
        this.moviesDto = moviesDto;
    }
}
