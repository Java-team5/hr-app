package com.team5.controller;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;

public class MainControllerTest {

    @Test
    public void initPath() {
        MainController mainController = new MainController();
        assertEquals(mainController.initPath().getViewName(), "index");
    }
}