package com.twschool.practice.api;

import com.twschool.practice.service.GuessNumberGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuessNumberGameController {
    
    @Autowired
    private GuessNumberGameService guessNumberGameService;
    
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

}
