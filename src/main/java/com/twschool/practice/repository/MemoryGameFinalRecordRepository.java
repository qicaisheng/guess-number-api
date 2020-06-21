package com.twschool.practice.repository;

import com.twschool.practice.domain.GameFinalRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MemoryGameFinalRecordRepository implements GameFinalRecordRepository {
    private final List<GameFinalRecord> gameFinalRecords = new ArrayList<>();

    @Override
    public void create(GameFinalRecord gameFinalRecord) {
        gameFinalRecords.add(gameFinalRecord);
    }

    @Override
    public List<GameFinalRecord> findBy(String userId) {
        return gameFinalRecords.stream()
                .filter(gameRecord -> gameRecord.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
