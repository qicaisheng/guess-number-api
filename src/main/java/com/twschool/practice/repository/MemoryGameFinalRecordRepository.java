package com.twschool.practice.repository;

import com.twschool.practice.domain.GameRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryGameFinalRecordRepository implements GameFinalRecordRepository {
    private List<GameRecord> gameRecords = new ArrayList<>();

    @Override
    public void create(GameRecord gameRecord) {
        gameRecords.add(gameRecord);
    }

    @Override
    public List<GameRecord> findBy(String userId) {
        return gameRecords.stream()
                .filter(gameRecord -> gameRecord.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
