package com.twschool.practice.domain;

public class GameRecord {
    private final String userId;
    private final GameStatus gameStatus;

    public GameRecord(String userId, GameStatus gameStatus) {

        this.userId = userId;
        this.gameStatus = gameStatus;
    }
}
