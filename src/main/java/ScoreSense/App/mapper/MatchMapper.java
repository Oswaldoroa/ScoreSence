package ScoreSense.App.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.MatchDTO;
import ScoreSense.App.model.Match;

public final class MatchMapper {

    public static MatchDTO toDTO(Match match) {
        if (match == null) {
            return null;
        }

        MatchDTO dto = new MatchDTO();

        dto.setId(match.getMatchId());
        dto.setHomeScore(match.getHomeScore());
        dto.setAwayScore(match.getAwayScore());

        if (match.getMatchDate() != null) {
            dto.setMatchDate(match.getMatchDate().toString());
        }

        if (match.getHomeTeam() != null) {
            dto.setTeamLocalId(match.getHomeTeam().getTeamId());
        }

        if (match.getAwayTeam() != null) {
            dto.setTeamVisitorId(match.getAwayTeam().getTeamId());
        }

        return dto;
    }

    public static Match toEntity(MatchDTO dto) {
        if (dto == null) {
            return null;
        }

        Match entity = new Match();

        if (dto.getId() != null) {
            entity.setMatchId(dto.getId());
        }

        entity.setHomeScore(dto.getHomeScore());
        entity.setAwayScore(dto.getAwayScore());

        if (dto.getMatchDate() != null && !dto.getMatchDate().isBlank()) {
            try {
                entity.setMatchDate(LocalDate.parse(dto.getMatchDate()));
            } catch (Exception e) {
                System.err.println("Error parsing matchDate: " + dto.getMatchDate());
            }
        }

        return entity;
    }

    public static void copyToEntity(MatchDTO dto, Match entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setHomeScore(dto.getHomeScore());
        entity.setAwayScore(dto.getAwayScore());

        if (dto.getMatchDate() != null && !dto.getMatchDate().isBlank()) {
            try {
                entity.setMatchDate(LocalDate.parse(dto.getMatchDate()));
            } catch (Exception e) {

            }
        }
    }

    public static List<MatchDTO> toDTOList(List<Match> matchList) {
        if (matchList == null) {
            return List.of();
        }
        return matchList.stream()
                .map(MatchMapper::toDTO)
                .collect(Collectors.toList());
    }
}
