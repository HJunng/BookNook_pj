package com.hll.booknook;

import com.hll.booknook.library.LibraryRepository;
import com.hll.booknook.library.LibraryService;
import com.hll.booknook.records.RecordRepository;
import com.hll.booknook.records.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    //EasyJpaLibraryRepository 사용.
    private final LibraryRepository libraryRepository;
    private final RecordRepository recordRepository;



    @Autowired
    public SpringConfig(LibraryRepository libraryRepository, RecordRepository recordRepository) {
        this.libraryRepository = libraryRepository;
        this.recordRepository = recordRepository;
    }


    @Bean
    public LibraryService libraryService(){
        return new LibraryService(libraryRepository);
    }

    @Bean
    public RecordService recordService(){
        return new RecordService(recordRepository);
    }
}
