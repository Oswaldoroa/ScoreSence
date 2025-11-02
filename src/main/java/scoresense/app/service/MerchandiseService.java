package scoresense.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.app.dto.MerchandiseRequest;
import scoresense.app.dto.MerchandiseResponse;
import scoresense.app.mapper.MerchandiseMapper;
import scoresense.app.model.Merchandise;
import scoresense.app.repository.MerchandiseRepository;

@Service
@Transactional
public class MerchandiseService {
    private final MerchandiseRepository merchandiseRepository;

    public MerchandiseService(MerchandiseRepository merchandiseRepository) {
        this.merchandiseRepository = merchandiseRepository;
    }

    public MerchandiseResponse create(MerchandiseRequest req) {
        Merchandise merchandise = new Merchandise();
        merchandise.setName(req.getName());
        merchandise.setPrice(req.getPrice());
        merchandise.setStock(req.getStock());
        merchandise.setType(req.getType());

        Merchandise saved = merchandiseRepository.save(merchandise);
        return MerchandiseMapper.toResponse(saved);
    }

    public List<MerchandiseResponse> getAll() {
        return merchandiseRepository.findAll()
                .stream()
                .map(MerchandiseMapper::toResponse)
                .collect(Collectors.toList());
    }
}
