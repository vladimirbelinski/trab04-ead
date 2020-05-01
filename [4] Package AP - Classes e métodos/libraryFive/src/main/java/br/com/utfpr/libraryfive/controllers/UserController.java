package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.service.UserService;
import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.populator.UserPopulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/user")
public class UserController {

    static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    UserPopulator userPopulator;

    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView deleteUser(@RequestParam(value = "actualUserEmail", required = true) String actualUserEmail,
                                   HttpServletRequest request, HttpServletResponse response) {

        UserModel actualUser = userService.findByEmail(actualUserEmail);
        userService.deleteUser(actualUser);

        return newPage("login/loginPage", "user");
    }

    @RequestMapping(value = "/listAllUsers", method = RequestMethod.GET)
    public ModelAndView listAllUsers(@ModelAttribute("actualUserEmail") final String actualUserEmail,
                                     HttpServletRequest request, HttpServletResponse response,
                                     BindingResult result) {

        // TODO - Implementar verificação se usuario atual é admin para visualizar esta tela
        List<UserModel> users = userService.findAllUsers();

        UserModel actualUser = userService.findByEmail(actualUserEmail);

        ModelAndView modelAndView = new ModelAndView("home/homepage");
        modelAndView.addObject("users", users);
        modelAndView.addObject("user", actualUser);
        LOG.info("Users success retrieved!");

        return modelAndView;
    }

    private ModelAndView newPage(String page, String object) {
        ModelAndView modelAndView = null;

        modelAndView = new ModelAndView(page);
        modelAndView.addObject(object, new UserModel());

        return modelAndView;
    }
}
