package ScoreSense.App.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ScoreSense.App.dto.RefereeRequest;
import ScoreSense.App.dto.RefereeResponse;
import ScoreSense.App.exception.ResourceNotFoundException;
import ScoreSense.App.mapper.RefereeMapper;
import ScoreSense.App.model.Referee;
import ScoreSense.App.repository.RefereeRepository;

@Service
@Transactional
public class RefereeService {

    private final RefereeRepository refereeRepository;

    public RefereeService(RefereeRepository refereeRepository) {
        this.refereeRepository = refereeRepository;
    }

    public Page<RefereeResponse> findAll(Pageable pageable) {
        return refereeRepository.findAll(pageable)
                .map(RefereeMapper::toResponse);
    }

    public RefereeResponse findById(Long id) {
        Referee referee = refereeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Referee", "id", id));
        return RefereeMapper.toResponse(referee);
    }

    public RefereeResponse create(RefereeRequest request) {
        Referee referee = RefereeMapper.toEntity(request);
        Referee savedReferee = refereeRepository.save(referee);
        return RefereeMapper.toResponse(savedReferee);
    }

    public RefereeResponse update(Long id, RefereeRequest request) {
        Referee referee = refereeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Referee", "id", id));

        RefereeMapper.copyToEntity(request, referee);

        Referee updatedReferee = refereeRepository.save(referee);
        return RefereeMapper.toResponse(updatedReferee);
    }

    public void delete(Long id) {
        if (!refereeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Referee", "id", id);
        }
        refereeRepository.deleteById(id);
    }
}