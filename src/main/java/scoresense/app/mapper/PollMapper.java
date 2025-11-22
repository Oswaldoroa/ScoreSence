package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.PollRequest;
import scoresense.app.dto.PollResponse;
import scoresense.app.model.Poll;

public final class PollMapper {

    public static PollResponse toResponse(Poll poll) {
        if (poll == null) return null;

        return PollResponse.builder()
                .pollId(poll.getPollId())
                .question(poll.getQuestion())
                .createdAt(poll.getCreatedAt())
                .expiresAt(poll.getExpiresAt())
                .build();
    }

    public static Poll toEntity(PollRequest request) {
        if (request == null) return null;

        Poll poll = new Poll();
        poll.setQuestion(request.getQuestion());
        poll.setExpiresAt(request.getExpiresAt());
        return poll;
    }

    public static void copyToEntity(PollRequest request, Poll entity) {
        if (request == null || entity == null) return;

        entity.setQuestion(request.getQuestion());
        entity.setExpiresAt(request.getExpiresAt());
    }

    public static List<PollResponse> toResponseList(List<Poll> polls) {
        if (polls == null) return List.of();

        return polls.stream()
                .map(PollMapper::toResponse)
                .collect(Collectors.toList());
    }
}