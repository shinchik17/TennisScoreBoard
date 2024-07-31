package com.alexshin.tennisscoreboard;


import com.alexshin.tennisscoreboard.model.dto.MatchDTO;
import com.alexshin.tennisscoreboard.model.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {


        Player player1 = new Player("Alkaras");
        Player player2 = new Player("Medvedev");
        MatchDTO match = new MatchDTO(player1, player2, UUID.randomUUID());
        match.setWinnerByNum(1);
//        MatchMapper mapper = new MatchMapper();
//
//        MatchScoreModel matchScore = mapper.toScoreModel(match);
//
//        ModelMapper modelMapper = new ModelMapper();
//        Player player = new Player("kek");
//        System.out.println();

        var source = match;
        String winner;
        if (source.getWinner() == null) {
            winner = "";
        } else {
            winner = source.getWinner().getName();
        }

        String winner2 = source.getWinner() == null ? "" : source.getWinner().getName();
        String winner3 = Optional.ofNullable(source.getWinner())
                .map(Player::getName)
                .orElse("");
        System.out.println(winner);
        System.out.println(winner2);
        System.out.println(winner3);


    }
}
