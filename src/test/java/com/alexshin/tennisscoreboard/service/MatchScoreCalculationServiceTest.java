package com.alexshin.tennisscoreboard.service;


import com.alexshin.tennisscoreboard.model.dto.MatchDTO;
import com.alexshin.tennisscoreboard.model.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static  org.junit.jupiter.api.Assertions.*;
import static com.alexshin.tennisscoreboard.service.MatchScoreCalculationService.*;


public class MatchScoreCalculationServiceTest {

    private MatchScoreCalculationService scoreCalculationService;
    private MatchDTO match;

    @BeforeEach
    void prepare(){
        scoreCalculationService = new MatchScoreCalculationService();
        match = new MatchDTO(new Player("Player1"), new Player("Player2"));
        //TODO: не нужно ли избавиться от PlayerModel в проекте в целом?
    }

    @Test
    void startTieBreak(){
        match.setPlayer1Game(NUM_GAMES_TO_WIN);
        match.setPlayer2Game(NUM_GAMES_TO_WIN);

        scoreCalculationService.updateMatchScore(match, 1);
        assertEquals(1, match.getPlayer1Point());
    }

    @Test
    void winTieBreak(){
        match.setPlayer1Game(NUM_GAMES_TO_WIN);
        match.setPlayer2Game(NUM_GAMES_TO_WIN);
        match.setPlayer1Point(NUM_POINTS_TO_WIN_TIE_BREAK - 1);

        scoreCalculationService.updateMatchScore(match, 1);
        assertEquals(1, match.getPlayer1Set());
    }

    @Test
    void continueTieBreakIfDifferenceIsLower2(){
        match.setPlayer1Game(NUM_GAMES_TO_WIN);
        match.setPlayer2Game(NUM_GAMES_TO_WIN);
        match.setPlayer1Point(NUM_POINTS_TO_WIN_TIE_BREAK - 1);
        match.setPlayer2Point(NUM_POINTS_TO_WIN_TIE_BREAK - 1);

        scoreCalculationService.updateMatchScore(match, 1);
        assertEquals(0, match.getPlayer1Set());
    }

    @Test
    void winGameBaseCondition(){
        match.setPlayer1Point(THIRD_POINT);

        scoreCalculationService.updateMatchScore(match, 1);
        assertEquals(1, match.getPlayer1Game());

    }

    @Test
    void getAdvantageAfterDeuce(){
        match.setPlayer1Point(THIRD_POINT);
        match.setPlayer2Point(THIRD_POINT);

        scoreCalculationService.updateMatchScore(match, 1);
        assertEquals(ADVANTAGE, match.getPlayer1Point());
    }

    @Test
    void restoreDeuceAfterLosingPLayerWonPoint(){
        match.setPlayer1Point(THIRD_POINT);
        match.setPlayer2Point(ADVANTAGE);

        scoreCalculationService.updateMatchScore(match, 1);
        assertEquals(THIRD_POINT, match.getPlayer1Point());
        assertEquals(THIRD_POINT, match.getPlayer2Point());
    }

    @Test
    void winGameAfterAdvantage(){
        match.setPlayer1Point(ADVANTAGE);
        match.setPlayer2Point(THIRD_POINT);

        scoreCalculationService.updateMatchScore(match, 1);
        assertEquals(1, match.getPlayer1Game());
    }

    @Test
    void winSetBaseCondition(){
        match.setPlayer1Game(NUM_GAMES_TO_WIN - 1);
        match.setPlayer2Game(NUM_GAMES_TO_WIN - GAMES_DIF_TO_WIN);
        match.setPlayer1Point(THIRD_POINT);

        scoreCalculationService.updateMatchScore(match, 1);
        assertEquals(1, match.getPlayer1Set());
    }

    @Test
    void winSetIn7Games(){
        match.setPlayer1Game(NUM_GAMES_TO_WIN);
        match.setPlayer2Game(NUM_GAMES_TO_WIN - 1);
        match.setPlayer1Point(THIRD_POINT);

        scoreCalculationService.updateMatchScore(match, 1);
        assertEquals(1, match.getPlayer1Set());
    }



}
