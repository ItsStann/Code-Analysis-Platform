package com.codeanalysis.platform.repository;

import com.codeanalysis.platform.model.ScanRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScanRecordRepo extends JpaRepository<ScanRecord, Long> {
    List<ScanRecord> findByFileName(String fileName);
}
