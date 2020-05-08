package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.service.UserService;
import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.util.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Transactional
public class LoginController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    Session session;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView showLogin() {

        ModelAndView modelAndView = new ModelAndView("login/login.html");
        modelAndView.addObject("user", new UserModel());

        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, HttpServletResponse response,
                                @ModelAttribute("user") UserModel user, BindingResult result) {

        ModelAndView modelAndView = new ModelAndView();

        if (userService.doLogin(user.getEmail(), user.getPassword()) != null) {
            LOG.info("User " + user.getEmail() + " exists, redirecting to homepage...");

            UserModel userModel = userService.findByEmail(user.getEmail());

            session.setCurrentUser(userModel);

            modelAndView.addObject("userName", userModel.getName());
            modelAndView.addObject("email", userModel.getEmail());

            return REDIRECT_TO_HOMEPAGE;
        } else {
            LOG.info("User " + user.getEmail() + "doesn't exists, redirecting to login page...");

            return REDIRECT_TO_LOGIN;
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView doSignup(@Valid UserModel user, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();

        UserModel userExists = userService.findByEmail(user.getEmail());

        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");

            LOG.warn("User with email " + user.getEmail() + " already exists!");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("login/signup.html");
        } else {
            userService.createUser(user);
            modelAndView.addObject("msg", "User has been registered successfuly!");
            modelAndView.addObject("user", new UserModel());
            modelAndView.setViewName("login/signup.html");

            LOG.info("User " + user.getEmail() + "success registered!");
        }
        return modelAndView;
    }

    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/access_denied.html");
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        return REDIRECT_TO_LOGIN;
    }
}
