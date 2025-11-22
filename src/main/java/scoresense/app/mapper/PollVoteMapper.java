package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.PollVoteRequest;
import scoresense.app.dto.PollVoteResponse;
import scoresense.app.dto.UserResponse;
import scoresense.app.model.Poll_vote;
import scoresense.app.model.Poll;
import scoresense.app.model.User;

public final class PollVoteMapper {

    public static PollVoteResponse toResponse(Poll_vote vote) {
        if (vote == null) return null;

        Poll poll = vote.getPoll();
        User user = vote.getUser();

        return PollVoteResponse.builder()
                .pollVoteId(vote.getPollvoteId())
                .optionSelected(vote.getOptionSelected())
                .votedAt(vote.getVotedAt())
                .poll(poll != null ? PollMapper.toResponse(poll) : null)
                .user(user != null ? UserResponse.builder()
                        .userId(user.getUserId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .build() : null)
                .build();
    }

    public static Poll_vote toEntity(PollVoteRequest request) {
        if (request == null) return null;

        Poll_vote vote = new Poll_vote();
        vote.setOptionSelected(request.getOptionSelected());
        return vote;
    }

    public static void copyToEntity(PollVoteRequest request, Poll_vote entity) {
        if (request == null || entity == null) return;

        entity.setOptionSelected(request.getOptionSelected());
    }

    public static List<PollVoteResponse> toResponseList(List<Poll_vote> votes) {
        if (votes == null) return List.of();

        return votes.stream()
                .map(PollVoteMapper::toResponse)
                .collect(Collectors.toList());
    }
}