package com.alexshin.tennisscoreboard.util;

import org.junit.jupiter.api.Test;
import static com.alexshin.tennisscoreboard.util.ParseParams.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParamParseTest {
    String EMPTY_NAME = "";
    String ILLEGAL_NAME = "Player1";
    String LEGAL_NAME = "Name Surname Smth";

    String EMPTY_NUM = "";
    String ILLEGAL_NUM = "3";
    String LEGAL_NUM = "1";
    // TODO: доделать
    @Test
    void parsePlayerName(){
        assertThrows(IllegalArgumentException.class, () -> parsePlayerNum(EMPTY_NUM));
        assertThrows(IllegalArgumentException.class, () -> parsePlayerNum(ILLEGAL_NUM));
        assertDoesNotThrow(() -> parsePlayerNum(LEGAL_NUM));
    }



}
