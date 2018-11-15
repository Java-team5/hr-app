package com.team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.team5.dao.Candidate.CandidateDAO;
import com.team5.models.CandidateFilter;
import com.team5.models.Candidate;

import java.util.List;
import java.util.Locale;

import static java.lang.Math.ceil;

@Controller
@RequestMapping(value ="/candidate/")
public class CandidateController {
    @Autowired
    CandidateDAO candidateDAO;
    CandidateFilter filter = new CandidateFilter();

    @RequestMapping(value = "/view/{page}/**", method = RequestMethod.GET)
    public ModelAndView setCandidateView(@PathVariable int page, String sort) {
        List<Candidate> candidates = null;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("sort",(sort !=null)?sort:"none");

        int total=5;
        if(page==1){}
        else{
            page=(page-1)*total+1;
        }

        if(sort!=null&&!sort.equals("none")){
            candidates = candidateDAO.getSortedEntitiesByPage(filter, sort, page, total);
        } else{
            candidates = candidateDAO.getEntitiesByPage(filter, page, total);
        }

        float pagesCount = (float) candidateDAO.count(filter) / total;
        int[] pages = new int[(int) ceil(pagesCount)];
        for(int i=0; i<pages.length; i++){
            pages[i] = i + 1;
        }

        modelAndView.getModelMap().addAttribute("type", "view");
        modelAndView.getModelMap().addAttribute("entity", "Candidate");
        modelAndView.addObject("filterInput", filter);
        modelAndView.getModelMap().addAttribute("candidate", candidates);
        modelAndView.getModelMap().addAttribute("pages", pages);
        modelAndView.setViewName("index");
        return modelAndView;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView setCandidateAdd(Locale locale){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("entity", "Candidate");
        modelAndView.getModelMap().addAttribute("type", "add");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewCandidate(@ModelAttribute("newCandidate") Candidate candidate ) {
        candidateDAO.save(candidate);
        return "redirect:/candidate/view/1";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView openEditPage(@PathVariable long id){
        Object candidate = candidateDAO.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("type", "edit");
        modelAndView.getModelMap().addAttribute("entity", "Candidate");
        modelAndView.getModelMap().addAttribute("candidate", candidate);
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editCandidate(@ModelAttribute("editCandidate") Candidate candidate) {
        System.out.println("edit_post_ок");
        candidateDAO.update(candidate);
        return "redirect:/candidate/view/1";
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public ModelAndView openUserAccount(@PathVariable long id){
        Object candidate = candidateDAO.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("type", "account");
        modelAndView.getModelMap().addAttribute("entity", "Candidate");
        modelAndView.getModelMap().addAttribute("candidate", candidate);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String skillSetFilter(@ModelAttribute("filterInput") CandidateFilter filterInput, String sort){
        filter.setName(filterInput.getName());
        filter.setSurname(filterInput.getSurname());
        return "redirect:/candidate/view/1?sort=" + sort;
    }
}
