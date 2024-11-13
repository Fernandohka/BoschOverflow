package com.duo.duo.model;

// import java.util.HashSet;
// import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="tb_user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private String name;
    @Column
    private String mail;
    @Column
    private String password;
    @Column
    private String edv;

    
    public User(String name, String mail, String password, String edv){
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.edv = edv;
    }
    public User(){}

    public Long getId() {
        return Id;
    }
    
    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEdv() {
        return edv;
    }
    
    public void setEdv(String edv) {
        this.edv = edv;
    }
}
