package com.training.capstone.MovieRestAPI.repository;

import com.training.capstone.MovieRestAPI.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query(value = "SELECT * FROM reviews WHERE movie_id = :movieId", nativeQuery = true)
    List<Review> findAllReviewByMovieId(@Param("movieId") int movieId);

    @Modifying
    @Query(value = "DELETE FROM reviews WHERE review_id = :reviewId AND movie_id = :movieId", nativeQuery = true)
    int deleteReviewByMovieAndReviewId(@Param("movieId") int movieId, @Param("reviewId") int reviewId);

}
