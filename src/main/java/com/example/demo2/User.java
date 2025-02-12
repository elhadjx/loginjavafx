package com.example.demo2;

public class User {
    private String email;
    private String firstname;
    private String lastname;
    private String password;

    public User(String email){
        this.email = email;
        this.firstname = capitalizeFirstLetter(email.split("@")[0].split("\\.")[0]);
        this.lastname = capitalizeFirstLetter(email.split("@")[0].split("\\.")[1]);
        this.password = firstname + lastname;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\''+
                '}';
    }

    public boolean isLoginValid(String in_password){
        if (in_password.equals(password)) return true;
        return false;
    }
    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFullName() { return firstname + " " + lastname; }
    String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        str = str.toLowerCase();
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
