package com.hll.booknook;

import com.hll.booknook.repository.LibraryRepository;
import com.hll.booknook.service.LibraryService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    //EasyJpaLibraryRepository 사용.
    private final LibraryRepository libraryRepository;

    @Autowired
    public SpringConfig(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }
    @Bean
    public LibraryService libraryService(){
        return new LibraryService(libraryRepository);
    }

}
