package com.twschool.practice.api;

import com.twschool.practice.service.GameNotExistedException;
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
        Mockito.when(guessNumberGameService.guess(Mockito.any(), Mockito.eq("1"))).thenReturn("4A0B");
    }

    @Test
    public void should_return_guess_result() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/games/guess")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"userId\": \"1\"}")
            .param("number", "1 2 3 4"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.input").value("1 2 3 4"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("4A0B"));
    }

    @Test
    public void should_start_game() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/games")
                .content("{\"userId\": \"1\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        
        Mockito.verify(guessNumberGameService, Mockito.times(1)).start(Mockito.eq("1"));
    }

    @Test
    public void should_return_http_status_code_400_when_guess_but_not_start_game() throws Exception {
        Mockito.doThrow(GameNotExistedException.class).when(guessNumberGameService).guess(Mockito.any(), Mockito.any());
        mockMvc.perform(MockMvcRequestBuilders.get("/games/guess")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\": \"1\"}")
                .param("number", "1 2 3 4"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("Please start new game"));
    }
}
