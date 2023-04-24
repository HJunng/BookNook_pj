package com.hll.booknook.repository;

import com.hll.booknook.domain.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    Library save(Library library);
    Optional<Library> findByIsbnAndEmail(String isbn, String email);
    List<Library> findAllByEmail(String email);
    List<Library> findByCon(Integer con);
}
