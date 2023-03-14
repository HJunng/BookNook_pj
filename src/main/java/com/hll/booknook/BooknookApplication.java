package com.hll.booknook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.hll.user"})
@EnableJpaRepositories({"com.hll.user"})
@SpringBootApplication
public class BooknookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooknookApplication.class, args);
        System.out.println("안녕하세요");
    }
}
