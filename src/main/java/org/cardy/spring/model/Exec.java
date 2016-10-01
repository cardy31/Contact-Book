package org.cardy.spring.model;

public class Exec {

    private int id;
    private String position;
    private String email;

    public Exec() {

    }

    public Exec(String position, String email) {
        this.position = position;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
