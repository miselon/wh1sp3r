package com.miselon.privnoteclonebackend.controllers;

import com.miselon.privnoteclonebackend.model.Note;
import com.miselon.privnoteclonebackend.model.NoteRequest;
import com.miselon.privnoteclonebackend.model.SaveResponse;
import com.miselon.privnoteclonebackend.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes/")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity<SaveResponse> addNote(@RequestBody NoteRequest noteRequest){
        // Validate request
        // If null is returned, then validation failed
        Note note = this.noteService.validateNoteRequest(noteRequest);
        if(note == null) return ResponseEntity.badRequest().build();
        else return ResponseEntity.ok(new SaveResponse(this.noteService.saveNote(note)));
    }

    @GetMapping("check/{id}")
    public Boolean checkIfNoteExists(@PathVariable String id){
        return noteService.isNoteInDatabase(id);
    }

    @GetMapping("{id}")
    public Note getNote(@PathVariable String id){
        return noteService.getNote(id);
    }

    @DeleteMapping("{id}")
    public Boolean deleteNote(@PathVariable String id){
        return this.noteService.deleteNote(id);
    }

}
