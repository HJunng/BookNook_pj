package com.hll.booknook.records;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "records") //바꾸기!!!
public class Records {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;

    @Column(name = "isbn")
    private String isbn; //바꾸기!

    @Column(name = "email")
    private String email;

    @Column(name = "category")
    private String category;

    @Column(name = "TimeCreated")
    private Date TimeCreated;

    @Column(name = "contents")
    private String contents;

    public Long getId() {
        return id;
    }
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getTimeCreated() {
        return TimeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        TimeCreated = timeCreated;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
