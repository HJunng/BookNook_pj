package com.hll.booknook.repository;

import com.hll.booknook.domain.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Long> {

    Library save(Library library);
    Optional<Library> findByIsbnAndEmail(String isbn, String email);
    List<Library> findAllByEmail(String email);

}
