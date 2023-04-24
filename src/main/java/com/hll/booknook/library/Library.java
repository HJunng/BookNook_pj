package com.hll.booknook.library;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Library")
public class Library {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    @Column(name = "isbn", length = 30)
    private String isbn;
    @Column(name = "email", length = 200)
    private String email;
    @Column(name = "con")
    private Integer con; //책 읽는 상태
    @Column(name = "start_date")
    private Date start_date;
    @Column(name = "finish_date")
    private Date finish_date;
    @Column(name = "grade")
    private Integer grade;
    @Column(name = "hope", length = 255)
    private String hope;
    @Column(name = "mark")
    private Integer mark;

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
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

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
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
