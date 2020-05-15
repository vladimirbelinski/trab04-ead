package br.com.utfpr.libraryfive.service;


import br.com.utfpr.libraryfive.model.CollectionCopyModel;

import javax.servlet.http.HttpServletRequest;

public interface CollectionCopyService {

    void createCollectionCopy(CollectionCopyModel collectionCopy);

    String findCollectionCopyByCollectionTitle(String collectionTitle);

    CollectionCopyModel findById(Integer id);

    CollectionCopyModel getCollectionCopyByRegisterForm(HttpServletRequest request, Boolean isNewCollectionCopy);
}
