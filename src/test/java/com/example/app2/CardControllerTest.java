package com.example.app2;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.logging.Logger;


@SpringBootTest
@AutoConfigureMockMvc
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CardService cardService;

    @BeforeEach
     void init(){
        cardService.setPath("src/test/java/resources/validData.csv");
    }


    @Test
    void getAllCardsIsNotEmpty() throws Exception {

        this.mockMvc.perform(get("/cards/getall"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void getAllCardsContainsProperValues() throws Exception {
        ResultActions actions = mockMvc.perform(get("/cards/getall"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name", Matchers.everyItem(Matchers.containsString(""))))
                .andExpect(jsonPath("$[*].surname", Matchers.everyItem(Matchers.containsString(""))))
                .andExpect(jsonPath("$[*].phone", Matchers.everyItem(Matchers.containsString(""))));
    }

    @Test
    void getCardsByNameEqualProperValues() throws Exception{
        String name="Gordon";
        this.mockMvc.perform(get("/cards/get/name/equals/"+name))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name", Matchers.everyItem(Matchers.equalTo(name))));
    }

    @Test
    void getCardsByNameContainsProperValues() throws Exception{
        String name="do";
        this.mockMvc.perform(get("/cards/get/name/contains/"+name))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name", Matchers.everyItem(Matchers.containsString(name))));
    }

    @Test
    void getCardsBySurnameEqualProperValues() throws Exception{
        String surname="Bond";
        this.mockMvc.perform(get("/cards/get/surname/equals/"+surname))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].surname", Matchers.everyItem(Matchers.equalTo(surname))));
    }

    @Test
    void getCardsBySurnameContainsProperValues() throws Exception{
        String surname="on";
        this.mockMvc.perform(get("/cards/get/surname/contains/"+surname))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].surname", Matchers.everyItem(Matchers.containsString(surname))));
    }

    @Test
    void getCardsByPhoneEqualProperValues() throws Exception{
        String phone="876315751796";
        this.mockMvc.perform(get("/cards/get/phone/equals/"+phone))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].phone", Matchers.everyItem(Matchers.equalTo(phone))));
    }

    @Test
    void getCardsByPhoneContainsProperValues() throws Exception{
        String phone="123";
        this.mockMvc.perform(get("/cards/get/phone/contains/"+phone))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].phone", Matchers.everyItem(Matchers.containsString(phone))));
    }
}