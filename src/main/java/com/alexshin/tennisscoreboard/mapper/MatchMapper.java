package com.alexshin.tennisscoreboard.mapper;

import com.alexshin.tennisscoreboard.model.dto.MatchScoreDTO;
import com.alexshin.tennisscoreboard.model.MatchModel;
import com.alexshin.tennisscoreboard.model.entity.Match;
import com.alexshin.tennisscoreboard.model.entity.Player;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.Optional;

import static com.alexshin.tennisscoreboard.service.MatchScoreCalculationService.getPlayerPointString;


public class MatchMapper extends ModelMapper {
    private final ModelMapper mapper = new ModelMapper();


    public MatchMapper() {
        configureMappings();
    }

    private void configureMappings() {

        Converter<MatchModel, MatchScoreDTO> toScoreConverter = context -> {
            MatchModel source = context.getSource();
            return new MatchScoreDTO(
                    source.getPlayer1().getName(),
                    source.getPlayer2().getName(),
                    String.valueOf(source.getPlayer1Set()),
                    String.valueOf(source.getPlayer2Set()),
                    String.valueOf(source.getPlayer1Game()),
                    String.valueOf(source.getPlayer2Game()),
                    getPlayerPointString(source, 1),
                    getPlayerPointString(source, 2),
                    Optional.ofNullable(source.getWinner()).map(Player::getName).orElse(""),
                    source.getUuid().toString()
                    );
        };

        TypeMap<MatchModel, MatchScoreDTO> matchModelToScoreDtoMap = mapper.createTypeMap(MatchModel.class, MatchScoreDTO.class);
        matchModelToScoreDtoMap.setConverter(toScoreConverter);

    }

    public MatchScoreDTO toScoreDto(MatchModel match) {
        return mapper.map(match, MatchScoreDTO.class);
    }


    public MatchModel toMatchModel(Match matchEntity) {
        return mapper.map(matchEntity, MatchModel.class);
    }

    public Match toEntity(MatchModel match) {
        return mapper.map(match, Match.class);
    }

}


