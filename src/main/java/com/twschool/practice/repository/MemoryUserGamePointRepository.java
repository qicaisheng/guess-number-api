package com.twschool.practice.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MemoryUserGamePointRepository implements UserGamePointRepository {
    private final List<UserGamePoint> userGamePoints = new ArrayList<>();
    
    @Override
    public void create(UserGamePoint userGamePoint) {
        userGamePoints.add(userGamePoint);
    }

    @Override
    public UserGamePoint findLatestBy(String userId) {
        Iterator<UserGamePoint> userGamePointIterator = userGamePoints.stream()
                .filter(userGamePoint -> userGamePoint.getUserId().equals(userId))
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator();
        return userGamePointIterator.hasNext() ? userGamePointIterator.next() : new UserGamePoint(userId, 0, 0);
    }
}
