package scoresense.app.mapper;


import java.util.List;
import java.util.stream.Collectors;

import scoresense.app.dto.MerchandiseResponse;
import scoresense.app.model.Merchandise;
public class MerchandiseMapper {
    public static MerchandiseResponse toResponse(Merchandise merchandise) {
        return MerchandiseResponse.builder()
                .merchandiseId(merchandise.getMerchandiseId())
                .name(merchandise.getName())
                .price(merchandise.getPrice())
                .stock(merchandise.getStock())
                .type(merchandise.getType())
                .build();
    }

    public static List<MerchandiseResponse> toResponseList(List<Merchandise> list) {
        return list.stream()
                .map(MerchandiseMapper::toResponse)
                .collect(Collectors.toList());
    }
}
