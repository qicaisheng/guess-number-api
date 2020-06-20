package com.twschool.practice.service;

import com.twschool.practice.domain.GuessNumberGame;
import com.twschool.practice.repository.MemoryGuessNumberGameRepository;
import org.junit.Test;
import org.mockito.Mockito;

public class GuessNumberGameServiceTest {

    @Test
    public void should_return_result_when_guess_number() {
        MemoryGuessNumberGameRepository memoryGuessNumberGameRepository = Mockito.mock(MemoryGuessNumberGameRepository.class);
        GuessNumberGame guessNumberGame = Mockito.mock(GuessNumberGame.class);
        Mockito.when(memoryGuessNumberGameRepository.find()).thenReturn(guessNumberGame);
        
        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository);

        guessNumberGameService.guess("1 2 3 4");

        Mockito.verify(memoryGuessNumberGameRepository, Mockito.times(1)).find();
        Mockito.verify(guessNumberGame, Mockito.times(1)).guess(Mockito.eq("1 2 3 4"));
    }
}
