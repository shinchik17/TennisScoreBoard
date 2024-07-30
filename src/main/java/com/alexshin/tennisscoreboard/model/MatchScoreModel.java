package com.alexshin.tennisscoreboard.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatchScoreModel {

    private String player1;
    private String player2;

    private String player1Set;
    private String player2Set;

    private String player1Game;
    private String player2Game;

    private String player1Point;
    private String player2Point;

    private String winner;

}

