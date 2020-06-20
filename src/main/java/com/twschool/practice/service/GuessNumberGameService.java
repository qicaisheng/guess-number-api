package com.twschool.practice.service;

import com.twschool.practice.repository.MemoryGuessNumberGameRepository;

public class GuessNumberGameService {
    
    private MemoryGuessNumberGameRepository memoryGuessNumberGameRepository;

    public GuessNumberGameService(MemoryGuessNumberGameRepository memoryGuessNumberGameRepository) {
        this.memoryGuessNumberGameRepository = memoryGuessNumberGameRepository;
    }

    public String guess(String userAnswer) {
        return memoryGuessNumberGameRepository.find().guess(userAnswer);
    }
}
