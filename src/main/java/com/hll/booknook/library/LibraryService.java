package com.hll.booknook.library;

import com.hll.booknook.library.Library;
import com.hll.booknook.library.LibraryRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Library service.
 */
@Transactional //jpa 때문에 있어야 함.
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    //서재 저장
    public Long save(Library library) {
        validateDuplicateLibrary(library); //중복 검증
        libraryRepository.save(library);

        return library.getPk();
    }

    private void validateDuplicateLibrary(Library library) {
        libraryRepository.findByIsbnAndEmail(library.getIsbn(), library.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 해당 책을 서재에 담으셨습니다.");
                });
    }
    // 회원별 서재 조회
    public List<Library> findLibrarysByEmail(String email){
        return libraryRepository.findAllByEmail(email);
    }
}
