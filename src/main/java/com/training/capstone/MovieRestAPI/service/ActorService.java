package com.training.capstone.MovieRestAPI.service;

import com.training.capstone.MovieRestAPI.dto.ActorDto;
import com.training.capstone.MovieRestAPI.model.Actor;
import com.training.capstone.MovieRestAPI.model.Movie;
import com.training.capstone.MovieRestAPI.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public Set<ActorDto> getAllActors() {
        List<Actor> actorList = actorRepository.findAll();
        return actorList.stream().map(this::toDto).collect(Collectors.toSet());
    }

    public Set<String> getActorMovies(int id) {
        Actor actor = actorRepository.findById(id).orElse(null);
        Set<Movie> movies = actor.getMovies();
        return movies.stream().map( movie -> movie.getTitle()).collect(Collectors.toSet());
    }

    public Set<Actor> toEntity(Set<ActorDto> actorDtos) {
        return actorDtos.stream().map(this::toEntity).collect(Collectors.toSet());
    }

    public Set<ActorDto> toDto(Set<Actor> actors) {
        return actors.stream().map(this::toDto).collect(Collectors.toSet());
    }


    public Actor toEntity(ActorDto actorDto) {
        Actor actor = new Actor();
        actor.setActorId(actorDto.getActorId());
        if(isActorExists(actorDto)){
            Actor existactor = actorRepository.findById(actor.getActorId()).orElse(null);
            return existactor;
        }
        actor.setName(actorDto.getName());
        actor.setGender(actorDto.getGender());
        actor.setPopularity(actorDto.getPopularity());
        return actor;
    }

    public ActorDto toDto(Actor actor) {
        ActorDto actorDto = new ActorDto();
        actorDto.setActorId(actor.getActorId());
        actorDto.setName(actor.getName());
        actorDto.setGender(actor.getGender());
        actorDto.setPopularity(actor.getPopularity());
        return actorDto;
    }


    public Boolean isActorExists(ActorDto dto) {
        return actorRepository.existsById(dto.getActorId());
    }

}
