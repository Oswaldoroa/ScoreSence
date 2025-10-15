package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.LeagueDTO;
import ScoreSense.App.model.League;
import ScoreSense.App.model.Team;

public final class LeagueMapper {

    public static LeagueDTO toDTO(League league) {
        if (league == null) {
            return null;
        }

        LeagueDTO dto = new LeagueDTO();

        dto.setId(league.getLeagueId());
        dto.setName(league.getName());
        dto.setCountry(league.getCountry());
        dto.setSeason(league.getSeason());
        dto.setLevel(league.getLevel());

        List<Team> teams = league.getTeams();

        if (teams != null && !teams.isEmpty()) {

            dto.setTeamsDTO(TeamMapper.toDTOList(teams));

            dto.setNumberOfTeams(teams.size());
        } else {
            dto.setTeamsDTO(List.of());
            dto.setNumberOfTeams(0);
        }

        return dto;
    }

    public static League toEntity(LeagueDTO dto) {
        if (dto == null) {
            return null;
        }

        League entity = new League();

        if (dto.getId() != null) {
            entity.setLeagueId(dto.getId());
        }

        entity.setName(dto.getName());
        entity.setCountry(dto.getCountry());
        entity.setSeason(dto.getSeason());
        entity.setLevel(dto.getLevel());

        return entity;
    }

    public static void copyToEntity(LeagueDTO dto, League entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setName(dto.getName());
        entity.setCountry(dto.getCountry());
        entity.setSeason(dto.getSeason());
        entity.setLevel(dto.getLevel());
    }

    public static List<LeagueDTO> toDTOList(List<League> leagueList) {
        if (leagueList == null) {
            return List.of();
        }
        return leagueList.stream()
                .map(LeagueMapper::toDTO)
                .collect(Collectors.toList());
    }
}
