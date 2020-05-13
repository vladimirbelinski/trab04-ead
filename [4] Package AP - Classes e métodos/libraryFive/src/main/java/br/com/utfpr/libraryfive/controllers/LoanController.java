package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.model.CollectionModel;
import br.com.utfpr.libraryfive.model.LoanModel;
import br.com.utfpr.libraryfive.service.CollectionService;
import br.com.utfpr.libraryfive.service.LoanService;
import br.com.utfpr.libraryfive.util.DateUtils;
import br.com.utfpr.libraryfive.util.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/loan")
public class LoanController extends AbstractController {

    @Autowired
    private Session session;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private LoanService loanService;

    @Autowired
    private CollectionService collectionService;

    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String newLoan(HttpServletRequest request, ModelAndView modelAndView) {

        String collectionTitle = request.getParameter("quantity").trim();
        Integer collectionQtd = Integer.valueOf(request.getParameter("quantidade"));

        CollectionModel collection = collectionService.findByTitle(collectionTitle);
        if (collection != null && collectionService.isAvailable(collection, collectionQtd))
            // fazer o empréstimo
            loanService.makeLoan(collectionTitle, collectionQtd);

        return REDIRECT_TO_MY_LOANS;
    }

    @RequestMapping(value = "/myloans", method = RequestMethod.GET)
    public ModelAndView showMyLoans(HttpServletRequest request) {

        List<LoanModel> loans = loanService.listAllByEmail(session.getCurrentUser().getEmail());

        if (!loans.isEmpty()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("loan/userLoan");
            modelAndView.addObject("loans", loans);
            modelAndView.addObject("userName", session.getCurrentUser().getName());

            return modelAndView;
        }
        // retorna erro
        return null;
    }
}
