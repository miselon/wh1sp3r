package com.miselon.privnoteclonebackend.services;

import com.miselon.privnoteclonebackend.model.Note;
import com.miselon.privnoteclonebackend.model.NoteRequest;
import com.miselon.privnoteclonebackend.persistance.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NoteServiceTests {

    @Mock
    private NoteRepository repository;
    private NoteService service;

    @BeforeEach
    void setUp() {
        this.service = new NoteService();
        this.service.setNoteRepository(this.repository);
    }

    @Test
    void canSaveNote() {
        Note note = new Note();
        // Use service to save a note
        this.service.saveNote(note);
        // Check if repo mock was called
        verify(this.repository).save(note);
    }

    @Test
    void doesNoteRequestValidationWork() {
        // Lifespan index out of bounds
        NoteRequest noteRequest = new NoteRequest();
        noteRequest.setLifespanIndex(10);
        assertNull(this.service.validateNoteRequest(noteRequest));
    }

    @Test
    void doesNoteRequestValidationWorkWithValidRequest() {
        // Lifespan index in bounds
        NoteRequest noteRequest = new NoteRequest();
        noteRequest.setLifespanIndex(4);
        assertNotNull(this.service.validateNoteRequest(noteRequest));
    }

    @Test
    void doesNoteExistCheckWorks() {
        String id = "1234";
        this.service.isNoteInDatabase(id);
        // Check if repo mock was called
        verify(this.repository).findById(id);
    }

    @Test
    void canNoteBeRetrievedFromRepo() {
        String id = "1234";
        this.service.getNote(id);
        // Check if repo mock was called
        verify(this.repository).findById(id);
    }

}