package com.twschool.practice;

public class GuessNumberGame {
    public static final int MAX_TRY_TIMES = 6;
    private GameAnswer gameAnswer;
    private GameStatus gameStatus;
    private int leftTryTimes = MAX_TRY_TIMES;

    public GuessNumberGame(GameAnswer gameAnswer) {
        this.gameAnswer = gameAnswer;
    }

    public String guess(String userAnswerString) {
        String result = gameAnswer.check(userAnswerString);
        leftTryTimes --;
        if ("4A0B".equals(result)) {
            gameStatus = GameStatus.SUCCEED;
        } else if (leftTryTimes == 0) {
            gameStatus = GameStatus.FAILED;
        } else {
            gameStatus = GameStatus.CONTINUED;
        }
        return result;
    }

    public GameStatus getStatus() {
        return gameStatus;
    }
}
