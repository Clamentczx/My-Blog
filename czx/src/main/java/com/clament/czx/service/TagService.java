package com.clament.czx.service;

import com.clament.czx.DataEntity.Classification;
import com.clament.czx.DataEntity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {
    Tag saveTag(Tag tag);
    Tag getTag(Long id);
    Page<Tag> listTag(Pageable pageable);
    Tag updateTag(Long id, Tag tag);
    void deleteTag(Long id);
    Tag getTagByName(String name);
}
