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
    
    @GetMapping("/game/guess")
    public Map<String, String> guess(@RequestParam String number, @RequestBody UserRequestBody userRequestBody) {
        
        String result = guessNumberGameService.guess(number, userRequestBody.getUserId());
        Map<String, String> response = new HashMap<>();
        response.put("input", number);
        response.put("result", result);
        
        return response;
    }

    @PostMapping("/game")
    public void start(@RequestBody UserRequestBody userRequestBody) {
        guessNumberGameService.start(userRequestBody.getUserId());
    }

}
