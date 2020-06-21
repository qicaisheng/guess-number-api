package com.twschool.practice.repository;

import com.twschool.practice.domain.GameRecord;

import java.util.List;

public interface GameFinalRecordRepository {
    void create(GameRecord gameRecord);

    List<GameRecord> findBy(String userId);
}
