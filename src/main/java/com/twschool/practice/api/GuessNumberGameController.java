package com.twschool.practice.api;

import com.twschool.practice.service.GuessNumberGameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GuessNumberGameController {
    
    private final GuessNumberGameService guessNumberGameService;

    public GuessNumberGameController(GuessNumberGameService guessNumberGameService) {
        this.guessNumberGameService = guessNumberGameService;
    }

    @PostMapping("/games/guess-numbers")
    public GuessResponseBody guess(@RequestBody UserRequestBody userRequestBody) {
        
        String result = guessNumberGameService.guess(userRequestBody.getNumber(), userRequestBody.getUserId());
        GuessResponseBody guessResponseBody = new GuessResponseBody();
        guessResponseBody.setInput(userRequestBody.getNumber());
        guessResponseBody.setResult(result);
        return guessResponseBody;
    }

    @PostMapping("/games")
    public void start(@RequestBody UserRequestBody userRequestBody) {
        guessNumberGameService.start(userRequestBody.getUserId());
    }

    @GetMapping("/games/game-points")
    public Map<String, Integer> gamePoints(@RequestBody UserRequestBody userRequestBody) {
        int points = guessNumberGameService.getGamePointsBy(userRequestBody.getUserId());
        Map<String, Integer> response = new HashMap<>();
        response.put("points", points);
        return response;
    }

}
