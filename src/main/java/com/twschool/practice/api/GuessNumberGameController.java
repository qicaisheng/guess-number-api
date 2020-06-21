package com.twschool.practice.api;

import com.twschool.practice.service.GuessNumberGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GuessNumberGameController {
    
    @Autowired
    private GuessNumberGameService guessNumberGameService;
    
    @PostMapping("/games/guess-numbers")
    public Map<String, String> guess(@RequestBody UserRequestBody userRequestBody) {
        
        String result = guessNumberGameService.guess(userRequestBody.getNumber(), userRequestBody.getUserId());
        Map<String, String> response = new HashMap<>();
        response.put("input", userRequestBody.getNumber());
        response.put("result", result);
        
        return response;
    }

    @PostMapping("/games")
    public void start(@RequestBody UserRequestBody userRequestBody) {
        guessNumberGameService.start(userRequestBody.getUserId());
    }

}
