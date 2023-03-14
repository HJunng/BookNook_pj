package com.hll.booknook;

import com.hll.booknook.repository.BookRepository;
import com.hll.booknook.repository.JpaBookRepository;
import com.hll.booknook.service.BookService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public BookService bookService(){
        return new BookService(bookRepository());
    }

    @Bean
    public BookRepository bookRepository(){
        return new JpaBookRepository(em);
    }
}
