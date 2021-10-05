package com.miselon.privnoteclonebackend.services;

import com.miselon.privnoteclonebackend.model.Note;
import com.miselon.privnoteclonebackend.model.NoteRequest;
import com.miselon.privnoteclonebackend.persistance.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class NoteService {

    @Autowired
    private NoteRepo noteRepo;

    /**
     * Saves note object to the repository
     * @return generated note ID
     */
    public String saveNote(Note note){
        this.noteRepo.save(note);
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

    /**
     * Checks if a note exists in the database,
     * used by the client app to either present a note
     * or to inform the user that a particular note doesn't exist.
     * @param id of the note
     * @return boolean representing note's existence
     */
    public Boolean isNoteInDatabase(String id){
        return this.noteRepo.findById(id).isPresent();
    }

    /**
     * Retrieves note from the repository,
     * upon successful retrieval note gets deleted from the repo
     * @param id of the note
     * @return Note object from the repo or null if note doesn't exist
     */
    public Note getNote(String id){
        Optional<Note> note = this.noteRepo.findById(id);
        if(note.isPresent()){
            this.noteRepo.deleteById(id);
            return note.get();
        } else return null;
    }

    public Boolean deleteNote(String id){
        this.noteRepo.deleteById(id);
        // Check if note was actually deleted
        return this.noteRepo.findById(id).isEmpty();
    }

    @Async
    public void runExpiredNoteDeleter(){
        while(true){
            this.noteRepo.deleteExpiredNotes(System.currentTimeMillis());
            try { Thread.sleep(5000); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

}
