package com.asib27.authentication.payload.response;

import java.sql.Timestamp;

import com.asib27.authentication.Book.Book;
import com.asib27.authentication.Reviews.Review;
import com.asib27.authentication.UserCloned.UserCloned;

public class ReviewResponse {

    private Long id;
    private int upVotes;
    private int downVotes;
    private String review_text;
    private int rating;
    private Timestamp addDate;
    private Book book;
    private UserCloned user;

    public ReviewResponse(Review review, UserCloned user){
        id = review.getId();
        upVotes = review.getUpvotes();
        downVotes = review.getDownvotes();
        review_text = review.getReview_text();
        rating = review.getRating();
        addDate = review.getAddDate();
        this.user = user;
        book = review.getBook();
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


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public UserCloned getUser() {
        return user;
    }

    public void setUser(UserCloned user) {
        this.user = user;
    }
}