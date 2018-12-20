package com.hwang.secure;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public String bio;
    public String userName;
    public String password;

    public ApplicationUser(){};

    public ApplicationUser(String firstName, String lastName, String dateOfBirth, String bio, String userName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.userName = userName;
        this.password = password;
    }

}
