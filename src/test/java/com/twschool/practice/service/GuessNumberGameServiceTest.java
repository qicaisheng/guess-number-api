package com.twschool.practice.service;

import com.twschool.practice.domain.GameNotExistedException;
import com.twschool.practice.domain.GameStatus;
import com.twschool.practice.domain.GuessNumberGame;
import com.twschool.practice.repository.MemoryGuessNumberGameRepository;
import com.twschool.practice.repository.UserGamePoint;
import com.twschool.practice.repository.UserGamePointRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GuessNumberGameServiceTest {

    private UserGamePointRepository userGamePointRepository;

    @Before
    public void setUp() throws Exception {
        userGamePointRepository = Mockito.mock(UserGamePointRepository.class);
        Mockito.when(userGamePointRepository.findLatestBy(Mockito.any())).thenReturn(new UserGamePoint("1", 14, 3));
    }

    @Test
    public void should_return_result_when_guess_number() {
        MemoryGuessNumberGameRepository memoryGuessNumberGameRepository = Mockito.mock(MemoryGuessNumberGameRepository.class);
        GuessNumberGame guessNumberGame = Mockito.mock(GuessNumberGame.class);
        Mockito.when(memoryGuessNumberGameRepository.findBy(Mockito.any())).thenReturn(guessNumberGame);
        
        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository, userGamePointRepository);

        guessNumberGameService.guess("1 2 3 4", "1");

        Mockito.verify(memoryGuessNumberGameRepository, Mockito.times(1)).findBy(Mockito.eq("1"));
        Mockito.verify(guessNumberGame, Mockito.times(1)).guess(Mockito.eq("1 2 3 4"));
    }


    @Test
    public void should_create_game() {
        MemoryGuessNumberGameRepository memoryGuessNumberGameRepository = Mockito.mock(MemoryGuessNumberGameRepository.class);
        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository, userGamePointRepository);

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

        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository, userGamePointRepository);

        guessNumberGameService.guess("1 2 3 4", "1");
        
        Mockito.verify(memoryGuessNumberGameRepository, Mockito.times(1)).deleteBy(Mockito.eq("1"));
    }

    @Test
    public void should_create_new_user_game_point_when_game_failed() {
        MemoryGuessNumberGameRepository memoryGuessNumberGameRepository = Mockito.mock(MemoryGuessNumberGameRepository.class);
        GuessNumberGame guessNumberGame = Mockito.mock(GuessNumberGame.class);
        Mockito.when(guessNumberGame.guess(Mockito.any())).thenReturn("1A2B");
        Mockito.when(guessNumberGame.getStatus()).thenReturn(GameStatus.FAILED);
        Mockito.when(memoryGuessNumberGameRepository.findBy(Mockito.any())).thenReturn(guessNumberGame);
 
        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository, userGamePointRepository);

        guessNumberGameService.guess("1 2 3 4", "1");

        Mockito.verify(userGamePointRepository, Mockito.times(1)).create(Mockito.any());
    }

    @Test(expected = GameNotExistedException.class)
    public void should_throw_exception_when_game_not_existed() {
        MemoryGuessNumberGameRepository memoryGuessNumberGameRepository = Mockito.mock(MemoryGuessNumberGameRepository.class);
        Mockito.when(memoryGuessNumberGameRepository.findBy(Mockito.any())).thenReturn(null);

        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository, userGamePointRepository);

        guessNumberGameService.guess("1 2 3 4", "1");
    }

    @Test
    public void should_get_game_points() {
        MemoryGuessNumberGameRepository memoryGuessNumberGameRepository = Mockito.mock(MemoryGuessNumberGameRepository.class);
        UserGamePoint userGamePoint = new UserGamePoint("userId1", 15, 3);
        Mockito.when(userGamePointRepository.findLatestBy("userId1")).thenReturn(userGamePoint);
        GuessNumberGameService guessNumberGameService = new GuessNumberGameService(memoryGuessNumberGameRepository, userGamePointRepository);
        
        int points = guessNumberGameService.getGamePointsBy("userId1");

        Assert.assertEquals(15, points);
    }
}
