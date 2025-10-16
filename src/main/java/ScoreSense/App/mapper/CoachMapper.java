package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.CoachDTO;
import ScoreSense.App.model.Coach;

public final class CoachMapper {

    public static CoachDTO toDTO(Coach coach) {
        if (coach == null) {
            return null;
        }

        CoachDTO dto = new CoachDTO();

        dto.setId(coach.getCoachId());
        dto.setName(coach.getName());
        dto.setNationality(coach.getNationality());
        dto.setExperiencedYears(coach.getExperiencedYears());

        if (coach.getTeam() != null) {
            dto.setTeamId(coach.getTeam().getTeamId());
        }

        return dto;
    }

    public static Coach toEntity(CoachDTO dto) {
        if (dto == null) {
            return null;
        }

        Coach entity = new Coach();

        if (dto.getId() != null) {
            entity.setCoachId(dto.getId());
        }

        entity.setName(dto.getName());
        entity.setNationality(dto.getNationality());
        entity.setExperiencedYears(dto.getExperiencedYears());

        return entity;
    }

    public static void copyToEntity(CoachDTO dto, Coach entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setName(dto.getName());
        entity.setNationality(dto.getNationality());
        entity.setExperiencedYears(dto.getExperiencedYears());
    }

    public static List<CoachDTO> toDTOList(List<Coach> coaches) {
        if (coaches == null) {
            return List.of();
        }
        return coaches.stream()
                .map(CoachMapper::toDTO)
                .collect(Collectors.toList());
    }
}
