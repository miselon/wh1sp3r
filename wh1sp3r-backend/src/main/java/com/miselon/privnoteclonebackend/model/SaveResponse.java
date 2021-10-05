package com.miselon.privnoteclonebackend.model;

public class SaveResponse {

    private String id;

    public SaveResponse(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
