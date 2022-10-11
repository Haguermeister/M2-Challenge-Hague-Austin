package com.austinhague.monthMathAPI.controller;

import com.austinhague.monthMathAPI.classes.MathSolution;
import com.austinhague.monthMathAPI.classes.Month;
import com.austinhague.monthMathAPI.controllers.MathAndMonthController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Borrowed from the activity in class on 10/5
@RunWith(SpringRunner.class)
@WebMvcTest(MathAndMonthController.class)
public class MonthAndMathControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        // we may need to set up some data in here
    }

    @Test
    public void shouldReturnRandomMonth() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/randomMonth"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.number").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty());
    }
    @Test
    public void shouldReturnNewMonthOnGetRequest() throws Exception {
        Month outputMonth = new Month(5,"May");

        String outputMonthJSON = mapper.writeValueAsString(outputMonth);

        mockMvc.perform(get("/month/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.number").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(content().json(outputMonthJSON));
    }
    @Test
    public void shouldRespondWith422WhenMonthNumberIsOutOfRange() throws Exception {

        mockMvc.perform(get("/month/13"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void shouldReturnNewMathSolutionOnAddPostRequest() throws Exception {
        MathSolution inputMathSolution = new MathSolution();
        inputMathSolution.setOperand1(8);
        inputMathSolution.setOperand2(12);

        String inputMathSolutionJSON = mapper.writeValueAsString(inputMathSolution);

        MathSolution outputMathSolution = new MathSolution();
        outputMathSolution.setOperand1(8);
        outputMathSolution.setOperand2(12);
        outputMathSolution.setOperation("add");
        outputMathSolution.setAnswer(20);

        String outputMathSolutionJSON = mapper.writeValueAsString(outputMathSolution);

        mockMvc.perform(post("/add")
                        .content(inputMathSolutionJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputMathSolutionJSON));
    }
    @Test
    public void shouldRespondWith422WhenMathSolutionIsPostedToAddWithoutOperand() throws Exception {
        MathSolution inputMathSolution = new MathSolution();
        inputMathSolution.setOperand2(8);

        String inputMathSolutionJSON = mapper.writeValueAsString(inputMathSolution);

        mockMvc.perform(post("/add")
                        .content(inputMathSolutionJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturnNewMathSolutionOnSubtractPostRequest() throws Exception {
        MathSolution inputMathSolution = new MathSolution();
        inputMathSolution.setOperand1(20);
        inputMathSolution.setOperand2(12);

        String inputMathSolutionJSON = mapper.writeValueAsString(inputMathSolution);

        MathSolution outputMathSolution = new MathSolution();
        outputMathSolution.setOperand1(20);
        outputMathSolution.setOperand2(12);
        outputMathSolution.setOperation("subtract");
        outputMathSolution.setAnswer(8);

        String outputMathSolutionJSON = mapper.writeValueAsString(outputMathSolution);

        mockMvc.perform(post("/subtract")
                        .content(inputMathSolutionJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputMathSolutionJSON));
    }
    @Test
    public void shouldRespondWith422WhenMathSolutionIsPostedToSubtractWithoutOperand() throws Exception {
        MathSolution inputMathSolution = new MathSolution();
        inputMathSolution.setOperand2(8);

        String inputMathSolutionJSON = mapper.writeValueAsString(inputMathSolution);

        mockMvc.perform(post("/subtract")
                        .content(inputMathSolutionJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturnNewMathSolutionOnMultiplyPostRequest() throws Exception {
        MathSolution inputMathSolution = new MathSolution();
        inputMathSolution.setOperand1(20);
        inputMathSolution.setOperand2(10);

        String inputMathSolutionJSON = mapper.writeValueAsString(inputMathSolution);

        MathSolution outputMathSolution = new MathSolution();
        outputMathSolution.setOperand1(20);
        outputMathSolution.setOperand2(10);
        outputMathSolution.setOperation("multiply");
        outputMathSolution.setAnswer(200);

        String outputMathSolutionJSON = mapper.writeValueAsString(outputMathSolution);

        mockMvc.perform(post("/multiply")
                        .content(inputMathSolutionJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputMathSolutionJSON));
    }
    @Test
    public void shouldRespondWith422WhenMathSolutionIsPostedToMultiplyWithoutOperand() throws Exception {
        MathSolution inputMathSolution = new MathSolution();
        inputMathSolution.setOperand2(20);

        String inputMathSolutionJSON = mapper.writeValueAsString(inputMathSolution);

        mockMvc.perform(post("/multiply")
                        .content(inputMathSolutionJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturnNewMathSolutionOnDividePostRequest() throws Exception {
        MathSolution inputMathSolution = new MathSolution();
        inputMathSolution.setOperand1(20);
        inputMathSolution.setOperand2(10);

        String inputMathSolutionJSON = mapper.writeValueAsString(inputMathSolution);

        MathSolution outputMathSolution = new MathSolution();
        outputMathSolution.setOperand1(20);
        outputMathSolution.setOperand2(10);
        outputMathSolution.setOperation("divide");
        outputMathSolution.setAnswer(2);

        String outputMathSolutionJSON = mapper.writeValueAsString(outputMathSolution);

        mockMvc.perform(post("/divide")
                        .content(inputMathSolutionJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputMathSolutionJSON));
    }
    @Test
    public void shouldRespondWith422WhenMathSolutionIsPostedToDivideWithoutOperand() throws Exception {
        MathSolution inputMathSolution = new MathSolution();
        inputMathSolution.setOperand2(20);

        String inputMathSolutionJSON = mapper.writeValueAsString(inputMathSolution);

        mockMvc.perform(post("/divide")
                        .content(inputMathSolutionJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void shouldRespondWith422WhenMathSolutionIsPostedToDivideWithOperand2Zero() throws Exception {
        MathSolution inputMathSolution = new MathSolution();
        inputMathSolution.setOperand2(10);
        inputMathSolution.setOperand2(0);

        String inputMathSolutionJSON = mapper.writeValueAsString(inputMathSolution);

        mockMvc.perform(post("/divide")
                        .content(inputMathSolutionJSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

}