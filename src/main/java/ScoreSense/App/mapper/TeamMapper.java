package ScoreSense.App.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ScoreSense.App.dto.TeamDTO;
import ScoreSense.App.model.Team;

public final class TeamMapper {

    public static TeamDTO toDTO(Team team) {
        if (team == null) {
            return null;
        }

        TeamDTO dto = new TeamDTO();

        dto.setId(team.getTeamId());
        dto.setName(team.getName());
        dto.setCountry(team.getCountry());
        dto.setFounded_year(team.getFounded_year());
        dto.setStadium(team.getStadium());
        dto.setLogo_url(team.getLogo_url());

        if (team.getLeague() != null) {
            dto.setLeagueId(team.getLeague().getLeagueId());
        }

        if (team.getCoach() != null) {
            dto.setCoachId(team.getCoach().getCoachId());
        }

        if (team.getPlayers() != null && !team.getPlayers().isEmpty()) {
            dto.setPlayersDTO(PlayerMapper.toDTOList(team.getPlayers()));
        } else {
            dto.setPlayersDTO(List.of());
        }

        if (team.getSentiments() != null && !team.getSentiments().isEmpty()) {
            dto.setSentimentsDTO(SentimentMapper.toDTOList(team.getSentiments()));
        } else {
            dto.setSentimentsDTO(List.of());
        }

        return dto;
    }

    public static Team toEntity(TeamDTO dto) {
        if (dto == null) {
            return null;
        }

        Team entity = new Team();

        if (dto.getId() != null) {
            entity.setTeamId(dto.getId());
        }

        entity.setName(dto.getName());
        entity.setCountry(dto.getCountry());
        entity.setFounded_year(dto.getFounded_year());
        entity.setStadium(dto.getStadium());
        entity.setLogo_url(dto.getLogo_url());

        return entity;
    }

    public static void copyToEntity(TeamDTO dto, Team entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setName(dto.getName());
        entity.setCountry(dto.getCountry());
        entity.setFounded_year(dto.getFounded_year());
        entity.setStadium(dto.getStadium());
        entity.setLogo_url(dto.getLogo_url());
    }

    public static List<TeamDTO> toDTOList(List<Team> teamsList) {
        if (teamsList == null) {
            return List.of();
        }
        return teamsList.stream()
                .map(TeamMapper::toDTO)
                .collect(Collectors.toList());
    }
}
