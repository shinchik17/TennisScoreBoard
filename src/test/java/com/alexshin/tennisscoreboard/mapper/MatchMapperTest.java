package com.alexshin.tennisscoreboard.mapper;

import com.alexshin.tennisscoreboard.model.MatchScoreModel;
import com.alexshin.tennisscoreboard.model.dto.MatchDTO;
import com.alexshin.tennisscoreboard.model.entity.Match;
import com.alexshin.tennisscoreboard.model.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.alexshin.tennisscoreboard.service.MatchScoreCalculationService.getPlayerPointString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchMapperTest {

    Match matchEntity;
    MatchDTO matchDTO;
    MatchMapper mapper = new MatchMapper();
    Player player1 = new Player(1, "Novak Djokovic_");
    Player player2 = new Player(2, "Daniil Medvedev_");

    @BeforeEach
    void prepare() {
        matchEntity = new Match();
        matchEntity.setPlayer1(player1);
        matchEntity.setPlayer2(player2);
        matchEntity.setWinner(player1);

        matchDTO = new MatchDTO(player1, player2);
        matchDTO.setUuid(UUID.randomUUID());
        matchDTO.setPlayerSet(1, 1);
        matchDTO.setPlayerGame(1, 2);
        matchDTO.setPlayerGame(2, 4);
        matchDTO.setPlayerGame(1, 30);
        matchDTO.setPlayerPoint(1, 41);
        matchDTO.setPlayerPoint(2, 40);
        matchDTO.setWinnerByNum(1);

    }

    @Test
    void entityToDTO() {
        MatchDTO matchDTO = mapper.toDTO(matchEntity);
        assertEquals(matchEntity.getPlayer1().getName(), matchDTO.getPlayer1().getName());
        assertEquals(matchEntity.getPlayer2().getName(), matchDTO.getPlayer2().getName());
        assertEquals(matchEntity.getWinner().getName(), matchDTO.getWinner().getName());
    }

    @Test
    void dtoToEntity() {
        var tempEntity = mapper.toEntity(matchDTO);

        assertEquals(tempEntity.getPlayer1().getName(), matchDTO.getPlayer1().getName());
        assertEquals(tempEntity.getPlayer2().getName(), matchDTO.getPlayer2().getName());
        assertEquals(tempEntity.getWinner().getName(), matchDTO.getWinner().getName());
    }

    @Test
    void dtoToScore() {
        MatchScoreModel matchScore = mapper.toScoreModel(matchDTO);

        assertEquals(matchDTO.getPlayer1().getName(), matchScore.getPlayer1());
        assertEquals(matchDTO.getPlayer2().getName(), matchScore.getPlayer2());
        assertEquals(matchDTO.getWinner().getName(), matchScore.getWinner());
        assertEquals(String.valueOf(matchDTO.getPlayer1Set()), matchScore.getPlayer1Set());
        assertEquals(String.valueOf(matchDTO.getPlayer1Set()), matchScore.getPlayer1Set());
        assertEquals(String.valueOf(matchDTO.getPlayer1Game()), matchScore.getPlayer1Game());
        assertEquals(String.valueOf(matchDTO.getPlayer2Game()), matchScore.getPlayer2Game());
        assertEquals(getPlayerPointString(matchDTO, 1), matchScore.getPlayer1Point());
        assertEquals(getPlayerPointString(matchDTO, 2), matchScore.getPlayer2Point());
        assertEquals(matchDTO.getUuid().toString(), matchScore.getUuid());



    }


}
