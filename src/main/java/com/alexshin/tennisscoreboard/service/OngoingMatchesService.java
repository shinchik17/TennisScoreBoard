package com.alexshin.tennisscoreboard.service;


import com.alexshin.tennisscoreboard.exception.NoSuchMatchException;
import com.alexshin.tennisscoreboard.model.dto.MatchDTO;
import com.alexshin.tennisscoreboard.model.entity.Match;
import org.modelmapper.ModelMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {
    private final Map<UUID, MatchDTO> matchesMap = new HashMap<>();
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    private final ModelMapper mapper = new ModelMapper();


//    public MatchDTO crea

    public MatchDTO getCurrentMatch(UUID uuid) {
        MatchDTO match = matchesMap.get(uuid);
        if (match == null) {
            throw new NoSuchMatchException("No match with uuid=" + uuid);
        }

        return match;
    }

    public void saveMatch(MatchDTO match) {
        if (MatchScoreCalculationService.isMatchFinished(match)) {
            finishedMatchesPersistenceService.saveMatch(mapper.map(match, Match.class));
        }
    }

}
