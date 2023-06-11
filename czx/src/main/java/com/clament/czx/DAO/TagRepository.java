package com.clament.czx.DAO;

import com.clament.czx.DataEntity.Classification;
import com.clament.czx.DataEntity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);
}
