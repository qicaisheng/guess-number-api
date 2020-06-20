package com.twschool.practice.service;

import com.twschool.practice.repository.GuessNumberGameRepository;
import org.springframework.stereotype.Service;

@Service
public class GuessNumberGameService {
    
    private GuessNumberGameRepository guessNumberGameRepository;

    public GuessNumberGameService(GuessNumberGameRepository guessNumberGameRepository) {
        this.guessNumberGameRepository = guessNumberGameRepository;
    }

    public String guess(String userAnswer) {
        return guessNumberGameRepository.find().guess(userAnswer);
    }
}
