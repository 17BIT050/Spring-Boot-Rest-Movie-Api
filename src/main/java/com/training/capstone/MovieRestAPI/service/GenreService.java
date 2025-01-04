package com.training.capstone.MovieRestAPI.service;

import com.training.capstone.MovieRestAPI.dto.GenreDto;
import com.training.capstone.MovieRestAPI.model.Genre;
import com.training.capstone.MovieRestAPI.model.Movie;
import com.training.capstone.MovieRestAPI.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public List<GenreDto> getAllGenres(){
        final List<Genre> genreList = genreRepository.findAll();
        return genreList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Set<String> getMoviesByGenreId(int genreId){
        Genre genre = genreRepository.findById(genreId).orElse(null);
        Set<String> movies = new HashSet<>();
        if(genre != null){
            Set<Movie> movieList = genre.getMovies();
            movies = movieList.stream().map(Movie::getTitle).collect(Collectors.toSet());
            return movies;
        }
        return null;
    }

    public Set<String> getMoviesByGenreName(String genreName){
        Genre genre = genreRepository.findByGenreName(genreName);
        Set<String> movies = new HashSet<>();
        if(genre != null){
            Set<Movie> movieList = genre.getMovies();
            movies = movieList.stream().map(Movie::getTitle).collect(Collectors.toSet());
            return movies;
        }
        return null;
    }

    public GenreDto saveGenre(GenreDto genreDto){
        Genre genre = toEntity(genreDto);
        Genre savedGenre = genreRepository.save(genre);
        return toDto(savedGenre);
    }

    public Set<Genre> toEntity(Set<GenreDto> genreDtos){
        return genreDtos.stream().map(this::toEntity).collect(Collectors.toSet());
    }

    public Set<GenreDto> toDto(Set<Genre> genres){
        return genres.stream().map(this::toDto).collect(Collectors.toSet());
    }


    public Genre toEntity(GenreDto genreDto){
        Genre genre = new Genre();
        genre.setGenreId(genreDto.getGenreId());
        genre.setName(genreDto.getName());
        return genre;
    }

    public GenreDto toDto(Genre genre){
        GenreDto genreDto = new GenreDto();
        genreDto.setGenreId(genre.getGenreId());
        genreDto.setName(genre.getName());
        return genreDto;
    }


}
