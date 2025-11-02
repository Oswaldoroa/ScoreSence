package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.PollResponse;
import scoresense.app.model.Poll;
public class PollMapper {
    public static PollResponse toResponse(Poll poll) {
        return PollResponse.builder()
                .pollId(poll.getPollId())
                .question(poll.getQuestion())
                .createdAt(poll.getCreatedAt())
                .expiresAt(poll.getExpiresAt())
                .build();
    }

    public static List<PollResponse> toResponseList(List<Poll> list) {
        return list.stream()
                .map(PollMapper::toResponse)
                .collect(Collectors.toList());
    }
}
