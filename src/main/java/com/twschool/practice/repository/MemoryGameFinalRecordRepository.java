package com.twschool.practice.repository;

import com.twschool.practice.domain.GameRecord;

public class MemoryGameFinalRecordRepository {
    private GameRecord gameRecord;

    public void create(GameRecord gameRecord) {

        this.gameRecord = gameRecord;
    }

    public GameRecord findBy(String userId) {
        return gameRecord;
    }
}
