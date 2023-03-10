package com.springstudy.models;

public class Client {
    private Integer cliendId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Boolean isDeleted;

    public Client(Integer cliendId, String firstName, String lastName, String phoneNumber, String email, Boolean isDeleted) {
        this.cliendId = cliendId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isDeleted = isDeleted;
    }

    public Integer getCliendId() {
        return cliendId;
    }

    public void setCliendId(Integer cliendId) {
        this.cliendId = cliendId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
