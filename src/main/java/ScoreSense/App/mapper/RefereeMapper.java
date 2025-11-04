package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.RefereeRequest;
import ScoreSense.App.dto.RefereeResponse;
import ScoreSense.App.model.Referee;

public final class RefereeMapper {
    private RefereeMapper() {}  // Prevent instantiation
      
    
    public static RefereeResponse toResponse(Referee referee) {
        if (referee == null) return null;

        return RefereeResponse.builder()
                .refereeId(referee.getRefereeId())
                .name(referee.getName())
                .nationality(referee.getNationality())
                .experienceYears(referee.getExperienceYears())
                .build();
    }

    public static List<RefereeResponse> toResponseList(List<Referee> referees) {
        if (referees == null) return List.of();
        return referees.stream()
                .map(RefereeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public static Referee toEntity(RefereeRequest request) {
        if (request == null) return null;

        Referee referee = new Referee();
        referee.setName(request.getName());
        referee.setNationality(request.getNationality());
        referee.setExperienceYears(request.getExperienceYears());
        return referee;
    }

    public static void copyToEntity(RefereeRequest request, Referee entity) {
        if (request == null || entity == null) return;

        entity.setName(request.getName());
        entity.setNationality(request.getNationality());
        entity.setExperienceYears(request.getExperienceYears());
    }
}
    