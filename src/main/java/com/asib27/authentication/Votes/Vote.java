package com.asib27.authentication.Votes;

import javax.persistence.*;

@Table(name = "votes")
@Entity(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vote_id")
    private Long vote_id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "review_id")
    private Long review_id;

    @Column(name = "up_vote")
    private int upVote;

    @Column(name = "down_vote")
    private int downVote;

    public Vote(Long user_id, Long review_id, int upVote, int downVote) {
        this.user_id = user_id;
        this.upVote = upVote;
        this.downVote = downVote;
        this.review_id = review_id;
    }


    public Vote() {
    }

    public Long getReview_id() {
        return review_id;
    }

    public void setReview_id(Long review_id) {
        this.review_id = review_id;
    }

    public Long getVote_id() {
        return vote_id;
    }

    public void setVote_id(Long vote_id) {
        this.vote_id = vote_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public int getUpVote() {
        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }
}
