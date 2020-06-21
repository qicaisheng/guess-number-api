package com.twschool.practice.repository;

import com.twschool.practice.domain.GameRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MemoryGameFinalRecordRepository implements GameFinalRecordRepository {
    private final List<GameRecord> gameRecords = new ArrayList<>();

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
