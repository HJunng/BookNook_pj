package com.hll.booknook.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LibraryForm {
    private String isbn;
    private String email;
    private int con;
    private Date start_date;
    private Date finish_date;
    private int grade;
    private String hope;
    private int mark;

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

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.start_date = format.parse(start_date);
    }

    public Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(String finish_date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.finish_date = format.parse(finish_date);
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getHope() {
        return hope;
    }

    public void setHope(String hope) {
        this.hope = hope;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
