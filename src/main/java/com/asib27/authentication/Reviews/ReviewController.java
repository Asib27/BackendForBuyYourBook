package com.asib27.authentication.Reviews;

import com.asib27.authentication.Book.Book;
import com.asib27.authentication.Book.BookService;
import com.asib27.authentication.UserCloned.UserClonedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;
    @Autowired
    BookService bookService;
    @Autowired
    UserClonedService userClonedService;

    @PostMapping("/add")
    public String addNewReview(@RequestBody Review review){
        review.setUser_id(userClonedService.getCurrentUser().getId());
        reviewService.addNewReview(review);
        return "new review added";
    }

    @GetMapping("/get/all")
    public List<Review> getReViewByBookId(@RequestParam String isbn){
        return reviewService.getReviewByBookId(isbn);
    }

    @DeleteMapping("/delete")
    public String deleteReviewById(@RequestParam Long id){
        reviewService.deleteReview(id);
        return "review deleted !!";
    }

    @PostMapping("/add/{review_id}/book/{book_id}")
    public Review addReviewToBook(@PathVariable Long review_id, @PathVariable String book_id){
        Review review = reviewService.getReview(review_id);
        Book book = bookService.getBook(book_id);
        review.setBook(book);
        return reviewService.addNewReview(review);
    }

    @PostMapping("/add/{review_id}/up")
    public Review upVoteReview(@PathVariable Long review_id){
        Review review = reviewService.getReview(review_id);
        review.setUpvotes(review.getUpvotes()+1);
        reviewService.updateUpvoteNotification(review.getUser_id());
        return reviewService.addNewReview(review);
    }

    @PostMapping("/add/{review_id}/down")
    public Review downVoteReview(@PathVariable Long review_id){
        Review review = reviewService.getReview(review_id);
        review.setDownvotes(review.getDownvotes() + 1);
        reviewService.updateDownVoteNotification(review.getUser_id());
        return reviewService.addNewReview(review);
    }

    @GetMapping("/get/average_rating")
    public float getAvgRatingByBookId(@RequestParam String isbn){
        float x =  reviewService.getAvgRatingByBookName(isbn);
        return x;
    }
    @GetMapping("/get/review_count")
    public int getReviewCountByBookId(@RequestParam String isbn){
        int x =  reviewService.getReviewCountByBookName(isbn);
        return x;
    }

    @GetMapping("/get/rating_percentage")
    public Map<String, String> getRatingPercentage(@RequestParam String isbn){
        List<Object[]> result = reviewService.getRatingPercentage(isbn);
        Map<String, String> map = new HashMap<>();
        for(Object[] x:result){
            map.put(x[0].toString(), x[1].toString());
        }

        return map;
    }

    @GetMapping("/get/random")
    public List<Review> getRandomReviews(@RequestParam String isbn, @RequestParam int no){
        return reviewService.getRandomReviews(isbn, no);
    }
    @GetMapping("/get/mixed")
    public List<Review> getMixedReviews(@RequestParam String isbn, @RequestParam int no){
        return reviewService.getMixedReviews(isbn, no);
    }




}
