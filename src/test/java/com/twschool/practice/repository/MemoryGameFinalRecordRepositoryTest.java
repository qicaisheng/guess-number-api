package com.twschool.practice.repository;

import com.twschool.practice.domain.GameFinalRecord;
import com.twschool.practice.domain.GameStatus;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MemoryGameFinalRecordRepositoryTest {

    @Test
    public void should_create_record() {
        MemoryGameFinalRecordRepository memoryGameFinalRecordRepository = new MemoryGameFinalRecordRepository();
        GameFinalRecord expectedGameFinalRecord = new GameFinalRecord("userId1", GameStatus.SUCCEED);

        memoryGameFinalRecordRepository.create(expectedGameFinalRecord);

        List<GameFinalRecord> gameFinalRecords = memoryGameFinalRecordRepository.findBy("userId1");

        Assert.assertEquals(1, gameFinalRecords.size());
        Assert.assertEquals(expectedGameFinalRecord, gameFinalRecords.get(0));
    }

    @Test
    public void should_find_records_by_id() {
        MemoryGameFinalRecordRepository memoryGameFinalRecordRepository = new MemoryGameFinalRecordRepository();
        GameFinalRecord expectedGameFinalRecord = new GameFinalRecord("userId1", GameStatus.SUCCEED);

        memoryGameFinalRecordRepository.create(new GameFinalRecord("userId1", GameStatus.SUCCEED));
        memoryGameFinalRecordRepository.create(new GameFinalRecord("userId1", GameStatus.FAILED));
        memoryGameFinalRecordRepository.create(new GameFinalRecord("userId2", GameStatus.SUCCEED));

        List<GameFinalRecord> gameFinalRecords = memoryGameFinalRecordRepository.findBy("userId1");

        Assert.assertEquals(2, gameFinalRecords.size());
        Assert.assertEquals(GameStatus.SUCCEED, gameFinalRecords.get(0).getGameStatus());
        Assert.assertEquals(GameStatus.FAILED, gameFinalRecords.get(1).getGameStatus());
    }
}
