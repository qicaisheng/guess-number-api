package com.twschool.practice.domain;

public class GameFinalRecord {
    private String userId;
    private GameStatus gameStatus;

    public GameFinalRecord(String userId, GameStatus gameStatus) {

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
