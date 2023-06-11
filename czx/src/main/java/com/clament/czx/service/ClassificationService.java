package com.clament.czx.service;


import com.clament.czx.DataEntity.Classification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sun.swing.StringUIClientPropertyKey;

public interface ClassificationService {
    Classification saveClassification(Classification classification);
    Classification getClassification(Long id);
    Page<Classification> listClassification(Pageable pageable);
    Classification updateClassification(Long id, Classification classification);
    void deleteClassification(Long id);
    Classification getClassificationByName(String name);
}
