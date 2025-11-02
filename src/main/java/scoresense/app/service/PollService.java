package scoresense.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scoresense.app.dto.PollRequest;
import scoresense.app.dto.PollResponse;
import scoresense.app.exception.ResourceNotFoundException;
import scoresense.app.mapper.PollMapper;
import scoresense.app.model.Poll;
import scoresense.app.repository.PollRepository;

@Service
@Transactional
public class PollService {

    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    // Obtener una encuesta por ID
    public PollResponse getById(Long id) {
        Poll poll = pollRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Poll", "id", id));
        return PollMapper.toResponse(poll);
    }

    // Crear una nueva encuesta
    public PollResponse create(PollRequest req) {
        Poll poll = new Poll();
        poll.setQuestion(req.getQuestion());
        poll.setCreatedAt(LocalDateTime.now());
        poll.setExpiresAt(LocalDateTime.now().plusDays(7)); // Por defecto expira en 7 días

        Poll saved = pollRepository.save(poll);
        return PollMapper.toResponse(saved);
    }

    // Actualizar una encuesta existente
    public PollResponse update(Long id, PollRequest req) {
        Poll poll = pollRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Poll", "id", id));

        poll.setQuestion(req.getQuestion());
        poll.setExpiresAt(LocalDateTime.now().plusDays(7)); // Reinicia vencimiento

        Poll updated = pollRepository.save(poll);
        return PollMapper.toResponse(updated);
    }

    // Eliminar una encuesta
    public void delete(Long id) {
        Poll poll = pollRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Poll", "id", id));
        pollRepository.delete(poll);
    }

    // Obtener todas las encuestas
    public List<PollResponse> getAll() {
        return pollRepository.findAll()
                .stream()
                .map(PollMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Obtener solo encuestas activas (no vencidas)
    public List<PollResponse> getActivePolls() {
        return pollRepository.findAll()
                .stream()
                .filter(poll -> poll.getExpiresAt().isAfter(LocalDateTime.now()))
                .map(PollMapper::toResponse)
                .collect(Collectors.toList());
    }

}
