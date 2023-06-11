package com.clament.czx.web.admin;

import com.clament.czx.DataEntity.Classification;
import com.clament.czx.DataEntity.Tag;
import com.clament.czx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class tagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/Tag-M")
    public String list(@PageableDefault(size = 8, sort = {"id"}, direction= Sort.Direction.DESC) Pageable pageable,
                       Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/Tag-M";
    }

    @GetMapping("/Tag-M/Tag-P")
    public String publish(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/Tag-P";
    }

    @PostMapping("/Tag-M")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tag2 = tagService.getTagByName(tag.getName());
        if (tag2 != null){
            result.rejectValue("name","nameError", "warning:name is repeatable");
        }
        if (result.hasErrors()){
            return "admin/Tag-P";
        }
        Tag tag1 = tagService.saveTag(tag);
        if(tag1 ==null){
            attributes.addFlashAttribute("message","add failed");
        }
        else{
            attributes.addFlashAttribute("message","add succeeded");
        }
        return "redirect:/admin/Tag-M";
    }

    @GetMapping("/Tag-M/{id}/Tag-P")
    public String editor(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/Tag-P";
    }

    @PostMapping("/Tag-M/{id}")
    public String editpost(@Valid Tag tag,BindingResult result ,RedirectAttributes attributes,@PathVariable Long id){
        Tag tag2 = tagService.getTagByName(tag.getName());
        if (tag2 != null){
            result.rejectValue("name","nameError", "warning:name is repeatable");
        }
        if (result.hasErrors()){
            return "admin/Tag-P";
        }
        Tag tag1 = tagService.updateTag(id, tag);
        if(tag1 ==null){
            attributes.addFlashAttribute("message","update failed");
        }
        else{
            attributes.addFlashAttribute("message","update  succeeded");
        }
        return "redirect:/admin/Tag-M";
    }

    @GetMapping("/Tag-M/{id}/Tag-D")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","delete succeed");
        return "redirect:/admin/Tag-M";
    }
}
