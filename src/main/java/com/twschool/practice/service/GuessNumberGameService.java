package com.twschool.practice.service;

import com.twschool.practice.GuessNumberGameRepository;
import com.twschool.practice.repository.MemoryGuessNumberGameRepository;

public class GuessNumberGameService {
    
    private GuessNumberGameRepository guessNumberGameRepository;

    public GuessNumberGameService(MemoryGuessNumberGameRepository guessNumberGameRepository) {
        this.guessNumberGameRepository = guessNumberGameRepository;
    }

    public String guess(String userAnswer) {
        return guessNumberGameRepository.find().guess(userAnswer);
    }
}
