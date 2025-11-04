package scoresense.app.service;

import java.util.List;

import scoresense.app.model.Rivalries;
public interface RivalriesService {
    List<Rivalries> findAll();
    Rivalries findById(Long id);
    Rivalries create(Rivalries rivalries);
    Rivalries update(Long id, Rivalries rivalries);
    void delete(Long id);
}
