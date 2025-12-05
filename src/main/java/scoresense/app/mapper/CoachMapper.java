package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.CoachRequest;
import scoresense.app.dto.CoachResponse;
import scoresense.app.model.Coach;
import scoresense.app.model.Team;

public final class CoachMapper {

    public static CoachResponse toResponse(Coach coach) {
        if (coach == null) return null;

        Team team = coach.getTeam();

        return CoachResponse.builder()
                .coachId(coach.getCoachId())
                .name(coach.getName())
                .nationality(coach.getNationality())
                .experiencedYears(coach.getExperiencedYears())
                .teamId(team != null ? team.getTeamId() : null)
                .build();
    }

    public static Coach toEntity(CoachRequest request) {
        if (request == null) return null;

        Coach coach = new Coach();
        coach.setName(request.getName());
        coach.setNationality(request.getNationality());
        coach.setExperiencedYears(request.getExperiencedYears());

        // Si necesitas asociar el Team por ID, hazlo en el servicio
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

        return coaches.stream()
                .map(CoachMapper::toResponse)
                .collect(Collectors.toList());
    }
}