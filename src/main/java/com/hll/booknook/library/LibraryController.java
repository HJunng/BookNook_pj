package com.hll.booknook.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }
    @GetMapping("/")
    public String show_index(){
        return "redirect:/html/index.html";
    }

    @PostMapping("/books/input") // 책 넣기
    public String create(LibraryForm form){
        Library library = new Library();
        library.setIsbn(form.getIsbn());
        library.setCon(form.getCon()); // 0:읽은책 1:읽고있는책 2:읽고싶은책
        library.setMark(form.getMark());
        library.setHope(form.getHope());
        library.setStart_date(form.getStart_date());
        library.setFinish_date(form.getFinish_date());
        library.setEmail("guswjd1gh@gmail.com");  //로그인 정보 가져오면 그걸로 바꾸기.

        System.out.println("library = " + library.getIsbn());

        libraryService.save(library);

        return "redirect:/html/index.html";
    }

    @GetMapping("/myLibrary") // 서재 조회
    public String list(Model model){
        List<Library> libraries = libraryService.findLibrarysByEmail("guswjd1gh@gmail.com");
        model.addAttribute("books",libraries);

        return "showLibrary";
    }
}