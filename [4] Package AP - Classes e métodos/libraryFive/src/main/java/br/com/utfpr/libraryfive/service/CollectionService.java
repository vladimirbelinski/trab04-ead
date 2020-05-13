package br.com.utfpr.libraryfive.service;

import br.com.utfpr.libraryfive.model.AuthorModel;
import br.com.utfpr.libraryfive.model.CollectionModel;

import java.util.List;

public interface CollectionService {

    void createCollection(CollectionModel collection);

    void editCollection(CollectionModel collection);

    void updateStatus(CollectionModel collection, String bookStatus);

    void deleteCollection(CollectionModel collection);

    void setActive(CollectionModel collection, Boolean active);

    List<CollectionModel> listAllCollections();

    List<CollectionModel> findAllAvailableCollection();

    CollectionModel findByTitle(String title);

    CollectionModel findByType(String type);

    List<CollectionModel> showCollectionInfo();

    boolean isAvailable(CollectionModel collection, Integer quantity);
}
