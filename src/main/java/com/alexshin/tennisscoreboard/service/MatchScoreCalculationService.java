package com.alexshin.tennisscoreboard.service;

import com.alexshin.tennisscoreboard.model.dto.MatchDTO;;

public class MatchScoreCalculationService {
    public static final int NUM_SETS_TO_WIN = 2;
    public static final int NUM_GAMES_TO_WIN = 6;
    public static final int NUM_GAMES_MAX = 7;
    public static final int GAMES_DIF_TO_WIN = 2;
    public static final int NULL_POINT = 0;
    public static final int FIRST_POINT = 15;
    public static final int SECOND_POINT = 30;
    public static final int THIRD_POINT = 40;
    public static final int ADVANTAGE = 41;
    public static final int NUM_POINTS_TO_WIN_TIE_BREAK = 7;
    public static final String ADVANTAGE_STRING = "AD";
    public static final String DISADVANTAGE_STRING = "";

    public static int invId(int id) {
        return id ^ 3;
    }


    public void updateMatchScore(MatchDTO match, int playerNum) {

        if (isTieBreak(match)){
            processTieBreak(match, playerNum);
            return;
        }

        addPoint(match, playerNum);

    }


    private void addPoint(MatchDTO match, int playerNum) {

        switch (match.getPlayerPoint(playerNum)) {
            case NULL_POINT:
                match.setPlayerPoint(playerNum, FIRST_POINT);
                return;
            case FIRST_POINT:
                match.setPlayerPoint(playerNum, SECOND_POINT);
                return;
            case SECOND_POINT:
                match.setPlayerPoint(playerNum, THIRD_POINT);
                return;
            case ADVANTAGE:
                addGame(match, playerNum);
                return;
        }

        // cur player point == 40
        if (match.getPlayerPoint(invId(playerNum)) <= SECOND_POINT) {
            addGame(match, playerNum);
        } else if (match.getPlayerPoint(invId(playerNum)) == THIRD_POINT) {
            match.setPlayerPoint(playerNum, ADVANTAGE);
        } else {
            match.setPlayerPoint(playerNum, THIRD_POINT);
            match.setPlayerPoint(invId(playerNum), THIRD_POINT);
        }
    }

    private void addGame(MatchDTO match, int playerNum) {
        int playerGame = match.getPlayerGame(playerNum) + 1;
        match.setPlayerGame(playerNum, playerGame);
        int oppositePlayerGame = match.getPlayerGame(invId(playerNum));

        if (playerGame == NUM_GAMES_TO_WIN || playerGame == NUM_GAMES_MAX) {
            if ((playerGame - oppositePlayerGame) >= GAMES_DIF_TO_WIN) {
                addSet(match, playerNum);
            }
        }
    }

    private void addSet(MatchDTO match, int playerNum) {
        match.setPlayerSet(playerNum, match.getPlayerSet(playerNum) + 1);
        if (isMatchFinished(match)) {
            match.setWinnerByNum(playerNum);
        }
    }

    private void processTieBreak(MatchDTO match, int playerNum){
        int playerPoint = match.getPlayerPoint(playerNum) + 1;
        match.setPlayerPoint(playerNum, playerPoint);
        int oppositePlayerPoint = match.getPlayerPoint(invId(playerNum));

        if (playerPoint >= NUM_POINTS_TO_WIN_TIE_BREAK && (playerPoint - oppositePlayerPoint) >= GAMES_DIF_TO_WIN){
            addSet(match, playerNum);
        }
    }

    public static boolean isTieBreak(MatchDTO match){
        return match.getPlayer1Game() == NUM_GAMES_TO_WIN && match.getPlayer2Game() == NUM_GAMES_TO_WIN;
    }

    public static boolean isMatchFinished(MatchDTO match){
        return match.getPlayer1Set() == NUM_SETS_TO_WIN || match.getPlayer2Set() == NUM_SETS_TO_WIN;
    }

    private static boolean hasAdvantage(MatchDTO match, int playerNum){
        return match.getPlayerPoint(playerNum) == ADVANTAGE;
    }


    public static String getPlayerPointString(MatchDTO match, int playerNum){
        if (hasAdvantage(match, playerNum)) {
            return ADVANTAGE_STRING;
        } else if (hasAdvantage(match, invId(playerNum))) {
            return DISADVANTAGE_STRING;
        }
        return String.valueOf(match.getPlayerPoint(playerNum));
    }


}
