package com.twschool.practice.repository;

import com.twschool.practice.domain.AnswerGenerator;
import com.twschool.practice.domain.GuessNumberGame;

public class MemoryGuessNumberGameRepository {
    
    private GuessNumberGame guessNumberGame;
    
    public GuessNumberGame create() {
        guessNumberGame = new GuessNumberGame(new AnswerGenerator());
        return guessNumberGame;
    }

    public GuessNumberGame find() {
        return guessNumberGame;
    }
}
