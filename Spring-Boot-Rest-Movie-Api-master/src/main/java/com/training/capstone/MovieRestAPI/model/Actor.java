package com.training.capstone.MovieRestAPI.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="actors")
public class Actor {

    @Id
    @Column(name = "actor_id",unique = true)
    private int actorId;
    @Column(name = "actor_name",nullable = false, length = 40)
    private String name;
    @Column(name="gender",nullable = false,length = 10)
    private String gender;
    @Column(name="popularity",nullable = false)
    private int popularity;

    @ManyToMany(mappedBy = "actors",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    private Set<Movie> movies = new HashSet<>();

   // private Movie movie;

    public Actor(){}

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

   /* public Movie getMovie() {
        return movie;
    }*/

    public void setMovie(Movie movie) {
        movies.add(movie);

    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
