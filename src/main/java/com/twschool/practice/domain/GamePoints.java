package com.twschool.practice.domain;

import java.util.ArrayList;
import java.util.List;

public class GamePoints {
    private List<GameRecord> gameRecords;

    public GamePoints(List<GameRecord> gameRecords) {
        this.gameRecords = gameRecords;
    }

    public int totalPoints() {
        int continuousMultipleFiveTimesSucceedCount = getSumOf(5);
        int continuousMultipleThreeTimesSucceedCount = getSumOf(3);
        return continuousMultipleThreeTimesSucceedCount * 3 + continuousMultipleFiveTimesSucceedCount * 2;
    }

    private List<Integer> getContinuousSucceedCountList() {
        List<Integer> continuousSucceedCountList = new ArrayList<>();
        boolean firstRecordSucceed = gameRecords.get(0).getGameStatus() == GameStatus.SUCCEED;
        continuousSucceedCountList.add(firstRecordSucceed ? 1 : 0);

        for (int index = 1; index < gameRecords.size(); index++) {
            if (gameRecords.get(index -1).getGameStatus() != GameStatus.SUCCEED) {
                continuousSucceedCountList.add(0);
            }
            if (gameRecords.get(index).getGameStatus() == GameStatus.SUCCEED) {
                int lastSucceedCount = continuousSucceedCountList.get(continuousSucceedCountList.size() - 1);
                continuousSucceedCountList.set(continuousSucceedCountList.size() - 1, lastSucceedCount + 1);
            }
        }
        return continuousSucceedCountList;
    }

    private int getSumOf(int continuousSucceedTimes) {
        return getContinuousSucceedCountList().stream()
                .map(count -> count / continuousSucceedTimes)
                .mapToInt(Integer::intValue)
                .sum();
    }

}
