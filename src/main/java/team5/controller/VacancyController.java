package team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import team5.dao.Vacancy.VacancyDAO;
import team5.dao.interfaces.EntityDao;
import team5.dao.interfaces.SortFilterCrudDao;
import team5.models.Feedback;
import team5.models.Vacancy;
import team5.models.VacancyFilter;
import team5.utils.Utils;

import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value ="/vacancy/")
public class VacancyController {
    @Autowired
    private SortFilterCrudDao<Vacancy, VacancyFilter> vacancyDAO;
    private VacancyFilter filter = new VacancyFilter();

    @RequestMapping(value = "/view/{page}/**", method = RequestMethod.GET)
    public ModelAndView setFeedbackView(@PathVariable int page, String sort) {
        List<Vacancy> vacancies = null;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("sort",(sort !=null)?sort:"none");
        int total = 5;
        int offset = Utils.getPageOffset(page,total);

        if(sort!=null&&!sort.equals("none")){
            vacancies=vacancyDAO.getSortedEntitiesByPage(filter, sort, page, total);
        } else{
            vacancies = vacancyDAO.getEntitiesByPage(filter, page, total);
        }

        int[] pages = Utils.getPagesIndexArray(vacancyDAO,total);

        modelAndView.getModelMap().addAttribute("type", "view");
        modelAndView.getModelMap().addAttribute("entity", "Vacancy");
        modelAndView.addObject("filterInput", filter);
        modelAndView.getModelMap().addAttribute("vacancies", vacancies);
        modelAndView.getModelMap().addAttribute("pages", pages);
        modelAndView.setViewName("index");
        return modelAndView;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView getVacancyEditFom(Locale locale){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("entity", "Vacancy");
        modelAndView.getModelMap().addAttribute("type", "add");
        //modelAndView.getModelMap().addAttribute("modelName", "newVacancy");//------
        modelAndView.addObject("vacancy", new Vacancy());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String setNewVacancy(@ModelAttribute("vacancy") @Valid Vacancy vacancy, BindingResult result){
        if (result.hasErrors()) {
            return "redirect:/vacancy/add";
        }
        vacancyDAO.save(vacancy);
        return "redirect:/vacancy/view/1";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView getEditVacancyPage(@PathVariable long id){
        Object vacancy = vacancyDAO.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("type", "edit");
        modelAndView.getModelMap().addAttribute("entity", "Vacancy");
        //modelAndView.getModelMap().addAttribute("modelName", "editVacancy");//------
        modelAndView.addObject("vacancy", vacancy);
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editVacancy(@ModelAttribute("vacancy") @Valid Vacancy vacancy, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/vacancy/updateSkill/" + vacancy.getId();
        }
        vacancyDAO.update(vacancy);
        return "redirect:/vacancy/view/1";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteSkill(@PathVariable final long id) {
        vacancyDAO.delete(id);
        return "redirect:/vacancy/view/1";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String skillSetFilter(@ModelAttribute("filterInput") VacancyFilter filterInput, String sort){
        filter.setPosition(filterInput.getPosition());
        return "redirect:/vacancy/view/1?sort="+sort;
    }
}
