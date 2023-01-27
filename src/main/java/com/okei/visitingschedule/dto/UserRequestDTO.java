package com.okei.visitingschedule.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserRequestDTO implements Serializable{

    private String username;
    private String password;
    private String lastname;
    private String firstname;
    private String middlename;
    private Long positionId;

    public UserRequestDTO(String username, String password, String lastname, String firstname, String middlename,Long positionId) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.positionId = positionId;
    }

    public UserRequestDTO(String username, String password, String lastname, String firstname,Long positionId) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.positionId = positionId;
    }

    public UserRequestDTO(String username, String password,Long positionId) {
        this.username = username;
        this.password = password;
        this.positionId = positionId;
    }

    public UserRequestDTO() {
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
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

    public Long getPositionId() {
        return positionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequestDTO that = (UserRequestDTO) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(lastname, that.lastname) && Objects.equals(firstname, that.firstname) && Objects.equals(middlename, that.middlename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, lastname, firstname, middlename);
    }

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", positionId=" + positionId +
                '}';
    }
}
