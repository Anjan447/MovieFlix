package com.anjan.MovieFlix.Controller;

import com.anjan.MovieFlix.Model.Movie;
import com.anjan.MovieFlix.Model.Review;
import com.anjan.MovieFlix.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(path = "/review")
public class ReviewController {


    @Autowired
    ReviewService reviewService;

    @PostMapping(path = "/add")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review  review1 = this.reviewService.addReview(review);
        return ResponseEntity.ok(review1);
    }

    @GetMapping(path = "/allReview")
    public List<Review> getReviews() {
        return this.reviewService.getReviews();
    }

    @DeleteMapping(path = "/{rid}")
    public void deleteReview(@PathVariable("rid") Integer rid) {
        this.reviewService.deleteReview(rid);
    }

    @GetMapping(path = "/review/{mid}")
    public List<Review> getReviewsOfMovie(@PathVariable("mid") Integer mid) {
        Movie movie = new Movie();
        movie.setMid(mid);
        return this.reviewService.getReviewsOfMovie(movie);
    }
}