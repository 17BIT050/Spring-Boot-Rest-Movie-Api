package com.training.capstone.MovieRestAPI.controller;

import com.training.capstone.MovieRestAPI.dto.ReviewDto;
import com.training.capstone.MovieRestAPI.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/movies/movie")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("/review/{movieId}")
    public List<ReviewDto> getReviews(@PathVariable int movieId){
        return reviewService.getReviewsByMovieId(movieId);
    }

    @PostMapping("/review/{movieId}")
    public List<ReviewDto> createReview(@PathVariable int movieId, @RequestBody Set<ReviewDto> reviewDtos){
       List<ReviewDto> reviewDtoList = new ArrayList<>();
        for(ReviewDto reviewDto : reviewDtos){
            Logger logger = Logger.getLogger(ReviewController.class.getName());
            logger.info("Movie id >>" + movieId);
            logger.info("Review title>>" + reviewDto.getTitle());
            ReviewDto resultReviewDto = reviewService.saveReview(movieId, reviewDto);
            reviewDtoList.add(resultReviewDto);
        }
        return reviewDtoList;
    }

    @PutMapping("/review/{movieId}/{reviewId}")
    public ReviewDto updateReview(@PathVariable int movieId,@PathVariable int reviewId, @RequestBody ReviewDto reviewDto){
        return reviewService.updateReviewByID(movieId,reviewId,reviewDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/review/{movieId}/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable int movieId, @PathVariable int reviewId){
        boolean result  = reviewService.deleteReviewById(movieId,reviewId);
        if(result)
         return ResponseEntity.ok("Review deleted successfully");
        else
            return ResponseEntity.badRequest().body("Review not found for the given movie id and review id");
    }

}
