package com.team5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.team5.dao.User.UserDao;
import com.team5.models.User;
import com.team5.models.UserFilter;

import java.util.List;

import static java.lang.Math.ceil;

@RestController
@RequestMapping(value ="/user/")
public class UserController {

    @Autowired
    UserDao userDao;
    UserFilter filter = new UserFilter();

    @GetMapping(value = "/view/{page}/**")
    public List<User> setUserView(@PathVariable int page, String sort) {
        List<User> users = null;

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
        return users;
    }

    @PostMapping(value = "/add")
    public ResponseEntity addNewUser(@ModelAttribute("newUser") User user) {
        userDao.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/edit")
    public ResponseEntity editUser(@ModelAttribute("editUser") User user) {
        userDao.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/account/{id}")
    public ResponseEntity getById(@PathVariable long id){
        User user = userDao.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String skillSetFilter(@ModelAttribute("filterInput") UserFilter filterInput, String sort){
        filter.setEmail(filterInput.getEmail());
        filter.setName(filterInput.getName());
        filter.setSurname(filterInput.getSurname());
        return "redirect:/user/view/1?sort="+sort;
    }
}
