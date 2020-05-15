package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.model.AuthorModel;
import br.com.utfpr.libraryfive.model.CollectionModel;
import br.com.utfpr.libraryfive.model.LoanModel;
import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.service.AuthorService;
import br.com.utfpr.libraryfive.service.CollectionService;
import br.com.utfpr.libraryfive.service.LoanService;
import br.com.utfpr.libraryfive.service.UserService;
import br.com.utfpr.libraryfive.util.ModifiedCollection;
import br.com.utfpr.libraryfive.util.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/admin")
public class AdminController {

    static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private Session session;

    @Autowired
    CollectionService collectionService;

    @Autowired
    LoanService loanService;

    @Autowired
    UserService userService;

    @Autowired
    AuthorService authorService;

    @RequestMapping(value = {"/manage/authors"}, method = RequestMethod.GET)
    public ModelAndView manageAuthors(ModelAndView modelAndView, HttpServletRequest request) {

        List<AuthorModel> authors = authorService.listAllAuthors();

        if (!authors.isEmpty()) {
            modelAndView.setViewName("author/adminAuthor");
            modelAndView.addObject("authors", authors);
            modelAndView.addObject("userName", session.getCurrentUser().getName());
            modelAndView.addObject("baseUrl", session.getBaseUrl(request));

            LOG.info("Authors success retrieved!");

            return modelAndView;
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = {"/manage/collections"}, method = RequestMethod.GET)
    public ModelAndView manageCollections(ModelAndView modelAndView, HttpServletRequest request) {

        List<CollectionModel> allCollections = collectionService.listAllCollections();
        List<ModifiedCollection> collections = new ArrayList<>();

        for (CollectionModel collectionModel : allCollections) {
            ModifiedCollection modifiedCollection = new ModifiedCollection();
            modifiedCollection.setId(collectionModel.getId());
            modifiedCollection.setTitle(collectionModel.getTitle());
            modifiedCollection.setAuthor(getAuthor(collectionModel));
            modifiedCollection.setPublicationYear(collectionModel.getPublicationYear());
            modifiedCollection.setType(collectionModel.getCollectionType().name());
            modifiedCollection.setHasCollectionCopy(collectionModel.getCollectionCopyList().isEmpty() ? false : true);

            collections.add(modifiedCollection);
        }

        if (!collections.isEmpty()) {
            modelAndView.setViewName("collection/collectionListAdmin");
            modelAndView.addObject("collections", collections);
            modelAndView.addObject("authors", authorService.listAllAuthors());
            modelAndView.addObject("userName", session.getCurrentUser().getName());
            modelAndView.addObject("baseUrl", session.getBaseUrl(request));

            LOG.info("Collections success retrieved!");

            return modelAndView;
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = {"/manage/loans"}, method = RequestMethod.GET)
    public ModelAndView manageLoans(ModelAndView modelAndView) {

        List<LoanModel> allLoans = loanService.listAll();

        if (!allLoans.isEmpty()) {

            modelAndView.setViewName("loan/adminLoan");
            modelAndView.addObject("loans", allLoans);
            modelAndView.addObject("userName", session.getCurrentUser().getName());

            LOG.info("Loans success retrieved!");

            return modelAndView;
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = {"/manage/users"}, method = RequestMethod.GET)
    public ModelAndView manageUsers(ModelAndView modelAndView, HttpServletRequest request) {

        List<UserModel> users = userService.listAllUsers();

        if (!users.isEmpty()) {
            modelAndView.setViewName("user/adminView");
            modelAndView.addObject("users", users);
            modelAndView.addObject("userName", session.getCurrentUser().getName());
            modelAndView.addObject("baseUrl", session.getBaseUrl(request));

            LOG.info("Users success retrieved!");

            return modelAndView;
        }
        // retorna erro
        return null;
    }

    private String getAuthor(CollectionModel collectionModel) {
        return authorService.findAuthorNameByCollectionTitle(collectionModel.getTitle()) != null ? authorService.findAuthorNameByCollectionTitle(collectionModel.getTitle()).getName() : "" ;
    }
}
