package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.model.*;
import br.com.utfpr.libraryfive.service.AuthorService;
import br.com.utfpr.libraryfive.service.CollectionCopyService;
import br.com.utfpr.libraryfive.service.CollectionService;
import br.com.utfpr.libraryfive.util.ModifiedCollection;
import br.com.utfpr.libraryfive.util.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/collection")
public class CollectionController extends AbstractController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CollectionCopyService collectionCopyService;

    @Autowired
    private Session session;

    @Autowired
    AuthorService authorService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showCollection(){

        List<CollectionModel> availableCollection = collectionService.findAllAvailableCollection();
        List<ModifiedCollection> collections = new ArrayList<>();

        for (CollectionModel collectionModel : availableCollection) {
            ModifiedCollection modifiedCollection = new ModifiedCollection();
            modifiedCollection.setId(collectionModel.getId());
            modifiedCollection.setTitle(collectionModel.getTitle());
            modifiedCollection.setAuthor(authorService.findAuthorNameByCollectionTitle(collectionModel.getTitle()).getName());
            modifiedCollection.setPublicationYear(collectionModel.getPublicationYear());
            modifiedCollection.setType(collectionModel.getCollectionType().name());

            collections.add(modifiedCollection);
        }

        if (!availableCollection.isEmpty()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("collection/collectionList");
            modelAndView.addObject("collections", collections);
            modelAndView.addObject("userName", session.getCurrentUser().getName());

            return modelAndView;
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createCollection(final HttpServletRequest request) {

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            CollectionModel collection = collectionService.getCollectionByRegisterForm(request, true);

            if (collection != null) {
                collectionService.createCollection(collection);

                // create in DB and connect collection with author (AuthorCollection)
                authorService.createAuthorCollection(collection);

                return REDIRECT_TO_ADMIN_VIEW_COLLECTIONS;
            }
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editCollection(final HttpServletRequest request) {

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            CollectionModel collection =  collectionService.getCollectionByRegisterForm(request, false);

            collectionService.editCollection(collection);

            return REDIRECT_TO_ADMIN_VIEW_COLLECTIONS;
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteCollection(@RequestParam("id") final int id){

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            CollectionModel collection =  collectionService.findById(id);
            collectionService.deleteCollection(collection);

            return REDIRECT_TO_ADMIN_VIEW_COLLECTIONS;
        }
        // retorna erro
        return null;
    }

    @RequestMapping(value = "/copy/new", method = RequestMethod.POST)
    public String createCollectionCopy(final HttpServletRequest request) {

        Boolean isAdmin = session.getCurrentUser().getAdmin();

        if (isAdmin) {
            CollectionCopyModel collectionCopy = collectionCopyService.getCollectionCopyByRegisterForm(request, true);

            if (collectionCopy != null) {
                collectionCopyService.createCollectionCopy(collectionCopy);

                return REDIRECT_TO_ADMIN_VIEW_COLLECTIONS;
            }
        }
        // retorna erro
        return null;
    }
}
