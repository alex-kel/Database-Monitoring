package ru.itis.core.entities;

/**
 * Created by Alex on 22.12.16.
 */
public class User {

    private long id;
    private String nick, firstName, lastName;

    public User() {
    }

    public User(long id, String nick, String firstName, String lastName) {
        this.id = id;
        this.nick = nick;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
