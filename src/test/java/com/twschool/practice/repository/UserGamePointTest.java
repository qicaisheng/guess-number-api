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

}