package com.alexshin.tennisscoreboard.service;


import com.alexshin.tennisscoreboard.exception.NoSuchMatchException;
import com.alexshin.tennisscoreboard.model.dto.MatchDTO;
import com.alexshin.tennisscoreboard.model.entity.Match;
import com.alexshin.tennisscoreboard.model.entity.Player;
import com.alexshin.tennisscoreboard.repository.PlayersRepository;
import org.modelmapper.ModelMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {
    private final Map<UUID, MatchDTO> matchesMap = new HashMap<>();
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    private final PlayersRepository playersRepository = new PlayersRepository();
    private final ModelMapper mapper = new ModelMapper();


    public MatchDTO createNewMatch(String player1name, String player2name){
        Player player1 = playersRepository.saveOrGet(mapper.map(player1name, Player.class));
        Player player2 = playersRepository.saveOrGet(mapper.map(player2name, Player.class));
        MatchDTO match = new MatchDTO(player1, player2);
        match.setUuid(UUID.randomUUID());
        matchesMap.put(match.getUuid(), match);
        return match;
    }

    public MatchDTO getCurrentMatch(UUID uuid) {
        MatchDTO match = matchesMap.get(uuid);
        if (match == null) {
            throw new NoSuchMatchException("No match with uuid=" + uuid);
        }

        return match;
    }

    public void saveMatch(MatchDTO match) {
        finishedMatchesPersistenceService.saveMatch(mapper.map(match, Match.class));
    }

}
