package org.cardy.spring.model;

public class Account {
    private int id;
    private String email;
    private String hash;
    private int refId;
    private Boolean admin;

    public Account() {

    }

    public Account(String email, String hash, int refId, Boolean admin) {
        this.email = email;
        this.hash = hash;
        this.refId = refId;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
