package com.team5.controller;

import org.springframework.validation.BindingResult;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface EntityController<T, K> {

    static final String SUCCESS = "Success";
    static final String ERROR = "Error";

    List<T> view(Cookie sortField, final int page);
    T viewById(K id);
    List<T> addSorting(HttpServletResponse response, String sortField);
    String add(T entity, BindingResult result);
    String delete(K id);
    String update(T entity, BindingResult result, K id);
}
