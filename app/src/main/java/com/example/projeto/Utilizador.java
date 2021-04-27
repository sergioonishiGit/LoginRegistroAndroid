package com.example.projeto;

public class Utilizador {

    int id;
    String username, pass;

    public Utilizador() {
        id = 0;
        username = "";
        pass = "";
    }

    public Utilizador(int id, String username, String pass) {
        this.id = id;
        this.username = username;
        this.pass = pass;
    }

    public Utilizador(Utilizador u) {
        this.id = u.id;
        this.username = u.username;
        this.pass = u.pass;
    }

    public Utilizador(int id) {
        this.id = id;
        username="" ;
        pass="";
    }

    public Utilizador(String username, String pass) {
        this.id = 0;
        this.username = username;
        this.pass = pass;
    }

    public long getId() {
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
