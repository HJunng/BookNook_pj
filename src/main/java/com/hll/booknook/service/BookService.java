package com.hll.booknook.service;

import com.hll.booknook.domain.Book;
import com.hll.booknook.repository.BookRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public long save(Book book){
        bookRepository.save(book);
        return book.getIsbn();
    }
}
