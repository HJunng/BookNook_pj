package com.hll.booknook.repository;

import com.hll.booknook.domain.Book;
import jakarta.persistence.EntityManager;

public class JpaBookRepository implements BookRepository{
    private final EntityManager em;

    public JpaBookRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book save(Book book) {
        em.persist(book);
        return book;
    }
}
