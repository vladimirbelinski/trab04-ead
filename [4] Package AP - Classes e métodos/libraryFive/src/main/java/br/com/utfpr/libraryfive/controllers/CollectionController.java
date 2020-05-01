package br.com.utfpr.libraryfive.controllers;

import br.com.utfpr.libraryfive.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Transactional
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createCollection(){
        System.out.println("Cadastrando o produto");
        return "collection/ok";
    }
}
