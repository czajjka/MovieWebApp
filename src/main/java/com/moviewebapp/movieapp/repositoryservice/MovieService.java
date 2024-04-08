package com.moviewebapp.movieapp.repositoryservice;

import com.moviewebapp.movieapp.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired MovieRepository movieRepository;

    public Movie getMovieById(int id){
        return movieRepository.findById(id).get();
    }

}
