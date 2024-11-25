package com.duo.duo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Question {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name="userSpaceId")
    private UserSpace userSpace;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserSpace getUserSpace() {
        return userSpace;
    }
    
    public void setUserSpace(UserSpace userSpace) {
        this.userSpace = userSpace;
    }
}
