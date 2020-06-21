package com.twschool.practice.service;

import com.twschool.practice.repository.GuessNumberGameRepository;
import org.springframework.stereotype.Service;

@Service
public class GuessNumberGameService {
    
    private GuessNumberGameRepository guessNumberGameRepository;

    public GuessNumberGameService(GuessNumberGameRepository guessNumberGameRepository) {
        this.guessNumberGameRepository = guessNumberGameRepository;
    }

    public String guess(String userAnswer, String userId) {
        return guessNumberGameRepository.findBy(userId).guess(userAnswer);
    }

    public void start(String userId) {
        guessNumberGameRepository.createBy(userId);
    }
}
