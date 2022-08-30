package com.asib27.authentication.Votes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;

    public void addUpVote(Vote vote) {
        voteRepository.save(vote);

    }

    public void addDownVote(Vote vote) {
        voteRepository.save(vote);
    }
}
