package com.twschool.practice.repository;

import com.twschool.practice.domain.AnswerGenerator;
import com.twschool.practice.domain.GuessNumberGame;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryGuessNumberGameRepository implements GuessNumberGameRepository {
    
    private GuessNumberGame guessNumberGame;
    
    @Override
    public GuessNumberGame create(String userId) {
        guessNumberGame = new GuessNumberGame(new AnswerGenerator());
        return guessNumberGame;
    }
    
    @Override
    public GuessNumberGame find(String userId) {
        return guessNumberGame;
    }
}
