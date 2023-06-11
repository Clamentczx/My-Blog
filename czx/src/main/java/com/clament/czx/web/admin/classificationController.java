package com.clament.czx.web.admin;

import com.clament.czx.DataEntity.Classification;
import com.clament.czx.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
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
public class classificationController {
    @Autowired
    private ClassificationService classificationService;

    @GetMapping("/Classification-M")
    public String list(@PageableDefault(size = 8, sort = {"id"}, direction= Sort.Direction.DESC) Pageable pageable,
                       Model model){
        model.addAttribute("page",classificationService.listClassification(pageable));
        return "admin/Classification-M";
    }

    @GetMapping("/Classification-M/Classification-P")
    public String publish(Model model){
        model.addAttribute("classification",new Classification());
        return "admin/Classification-P";
    }

    @PostMapping("/Classification-M")
    public String post(@Valid Classification classification,BindingResult result,RedirectAttributes attributes){
        Classification classification2 = classificationService.getClassificationByName(classification.getName());
        if (classification2 != null){
            result.rejectValue("name","nameError", "warning:name is repeatable");
        }
        if (result.hasErrors()){
            return "admin/Classification-P";
        }
        Classification classification1 = classificationService.saveClassification(classification);
        if(classification1 ==null){
            attributes.addFlashAttribute("message","add failed");
        }
        else{
            attributes.addFlashAttribute("message","add succeeded");
        }
        return "redirect:/admin/Classification-M";
    }

    @GetMapping("/Classification-M/{id}/Classification-P")
    public String editor(@PathVariable Long id, Model model){
        model.addAttribute("classification",classificationService.getClassification(id));
        return "admin/Classification-P";
    }

    @PostMapping("/Classification-M/{id}")
    public String editpost(@Valid Classification classification,BindingResult result ,RedirectAttributes attributes,@PathVariable Long id){
        Classification classification2 = classificationService.getClassificationByName(classification.getName());
        if (classification2 != null){
            result.rejectValue("name","nameError", "warning:name is repeatable");
        }
        if (result.hasErrors()){
            return "admin/Classification-P";
        }
        Classification classification1 = classificationService.updateClassification(id, classification);
        if(classification1 ==null){
            attributes.addFlashAttribute("message","update failed");
        }
        else{
            attributes.addFlashAttribute("message","update  succeeded");
        }
        return "redirect:/admin/Classification-M";
    }

    @GetMapping("/Classification-M/{id}/Classification-D")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        classificationService.deleteClassification(id);
        attributes.addFlashAttribute("message","delete succeed");
        return "redirect:/admin/Classification-M";
    }

}
