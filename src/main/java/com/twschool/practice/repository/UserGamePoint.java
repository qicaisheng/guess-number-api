package com.twschool.practice.repository;

import com.twschool.practice.domain.GameStatus;

public class UserGamePoint {
    private final String userId;
    private int point;
    private int continuousSucceedTimes;

    public UserGamePoint(String userId, int point, int continuousSucceedTimes) {
        this.userId = userId;
        this.point = point;
        this.continuousSucceedTimes = continuousSucceedTimes;
    }

    public String getUserId() {
        return userId;
    }

    public int getPoint() {
        return point;
    }

    public int getContinuousSucceedTimes() {
        return continuousSucceedTimes;
    }

    public void receive(GameStatus gameStatus) {
        if (gameStatus == GameStatus.SUCCEED) {
            point = point + 3;
            continuousSucceedTimes ++;
            if (continuousSucceedTimes % 3 == 0) {
                point = point + 2;
            }
            if (continuousSucceedTimes % 5 == 0) {
                point = point + 3;
            }
        }
        if (gameStatus == GameStatus.FAILED) {
            point = point - 3;
            continuousSucceedTimes = 0;
        }
    }
}
