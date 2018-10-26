package team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import team5.dao.interfaces.EntityDao;
import team5.models.User;
import team5.utils.Utils;

import java.util.List;

@Controller
@RequestMapping(value ="/user/")
public class UserController {

    @Autowired
    EntityDao userDao;

    @RequestMapping(value = "/view/{page}/**", method = RequestMethod.GET)
    public ModelAndView setUserView(@PathVariable int page, String sortBy) {
        int total=5;
        int offset = Utils.getPageOffset(page,total);

        List<User> users = null;

        if(sortBy!="none"){
            users = userDao.getSortedEntitiesByPage(sortBy, offset, total);
        } else {
            users = userDao.getEntitiesByPage(offset, total);
        }

        System.out.println("");

        int[] pages = Utils.getPagesIndexArray(userDao,total);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("type", "view");
        modelAndView.getModelMap().addAttribute("entity", "User");
        modelAndView.getModelMap().addAttribute("user", users);
        modelAndView.getModelMap().addAttribute("pages", pages);
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView serUserAdd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("entity", "User");
        modelAndView.getModelMap().addAttribute("type", "add");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addNewUser(@ModelAttribute("newUser") User user ) {
        userDao.save(user);
        return setUserView(1, "none");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView openEditPage(@PathVariable long id){
        Object user = userDao.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("type", "edit");
        modelAndView.getModelMap().addAttribute("entity", "User");
        modelAndView.getModelMap().addAttribute("user", user);
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView editUser(@ModelAttribute("editUser") User user) {
        userDao.update(user);
        return setUserView(1, "none");
    }
}
