package com.uniovi.notaneitor.entities;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class Mark {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private Double score;
    private Boolean resend = false;

    @ManyToOne
    @JoinColumn(name = "user_id") private User user;

    public Mark() { }

    public Mark(Long id, String description,Double score) {
        this.id = id;
        this.score = score;
        this.description =  description;
    }

    public Mark(String description,Double score,User user) {

        this.score = score;
        this.description = description;
        this.user = user;
    }

    public Mark(Long id, String description,Double score,User user) {
        this.id = id;
        this.score = score;
        this.description = description;
        this.user = user;
    }

    public Boolean getResend() {
        return resend;
    }

    public void setResend(Boolean resend) {
        this.resend = resend;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return Objects.equals(id, mark.id) && Objects.equals(description, mark.description) && Objects.equals(score, mark.score) && Objects.equals(user, mark.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, score, user);
    }
}
