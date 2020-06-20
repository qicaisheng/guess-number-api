package com.twschool.practice.repository;

import com.twschool.practice.domain.GuessNumberGame;
import org.junit.Assert;
import org.junit.Test;

public class MemoryGuessNumberGameRepositoryTest {

    @Test
    public void should_create_guess_number_game() {
        MemoryGuessNumberGameRepository guessNumberGameRepository = new MemoryGuessNumberGameRepository();
        
        GuessNumberGame guessNumberGame = guessNumberGameRepository.create();

        Assert.assertNotNull(guessNumberGame);
    }

    @Test
    public void should_get_guess_number_game() {
        MemoryGuessNumberGameRepository guessNumberGameRepository = new MemoryGuessNumberGameRepository();
        guessNumberGameRepository.create();
        
        GuessNumberGame guessNumberGame = guessNumberGameRepository.find();

        Assert.assertNotNull(guessNumberGame);
    }
}
