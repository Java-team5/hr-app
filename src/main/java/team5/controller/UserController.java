package team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import team5.dao.User.UserDao;
import team5.models.User;
import team5.models.UserFilter;

import java.util.List;

import static java.lang.Math.ceil;

@Controller
@RequestMapping(value ="/user/")
public class UserController {

    @Autowired
    UserDao userDao;
    UserFilter filter = new UserFilter();

    @RequestMapping(value = "/view/{page}/**", method = RequestMethod.GET)
    public ModelAndView setUserView(@PathVariable int page, String sort) {
        List<User> users = null;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("sort",(sort !=null)?sort:"none");

        int total=5;
        if(page==1){}
        else{
            page=(page-1)*total+1;
        }

        if(sort!=null&&!sort.equals("none")){
            users=userDao.getSortedEntitiesByPage(filter, sort, page, total);
        } else{
            users = userDao.getEntitiesByPage(filter, page, total);
        }


        float pagesCount = (float) userDao.count(filter) / total;
        int[] pages = new int[(int) ceil(pagesCount)];
        for(int i=0; i<pages.length; i++){
            pages[i] = i + 1;
        }


        modelAndView.getModelMap().addAttribute("type", "view");
        modelAndView.getModelMap().addAttribute("entity", "User");
        modelAndView.addObject("filterInput", filter);
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
    public String addNewUser(@ModelAttribute("newUser") User user) {
        userDao.save(user);
        return "redirect:/user/view/1";
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
    public String editUser(@ModelAttribute("editUser") User user) {
        userDao.update(user);
        return "redirect:/user/view/1";
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public ModelAndView openUserAccount(@PathVariable long id){
        Object user = userDao.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("type", "account");
        modelAndView.getModelMap().addAttribute("entity", "User");
        modelAndView.getModelMap().addAttribute("user", user);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String skillSetFilter(@ModelAttribute("filterInput") UserFilter filterInput, String sort){
        filter.setEmail(filterInput.getEmail());
        filter.setName(filterInput.getName());
        filter.setSurname(filterInput.getSurname());
        return "redirect:/user/view/1?sort="+sort;
    }
}
