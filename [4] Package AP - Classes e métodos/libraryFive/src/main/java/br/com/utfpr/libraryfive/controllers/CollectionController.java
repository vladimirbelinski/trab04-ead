package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.model.*;
import br.com.utfpr.libraryfive.service.CollectionService;
import br.com.utfpr.libraryfive.util.ModifiedCollection;
import br.com.utfpr.libraryfive.util.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/collection")
public class CollectionController extends AbstractController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private Session session;

    // listar
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showCollection(){

        List<CollectionModel> availableCollection = collectionService.findAllAvailableCollection();
        List<ModifiedCollection> collections = new ArrayList<>();

        for (CollectionModel collectionModel : availableCollection) {
            ModifiedCollection modifiedCollection = new ModifiedCollection();
            modifiedCollection.setId(collectionModel.getId());
            modifiedCollection.setTitle(collectionModel.getTitle());
            modifiedCollection.setAuthor("teste");
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

    // cadastrar
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView createCollection(@ModelAttribute("collection") CollectionModel collection,
                                         @ModelAttribute("collectionCopy") CollectionCopyModel collectionCopy,
                                         @ModelAttribute("author") AuthorModel author) {

        collectionService.createCollection(collection);

        ModelAndView modelAndView = new ModelAndView("collection/collectionNew");
        modelAndView.addObject("collection", new CollectionModel());

        return modelAndView;
    }

    // deletar
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteCollection(){
        return "collection/ok";
    }
}
