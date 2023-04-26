package com.hll.booknook.records;

import com.hll.booknook.records.Records;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Records, Long> {

    Records save(Records record);
    List<Records> findAllByEmail(String email);
    List<Records> findAllByEmailAndIsbn(String email, String isbn);
}
