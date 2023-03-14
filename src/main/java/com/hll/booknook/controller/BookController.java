package com.hll.booknook.controller;

import com.hll.booknook.domain.Book;
import com.hll.booknook.repository.BookRepository;
import com.hll.booknook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books/readed")
    public String create(BookForm form){
        Book book = new Book();
        book.setIsbn(form.getIsbn());

        System.out.println("book = " + book.getIsbn());

        bookService.save(book);

        return "redirect:/html/index.html";
    }
}
