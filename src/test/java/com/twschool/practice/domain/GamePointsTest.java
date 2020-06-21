package com.twschool.practice.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GamePointsTest {

    @Test
    public void should_calculate_game_points_3_given_user_game_final_records_3_continuous_succeed() {
        List<GameRecord> gameRecords = Arrays.asList(new GameRecord("1", GameStatus.SUCCEED),
                new GameRecord("1", GameStatus.SUCCEED),
                new GameRecord("1", GameStatus.SUCCEED));
        GamePoints gamePoints = new GamePoints(gameRecords);

        Assert.assertEquals(3, gamePoints.totalPoints());
    }

    @Test
    public void should_calculate_game_points_0_given_user_game_final_records_3_break_succeed() {
        List<GameRecord> gameRecords = Arrays.asList(new GameRecord("1", GameStatus.SUCCEED),
                new GameRecord("1", GameStatus.FAILED),
                new GameRecord("1", GameStatus.SUCCEED),
                new GameRecord("1", GameStatus.SUCCEED));
        GamePoints gamePoints = new GamePoints(gameRecords);

        Assert.assertEquals(0, gamePoints.totalPoints());
    }

}
