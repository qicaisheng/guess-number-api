package com.twschool.practice.repository;

import com.twschool.practice.domain.GameRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryGameFinalRecordRepository {
    private List<GameRecord> gameRecords = new ArrayList<>();

    public void create(GameRecord gameRecord) {
        gameRecords.add(gameRecord);
    }

    public List<GameRecord> findBy(String userId) {
        return gameRecords.stream()
                .filter(gameRecord -> gameRecord.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
