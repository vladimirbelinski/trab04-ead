package br.com.utfpr.libraryfive.config;

import br.com.utfpr.libraryfive.DAO.CollectionDao;
import br.com.utfpr.libraryfive.DAO.UserDao;
import br.com.utfpr.libraryfive.controllers.AbstractController;
import br.com.utfpr.libraryfive.controllers.CollectionController;
import br.com.utfpr.libraryfive.controllers.HomeController;
import br.com.utfpr.libraryfive.controllers.LoginController;
import br.com.utfpr.libraryfive.model.*;
import br.com.utfpr.libraryfive.populator.UserPopulator;
import br.com.utfpr.libraryfive.service.CollectionService;
import br.com.utfpr.libraryfive.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
// informa quais serão as classes à serem lidas
@ComponentScan(basePackages = "br.com.utfpr.libraryfive")
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/pages/");
        resolver.setSuffix(".jsp");

        return resolver;
    }

    @Bean
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }
}
