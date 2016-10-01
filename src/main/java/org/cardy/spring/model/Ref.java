package org.cardy.spring.model;

public class Ref {
    private int user_id;
    private String firstname;
    private String lastname;
    private String email;
    private String hash;
    private String phone;
    private int level;
    private String area;
    private boolean active;

    /**
     * Zero parameter constructor
     */
    public Ref() {

    }

    public Ref(String firstname, String lastname, String email, String hash, String phone, int level, String area, boolean active) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.hash = hash;
        this.phone = phone;
        this.level = level;
        this.area = area;
        this.active = active;
    }

    /*
     * Getters and Setters
     */
    public int getId() {
        return user_id;
    }

    public void setId(int user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
