package ScoreSense.App.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ScoreSense.App.dto.CoachRequest;
import ScoreSense.App.dto.CoachResponse;
import ScoreSense.App.exception.ResourceNotFoundException;
import ScoreSense.App.mapper.CoachMapper;
import ScoreSense.App.model.Coach;
import ScoreSense.App.model.Team;
import ScoreSense.App.repository.CoachRepository;
import ScoreSense.App.repository.TeamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
@Transactional
public class CoachService {

    private final CoachRepository coachRepository;
    private final TeamRepository teamRepository;

    public CoachService(CoachRepository coachRepository, TeamRepository teamRepository) {
        this.coachRepository = coachRepository;
        this.teamRepository = teamRepository;
    }

    public CoachResponse getById(Long id) {
        Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coach", "id", id));
        return toResponse(coach);
    }

    public CoachResponse create(CoachRequest req) {
        Coach coach = new Coach();
        coach.setName(req.getName());
        coach.setNationality(req.getNationality());
        coach.setExperiencedYears(req.getExperiencedYears());

        Team team = teamRepository.findById(req.getTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", req.getTeamId()));
        coach.setTeam(team);

        Coach saved = coachRepository.save(coach);
        return toResponse(saved);
    }

    public CoachResponse update(Long id, CoachRequest req) {
        Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coach", "id", id));

        coach.setName(req.getName());
        coach.setNationality(req.getNationality());
        coach.setExperiencedYears(req.getExperiencedYears());

        Team team = teamRepository.findById(req.getTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", req.getTeamId()));
        coach.setTeam(team);

        Coach updated = coachRepository.save(coach);
        return toResponse(updated);
    }

    public void delete(Long id) {
        Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coach", "id", id));
        coachRepository.delete(coach);
    }

    private CoachResponse toResponse(Coach coach) {
        return CoachResponse.builder()
                .coachId(coach.getCoachId())
                .name(coach.getName())
                .nationality(coach.getNationality())
                .experiencedYears(coach.getExperiencedYears())
                .teamId(coach.getTeam() != null ? coach.getTeam().getTeamId() : null)
                .build();
    }

    public List<CoachResponse> findByName(String name) {
        return coachRepository.findByNameIgnoreCase(name)
                .stream()
                .map(CoachMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<CoachResponse> findExperiencedCoaches(int years) {
        return coachRepository.findByExperiencedYearsGreaterThan(years)
                .stream()
                .map(CoachMapper::toResponse)
                .collect(Collectors.toList());
    }
    public Page<CoachResponse> getAllPaged(Pageable pageable) {
    return coachRepository.findAll(pageable)
            .map(CoachMapper::toResponse);
    }
    public List<CoachResponse> findByNationality(String nationality) {
        return coachRepository.findByNationalityIgnoreCase(nationality)
                .stream()
                .map(CoachMapper::toResponse)
                .collect(Collectors.toList());
    }
}
