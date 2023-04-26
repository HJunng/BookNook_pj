package com.hll.booknook.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.Map.Entry;

import static java.util.stream.Collectors.groupingBy;

@Controller
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }
    @GetMapping("/")
    public String show_index(){
        return "index";
    }

    @GetMapping("/books/ranking")//책 순위: 임시 페이지 원래는 redirect:/html/index.html에 나와야하는 기능
    public String rank_index(Model model) {
        List<Library> rankList = this.libraryService.getList();
        //그룹화
        Map<String, List<Library>> collect = rankList.stream().collect(groupingBy((Library::getEmail)));
        Map<String, Integer> ranking = new HashMap<>();
        //mapping된 아이디 별 읽은 책 권수를 ranking map 에 저장!
        for (String key : collect.keySet()) {
            int value = collect.get(key).toString().split(",").length;
            ranking.put(key, value);
        }
        //순위: 비교할 수 있는 리스트로 전환
        List<Entry<String, Integer>> list_entries = new ArrayList<>(ranking.entrySet());
        //값 비교
        Collections.sort(list_entries, (obj1, obj2) -> {
            // 내림 차순 정렬
            return obj2.getValue().compareTo(obj1.getValue());
        });

        //data 파라미터 보내기
        List<HashMap<String, String>> rk = new ArrayList<>();
        for (Entry<String, Integer> entry : list_entries) {
            HashMap<String, String> data = new HashMap<>();
            data.put("email", entry.getKey());
            data.put("cnt", String.valueOf(entry.getValue()));
            rk.add(data);
        }
        model.addAttribute("rankList", rk);
        return "rank_list";
    }

    @PostMapping("/books/input") // 책 넣기
    public String create(LibraryForm form){
        Library library = new Library();
        library.setIsbn(form.getIsbn());
        library.setCon(form.getCon()); // 0:읽은책 1:읽고있는책 2:읽고싶은책
        library.setAuthor(form.getAuthor());
        library.setMark(form.getMark());
        library.setHope(form.getHope());
        library.setStart_date(form.getStart_date());
        library.setFinish_date(form.getFinish_date());
        library.setEmail("guswjd1gh@gmail.com");  //로그인 정보 가져오면 그걸로 바꾸기.

        System.out.println("library = " + library.getIsbn());

        libraryService.save(library);

        return "index";
    }

    @GetMapping("/myLibrary") // 서재 조회
    public String list(Model model){
        List<Library> libraries = libraryService.findLibrarysByEmail("guswjd1gh@gmail.com");
        model.addAttribute("books",libraries);

        return "showLibrary";
    }
}