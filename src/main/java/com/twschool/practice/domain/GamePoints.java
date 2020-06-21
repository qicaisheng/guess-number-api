package com.twschool.practice.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GamePoints {
    private List<GameRecord> gameRecords;

    public GamePoints(List<GameRecord> gameRecords) {
        this.gameRecords = gameRecords;
    }

    public int totalPoints() {
        Map<Integer, Integer> continuousSucceedCountMap = new HashMap<>();
        boolean firstRecordSucceed = gameRecords.get(0).getGameStatus() == GameStatus.SUCCEED;
        continuousSucceedCountMap.put(1, firstRecordSucceed ? 1 : 0);
        
        for (int index = 1; index < gameRecords.size(); index++) {
            if (gameRecords.get(index -1).getGameStatus() != GameStatus.SUCCEED) {
                continuousSucceedCountMap.put(continuousSucceedCountMap.size(), 0);
            }
            if (gameRecords.get(index).getGameStatus() == GameStatus.SUCCEED) {
                continuousSucceedCountMap.put(index, continuousSucceedCountMap.get(index) + 1);
            }
        }
        List<Integer> list = continuousSucceedCountMap.values().stream().filter(count -> count > 3).collect(Collectors.toList());
        return list.size() * 3;
    }
    
}
