package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.service.CollectionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Transactional
@RequestMapping("/home")
public class HomeController {

    static final Logger log = Logger.getLogger(HomeController.class);

    @Autowired
    CollectionService collectionService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        log.debug("Estou na página principal");

        collectionService.findAllAvailableCollection();
        return "home/homepage";
    }

}
