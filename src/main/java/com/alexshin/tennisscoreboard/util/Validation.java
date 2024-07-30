package com.alexshin.tennisscoreboard.util;


public class Validation {

    private final static String NAME_VALIDATE_PATTERN = "[a-zA-zА-Яа-я ]";
    private final static String uuidSubPattern = "[0-9a-fA-F]";
    private final static String UUID_VALIDATE_PATTERN = String.format("%1$s{8}-%1$s{4}-%1$s{4}-%1$s{4}-%1$s{12}", uuidSubPattern);
    private final static String PLAYER_NUM_VALIDATE_PATTERN = "[1|2]";
    private final static String PAGE_NUM_VALIDATE_PATTERN = "\\d+";

    private Validation(){}

    public static boolean isValidPlayerNameStr(String playerName){
        return playerName.matches(NAME_VALIDATE_PATTERN);
    }

    public static boolean isValidUuidStr(String uuid){
        return uuid.matches(UUID_VALIDATE_PATTERN);
    }

    public static boolean isValidPlayerNumStr(String playerNum){
        return playerNum.matches(PLAYER_NUM_VALIDATE_PATTERN);
    }

    public static boolean isValidPageNumStr(String pageNum){
        return pageNum.matches(PAGE_NUM_VALIDATE_PATTERN);
    }

    public static boolean isValidPlayerFilter(String playerName){
        return playerName.matches(NAME_VALIDATE_PATTERN);
    }


    public static boolean isValidPlayerName(String playerName) {
        return playerName != null && isValidPlayerNameStr(playerName);
    }

    public static boolean isValidUuid(String uuid) {
        return uuid != null && isValidUuidStr(uuid);
    }

    public static boolean isValidPlayerNum(String playerNum) {
        return playerNum != null && isValidPlayerNumStr(playerNum);
    }


}
