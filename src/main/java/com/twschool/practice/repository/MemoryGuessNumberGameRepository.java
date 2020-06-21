package com.twschool.practice.repository;

import com.twschool.practice.domain.AnswerGenerator;
import com.twschool.practice.domain.GuessNumberGame;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryGuessNumberGameRepository implements GuessNumberGameRepository {
    
    private final Map<String, GuessNumberGame> guessNumberGameMap = new HashMap<>();
    
    @Override
    public GuessNumberGame createBy(String userId) {
        GuessNumberGame guessNumberGame = new GuessNumberGame(new AnswerGenerator());
        guessNumberGameMap.put(userId, guessNumberGame);
        return guessNumberGame;
    }
    
    @Override
    public GuessNumberGame findBy(String userId) {
        return guessNumberGameMap.get(userId);
    }

    @Override
    public void deleteBy(String userId) {
        guessNumberGameMap.remove(userId);
    }
}
