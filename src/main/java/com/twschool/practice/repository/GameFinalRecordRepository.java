package com.twschool.practice.repository;

import com.twschool.practice.domain.GameFinalRecord;

import java.util.List;

public interface GameFinalRecordRepository {
    void create(GameFinalRecord gameFinalRecord);

    List<GameFinalRecord> findBy(String userId);
}
