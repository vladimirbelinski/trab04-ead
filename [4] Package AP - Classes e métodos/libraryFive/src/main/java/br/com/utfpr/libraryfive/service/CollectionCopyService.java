package br.com.utfpr.libraryfive.service;


import br.com.utfpr.libraryfive.model.CollectionCopyModel;

public interface CollectionCopyService {

    void createCollectionCopy(CollectionCopyModel collectionCopy);

    String findCollectionCopyByCollectionTitle(String collectionTitle);
}
