package team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import team5.dao.interfaces.EntityDao;
import team5.models.Feedback;
import team5.models.Candidate;
import team5.utils.Utils;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value ="/candidate/")
public class CandidateController {
    @Autowired
    EntityDao candidateDAO;

    @RequestMapping(value = "/view/{page}/**", method = RequestMethod.GET)
    public ModelAndView setCandidateView(@PathVariable int page, String sortBy) {
        int total = 5;
        int offset = Utils.getPageOffset(page,total);

        List<Candidate> candidates = null;
        System.out.println(sortBy);

        if(sortBy!="none"){
            candidates = candidateDAO.getSortedEntitiesByPage(sortBy, offset, total);
        } else {
            candidates = candidateDAO.getEntitiesByPage(offset, total);
        }

        int[] pages = Utils.getPagesIndexArray(candidateDAO,total);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("type", "view");
        modelAndView.getModelMap().addAttribute("entity", "Candidate");
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
    @ResponseBody
    public ModelAndView addNewCandidate(@ModelAttribute("newCandidate") Candidate candidate ) {
        candidateDAO.save(candidate);
        return setCandidateView(1, "none");
    }
}
