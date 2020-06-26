package com.twschool.practice.service;

import com.twschool.practice.domain.GameNotExistedException;
import com.twschool.practice.domain.GameStatus;
import com.twschool.practice.domain.GuessNumberGame;
import com.twschool.practice.repository.GuessNumberGameRepository;
import com.twschool.practice.repository.MemoryGuessNumberGameRepository;
import com.twschool.practice.repository.UserGamePoint;
import com.twschool.practice.repository.UserGamePointRepository;
import org.springframework.stereotype.Service;

@Service
public class GuessNumberGameService {
    
    private GuessNumberGameRepository guessNumberGameRepository;
    private UserGamePointRepository userGamePointRepository;

    public GuessNumberGameService(MemoryGuessNumberGameRepository memoryGuessNumberGameRepository, UserGamePointRepository userGamePointRepository) {

        guessNumberGameRepository = memoryGuessNumberGameRepository;
        this.userGamePointRepository = userGamePointRepository;
    }

    public String guess(String userAnswer, String userId) {
        GuessNumberGame guessNumberGame = guessNumberGameRepository.findBy(userId);
        if (guessNumberGame == null) {
            throw new GameNotExistedException();
        }
        String guess = guessNumberGame.guess(userAnswer);
        if (guessNumberGame.getStatus() != GameStatus.CONTINUED) {
            calculatePoint(userId, guessNumberGame.getStatus());
            exitGame(userId);
        }
        return guess;
    }

    public void start(String userId) {
        guessNumberGameRepository.createBy(userId);
    }

    public int getGamePointsBy(String userId) {
        UserGamePoint userGamePoint = userGamePointRepository.findLatestBy(userId);
        return userGamePoint.getPoint();
    }

    private void calculatePoint(String userId, GameStatus status) {
        UserGamePoint userGamePoint = userGamePointRepository.findLatestBy(userId);
        userGamePoint.receive(status);
        userGamePointRepository.create(userGamePoint);
    }

    private void exitGame(String userId) {
        guessNumberGameRepository.deleteBy(userId);
    }
}
