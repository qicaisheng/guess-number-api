package com.twschool.practice.repository;

import com.twschool.practice.domain.GameRecord;
import com.twschool.practice.domain.GameStatus;
import org.junit.Assert;
import org.junit.Test;

public class MemoryGuessNumberGameRecordRepositoryTest {

    @Test
    public void should_create_record() {
        MemoryGameFinalRecordRepository memoryGameFinalRecordRepository = new MemoryGameFinalRecordRepository();
        GameRecord expectedGameRecord = new GameRecord("userId1", GameStatus.SUCCEED);

        memoryGameFinalRecordRepository.create(expectedGameRecord);

        GameRecord gameRecord = memoryGameFinalRecordRepository.findBy("userId1");

        Assert.assertEquals(expectedGameRecord, gameRecord);
    }

    @Test
    public void should_find_record_by_id() {
        MemoryGameFinalRecordRepository memoryGameFinalRecordRepository = new MemoryGameFinalRecordRepository();
        GameRecord expectedGameRecord = new GameRecord("userId1", GameStatus.SUCCEED);

        memoryGameFinalRecordRepository.create(expectedGameRecord);
        memoryGameFinalRecordRepository.create(new GameRecord("userId2", GameStatus.SUCCEED));

        GameRecord gameRecord = memoryGameFinalRecordRepository.findBy("userId1");

        Assert.assertEquals(expectedGameRecord, gameRecord);
    }
}
