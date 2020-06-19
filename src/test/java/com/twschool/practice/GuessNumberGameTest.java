package com.twschool.practice;

import org.junit.Assert;
import org.junit.Test;

public class GuessNumberGameTest {
    @Test
    public void should_return_4A0B_when_input_1234_given_game_with_answer_1234() {
        GameAnswer gameAnswer = new GameAnswer("1 2 3 4");
        GuessNumberGame guessNumberGame = new GuessNumberGame(gameAnswer);
        
        String result = guessNumberGame.guess("1 2 3 4");

        Assert.assertEquals("4A0B", result);
    }

    @Test
    public void should_return_2A0B_when_input_1256_given_game_with_answer_1234() {
        GameAnswer gameAnswer = new GameAnswer("1 2 3 4");
        GuessNumberGame guessNumberGame = new GuessNumberGame(gameAnswer);

        String result = guessNumberGame.guess("1 2 5 6");

        Assert.assertEquals("2A0B", result);
    }

    @Test
    public void should_return_succeed_when_get_status_after_input_1234_given_game_with_answer_1234() {
        GameAnswer gameAnswer = new GameAnswer("1 2 3 4");
        GuessNumberGame guessNumberGame = new GuessNumberGame(gameAnswer);
        guessNumberGame.guess("1 2 3 4");

        GameStatus gameStatus = guessNumberGame.getStatus();

        Assert.assertEquals(GameStatus.SUCCEED, gameStatus);
    }

    @Test
    public void should_return_continued_when_get_status_after_input_1256_given_game_with_answer_1234() {
        GameAnswer gameAnswer = new GameAnswer("1 2 3 4");
        GuessNumberGame guessNumberGame = new GuessNumberGame(gameAnswer);
        guessNumberGame.guess("1 2 5 6");

        GameStatus gameStatus = guessNumberGame.getStatus();

        Assert.assertEquals(GameStatus.CONTINUED, gameStatus);
    }

}
