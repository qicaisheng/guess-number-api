package com.twschool.practice.service;

import com.twschool.practice.domain.GameNotExistedException;
import com.twschool.practice.domain.GameFinalRecord;
import com.twschool.practice.domain.GameStatus;
import com.twschool.practice.domain.GuessNumberGame;
import com.twschool.practice.repository.GameFinalRecordRepository;
import com.twschool.practice.repository.GuessNumberGameRepository;
import com.twschool.practice.repository.MemoryGameFinalRecordRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class GuessNumberGameServiceTest {

    private GameFinalRecordRepository gameFinalRecordRepository;

    @Before
    public void setUp() throws Exception {
        gameFinalRecordRepository = Mockito.mock(MemoryGameFinalRecordRepository.class);
    }

    @Test
    public void should_return_result_when_guess_number() {
        GuessNumberGameRepository guessNumberGameRepository = Mockito.mock(GuessNumberGameRepository.class);
        GuessNumberGame guessNumberGame = Mockito.mock(GuessNumberGame.class);
        Mockito.when(guessNumberGameRepository.findBy(Mockito.any())).thenReturn(guessNumberGame);
        
        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(guessNumberGameRepository, gameFinalRecordRepository);

        guessNumberGameService.guess("1 2 3 4", "1");

        Mockito.verify(guessNumberGameRepository, Mockito.times(1)).findBy(Mockito.eq("1"));
        Mockito.verify(guessNumberGame, Mockito.times(1)).guess(Mockito.eq("1 2 3 4"));
    }


    @Test
    public void should_create_game() {
        GuessNumberGameRepository guessNumberGameRepository = Mockito.mock(GuessNumberGameRepository.class);
        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(guessNumberGameRepository, gameFinalRecordRepository);

        guessNumberGameService.start("1");

        Mockito.verify(guessNumberGameRepository, Mockito.times(1)).createBy(Mockito.eq("1"));
    }

    @Test
    public void should_exit_game_when_game_failed() {
        GuessNumberGameRepository guessNumberGameRepository = Mockito.mock(GuessNumberGameRepository.class);
        GuessNumberGame guessNumberGame = Mockito.mock(GuessNumberGame.class);
        Mockito.when(guessNumberGame.guess(Mockito.any())).thenReturn("1A2B");
        Mockito.when(guessNumberGame.getStatus()).thenReturn(GameStatus.FAILED);
        Mockito.when(guessNumberGameRepository.findBy(Mockito.any())).thenReturn(guessNumberGame);

        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(guessNumberGameRepository, gameFinalRecordRepository);

        guessNumberGameService.guess("1 2 3 4", "1");
        
        Mockito.verify(guessNumberGameRepository, Mockito.times(1)).deleteBy(Mockito.eq("1"));
    }

    @Test
    public void should_record_final_record_when_game_failed() {
        GuessNumberGameRepository guessNumberGameRepository = Mockito.mock(GuessNumberGameRepository.class);
        GuessNumberGame guessNumberGame = Mockito.mock(GuessNumberGame.class);
        Mockito.when(guessNumberGame.guess(Mockito.any())).thenReturn("1A2B");
        Mockito.when(guessNumberGame.getStatus()).thenReturn(GameStatus.FAILED);
        Mockito.when(guessNumberGameRepository.findBy(Mockito.any())).thenReturn(guessNumberGame);
        
        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(guessNumberGameRepository, gameFinalRecordRepository);

        guessNumberGameService.guess("1 2 3 4", "1");

        Mockito.verify(gameFinalRecordRepository, Mockito.times(1)).create(Mockito.any());
    }

    @Test(expected = GameNotExistedException.class)
    public void should_throw_exception_when_game_not_existed() {
        GuessNumberGameRepository guessNumberGameRepository = Mockito.mock(GuessNumberGameRepository.class);
        Mockito.when(guessNumberGameRepository.findBy(Mockito.any())).thenReturn(null);

        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(guessNumberGameRepository, gameFinalRecordRepository);

        guessNumberGameService.guess("1 2 3 4", "1");
    }

    @Test
    public void should_get_game_points() {
        GuessNumberGameRepository guessNumberGameRepository = Mockito.mock(GuessNumberGameRepository.class);
        List<GameFinalRecord> gameFinalRecords = Arrays.asList(new GameFinalRecord("userId1", GameStatus.SUCCEED),
                new GameFinalRecord("userId1", GameStatus.SUCCEED),
                new GameFinalRecord("userId1", GameStatus.SUCCEED));
        Mockito.when(gameFinalRecordRepository.findBy("userId1")).thenReturn(gameFinalRecords);
        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(guessNumberGameRepository, gameFinalRecordRepository);
        
        int points = guessNumberGameService.getGamePointsBy("userId1");

        Assert.assertEquals(3, points);
    }
}
