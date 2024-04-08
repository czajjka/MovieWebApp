package com.moviewebapp.movieapp.repositoryservice;

import com.moviewebapp.movieapp.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Integer> {
}
