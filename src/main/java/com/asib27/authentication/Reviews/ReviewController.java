package com.asib27.authentication.Reviews;

import com.asib27.authentication.Book.Book;
import com.asib27.authentication.Book.BookService;
import com.asib27.authentication.UserCloned.UserClonedService;
import com.asib27.authentication.payload.response.ReviewResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/book/{isbn}")
public class ReviewController {

    @Autowired
    ReviewService reviewService;
    @Autowired
    BookService bookService;
    @Autowired
    UserClonedService userClonedService;

    private List<ReviewResponse> getReviewResponseList(List<Review> list){
        List<ReviewResponse> ans = new  ArrayList<>(list.size());

        for (int i = 0; i < list.size(); i++) {
            ans.add(i, new ReviewResponse(list.get(i)) );
        }

        return ans;
    }

    @PostMapping("/review/add")
    public String addNewReview(@RequestBody Review review){
        review.setUser_id(userClonedService.getCurrentUser().getId());
        reviewService.addNewReview(review);
        return "new review added";
    }

    @GetMapping("/review/all")
    public List<ReviewResponse> getReViewByBookId(@PathVariable String isbn){
        return getReviewResponseList(reviewService.getReviewByBookId(isbn));
    }

    @DeleteMapping("/review/delete/{review_id}")
    public String deleteReviewById(@PathVariable Long review_id){
        reviewService.deleteReview(review_id);
        return "review deleted !!";
    }

    @PostMapping("/add/{review_id}/book/{book_id}")
    public Review addReviewToBook(@PathVariable Long review_id, @PathVariable String book_id){
        Review review = reviewService.getReview(review_id);
        Book book = bookService.getBook(book_id);
        review.setBook(book);
        return reviewService.addNewReview(review);
    }

    @PostMapping("/review/upvote/{review_id}")
    public Review upVoteReview(@PathVariable Long review_id){
        Review review = reviewService.getReview(review_id);
        review.setUpvotes(review.getUpvotes()+1);
        reviewService.updateUpvoteNotification(review.getUser_id());
        return reviewService.addNewReview(review);
    }

    @PostMapping("/review/downvote/{review_id}")
    public Review downVoteReview(@PathVariable Long review_id){
        Review review = reviewService.getReview(review_id);
        review.setDownvotes(review.getDownvotes() + 1);
        reviewService.updateDownVoteNotification(review.getUser_id());
        return reviewService.addNewReview(review);
    }

    @GetMapping("/rating/average_rating")
    public double getAvgRatingByBookId(@PathVariable String isbn){
        double x =  reviewService.getAvgRatingByIsbn(isbn);
        return x;
    }
    @GetMapping("/rating/count")
    public int getReviewCountByBookId(@PathVariable String isbn){
        int x =  reviewService.getReviewCountByBookName(isbn);
        return x;
    }

    @GetMapping("/rating/rating_percentage")
    public Map<String, String> getRatingPercentage(@PathVariable String isbn){
        List<Object[]> result = reviewService.getRatingPercentage(isbn);
        Map<String, String> map = new HashMap<>();
        for(Object[] x:result){
            map.put(x[0].toString(), x[1].toString());
        }

        return map;
    }

    @GetMapping("/get/random")
    public List<Review> getRandomReviews(@PathVariable String isbn, @RequestParam int no){
        return reviewService.getRandomReviews(isbn, no);
    }
    @GetMapping("/get/mixed")
    public List<Review> getMixedReviews(@PathVariable String isbn, @RequestParam int no){
        return reviewService.getMixedReviews(isbn, no);
    }


    @GetMapping("/review")
    public List<ReviewResponse> getTypeBasedReviews(@PathVariable String isbn, 
        @RequestParam(name="type", defaultValue = "mixed") String type, 
        @RequestParam(name="count", defaultValue = "5") int count
    ){
        if(type.equals("random")){
            return getReviewResponseList(reviewService.getRandomReviews(isbn, count));
        }
        else if(type.equals("mixed")){
            return getReviewResponseList(reviewService.getMixedReviews(isbn, count));
        }
        else{
            return getReviewResponseList(reviewService.getMixedReviews(isbn, count));
        }
    }

}
