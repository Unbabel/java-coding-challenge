package com.unbabel.challenge.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UnbabelTranslationControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnIndexPage() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("availableSourcelanguagues", "translationsEntries"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnAvailableTargetLanguagesHTML() throws Exception{
        mockMvc.perform(get("/targetlanguages/en"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(model().attributeExists("targetLanguages"));
    }

    @Test
    public void shouldReturnInfoAboutCreatedTranslationHTML() throws Exception{
        mockMvc.perform(post("/translate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"text\": \"Hello, world\", \"source_language\": \"en\", \"target_language\": \"pt\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(model().attributeExists("translationEntryRow"));
    }



}
