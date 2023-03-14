package com.hll.booknook.controller;

public class BookForm {

    private String isbn;

    public long getIsbn() {
        return Long.parseLong(isbn);
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
