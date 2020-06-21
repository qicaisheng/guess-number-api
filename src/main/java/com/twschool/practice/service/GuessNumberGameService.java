package com.twschool.practice.service;

import com.twschool.practice.domain.GamePoints;
import com.twschool.practice.domain.GameRecord;
import com.twschool.practice.domain.GameStatus;
import com.twschool.practice.domain.GuessNumberGame;
import com.twschool.practice.repository.GuessNumberGameRepository;
import com.twschool.practice.repository.MemoryGameFinalRecordRepository;
import com.twschool.practice.repository.MemoryGuessNumberGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuessNumberGameService {
    
    private GuessNumberGameRepository guessNumberGameRepository;
    private MemoryGameFinalRecordRepository gameFinalRecordRepository;

    public GuessNumberGameService(MemoryGuessNumberGameRepository memoryGuessNumberGameRepository, MemoryGameFinalRecordRepository memoryGameFinalRecordRepository) {

        guessNumberGameRepository = memoryGuessNumberGameRepository;
        this.gameFinalRecordRepository = memoryGameFinalRecordRepository;
    }

    public String guess(String userAnswer, String userId) {
        GuessNumberGame guessNumberGame = guessNumberGameRepository.findBy(userId);
        if (guessNumberGame == null) {
            throw new GameNotExistedException();
        }
        String guess = guessNumberGame.guess(userAnswer);
        if (guessNumberGame.getStatus() != GameStatus.CONTINUED) {
            gameFinalRecordRepository.create(new GameRecord(userId, guessNumberGame.getStatus()));
            guessNumberGameRepository.deleteBy(userId);
        }
        return guess;
    }

    public void start(String userId) {
        guessNumberGameRepository.createBy(userId);
    }

    public int getGamePointsBy(String userId) {
        List<GameRecord> gameRecords = gameFinalRecordRepository.findBy(userId);
        GamePoints gamePoints = new GamePoints(gameRecords);
        return gamePoints.totalPoints();
    }
}
