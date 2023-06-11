package com.clament.czx.DAO;


import com.clament.czx.DataEntity.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationRepository extends JpaRepository<Classification,Long> {
    Classification findByName(String name);
}
