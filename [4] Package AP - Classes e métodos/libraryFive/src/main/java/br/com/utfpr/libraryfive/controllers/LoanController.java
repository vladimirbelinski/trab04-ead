package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.model.CollectionCopyModel;
import br.com.utfpr.libraryfive.model.CollectionModel;
import br.com.utfpr.libraryfive.model.LoanModel;
import br.com.utfpr.libraryfive.model.ReturnModel;
import br.com.utfpr.libraryfive.service.CollectionCopyService;
import br.com.utfpr.libraryfive.service.CollectionService;
import br.com.utfpr.libraryfive.service.LoanService;
import br.com.utfpr.libraryfive.service.ReturnService;
import br.com.utfpr.libraryfive.util.DateUtils;
import br.com.utfpr.libraryfive.util.FormatUtils;
import br.com.utfpr.libraryfive.util.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/loan")
public class LoanController extends AbstractController {

    @Autowired
    private Session session;

    @Autowired
    private FormatUtils formatUtils;

    @Autowired
    private LoanService loanService;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CollectionCopyService collectionCopyService;

    @Autowired
    private ReturnService returnService;

    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String newLoan(HttpServletRequest request, ModelAndView modelAndView) {

        Integer collectionId = formatUtils.getIntegerValue(request.getParameter("collectionId"));
        Integer collectionQty = formatUtils.getIntegerValue(request.getParameter("quantity"));

        if (collectionId != null && collectionService.isAvailable(collectionId, collectionQty))
            // fazer o empréstimo
            loanService.makeLoan(collectionId, collectionQty);

        return REDIRECT_TO_MY_LOANS;
    }

    @RequestMapping(value = "/myloans", method = RequestMethod.GET)
    public ModelAndView showMyLoans(HttpServletRequest request) {

        List<LoanModel> loans = loanService.listAllByEmail(session.getCurrentUser().getEmail());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loan/userLoan");
        modelAndView.addObject("loans", loans);
        modelAndView.addObject("userName", session.getCurrentUser().getName());
        modelAndView.addObject("baseUrl", session.getBaseUrl(request));

        return modelAndView;
    }

    @RequestMapping(value = {"/return"}, method = RequestMethod.GET)
    public String returnLoan(@RequestParam("id") final int id) {

        LoanModel loan = loanService.findById(id);
        returnService.makeReturn(loan);

        return REDIRECT_TO_MY_LOANS;
    }

    @RequestMapping(value = "/myhistory", method = RequestMethod.GET)
    public ModelAndView myHistoryLoans() {

        List<ReturnModel> returnedLoans = returnService.findAllReturnedLoansByEmail(session.getCurrentUser().getEmail());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loan/loanHistory");
        modelAndView.addObject("returnedLoans", returnedLoans);
        modelAndView.addObject("userName", session.getCurrentUser().getName());

        return modelAndView;
    }

    @RequestMapping(value = "/renew", method = RequestMethod.GET)
    public String renewLoan(@RequestParam("id") final int id) {

        LoanModel loan = loanService.findById(id);
        if (loan != null) {
            loanService.renewLoan(loan);

            return REDIRECT_TO_MY_LOANS;
        }
        // error
        return null;
    }
}
