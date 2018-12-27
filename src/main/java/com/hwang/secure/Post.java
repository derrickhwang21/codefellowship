package com.hwang.secure;

import com.hwang.secure.ApplicationUser;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;


@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String body;
    public String createdAt;

    @ManyToOne
    public ApplicationUser applicationUser;

    Post(){}

    Post(String createdAt, String body){
        this.body = body;
        this.createdAt = createdAt;
    }
}

