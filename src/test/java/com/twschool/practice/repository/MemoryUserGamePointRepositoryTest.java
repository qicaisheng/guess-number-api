package com.twschool.practice.repository;

import org.junit.Assert;
import org.junit.Test;

public class MemoryUserGamePointRepositoryTest {

    @Test
    public void should_create_user_game_point() {
        
        UserGamePointRepository userGamePointRepository = new MemoryUserGamePointRepository();

        UserGamePoint expectedUserGamePoint = new UserGamePoint("userId1", 11, 3);
        userGamePointRepository.create(expectedUserGamePoint);
        
        UserGamePoint userGamePoint = userGamePointRepository.findLatestBy("userId1");
        Assert.assertNotNull(userGamePoint);
        Assert.assertEquals(expectedUserGamePoint, userGamePoint);
    }

    @Test
    public void should_find_latest_user_game_point() {

        UserGamePointRepository userGamePointRepository = new MemoryUserGamePointRepository();

        userGamePointRepository.create(new UserGamePoint("userId1", 11, 3));
        UserGamePoint expectedUserGamePoint = new UserGamePoint("userId1", 8, 0);
        userGamePointRepository.create(expectedUserGamePoint);
        userGamePointRepository.create(new UserGamePoint("userId2", 11, 3));

        UserGamePoint userGamePoint = userGamePointRepository.findLatestBy("userId1");
        Assert.assertNotNull(userGamePoint);
        Assert.assertEquals(expectedUserGamePoint, userGamePoint);
    }
}
