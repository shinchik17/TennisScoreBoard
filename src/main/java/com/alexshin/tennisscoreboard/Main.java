package com.alexshin.tennisscoreboard;


import com.alexshin.tennisscoreboard.model.entity.Match;
import com.alexshin.tennisscoreboard.repository.MatchesRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {


//        Player player1 = new Player("Alkaras");
//        Player player2 = new Player("Medvedev");
//        MatchModel match = new MatchModel(player1, player2, UUID.randomUUID());
//        match.setWinnerByNum(1);
//        MatchMapper mapper = new MatchMapper();
//        MatchScoreDTO matchScore = mapper.toScoreModel(match);
        String NAME_FILTER_VALIDATE_PATTERN = "[a-zA-zА-Яа-я ]";
        MatchesRepository matchesRepository = new MatchesRepository();

        List<Match> matches = matchesRepository.findMatchesByPlayerName(1, 15, "Carlos Alcaraz");
        System.out.println(matches);


    }
}
