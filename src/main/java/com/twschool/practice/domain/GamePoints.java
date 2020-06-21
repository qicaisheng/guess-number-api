package com.twschool.practice.domain;

import java.util.ArrayList;
import java.util.List;

public class GamePoints {
    private List<GameRecord> gameRecords;

    public GamePoints(List<GameRecord> gameRecords) {
        this.gameRecords = gameRecords;
    }

    public int totalPoints() {
        int continuousMultipleFiveTimesSucceedCount = getContinuousSucceedCountList().stream()
                .map(count -> count / 5)
                .mapToInt(Integer::intValue)
                .sum();
        int continuousMultipleThreeTimesSucceedCount = getContinuousSucceedCountList().stream()
                .map(count -> count / 3)
                .mapToInt(Integer::intValue)
                .sum();
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

}
