package com.anjan.MovieFlix.ServiceImpl;

import com.anjan.MovieFlix.Model.Movie;
import com.anjan.MovieFlix.Model.Review;
import com.anjan.MovieFlix.Repo.ReviewRepo;
import com.anjan.MovieFlix.Security.JwtAuthenticationFilter;
import com.anjan.MovieFlix.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {


    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    JwtAuthenticationFilter jwtFilter;
    @Override
    public Review addReview(Review review) {
        return this.reviewRepo.save(review);
    }

    @Override
    public List<Review> getReviews() {
        return this.reviewRepo.findAll();
    }

    @Override
    public void deleteReview(Integer rid) {
        this.reviewRepo.deleteById(rid);
    }
    @Override
    public List<Review> getReviewsOfMovie(Movie movie) {
        return this.reviewRepo.findBymovie(movie);
    }
}

