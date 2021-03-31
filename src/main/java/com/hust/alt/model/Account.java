package com.hust.alt.model;

public class Account {
    private int id;
    private String username;
    private String password;
    private String email;
    private String sdt;

    public Account(String username, String password, String email, String sdt) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.sdt = sdt;
    }

    public Account() {
    }

    public Account(int id, String username, String password, String email, String sdt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.sdt = sdt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
