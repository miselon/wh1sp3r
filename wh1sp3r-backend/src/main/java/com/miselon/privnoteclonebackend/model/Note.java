package com.miselon.privnoteclonebackend.model;

import javax.persistence.*;
import java.util.UUID;

/**
 *  Note represents how note finally is going to be saved in the database.
 *  Instantiating Note automatically generates it's ID and calculates it's lifespan.
 */

@Entity(name = "Note")
@Table(name = "note_table")
public class Note {

    @Id
    private String id;

    @Column(nullable = false)
    private Long ts;

    @Column(nullable = false)
    @Lob
    private String encryptedMessage;

    public Note(NoteRequest noteRequest){
        // Generate ID
        this.id = UUID.randomUUID().toString().substring(0, 8);
        // Calculate lifespan based on index
        // received from the client app
        this.ts = calculateLifespan(noteRequest.getLifespanIndex());
        this.encryptedMessage = noteRequest.getEncryptedMessage();
    }

    // Default constructor for serialization purposes
    public Note() {
    }

    private Long calculateLifespan(int index){
        // TODO this sucks
        Long millis = System.currentTimeMillis();
        // 30 days
        if(index == 0) millis += 2592000000L;
        // 7 days
        else if(index == 1) millis += 604800000L;
        // 3 days
        else if(index == 2) millis += 259200000L;
        // 1 days
        else if(index == 3) millis += 86400000L;
        // 12 hours
        else if(index == 4) millis += 43200000L;
        // 6 hours
        else if(index == 5) millis += 21600000L;
        // 3 hours
        else if(index == 6) millis += 10800000L;
        // 1 hour
        else if(index == 7) millis += 3600000L;
        // 30 minutes
        else if(index == 8) millis += 1800000L;
        // 15 minutes
        else if(index == 9) millis += 900000L;
        return millis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public String getEncryptedMessage() {
        return encryptedMessage;
    }

    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }

}
