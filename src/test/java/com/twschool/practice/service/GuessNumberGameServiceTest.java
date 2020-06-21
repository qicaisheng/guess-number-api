package com.twschool.practice.service;

import com.twschool.practice.domain.GameRecord;
import com.twschool.practice.domain.GameStatus;
import com.twschool.practice.domain.GuessNumberGame;
import com.twschool.practice.repository.MemoryGameFinalRecordRepository;
import com.twschool.practice.repository.MemoryGuessNumberGameRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class GuessNumberGameServiceTest {

    private MemoryGameFinalRecordRepository memoryGameFinalRecordRepository;

    @Before
    public void setUp() throws Exception {
        memoryGameFinalRecordRepository = Mockito.mock(MemoryGameFinalRecordRepository.class);
    }

    @Test
    public void should_return_result_when_guess_number() {
        MemoryGuessNumberGameRepository memoryGuessNumberGameRepository = Mockito.mock(MemoryGuessNumberGameRepository.class);
        GuessNumberGame guessNumberGame = Mockito.mock(GuessNumberGame.class);
        Mockito.when(memoryGuessNumberGameRepository.findBy(Mockito.any())).thenReturn(guessNumberGame);
        
        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository, memoryGameFinalRecordRepository);

        guessNumberGameService.guess("1 2 3 4", "1");

        Mockito.verify(memoryGuessNumberGameRepository, Mockito.times(1)).findBy(Mockito.eq("1"));
        Mockito.verify(guessNumberGame, Mockito.times(1)).guess(Mockito.eq("1 2 3 4"));
    }


    @Test
    public void should_create_game() {
        MemoryGuessNumberGameRepository memoryGuessNumberGameRepository = Mockito.mock(MemoryGuessNumberGameRepository.class);
        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository, memoryGameFinalRecordRepository);

        guessNumberGameService.start("1");

        Mockito.verify(memoryGuessNumberGameRepository, Mockito.times(1)).createBy(Mockito.eq("1"));
    }

    @Test
    public void should_exit_game_when_game_failed() {
        MemoryGuessNumberGameRepository memoryGuessNumberGameRepository = Mockito.mock(MemoryGuessNumberGameRepository.class);
        GuessNumberGame guessNumberGame = Mockito.mock(GuessNumberGame.class);
        Mockito.when(guessNumberGame.guess(Mockito.any())).thenReturn("1A2B");
        Mockito.when(guessNumberGame.getStatus()).thenReturn(GameStatus.FAILED);
        Mockito.when(memoryGuessNumberGameRepository.findBy(Mockito.any())).thenReturn(guessNumberGame);

        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository, memoryGameFinalRecordRepository);

        guessNumberGameService.guess("1 2 3 4", "1");
        
        Mockito.verify(memoryGuessNumberGameRepository, Mockito.times(1)).deleteBy(Mockito.eq("1"));
    }

    @Test
    public void should_record_final_record_when_game_failed() {
        MemoryGuessNumberGameRepository memoryGuessNumberGameRepository = Mockito.mock(MemoryGuessNumberGameRepository.class);
        GuessNumberGame guessNumberGame = Mockito.mock(GuessNumberGame.class);
        Mockito.when(guessNumberGame.guess(Mockito.any())).thenReturn("1A2B");
        Mockito.when(guessNumberGame.getStatus()).thenReturn(GameStatus.FAILED);
        Mockito.when(memoryGuessNumberGameRepository.findBy(Mockito.any())).thenReturn(guessNumberGame);
        
        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository, memoryGameFinalRecordRepository);

        guessNumberGameService.guess("1 2 3 4", "1");

        Mockito.verify(memoryGameFinalRecordRepository, Mockito.times(1)).create(Mockito.any());
    }

    @Test(expected = GameNotExistedException.class)
    public void should_throw_exception_when_game_not_existed() {
        MemoryGuessNumberGameRepository memoryGuessNumberGameRepository = Mockito.mock(MemoryGuessNumberGameRepository.class);
        Mockito.when(memoryGuessNumberGameRepository.findBy(Mockito.any())).thenReturn(null);

        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository, memoryGameFinalRecordRepository);

        guessNumberGameService.guess("1 2 3 4", "1");
    }

    @Test
    public void should_get_game_points() {
        MemoryGuessNumberGameRepository memoryGuessNumberGameRepository = Mockito.mock(MemoryGuessNumberGameRepository.class);
        List<GameRecord> gameRecords = Arrays.asList(new GameRecord("userId1", GameStatus.SUCCEED),
                new GameRecord("userId1", GameStatus.SUCCEED),
                new GameRecord("userId1", GameStatus.SUCCEED));
        Mockito.when(memoryGameFinalRecordRepository.findBy("userId1")).thenReturn(gameRecords);
        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository, memoryGameFinalRecordRepository);
        
        int points = guessNumberGameService.getGamePointsBy("userId1");

        Assert.assertEquals(3, points);
    }
}
