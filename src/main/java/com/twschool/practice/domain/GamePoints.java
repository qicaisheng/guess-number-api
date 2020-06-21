package com.twschool.practice.domain;

import java.util.ArrayList;
import java.util.List;

public class GamePoints {
    private List<GameFinalRecord> gameFinalRecords;

    public GamePoints(List<GameFinalRecord> gameFinalRecords) {
        this.gameFinalRecords = gameFinalRecords;
    }

    public int totalPoints() {
        if (gameFinalRecords == null || gameFinalRecords.size() == 0) {
            return 0;
        }
        int continuousFiveTimesSucceedCount = getSumOf(5);
        int continuousThreeTimesSucceedCount = getSumOf(3);
        return continuousThreeTimesSucceedCount * 3 + continuousFiveTimesSucceedCount * 2;
    }

    private List<Integer> getContinuousSucceedCountList() {
        List<Integer> continuousSucceedCountList = new ArrayList<>();
        boolean firstRecordSucceed = gameFinalRecords.get(0).getGameStatus() == GameStatus.SUCCEED;
        continuousSucceedCountList.add(firstRecordSucceed ? 1 : 0);

        for (int index = 1; index < gameFinalRecords.size(); index++) {
            if (gameFinalRecords.get(index).getGameStatus() == GameStatus.FAILED) {
                continuousSucceedCountList.add(0);
            }
            if (gameFinalRecords.get(index).getGameStatus() == GameStatus.SUCCEED) {
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
