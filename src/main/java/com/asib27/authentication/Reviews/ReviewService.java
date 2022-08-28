package com.asib27.authentication.Reviews;

import com.asib27.authentication.UserCloned.UserCloned;
import com.asib27.authentication.payload.response.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public Review addNewReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review getReview(Long id) {
        boolean exist = reviewRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("review does not exist !!");
        }
        else{
            return reviewRepository.findById(id).get();
        }
    }

    public void deleteReview(Long id) {
        boolean exist = reviewRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("review does not exist !!");
        }else{
            reviewRepository.deleteById(id);
        }
    }

    public float getAvgRatingByBookName(String id) {

        return reviewRepository.getAvgRatingByBookName(id);
    }

    public int getReviewCountByBookName(String id) {
        return reviewRepository.getReviewCountByBookName(id);
    }

    public List<Object[]> getRatingPercentage(String id) {
        return reviewRepository.getRatingPercentage(id);
    }

    public List<Review> getReviewByBookId(String book_id) {
        return reviewRepository.getReviewByBookId(book_id);
    }

    public List<Review> getRandomReviews(String id, int no) {
        return reviewRepository.getRandomReviews(id, no);
    }

    public List<Review> getMixedReviews(String id, int no) {
        return reviewRepository.getMixedReviews(id, no);
    }

    @Transactional
    public void updateUpvoteNotification(Long id) {
        reviewRepository.updateUpvote(id);
    }

    @Transactional
    public void updateDownVoteNotification(Long id) {
        reviewRepository.updateDownvote(id);
    }

    public ReviewResponse getAllAboutReview(Review review, UserCloned user) {
        return new ReviewResponse(review, user);
    }
}
