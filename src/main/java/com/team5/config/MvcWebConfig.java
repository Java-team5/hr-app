package com.team5.config;

import com.team5.dao.Candidate.CandidateDAO;
import com.team5.dao.FeedbackDAO;
import com.team5.dao.User.UserDao;
import com.team5.dao.Vacancy.VacancyDAO;
import com.team5.dao.interfaces.SortFilterCrudDao;
import com.team5.models.Vacancy;
import com.team5.models.VacancyFilter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan("com.team5")
public class MvcWebConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/pages/", ".jsp");
    }

    @Bean("messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:locale/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean(name = "candidateDAO")
    public CandidateDAO candidateDao() {
        return new CandidateDAO();
    }

    @Bean(name = "userDao")
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean(name = "feedbackDAO")
    public FeedbackDAO feedbackDAO() {
        return new FeedbackDAO();
    }

    @Bean(name = "vacancyDAO")
    public SortFilterCrudDao<Vacancy, VacancyFilter> vacancyDAO() {
        return new VacancyDAO();
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        return localeResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

}