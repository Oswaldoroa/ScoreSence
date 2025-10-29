package scoresense.app.service;

import java.util.List;

import scoresense.app.model.Match;
public interface MatchService {
    List<Match> findAll();
    Match findById(Long id);
    Match create(Match match);
    Match update(Long id, Match match);
    void delete(Long id);
    List<Match> getMatchesByTeamName(String teamName);
    List<Match> findAll(int page, int pageSize);
}
