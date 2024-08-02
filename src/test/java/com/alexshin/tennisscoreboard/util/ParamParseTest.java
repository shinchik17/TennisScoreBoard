package com.alexshin.tennisscoreboard.util;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.alexshin.tennisscoreboard.util.ParseParams.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParamParseTest {
    final String EMPTY = "";

    final String LEGAL_PLAYER_NAME = "Name Surname Smth";
    final String ILLEGAL_PLAYER_NAME = "Player1";

    final String LEGAL_PLAYER_NUM = "1";
    final String ILLEGAL_PLAYER_NUM = "3";

    final String LEGAL_PAGE_NUM = "5";
    final String ILLEGAL_PAGE_NUM = "0";

    final String LEGAL_UUID = UUID.randomUUID().toString();
    final String ILLEGAL_UUID = "231231-3231";

    @Test
    void testParsePlayerName() {
        assertThrows(IllegalArgumentException.class, () -> parsePlayerName(EMPTY));
        assertThrows(IllegalArgumentException.class, () -> parsePlayerName(ILLEGAL_PLAYER_NAME));
        assertDoesNotThrow(() -> parsePlayerName(LEGAL_PLAYER_NAME));
    }

    @Test
    void testParsePlayerNum() {
        assertThrows(IllegalArgumentException.class, () -> parsePlayerNum(EMPTY));
        assertThrows(IllegalArgumentException.class, () -> parsePlayerNum(ILLEGAL_PLAYER_NUM));
        assertDoesNotThrow(() -> parsePlayerNum(LEGAL_PLAYER_NUM));
    }

    @Test
    void testParsePageNum() {
        assertThrows(IllegalArgumentException.class, () -> parsePageNum(EMPTY));
        assertDoesNotThrow(() -> parsePageNum(ILLEGAL_PAGE_NUM));
        assertDoesNotThrow(() -> parsePageNum(LEGAL_PAGE_NUM));
    }

    @Test
    void testParseUuid() {
        assertThrows(IllegalArgumentException.class, () -> parseUUID(EMPTY));
        assertThrows(IllegalArgumentException.class, () -> parseUUID(ILLEGAL_UUID));
        assertDoesNotThrow(() -> parseUUID(LEGAL_UUID));
    }

    @Test
    void testParsePlayerFilter() {
        assertThrows(IllegalArgumentException.class, () -> parsePlayerFilter(ILLEGAL_PLAYER_NAME));
        assertDoesNotThrow(() -> parsePlayerFilter(EMPTY));
        assertDoesNotThrow(() -> parsePlayerFilter(LEGAL_PLAYER_NAME));
    }


}
