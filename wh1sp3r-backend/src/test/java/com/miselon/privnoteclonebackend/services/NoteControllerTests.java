package com.miselon.privnoteclonebackend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miselon.privnoteclonebackend.controllers.NoteController;
import com.miselon.privnoteclonebackend.model.NoteRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NoteController.class)
public class NoteControllerTests {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private NoteService noteService;
    private ObjectMapper serializer;

    @BeforeEach
    void setUp() {
        this.serializer = new ObjectMapper();
    }

    @Test
    void addNoteMvcTest() throws Exception {
        // Create and serialize note
        NoteRequest noteRequest = new NoteRequest();
        String requestBody = this.serializer.writeValueAsString(noteRequest);
        // Preform mock request
        // TODO
        // For now expect 400 because request validation will fail
        this.mvc.perform(post("/api/notes/")
                    .content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void checkIfNoteExistsMvcTest() throws Exception {
        this.mvc.perform(get("/api/notes/check/1234"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("false"));
    }

}
