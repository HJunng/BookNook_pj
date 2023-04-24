package com.hll.booknook.records;

import com.hll.booknook.records.Records;
import com.hll.booknook.records.RecordRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public Long save(Records record){
        recordRepository.save(record);

        return record.getId();
    }

    public List<Records> findAllByEmailAndIsbn(String email, String isbn){
        return recordRepository.findAllByEmailAndIsbn(email, isbn);
    }
    // 회원별 기록 조회
    public List<Records> findAllByEmail(String email){
        return recordRepository.findAllByEmail(email);
    }
}
