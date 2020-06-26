package com.twschool.practice.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryUserGamePointRepository implements UserGamePointRepository {
    private final List<UserGamePoint> userGamePoints = new ArrayList<>();
    
    @Override
    public void create(UserGamePoint userGamePoint) {
        userGamePoints.add(userGamePoint);
    }

    @Override
    public UserGamePoint findLatestBy(String userId) {
        return userGamePoints.stream()
                .filter(userGamePoint -> userGamePoint.getUserId().equals(userId))
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .next();
    }
}