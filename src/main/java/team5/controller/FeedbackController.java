package team5.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import team5.dao.Feedback.FeedbackCRUDDAO;
import team5.models.Feedback;
import team5.utils.Utils;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value ="/feedback/")
public class FeedbackController {
    @Autowired
    FeedbackCRUDDAO feedbackDAO;

    @RequestMapping(value = "/view/{page}/**", method = RequestMethod.GET)
    public ModelAndView setFeedbackView(Locale locale, @PathVariable int page) {
        int total = 5;

        int offset = Utils.getPageOffset(page,total);

        List<Feedback> feedbacks = feedbackDAO.getEntitiesByPage(offset, total);

        int[] pages = Utils.getPagesIndexArray(feedbackDAO,total);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("type", "view");
        modelAndView.getModelMap().addAttribute("entity", "Feedback");
        modelAndView.getModelMap().addAttribute("feedback", feedbacks);
        modelAndView.getModelMap().addAttribute("pages", pages);

        modelAndView.setViewName("index");
        return modelAndView;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView setFeedbackAdd() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("entity", "Feedback");
        modelAndView.getModelMap().addAttribute("type", "add");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewFeedback(@ModelAttribute("newFeedback") Feedback feedback) {
        feedbackDAO.save(feedback);
        return "redirect:/feedback/view/1";
    }

    @RequestMapping(value = "/edit/{id1}|{id2}", method = RequestMethod.GET)
    public ModelAndView openEditPage(@PathVariable long id1, @PathVariable long id2) {
        Object feedback = feedbackDAO.getByIds(id1, id2);


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("type", "edit");
        modelAndView.getModelMap().addAttribute("entity", "Feedback");
        modelAndView.getModelMap().addAttribute("feedback", feedback);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("editFeedback") Feedback feedback) {
        feedbackDAO.update(feedback);
        return "redirect:/feedback/view/1";
    }

    @RequestMapping(value = "/account/{id1}|{id2}", method = RequestMethod.GET)
    public ModelAndView openUserAccount(@PathVariable long id1, @PathVariable long id2) {
        Object feedback = feedbackDAO.getByIds(id1, id2);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("type", "account");
        modelAndView.getModelMap().addAttribute("entity", "Feedback");
        modelAndView.getModelMap().addAttribute("feedback", feedback);
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
