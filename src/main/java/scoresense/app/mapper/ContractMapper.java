package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.ContractRequest;
import scoresense.app.dto.ContractResponse;
import scoresense.app.dto.PlayerResponse;
import scoresense.app.dto.TeamResponse;
import scoresense.app.dto.LeagueResponse;
import scoresense.app.model.Contract;
import scoresense.app.model.Player;
import scoresense.app.model.Team;

public final class ContractMapper {

    public static ContractResponse toResponse(Contract contract) {
        if (contract == null) return null;

        Team team = contract.getTeam();
        Player player = contract.getPlayer();

        return ContractResponse.builder()
                .contractId(contract.getContractId())
                .entityType(contract.getEntityType())
                .entityId(contract.getEntityId())
                .startDate(contract.getStartDate())
                .endDate(contract.getEndDate())
                .salary(contract.getSalary())
                .releaseClause(contract.getReleaseClause())
                .team(team != null ? TeamResponse.builder()
                        .teamId(team.getTeamId())
                        .name(team.getName())
                        .country(team.getCountry())
                        .foundedYear(team.getFoundedYear())
                        .stadium(team.getStadium())
                        .logoUrl(team.getLogoUrl())
                        .league(team.getLeague() != null ? LeagueResponse.builder()
                                .leagueId(team.getLeague().getLeagueId())
                                .name(team.getLeague().getName())
                                .country(team.getLeague().getCountry())
                                .season(team.getLeague().getSeason())
                                .level(team.getLeague().getLevel())
                                .build() : null)
                        .build() : null)
                .player(player != null ? PlayerResponse.builder()
                        .playerId(player.getPlayerId())
                        .name(player.getName())
                        .position(player.getPosition())
                        .age(player.getAge())
                        .nationality(player.getNationality())
                        .height(player.getHeight())
                        .weight(player.getWeight())
                        .team(player.getTeam() != null ? TeamResponse.builder()
                                .teamId(player.getTeam().getTeamId())
                                .name(player.getTeam().getName())
                                .country(player.getTeam().getCountry())
                                .foundedYear(player.getTeam().getFoundedYear())
                                .stadium(player.getTeam().getStadium())
                                .logoUrl(player.getTeam().getLogoUrl())
                                .league(player.getTeam().getLeague() != null ? LeagueResponse.builder()
                                        .leagueId(player.getTeam().getLeague().getLeagueId())
                                        .name(player.getTeam().getLeague().getName())
                                        .country(player.getTeam().getLeague().getCountry())
                                        .season(player.getTeam().getLeague().getSeason())
                                        .level(player.getTeam().getLeague().getLevel())
                                        .build() : null)
                                .build() : null)
                        .build() : null)
                .build();
    }

    public static Contract toEntity(ContractRequest request) {
        if (request == null) return null;

        Contract contract = new Contract();
        contract.setEntityType(request.getEntityType());
        contract.setEntityId(request.getEntityId());
        contract.setStartDate(request.getStartDate());
        contract.setEndDate(request.getEndDate());
        contract.setSalary(request.getSalary());
        contract.setReleaseClause(request.getReleaseClause());

        return contract;
    }

    public static void copyToEntity(ContractRequest request, Contract entity) {
        if (request == null || entity == null) return;

        entity.setEntityType(request.getEntityType());
        entity.setEntityId(request.getEntityId());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setSalary(request.getSalary());
        entity.setReleaseClause(request.getReleaseClause());
    }

    public static List<ContractResponse> toResponseList(List<Contract> contracts) {
        if (contracts == null) return List.of();

        return contracts.stream()
                .map(ContractMapper::toResponse)
                .collect(Collectors.toList());
    }
}