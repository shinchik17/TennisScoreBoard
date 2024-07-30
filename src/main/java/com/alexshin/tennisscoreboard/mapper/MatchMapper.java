package com.alexshin.tennisscoreboard.mapper;

import com.alexshin.tennisscoreboard.model.MatchScoreModel;
import com.alexshin.tennisscoreboard.model.dto.MatchDTO;
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

        Converter<MatchDTO, MatchScoreModel> toScoreConverter = context -> {
            MatchDTO source = context.getSource();
            String winner;
            if (source.getWinner() == null) {
                winner = "";
            } else {
                winner = source.getWinner().getName();
            }

            return new MatchScoreModel(
                    source.getPlayer1().getName(),
                    source.getPlayer2().getName(),
                    String.valueOf(source.getPlayer1Set()),
                    String.valueOf(source.getPlayer2Set()),
                    String.valueOf(source.getPlayer1Game()),
                    String.valueOf(source.getPlayer2Game()),
                    getPlayerPointString(source, 1),
                    getPlayerPointString(source, 2),
                    winner,
                    source.getUuid().toString()
                    );
        };

        TypeMap<MatchDTO, MatchScoreModel> dtoToScoreMap = mapper.createTypeMap(MatchDTO.class, MatchScoreModel.class);
        dtoToScoreMap.setConverter(toScoreConverter);

    }

    public MatchScoreModel toScoreModel(MatchDTO match) {
        return mapper.map(match, MatchScoreModel.class);
    }


    public MatchDTO toDTO(Match matchEntity) {
        return mapper.map(matchEntity, MatchDTO.class);
    }

    public Match toEntity(MatchDTO match) {
        return mapper.map(match, Match.class);
    }

}


