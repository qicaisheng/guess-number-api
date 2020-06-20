package com.twschool.practice.api;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GuessNumberGameController {
    
    @GetMapping("/game/guess")
    public Map<String, String> guess(@RequestParam String number) {
        
        Map<String, String> response = new HashMap<>();
        response.put("input", number);
        response.put("result", "4A0B");
        
        return response;
    }

}
