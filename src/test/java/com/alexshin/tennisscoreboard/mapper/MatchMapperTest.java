package com.alexshin.tennisscoreboard.mapper;

import com.alexshin.tennisscoreboard.model.dto.MatchScoreDTO;
import com.alexshin.tennisscoreboard.model.MatchModel;
import com.alexshin.tennisscoreboard.model.entity.Match;
import com.alexshin.tennisscoreboard.model.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.alexshin.tennisscoreboard.service.MatchScoreCalculationService.getPlayerPointString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchMapperTest {

    Match matchEntity;
    MatchModel matchModel;
    MatchMapper mapper = new MatchMapper();
    Player player1 = new Player(1, "Novak Djokovic_");
    Player player2 = new Player(2, "Daniil Medvedev_");

    @BeforeEach
    void prepare() {
        matchEntity = new Match();
        matchEntity.setPlayer1(player1);
        matchEntity.setPlayer2(player2);
        matchEntity.setWinner(player1);

        matchModel = new MatchModel(player1, player2, UUID.randomUUID());
        matchModel.setPlayerSet(1, 1);
        matchModel.setPlayerGame(1, 2);
        matchModel.setPlayerGame(2, 4);
        matchModel.setPlayerGame(1, 30);
        matchModel.setPlayerPoint(1, 41);
        matchModel.setPlayerPoint(2, 40);
        matchModel.setWinnerByNum(1);
    }

    @Test
    void entityToDTO() {
        MatchModel matchModel = mapper.toMatchModel(matchEntity);
        assertEquals(matchEntity.getPlayer1(), matchModel.getPlayer1());
        assertEquals(matchEntity.getPlayer2(), matchModel.getPlayer2());
        assertEquals(matchEntity.getWinner(), matchModel.getWinner());
    }

    @Test
    void dtoToEntity() {
        var tempEntity = mapper.toEntity(matchModel);

        assertEquals(tempEntity.getPlayer1(), matchModel.getPlayer1());
        assertEquals(tempEntity.getPlayer2(), matchModel.getPlayer2());
        assertEquals(tempEntity.getWinner(), matchModel.getWinner());
    }

    @Test
    void dtoToScore() {
        MatchScoreDTO matchScore = mapper.toScoreDto(matchModel);

        assertEquals(matchModel.getPlayer1().getName(), matchScore.getPlayer1());
        assertEquals(matchModel.getPlayer2().getName(), matchScore.getPlayer2());
        assertEquals(String.valueOf(matchModel.getPlayer1Set()), matchScore.getPlayer1Set());
        assertEquals(String.valueOf(matchModel.getPlayer1Set()), matchScore.getPlayer1Set());
        assertEquals(String.valueOf(matchModel.getPlayer1Game()), matchScore.getPlayer1Game());
        assertEquals(String.valueOf(matchModel.getPlayer2Game()), matchScore.getPlayer2Game());
        assertEquals(getPlayerPointString(matchModel, 1), matchScore.getPlayer1Point());
        assertEquals(getPlayerPointString(matchModel, 2), matchScore.getPlayer2Point());
        assertEquals(matchModel.getUuid().toString(), matchScore.getUuid());

        if (matchModel.getWinner() == null) {
            assertEquals("", matchScore.getWinner());
        } else {
            assertEquals(matchModel.getWinner().getName(), matchScore.getWinner());
        }


    }


}
