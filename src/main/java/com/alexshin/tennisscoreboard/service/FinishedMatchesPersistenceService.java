package com.alexshin.tennisscoreboard.service;


import com.alexshin.tennisscoreboard.model.entity.Match;
import com.alexshin.tennisscoreboard.repository.MatchesRepository;

public class FinishedMatchesPersistenceService {
    private final MatchesRepository matchesRepository = new MatchesRepository();


    public void saveMatch(Match match){
        matchesRepository.save(match);
    }

}
