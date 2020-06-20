package com.twschool.practice.api;

import com.twschool.practice.service.GuessNumberGameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class GuessNumberGameControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private GuessNumberGameService guessNumberGameService;

    @Before
    public void setUp() throws Exception {
        Mockito.when(guessNumberGameService.guess(Mockito.any())).thenReturn("4A0B");
    }

    @Test
    public void should_return_guess_result() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/game/guess")
            .contentType(MediaType.APPLICATION_JSON)
            .param("number", "1 2 3 4"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.input").value("1 2 3 4"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("4A0B"));
    }

    @Test
    public void should_start_game() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/game")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        Mockito.verify(guessNumberGameService, Mockito.times(1)).start();
    }
}
