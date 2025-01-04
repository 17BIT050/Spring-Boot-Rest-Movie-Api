package com.training.capstone.MovieRestAPI.service;

import com.training.capstone.MovieRestAPI.dto.ReviewDto;
import com.training.capstone.MovieRestAPI.model.Movie;
import com.training.capstone.MovieRestAPI.model.Review;
import com.training.capstone.MovieRestAPI.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ReviewService {


    @Lazy
    @Autowired
    MovieService movieService;

    @Autowired
    ReviewRepository reviewRepository;

    Logger logger = Logger.getLogger(ReviewService.class.getName());

    @Transactional
    public ReviewDto saveReview(int movieId, ReviewDto reviewDto) {
        Movie movie = movieService.getMovieById(movieId); // calls movie service to get movie by id from movie repo
        if (movie != null) {
            Set<Review> reviews = movie.getReviews();
            Review resultReview = toEntity(reviewDto); // convert reviewDto to review entity
            reviews.add(resultReview); // add the review to the set
            movie.setReviews(reviews); // set the reviews to the movie
            resultReview.setMovie(movieService.getMovieById(movieId)); // set the movie to the review
            Review savedReview = reviewRepository.save(resultReview); // save the review to the review repo
            return toDto(savedReview);
        }
        return null;
    }

    @Transactional
    public ReviewDto updateReviewByID(int movieId, int reviewId, ReviewDto reviewDto) {
        Movie movie = movieService.getMovieById(movieId); // calls movie service to get movie by id from movie repo
        if (movie != null) {
            Set<Review> reviews = movie.getReviews();
            // check if the review exists in the set
            Review isReviewExist = reviews.stream().
                    filter(review -> review.getReviewId() == reviewId).
                    findFirst().orElse(null);
            logger.info("Movie id - " + movieId + "Created at - " + isReviewExist.getCreatedAt());
//            for(Review review : reviews){
//                logger.info("ID >> " + review.getReviewId());
//                logger.info("Created at >> " + review.getCreatedAt());
//            }
            if (isReviewExist != null) {
                logger.info("review id >> " + reviewDto.getReviewId());
                reviewDto.setReviewId(reviewId); // set the review id
                logger.info("review id >> " + reviewDto.getReviewId());
                Review review = toEntity(isReviewExist, reviewDto); // convert incoming update reviewDto to exist review entity
                reviews.remove(isReviewExist); //remove the old review from set
                reviews.add(review); //add the new review to set
                movie.setReviews(reviews); // set the reviews to the movie
                review.setMovie(movieService.getMovieById(movieId)); // set the movie to the review
                Review savedReview = reviewRepository.save(review); // save the review to the review repo
                return toDto(savedReview);
            } else {
                return null;
            }
        }
        return null;
    }

    @Transactional
    public boolean deleteReviewById(int movieId, int reviewId){
        boolean result =  reviewRepository.deleteReviewByMovieAndReviewId(movieId, reviewId) > 0;
        return result;
    }

    public List<ReviewDto> getReviewsByMovieId(int movieId) {
        List<Review> reviews = reviewRepository.findAllReviewByMovieId(movieId);
        return reviews.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Set<Review> toEntity(Set<ReviewDto> reviewDtos) {
        return reviewDtos.stream().map(this::toEntity).collect(Collectors.toSet());
    }

    public Set<ReviewDto> toDto(Set<Review> reviews) {
        return reviews.stream().map(this::toDto).collect(Collectors.toSet());
    }

    public Review toEntity(ReviewDto reviewDto) {
        Review review = new Review();
        LocalDateTime now = LocalDateTime.now();
        reviewDto.setCreatedAt(now);
        reviewDto.setUpdatedAt(now);
        review.setCreatedAt(reviewDto.getCreatedAt());
        review.setUpdatedAt(reviewDto.getUpdatedAt());
        review.setReviewId(reviewDto.getReviewId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setAuthor(reviewDto.getAuthor());
        return review;
    }

    public Review toEntity(Review review, ReviewDto reviewDto) {
        LocalDateTime now = LocalDateTime.now();
        if (review.getCreatedAt() == null) {
            reviewDto.setCreatedAt(now);
            reviewDto.setUpdatedAt(now);
            review.setCreatedAt(reviewDto.getCreatedAt());
            review.setUpdatedAt(reviewDto.getUpdatedAt());
        } else {
            reviewDto.setUpdatedAt(now);
            reviewDto.setCreatedAt(review.getCreatedAt());
            review.setUpdatedAt(reviewDto.getUpdatedAt());
        }

        review.setReviewId(reviewDto.getReviewId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setAuthor(reviewDto.getAuthor());
        return review;
    }

    public ReviewDto toDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReviewId(review.getReviewId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setAuthor(review.getAuthor());
        reviewDto.setCreatedAt(review.getCreatedAt());
        reviewDto.setUpdatedAt(review.getUpdatedAt());
        return reviewDto;
    }
}
