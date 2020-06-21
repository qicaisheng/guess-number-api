package com.twschool.practice.service;

import com.twschool.practice.domain.GameStatus;
import com.twschool.practice.domain.GuessNumberGame;
import com.twschool.practice.repository.MemoryGuessNumberGameRepository;
import org.junit.Test;
import org.mockito.Mockito;

public class GuessNumberGameServiceTest {

    @Test
    public void should_return_result_when_guess_number() {
        MemoryGuessNumberGameRepository memoryGuessNumberGameRepository = Mockito.mock(MemoryGuessNumberGameRepository.class);
        GuessNumberGame guessNumberGame = Mockito.mock(GuessNumberGame.class);
        Mockito.when(memoryGuessNumberGameRepository.findBy(Mockito.any())).thenReturn(guessNumberGame);
        
        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository);

        guessNumberGameService.guess("1 2 3 4", "1");

        Mockito.verify(memoryGuessNumberGameRepository, Mockito.times(1)).findBy(Mockito.eq("1"));
        Mockito.verify(guessNumberGame, Mockito.times(1)).guess(Mockito.eq("1 2 3 4"));
    }


    @Test
    public void should_create_game() {
        MemoryGuessNumberGameRepository memoryGuessNumberGameRepository = Mockito.mock(MemoryGuessNumberGameRepository.class);
        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository);

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

        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository);

        guessNumberGameService.guess("1 2 3 4", "1");
        
        Mockito.verify(memoryGuessNumberGameRepository, Mockito.times(1)).deleteBy(Mockito.eq("1"));
    }

}
