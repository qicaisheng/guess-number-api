package com.twschool.practice.repository;

import com.twschool.practice.domain.GameRecord;
import com.twschool.practice.domain.GameStatus;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MemoryGameFinalRecordRepositoryTest {

    @Test
    public void should_create_record() {
        MemoryGameFinalRecordRepository memoryGameFinalRecordRepository = new MemoryGameFinalRecordRepository();
        GameRecord expectedGameRecord = new GameRecord("userId1", GameStatus.SUCCEED);

        memoryGameFinalRecordRepository.create(expectedGameRecord);

        List<GameRecord> gameRecords = memoryGameFinalRecordRepository.findBy("userId1");

        Assert.assertEquals(1, gameRecords.size());
        Assert.assertEquals(expectedGameRecord, gameRecords.get(0));
    }

    @Test
    public void should_find_records_by_id() {
        MemoryGameFinalRecordRepository memoryGameFinalRecordRepository = new MemoryGameFinalRecordRepository();
        GameRecord expectedGameRecord = new GameRecord("userId1", GameStatus.SUCCEED);

        memoryGameFinalRecordRepository.create(new GameRecord("userId1", GameStatus.SUCCEED));
        memoryGameFinalRecordRepository.create(new GameRecord("userId1", GameStatus.FAILED));
        memoryGameFinalRecordRepository.create(new GameRecord("userId2", GameStatus.SUCCEED));

        List<GameRecord> gameRecords = memoryGameFinalRecordRepository.findBy("userId1");

        Assert.assertEquals(2, gameRecords.size());
        Assert.assertEquals(GameStatus.SUCCEED, gameRecords.get(0).getGameStatus());
        Assert.assertEquals(GameStatus.FAILED, gameRecords.get(1).getGameStatus());
    }
}
