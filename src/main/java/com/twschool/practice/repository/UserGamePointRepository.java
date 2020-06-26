package com.twschool.practice.repository;

public interface UserGamePointRepository {
    void create(UserGamePoint userGamePoint);

    UserGamePoint findLatestBy(String userId);
}
