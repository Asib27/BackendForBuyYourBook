package com.asib27.authentication.Votes;

import com.asib27.authentication.UserCloned.UserCloned;
import com.asib27.authentication.UserCloned.UserClonedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vote")
public class VoteController {
    @Autowired
    UserClonedService userClonedService;

    @Autowired
    VoteService voteService;


    @PostMapping("/upvote")
    public void addUpvote(@RequestParam Long review_id) {
        UserCloned user = userClonedService.getCurrentUser();
        Vote vote = new Vote();
        vote.setUser_id(user.getId());
        vote.setReview_id(review_id);
        vote.setUpVote(1);
        vote.setDownVote(0);
        voteService.addUpVote(vote);
    }

    @PostMapping("/downvote")
    public void addDownVote(@RequestParam Long review_id){
        UserCloned user = userClonedService.getCurrentUser();
        Vote vote = new Vote();
        vote.setUser_id(user.getId());
        vote.setReview_id(review_id);
        vote.setUpVote(0);
        vote.setDownVote(1);
        voteService.addDownVote(vote);
    }

}
