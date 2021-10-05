package com.miselon.privnoteclonebackend.model;

/**
 *  NoteRequest represents note save request submitted by the client app.
 */

public class NoteRequest {

    private int lifespanIndex;
    private String encryptedMessage;

    public NoteRequest(int lifespanIndex, String encryptedMessage) {
        this.lifespanIndex = lifespanIndex;
        this.encryptedMessage = encryptedMessage;
    }

    public NoteRequest() {
    }

    public int getLifespanIndex() {
        return lifespanIndex;
    }

    public void setLifespanIndex(int lifespanIndex) {
        this.lifespanIndex = lifespanIndex;
    }

    public String getEncryptedMessage() {
        return encryptedMessage;
    }

    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }
}
