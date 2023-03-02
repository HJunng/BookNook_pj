package com.hll.booknook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooknookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooknookApplication.class, args);
        System.out.println("안녕하세요");
    }
}
