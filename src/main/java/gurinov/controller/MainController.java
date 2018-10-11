package gurinov.controller;

import gurinov.MavenVersion;
import gurinov.WorkWithDate;
import gurinov.model.Birthday;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

@Controller
@RequestMapping("/")
public class MainController {

    //private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public ModelAndView setBirthdayFromInput(Locale locale){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("version", MavenVersion.getVersion());
        modelAndView.addObject("dateFromInput", new Birthday());
        modelAndView.setViewName("main_page");
        //logger.info("The client locale is {}.",locale);
        //logger.info("The client drowses main_page.jsp");
        return modelAndView;
    }

    @RequestMapping(value = "/dateInfo", method = RequestMethod.POST)
    public String getInfoAboutDate(@ModelAttribute("dateFromInput") Birthday birthday, ModelMap model){
        model.addAttribute("age", WorkWithDate.calculateAge(birthday.getDate()));
        model.addAttribute("dayBeforeBirthday", WorkWithDate.calculateDaysBeforeBirthday(birthday.getDate()));
        //logger.info("The client drowses birthday_info_page.jsp");
        return "birthday_info_page";
    }

}

