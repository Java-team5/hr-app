package com.team5.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainControllerTest {

    @Test
    public void initPath() {
        MainController mainController = new MainController();
        assertEquals(mainController.initPath().getViewName(), "index");
    }
}