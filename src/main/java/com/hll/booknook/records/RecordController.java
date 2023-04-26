package com.hll.booknook.records;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class RecordController {

    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }
    @RequestMapping("/record/writing")
    public String writingRecord(@RequestParam String isbn, Model model){

        model.addAttribute("isbn",isbn);
        return "writingRecord";
    }
    @RequestMapping("/record/show")
    public String showRecordsByIsbn(@RequestParam String email, @RequestParam String isbn, Model model){
        List<Records> records = recordService.findAllByEmailAndIsbn(email,isbn);
        model.addAttribute("records",records);
        model.addAttribute("userEmail",email);

        return "showRecords";
    }

    @PostMapping("/record/save")
    public String save(RecordsForm form){
        //page 추가할까?
        Records record = new Records();
        record.setIsbn(form.getIsbn());
        record.setEmail("guswjd1gh@gmail.com");
        record.setTimeCreated(new Date());
        record.setContents(form.getContents());

        recordService.save(record);

        //return "showRecords"; //여기로 가면 showAll처럼 매핑이 안되서 아직 안보임.
        return "index";
    }

    // 기록보기로 들어가면 email에 해당되는 기록들 전부 나옴.
    @GetMapping("/records/showAll")
    public String showAllRecords(Model model){
        List<Records> records = recordService.findAllByEmail("guswjd1gh@gmail.com");
        model.addAttribute("records",records);
        model.addAttribute("userEmail","guswjd1gh@gmail.com");


        return "showRecords";
    }
}
