package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.service.UserService;
import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.populator.UserPopulator;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Transactional
@RequestMapping("/")
public class LoginController extends AbstractController {

    private static final String XXX = "/selecionar-perfil";
    private static final String FORM_GLOBAL_ERROR = "form.global.error";
    private static final String REDIRECT_TO_REGISTER = REDIRECT_PREFIX + "/register";
    private static final String REDIRECT_TO_HOMEPAGE = REDIRECT_PREFIX + "/home";
    private static final String REDIRECT_TO_LOGIN = REDIRECT_PREFIX + "/login";

    static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    UserPopulator userPopulator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String redirectPage() {
        return REDIRECT_TO_LOGIN;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView("login/loginPage");
        modelAndView.addObject("user", new UserModel());

        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response,
                                @ModelAttribute("user") UserModel user, BindingResult result,
                                final RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = null;

        if (userService.findByEmail(user.getEmail()) != null) {
            //TODO - Fazer validacao de senha
            LOG.info("User " + user.getEmail() + "exists, redirecting to homepage...");

            modelAndView = new ModelAndView(new RedirectView("user/listAllUsers"));
            redirectAttributes.addFlashAttribute("actualUserEmail", user.getEmail());
            return modelAndView;
        } else {
            LOG.info("User " + user.getEmail() + "doesn't exists, redirecting to login page...");
            modelAndView = new ModelAndView("login/loginPage");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView("login/register");
        modelAndView.addObject("user", new UserModel());

        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView doRegister(HttpServletRequest request, HttpServletResponse response,
                                   final UserModel user, BindingResult result) {

        ModelAndView modelAndView = null;

        if (user.getEmail().contains("@utfpr.edu")) {
            if (userService.findByEmail(user.getEmail()) == null) {
                userPopulator.populeUser(user);
                try {
                    userService.createUser(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LOG.info("User " + user.getEmail() + "success registered!");
            } else {
                LOG.warn("User with email " + user.getEmail() + " already exists!");

                return newPage("login/loginPage", "user");
            }
            return new ModelAndView("home/homepage", "user", user);
        }
        return newPage("login/register", "user");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String doRegister(HttpServletRequest request, HttpServletResponse response) {

        return REDIRECT_TO_LOGIN;
    }

    private ModelAndView newPage(String page, String object) {
        ModelAndView modelAndView = null;

        modelAndView = new ModelAndView(page);
        modelAndView.addObject(object, new UserModel());

        return modelAndView;
    }
}
