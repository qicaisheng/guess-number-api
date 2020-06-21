package com.twschool.practice.service;

import com.twschool.practice.domain.*;
import com.twschool.practice.repository.GameFinalRecordRepository;
import com.twschool.practice.repository.GuessNumberGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuessNumberGameService {
    
    private GuessNumberGameRepository guessNumberGameRepository;
    private GameFinalRecordRepository gameFinalRecordRepository;

    public GuessNumberGameService(GuessNumberGameRepository guessNumberGameRepository, GameFinalRecordRepository gameFinalRecordRepository) {

        this.guessNumberGameRepository = guessNumberGameRepository;
        this.gameFinalRecordRepository = gameFinalRecordRepository;
    }

    public String guess(String userAnswer, String userId) {
        GuessNumberGame guessNumberGame = guessNumberGameRepository.findBy(userId);
        if (guessNumberGame == null) {
            throw new GameNotExistedException();
        }
        String guess = guessNumberGame.guess(userAnswer);
        if (guessNumberGame.getStatus() != GameStatus.CONTINUED) {
            gameFinalRecordRepository.create(new GameFinalRecord(userId, guessNumberGame.getStatus()));
            guessNumberGameRepository.deleteBy(userId);
        }
        return guess;
    }

    public void start(String userId) {
        guessNumberGameRepository.createBy(userId);
    }

    public int getGamePointsBy(String userId) {
        List<GameFinalRecord> gameFinalRecords = gameFinalRecordRepository.findBy(userId);
        GamePoints gamePoints = new GamePoints(gameFinalRecords);
        return gamePoints.totalPoints();
    }
}
