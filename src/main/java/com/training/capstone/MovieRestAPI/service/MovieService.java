package com.training.capstone.MovieRestAPI.service;

import com.training.capstone.MovieRestAPI.dto.ActorDto;
import com.training.capstone.MovieRestAPI.dto.GenreDto;
import com.training.capstone.MovieRestAPI.dto.MovieDto;
import com.training.capstone.MovieRestAPI.dto.ReviewDto;
import com.training.capstone.MovieRestAPI.model.Actor;
import com.training.capstone.MovieRestAPI.model.Genre;
import com.training.capstone.MovieRestAPI.model.Movie;
import com.training.capstone.MovieRestAPI.model.Review;
import com.training.capstone.MovieRestAPI.repository.MovieRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private ActorService actorService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private GenreService genreService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    MovieRepository movieRepository;

    @Transactional
    public MovieDto saveMovie( MovieDto movieDto){
        final Movie movie = toEntity(movieDto);
        final Movie savedMovie = movieRepository.save(movie);
        return toDto(movie);
    }

    public List<MovieDto> getAllMovies(){
        final List<Movie> movies =  movieRepository.findAll();
        return movies.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Movie getMovieById(int movieId){
        return movieRepository.findById(movieId).orElse(null);
    }


    @Transactional
    public Movie toEntity(MovieDto movieDto){
        Movie movie = new Movie();
        movie.setMovieId(movieDto.getMovieId());
        movie.setTitle(movieDto.getTitle());
        movie.setOverview(movieDto.getOverview());
        movie.setStatus(movieDto.getStatus());
        movie.setTagline(movieDto.getTagline());
        movie.setRevenue(movieDto.getRevenue());



        final Set<ActorDto> actorDtos = movieDto.getActorsDto();
//        for(ActorDto actorDto : actorDtos){
//            boolean result = actorService.insertActorIfNotExists(actorDto);
//            if(!result){
                final Set<Actor> actors = actorService.toEntity(actorDtos);
                movie.setActors(actors);
//            }
//        }

        actors.forEach(actor -> actor.setMovie(movie));

        final Set<GenreDto> genreDtos = movieDto.getGenresDto();
        final Set<Genre> genres = genreService.toEntity(genreDtos);
        movie.setGenres(genres);
        genres.forEach(genre -> genre.setMovie(movie));


        final Set<ReviewDto> reviewDtos = movieDto.getReviewsDto();
        final Set<Review> reviews = reviewService.toEntity(reviewDtos);
        movie.setReviews(reviews);
        reviews.forEach(review -> review.setMovie(movie));

        return movie;
    }

    public MovieDto toDto(Movie movie){
        MovieDto movieDto = new MovieDto();
        movieDto.setMovieId(movie.getMovieId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setOverview(movie.getOverview());
        movieDto.setStatus(movie.getStatus());
        movieDto.setTagline(movie.getTagline());
        movieDto.setRevenue(movie.getRevenue());

        final Set<Actor> actors =  movie.getActors();
        final Set<ActorDto> actorDtos = actorService.toDto(actors);
        movieDto.setActorsDto(actorDtos);

        final Set<Review> reviews = movie.getReviews();
        final Set<ReviewDto> reviewDtos = reviewService.toDto(reviews);
        movieDto.setReviewsDto(reviewDtos);

        final Set<Genre> genres = movie.getGenres();
        final Set<GenreDto> genreDtos = genreService.toDto(genres);
        movieDto.setGenresDto(genreDtos);

        return movieDto;
    }

}
