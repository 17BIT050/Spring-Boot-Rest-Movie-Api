package com.training.capstone.MovieRestAPI.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="genres")
public class Genre {

    @Id
    @Column(name="genre_id")
    private int genreId;
    @Column(name="genre_name", unique = true, length = 20)
    private String name;

    @ManyToMany(mappedBy = "genres",cascade = CascadeType.ALL)
    private Set<Movie> movies = new HashSet<>();

  //  private Movie movie;

    public Genre(){}

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

//    public Movie getMovie() {
//        return movie;
//    }

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
