package scoresense.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.ContractResponse;
import scoresense.app.model.Contract;
public class ContractMapper {
    public static ContractResponse toResponse(Contract contract) {
        return ContractResponse.builder()
                .contractId(contract.getContractId())
                .startDate(contract.getStartDate())
                .endDate(contract.getEndDate())
                .salary(contract.getSalary())
                .playerId(contract.getPlayer() != null ? contract.getPlayer().getPlayerId() : null)
                .build();
    }

    public static List<ContractResponse> toResponseList(List<Contract> contracts) {
        return contracts.stream()
                .map(ContractMapper::toResponse)
                .collect(Collectors.toList());
    }
}
