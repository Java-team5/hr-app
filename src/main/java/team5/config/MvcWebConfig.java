package team5.config;

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
import team5.dao.CandidateDAO;
import team5.dao.FeedbackDAO;
import team5.dao.SkillDao;
import team5.dao.UserDao;
import team5.dao.VacancyDAO;

@Configuration
@EnableWebMvc
@ComponentScan("team5.controller")
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

    @Bean(name = "skillDao")
    public SkillDao skillDao(){
        return new SkillDao();
    }

    @Bean(name = "candidateDAO")
    public CandidateDAO candidateDao(){
        return new CandidateDAO();
    }

    @Bean(name = "userDao")
    public UserDao userDao(){
        return new UserDao();
    }

    @Bean(name = "feedbackDAO")
    public FeedbackDAO feedbackDAO(){return new FeedbackDAO(); }

    @Bean(name = "vacancyDAO")
    public VacancyDAO vacancyDAO(){return new VacancyDAO(); }

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