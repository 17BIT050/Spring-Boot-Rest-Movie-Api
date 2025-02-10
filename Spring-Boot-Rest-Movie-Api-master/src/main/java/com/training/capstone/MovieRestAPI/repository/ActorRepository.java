package com.training.capstone.MovieRestAPI.repository;

import com.training.capstone.MovieRestAPI.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Integer> {

}
