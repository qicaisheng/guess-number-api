package com.twschool.practice.service;

import com.twschool.practice.domain.GameStatus;
import com.twschool.practice.domain.GuessNumberGame;
import com.twschool.practice.repository.GuessNumberGameRepository;
import org.springframework.stereotype.Service;

@Service
public class GuessNumberGameService {
    
    private GuessNumberGameRepository guessNumberGameRepository;

    public GuessNumberGameService(GuessNumberGameRepository guessNumberGameRepository) {
        this.guessNumberGameRepository = guessNumberGameRepository;
    }

    public String guess(String userAnswer, String userId) {
        GuessNumberGame guessNumberGame = guessNumberGameRepository.findBy(userId);
        String guess = guessNumberGame.guess(userAnswer);
        if (guessNumberGame.getStatus() != GameStatus.CONTINUED) {
            guessNumberGameRepository.deleteBy(userId);
        }
        return guess;
    }

    public void start(String userId) {
        guessNumberGameRepository.createBy(userId);
    }
}
