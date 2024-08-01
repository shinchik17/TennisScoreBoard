package com.alexshin.tennisscoreboard.util;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.alexshin.tennisscoreboard.util.ParseParams.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParamParseTest {
    String EMPTY = "";

    String LEGAL_PLAYER_NAME = "Name Surname Smth";
    String ILLEGAL_PLAYER_NAME = "Player1";

    String LEGAL_PLAYER_NUM = "1";
    String ILLEGAL_PLAYER_NUM = "3";

    String LEGAL_PAGE_NUM = "5";
    String ILLEGAL_PAGE_NUM = "0";

    String LEGAL_UUID = UUID.randomUUID().toString();
    String ILLEGAL_UUID = "231231-3231";

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
