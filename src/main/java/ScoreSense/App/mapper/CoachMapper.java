package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.CoachRequest;
import ScoreSense.App.dto.CoachResponse;
import ScoreSense.App.model.Coach;

public final class CoachMapper {

    public static CoachResponse toResponse(Coach coach) {
        if (coach == null) return null;
        return CoachResponse.builder()
                .coachId(coach.getCoachId())
                .name(coach.getName())
                .nationality(coach.getNationality())
                .experiencedYears(coach.getExperiencedYears())
                .teamId(coach.getTeam() != null ? coach.getTeam().getTeamId() : null)
                .build();
    }

    public static Coach toEntity(CoachRequest request) {
        if (request == null) return null;
        Coach coach = new Coach();
        coach.setName(request.getName());
        coach.setNationality(request.getNationality());
        coach.setExperiencedYears(request.getExperiencedYears());

        return coach;
    }

    public static void copyToEntity(CoachRequest request, Coach entity) {
        if (request == null || entity == null) return;
        entity.setName(request.getName());
        entity.setNationality(request.getNationality());
        entity.setExperiencedYears(request.getExperiencedYears());
    }

    public static List<CoachResponse> toResponseList(List<Coach> coaches) {
        if (coaches == null) return List.of();
        return coaches.stream().map(CoachMapper::toResponse).collect(Collectors.toList());
    }
}
