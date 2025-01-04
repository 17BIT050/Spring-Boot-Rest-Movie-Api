package com.training.capstone.MovieRestAPI.repository;

import com.training.capstone.MovieRestAPI.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Integer> {

    @Query(value = "SELECT genre_id, genre_name FROM genres WHERE genre_name = :genreName", nativeQuery = true)
    Genre findByGenreName(String genreName);
}
