package scoresense.app.mapper;

import scoresense.app.dto.Poll_voteRequest;
import scoresense.app.dto.Poll_voteResponse;
import scoresense.app.model.Poll;
import scoresense.app.model.Poll_vote;
import scoresense.app.model.User;
public class Poll_voteMapper {
    public static Poll_vote toEntity(Poll_voteRequest req, Poll poll, User user) {
        Poll_vote vote = new Poll_vote();
        vote.setPoll(poll);
        vote.setUser(user);
        vote.setOptionSelected(req.getOptionSelected());
        return vote;
    }


    public static Poll_voteResponse toResponse(Poll_vote vote) {
        return Poll_voteResponse.builder()
                .pollVoteId(vote.getPollvoteId())
                .pollId(vote.getPoll() != null ? vote.getPoll().getPollId() : null)
                .userId(vote.getUser() != null ? vote.getUser().getUserId() : null)
                .optionSelected(vote.getOptionSelected())
                .votedAt(vote.getVotedAt())
                .build();
    }
}
