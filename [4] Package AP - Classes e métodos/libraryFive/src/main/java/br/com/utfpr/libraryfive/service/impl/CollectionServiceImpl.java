package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.DAO.CollectionDao;
import br.com.utfpr.libraryfive.model.CollectionModel;
import br.com.utfpr.libraryfive.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("collectionService")
@Transactional
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionDao collectionDao;

    @Override
    public void createCollection(CollectionModel collection) {

        CollectionModel existingCollection = findByTitle(collection.getTitle());
        if (existingCollection == null) {
            collectionDao.createCollection(collection);
        } else {

        }
    }

    @Override
    public void editCollection(CollectionModel collection) {
        collectionDao.editCollection(collection);
    }

    @Override
    public void updateStatus(CollectionModel collection, String bookStatus) {
        collectionDao.updateStatus(collection, bookStatus);
    }

    @Override
    public void deleteCollection(CollectionModel collection) {
        collectionDao.deleteCollection(collection);
    }

    @Override
    public void setActive(CollectionModel collection, Boolean active) {
        collectionDao.setActive(collection, active);
    }

    @Override
    public List<CollectionModel> listAllCollections() {
        return collectionDao.listAllCollections();
    }

    @Override
    public List<CollectionModel> findAllAvailableCollection() {
        return collectionDao.findAllAvailableCollection();
    }

    @Override
    public CollectionModel findByTitle(String title) {
        return collectionDao.findByTitle(title);
    }

    @Override
    public CollectionModel findByType(String type) {
        return collectionDao.findByType(type);
    }

    @Override
    public List<CollectionModel> showCollectionInfo() {
        return collectionDao.showCollectionInfo();
    }

    @Override
    public boolean isAvailable(CollectionModel collection, Integer quantity) {
        return collectionDao.isAvailable(collection, quantity);
    }
}
