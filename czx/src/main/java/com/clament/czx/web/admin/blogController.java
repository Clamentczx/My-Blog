package com.clament.czx.web.admin;


import com.clament.czx.DataEntity.Blog;
import com.clament.czx.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class blogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/admin")
    public String blogs(@PageableDefault(size = 5, sort = {"updateTime"}, direction= Sort.Direction.DESC) Pageable pageable, Blog blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/admin";
    }


    @GetMapping("/admin/publish")
    public String publish(){
        return "admin/publish";
    }
}
