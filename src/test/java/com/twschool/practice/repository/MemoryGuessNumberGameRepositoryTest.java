package com.twschool.practice.repository;

import com.twschool.practice.domain.GuessNumberGame;
import org.junit.Assert;
import org.junit.Test;

public class MemoryGuessNumberGameRepositoryTest {

    @Test
    public void should_create_guess_number_game() {
        MemoryGuessNumberGameRepository guessNumberGameRepository = new MemoryGuessNumberGameRepository();
        
        GuessNumberGame guessNumberGame = guessNumberGameRepository.create("1");

        Assert.assertNotNull(guessNumberGame);
    }

    @Test
    public void should_get_guess_number_game() {
        MemoryGuessNumberGameRepository guessNumberGameRepository = new MemoryGuessNumberGameRepository();
        guessNumberGameRepository.create("1");
        
        GuessNumberGame guessNumberGame = guessNumberGameRepository.find("1");

        Assert.assertNotNull(guessNumberGame);
    }

    @Test
    public void should_get_guess_number_game_by_user_id() {
        MemoryGuessNumberGameRepository guessNumberGameRepository = new MemoryGuessNumberGameRepository();
        GuessNumberGame guessNumberGame1 = guessNumberGameRepository.create("1");
        guessNumberGameRepository.create("2");

        GuessNumberGame guessNumberGame = guessNumberGameRepository.find("1");

        Assert.assertNotNull(guessNumberGame);
        Assert.assertEquals(guessNumberGame1, guessNumberGame);
    }
}
