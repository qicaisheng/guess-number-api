package com.twschool.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GameAnswer {
    private final List<String> answerNumbers;

    public GameAnswer(String answerString) {
        this.answerNumbers = Arrays.asList(answerString.split(" "));
    }

    public String check(String userAnswerString) {
        List<String> userAnswerNumbers = Arrays.asList(userAnswerString.split(" "));
        int valueAndPositionCorrectCount = 0;
        int valueIncorrectAndPositionCorrectCount = 0;
        for (int index = 0; index < answerNumbers.size(); index++) {
            if (answerNumbers.get(index).equals(userAnswerNumbers.get(index))) {
                valueAndPositionCorrectCount ++;
            } else if (answerNumbers.contains(userAnswerNumbers.get(index))) {
                valueIncorrectAndPositionCorrectCount ++;
            }
        }
        return valueAndPositionCorrectCount + "A" + valueIncorrectAndPositionCorrectCount + "B";
    }

    public boolean isValidFormat() {
        Set<String> answerNumberSet = answerNumbers.stream().collect(Collectors.toSet());
        if (answerNumberSet.size() != 4) {
            return false;
        }
        return true;
    }
}
