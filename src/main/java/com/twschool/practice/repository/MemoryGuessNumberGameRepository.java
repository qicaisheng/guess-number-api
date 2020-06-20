package com.twschool.practice.repository;

import com.twschool.practice.domain.AnswerGenerator;
import com.twschool.practice.domain.GuessNumberGame;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryGuessNumberGameRepository implements GuessNumberGameRepository {
    
    private Map<String, GuessNumberGame> guessNumberGameMap = new HashMap<>();
    
    @Override
    public GuessNumberGame create(String userId) {
        GuessNumberGame guessNumberGame = new GuessNumberGame(new AnswerGenerator());
        guessNumberGameMap.put(userId, guessNumberGame);
        return guessNumberGame;
    }
    
    @Override
    public GuessNumberGame find(String userId) {
        return guessNumberGameMap.get(userId);
    }
}
