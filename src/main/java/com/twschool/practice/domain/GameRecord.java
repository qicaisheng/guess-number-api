package com.twschool.practice.domain;

public class GameRecord {
    private String userId;
    private GameStatus gameStatus;

    public GameRecord(String userId, GameStatus gameStatus) {

        this.userId = userId;
        this.gameStatus = gameStatus;
    }

    public String getUserId() {
        return userId;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
