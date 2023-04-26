package com.hll.booknook.records;

import java.util.Date;

public class RecordsForm {

    private String isbn;
    private String email;
    private String category;
    private Date TimeCreated;
    private String contents;

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
