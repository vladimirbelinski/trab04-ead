package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.util.Session;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
@RequestMapping("/home")
public class HomeController extends AbstractController {

    static final Logger log = Logger.getLogger(HomeController.class);

    @Autowired
    Session session;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView) {

        if (session.getCurrentUser().getAdmin()) {
            modelAndView.setViewName("home/homeAdmin");
        } else {
            modelAndView.setViewName("home/homeUser");
        }

        modelAndView.addObject("userName", session.getCurrentUser().getName());

        return modelAndView;
    }
}
