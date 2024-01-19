package com.anjan.MovieFlix.Service;

import com.anjan.MovieFlix.Model.Movie;
import com.anjan.MovieFlix.Model.Review;

import java.util.List;

public interface ReviewService {
    Review addReview(Review review);

    List<Review> getReviews();

    void deleteReview(Integer rid);

    List<Review> getReviewsOfMovie(Movie movie);
}
