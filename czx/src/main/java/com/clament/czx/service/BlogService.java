package com.clament.czx.service;

import com.clament.czx.DataEntity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BlogService {
    Blog getBlog(Long id);
    Page<Blog> listBlog(Pageable pageable, Blog blog);
    Blog saveBlog(Blog blog);
    Blog updateBolg(Long id, Blog blog);
    void deleteBlog(Long id);
}
