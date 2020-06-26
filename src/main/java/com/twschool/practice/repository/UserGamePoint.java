package com.twschool.practice.repository;

public class UserGamePoint {
    private final String userId;
    private final int point;
    private final int continuousSucceedTimes;

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
}
