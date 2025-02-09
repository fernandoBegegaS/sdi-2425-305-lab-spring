package com.uniovi.notaneitor.entities;

import jakarta.persistence.*;

@Entity
public class Mark {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private Double score;

    @ManyToOne
    @JoinColumn(name = "user_id") private User user;

    public Mark() { }

    public Mark(Long id, String description,Double score) {
        this.id = id;
        this.score = score;
        this.description = description;
    }

    public Mark(String description,Double score,User user) {
        this.id = id;
        this.score = score;
        this.description = description;
        this.user = user;
    }

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", score=" + score +
                '}';
    }
}
