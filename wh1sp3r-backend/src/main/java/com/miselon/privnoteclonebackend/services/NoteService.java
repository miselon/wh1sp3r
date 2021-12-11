package com.miselon.privnoteclonebackend.services;

import com.miselon.privnoteclonebackend.model.Note;
import com.miselon.privnoteclonebackend.model.NoteRequest;
import com.miselon.privnoteclonebackend.persistance.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    /**
     * Saves note object to the repository
     * @return generated note ID
     */
    public String saveNote(Note note){
        this.noteRepository.save(note);
        return note.getId();
    }

    /**
     * Validates request from the client app
     * @return new Note object or null if validation fails
     */
    public Note validateNoteRequest(NoteRequest noteRequest){
        // Lifespan index out of bounds
        if(noteRequest.getLifespanIndex() < 0 || noteRequest.getLifespanIndex() > 9) return null;
        // TODO content size validation
        return new Note(noteRequest);
    }

    public Boolean isNoteInDatabase(String id){
        return this.noteRepository.findById(id).isPresent();
    }

    /**
     * Retrieves note from the repository,
     * upon successful retrieval note gets deleted from the repo
     * @param id of the note
     * @return Note object from the repo or null if note doesn't exist
     */
    public Note getNote(String id){
        Optional<Note> note = this.noteRepository.findById(id);
        if(note.isPresent()){
            this.noteRepository.deleteById(id);
            return note.get();
        } else return null;
    }

    public Boolean deleteNote(String id){
        this.noteRepository.deleteById(id);
        // Check if note was actually deleted
        return this.noteRepository.findById(id).isEmpty();
    }

    @Async
    public void runExpiredNoteDeleter(){
        while(true){
            this.noteRepository.deleteExpiredNotes(System.currentTimeMillis());
            try { Thread.sleep(5000); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    // For testing
    public void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
}
