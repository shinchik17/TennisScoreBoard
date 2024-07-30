package com.alexshin.tennisscoreboard.util;

import com.alexshin.tennisscoreboard.exception.IllegalPlayerNameException;
import com.alexshin.tennisscoreboard.exception.IllegalPlayerNumException;
import com.alexshin.tennisscoreboard.exception.IllegalUuidFormatException;

import java.util.UUID;

import static com.alexshin.tennisscoreboard.util.Validation.*;

public class ParseParams {

    public static String parsePlayerName(String playerName) {
        if (isValidPlayerName(playerName)) {
            return playerName;
        }
        else {
            throw new IllegalPlayerNameException(playerName);
        }
    }

    public static UUID parseUUID(String uuid) {
        if (isValidUuid(uuid)) {
            return UUID.fromString(uuid);
        }
        else {
            throw new IllegalUuidFormatException(uuid);
        }
    }


    public static int parsePlayerNum(String playerNum) {
        if (isValidUuid(playerNum)) {
            return Integer.parseInt(playerNum);
        }
        else {
            throw new IllegalPlayerNumException(playerNum);
        }
    }

}
