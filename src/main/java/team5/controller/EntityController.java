package team5.controller;

import org.springframework.validation.BindingResult;
import team5.models.Skill;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface EntityController<T,K> {

    static final String SUCCESS = "Success";
    static final String ERROR = "Error";

    public List<T> view(Cookie sortField, final int page);
    public T viewById(K id);
    public List<T> addSorting(HttpServletResponse response, String sortField);
    public String add(T entity, BindingResult result);
    public String delete(K id);
    public String update(T entity, BindingResult result, K id);
}
