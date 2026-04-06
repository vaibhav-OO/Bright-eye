package com.example.bright_eyed;

public class Userbright {

    String Email,Password,Name,Address,Number;

    public Userbright(){

    }

    public Userbright(String address, String number) {
        Address = address;
        Number = number;
    }

    public String getAddress() {
        return Address;
    }

    public String getNumber() {
        return Number;
    }

    public Userbright(String email, String password, String name) {
        Email = email;
        Password = password;
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
