package scoresense.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.app.dto.Poll_voteRequest;
import scoresense.app.dto.Poll_voteResponse;
import scoresense.app.exception.ResourceNotFoundException;
import scoresense.app.mapper.Poll_voteMapper;
import scoresense.app.model.Poll;
import scoresense.app.model.Poll_vote;
import scoresense.app.model.User;
import scoresense.app.repository.PollRepository;
import scoresense.app.repository.Poll_voteRepository;
import scoresense.app.repository.UserRepository;

@Service
@Transactional
public class Poll_voteService {
    private final Poll_voteRepository poll_voteRepository;
    private final PollRepository pollRepository;
    private final UserRepository userRepository;

    public Poll_voteService(Poll_voteRepository poll_voteRepository, PollRepository pollRepository, UserRepository userRepository) {
        this.poll_voteRepository = poll_voteRepository;
        this.pollRepository = pollRepository;
        this.userRepository = userRepository;
    }


    public Poll_voteResponse create(Poll_voteRequest req) {
        Poll poll = pollRepository.findById(req.getPollId())
                .orElseThrow(() -> new ResourceNotFoundException("Poll", "id", req.getPollId()));

        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", req.getUserId()));

        Poll_vote vote = new Poll_vote();
        vote.setPoll(poll);
        vote.setUser(user);
        vote.setOptionSelected(req.getOptionSelected());

        Poll_vote saved = poll_voteRepository.save(vote);
        return Poll_voteMapper.toResponse(saved);
    }


    public List<Poll_voteResponse> getVotesByPoll(Long pollId) {
        return poll_voteRepository.findByPoll_PollId(pollId)
                .stream()
                .map(Poll_voteMapper::toResponse)
                .collect(Collectors.toList());
    }


    public List<Poll_voteResponse> getVotesByUser(Long userId) {
        return poll_voteRepository.findByUser_UserId(userId)
                .stream()
                .map(Poll_voteMapper::toResponse)
                .collect(Collectors.toList());
    }
}
