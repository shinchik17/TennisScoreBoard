package com.alexshin.tennisscoreboard.util;

import com.alexshin.tennisscoreboard.exception.parsing.*;

import java.util.Optional;
import java.util.UUID;

import static com.alexshin.tennisscoreboard.util.Validation.*;

public class ParseParams {
    private static final int DEFAULT_PAGE = 1;

    private ParseParams(){}

    public static String parsePlayerName(String playerName) {
        if (isValidPlayerName(playerName)) {
            return playerName.trim();
        } else {
            throw new IllegalPlayerNameException(playerName);
        }
    }

    public static UUID parseUUID(String uuid) {
        if (isValidUuid(uuid)) {
            return UUID.fromString(uuid);
        } else {
            throw new IllegalUuidFormatException(uuid);
        }
    }


    public static int parsePlayerNum(String playerNum) {
        if (isValidPlayerNum(playerNum)) {
            return Integer.parseInt(playerNum);
        } else {
            throw new IllegalPlayerNumException(playerNum);
        }
    }

    public static int parsePageNum(String pageNum) {

        if (pageNum != null) {
            if (isValidPageNumStr(pageNum)) {
                return Math.max(Integer.parseInt(pageNum), DEFAULT_PAGE);
            } else {
                throw new IllegalPageNumException(pageNum);
            }
        }
        return DEFAULT_PAGE;

    }

    public static Optional<String> parsePlayerFilter(String playerName) {

        if (playerName != null) {
            if (isValidPlayerFilter(playerName)) {
                return Optional.of(playerName.trim());
            } else if (playerName.isEmpty() || playerName.isBlank()) {
                return Optional.empty();
            }
            else {
                throw new IllegalPlayerFilterException(playerName);
            }
        }
        return Optional.empty();
    }



}
