package com.twschool.practice.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePoints {
    private List<GameRecord> gameRecords;

    public GamePoints(List<GameRecord> gameRecords) {
        this.gameRecords = gameRecords;
    }

    public int totalPoints() {
        int continuousMultipleFiveTimesSucceedCount = getContinuousSucceedCount().stream()
                .map(count -> count / 5)
                .mapToInt(Integer::intValue)
                .sum();
        int continuousMultipleThreeTimesSucceedCount = getContinuousSucceedCount().stream()
                .map(count -> count / 3)
                .mapToInt(Integer::intValue)
                .sum();
        return continuousMultipleThreeTimesSucceedCount * 3 + continuousMultipleFiveTimesSucceedCount * 2;
    }

    private List<Integer> getContinuousSucceedCount() {
        Map<Integer, Integer> continuousSucceedCountMap = new HashMap<>();
        boolean firstRecordSucceed = gameRecords.get(0).getGameStatus() == GameStatus.SUCCEED;
        continuousSucceedCountMap.put(1, firstRecordSucceed ? 1 : 0);

        for (int index = 1; index < gameRecords.size(); index++) {
            if (gameRecords.get(index -1).getGameStatus() != GameStatus.SUCCEED) {
                continuousSucceedCountMap.put(continuousSucceedCountMap.size() + 1, 0);
            }
            if (gameRecords.get(index).getGameStatus() == GameStatus.SUCCEED) {
                int lastSucceedCount = continuousSucceedCountMap.get(continuousSucceedCountMap.size());
                continuousSucceedCountMap.put(continuousSucceedCountMap.size(), lastSucceedCount + 1);
            }
        }
        return new ArrayList<>(continuousSucceedCountMap.values());
    }

}
