package com.twschool.practice.repository;

import com.twschool.practice.domain.GameStatus;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserGamePointTest {

    @Test
    public void should_add_three_point_when_receive_new_game_succeed_status_given_1_continuous_succeed_times() {
        UserGamePoint userGamePoint = new UserGamePoint("userId1", 3, 1);
        
        userGamePoint.receive(GameStatus.SUCCEED);

        Assert.assertEquals(3 + 3, userGamePoint.getPoint());
    }

    @Test
    public void should_deduct_three_point_when_receive_new_game_failed_status_given_1_continuous_succeed_times() {
        UserGamePoint userGamePoint = new UserGamePoint("userId1", 3, 1);

        userGamePoint.receive(GameStatus.FAILED);

        Assert.assertEquals(3 - 3, userGamePoint.getPoint());
    }

    @Test
    public void should_set_continuous_succeed_times_0_when_receive_new_game_failed_status_given_1_continuous_succeed_times() {
        UserGamePoint userGamePoint = new UserGamePoint("userId1", 3, 1);

        userGamePoint.receive(GameStatus.FAILED);

        Assert.assertEquals(0, userGamePoint.getContinuousSucceedTimes());
    }

    @Test
    public void should_add_continuous_succeed_times_by_1_when_receive_new_game_succeed_status_given_1_continuous_succeed_times() {
        UserGamePoint userGamePoint = new UserGamePoint("userId1", 3, 1);

        userGamePoint.receive(GameStatus.SUCCEED);

        Assert.assertEquals(1 + 1, userGamePoint.getContinuousSucceedTimes());
    }

    @Test
    public void should_add_five_point_when_receive_new_game_succeed_status_given_2_continuous_succeed_times() {
        UserGamePoint userGamePoint = new UserGamePoint("userId1", 6, 2);

        userGamePoint.receive(GameStatus.SUCCEED);

        Assert.assertEquals(6 + 3 + 2, userGamePoint.getPoint());
    }

    @Test
    public void should_add_six_point_when_receive_new_game_succeed_status_given_4_continuous_succeed_times() {
        UserGamePoint userGamePoint = new UserGamePoint("userId1", 14, 4);

        userGamePoint.receive(GameStatus.SUCCEED);

        Assert.assertEquals(14 + 3 + 3, userGamePoint.getPoint());
    }

    @Test
    public void should_add_five_point_when_receive_new_game_succeed_status_given_5_continuous_succeed_times() {
        UserGamePoint userGamePoint = new UserGamePoint("userId1", 20, 5);

        userGamePoint.receive(GameStatus.SUCCEED);

        Assert.assertEquals(20 + 3 + 2, userGamePoint.getPoint());
    }

    @Test
    public void should_add_six_point_when_receive_new_game_succeed_status_given_9_continuous_succeed_times() {
        UserGamePoint userGamePoint = new UserGamePoint("userId1", 36, 9);

        userGamePoint.receive(GameStatus.SUCCEED);

        Assert.assertEquals(36 + 3 + 3, userGamePoint.getPoint());
    }

}