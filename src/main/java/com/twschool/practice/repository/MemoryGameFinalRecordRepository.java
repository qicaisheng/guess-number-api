package com.twschool.practice.repository;

import com.twschool.practice.domain.GameRecord;

import java.util.HashMap;
import java.util.Map;

public class MemoryGameFinalRecordRepository {
    private Map<String, GameRecord> gameRecordMap = new HashMap<>();

    public void create(GameRecord gameRecord) {
        gameRecordMap.put(gameRecord.getUserId(), gameRecord);
    }

    public GameRecord findBy(String userId) {
        return gameRecordMap.get(userId);
    }
}
