package com.anjan.MovieFlix.Repo;
import com.anjan.MovieFlix.Model.Movie;
import com.anjan.MovieFlix.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Integer> {

    public List<Review> findBymovie(Movie movie);
}