package com.hll.booknook.service;

import com.hll.booknook.domain.Library;
import com.hll.booknook.repository.LibraryRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Library service.
 */
@Transactional //jpa 땜시 있어야 함.
public class LibraryService {

    private final LibraryRepository libraryRepository;

    /**
     * A new Library service.
     *
     */
    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    /**
     * 서재 저장
     *
     * @param library
     * @return 서재 pk
     */
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

    /**
     * 회원별 서재 조회
     *
     * @param email
     * @return the list
     */
    public List<Library> findLibrarysByEmail(String email){
        return libraryRepository.findAllByEmail(email);
    }
}
