package br.com.utfpr.libraryfive.service;


import br.com.utfpr.libraryfive.model.CollectionCopyModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CollectionCopyService {

    void createCollectionCopy(CollectionCopyModel collectionCopy);

    void editCollectionCopy(CollectionCopyModel collectionCopy);

    void deleteCollectionCopy(CollectionCopyModel collectionCopy);

    List<CollectionCopyModel> listAllCollectionCopy();

    CollectionCopyModel findById(Integer id);

    String findCollectionCopyByCollectionTitle(String collectionTitle);

    CollectionCopyModel getCollectionCopyByRegisterForm(HttpServletRequest request, Boolean isNewCollectionCopy);
}
