package com.asib27.authentication.payload.response;

import java.sql.Timestamp;

import com.asib27.authentication.Reviews.Review;

public class ReviewResponse {

    private Long id;
    private int upVotes;
    private int downVotes;
    private String review_text;
    private int rating;
    private Timestamp addDate;
    private Long user_id;
  
    public ReviewResponse(Review review){
        id = review.getId();
        upVotes = review.getUpvotes();
        downVotes = review.getDownvotes();
        review_text = review.getReview_text();
        rating = review.getRating();
        addDate = review.getAddDate();
        user_id = review.getUser_id();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUpVotes() {
        return this.upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return this.downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public String getReview_text() {
        return this.review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Timestamp getAddDate() {
        return this.addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }

    public Long getUser_name() {
        return this.user_id;
    }

    public void setUser_name(Long user_name) {
        this.user_id = user_name;
    }

}