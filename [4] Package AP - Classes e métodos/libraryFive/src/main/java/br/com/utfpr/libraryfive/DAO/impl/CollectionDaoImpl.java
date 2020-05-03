package br.com.utfpr.libraryfive.DAO.impl;

import br.com.utfpr.libraryfive.DAO.CollectionDao;
import br.com.utfpr.libraryfive.model.CollectionModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("collectionDao")
@Transactional
public class DefaultCollectionDao implements CollectionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createCollection(CollectionModel collection) {
        entityManager.persist(collection);
    }

    @Override
    public void editCollection(CollectionModel collection) {

    }

    @Override
    public void updateStatus(CollectionModel collection, String bookStatus) {

    }

    @Override
    public void deleteCollection(CollectionModel collection) {

    }

    @Override
    public void setActive(CollectionModel collection, Boolean active) {

    }

    @Override
    public List<CollectionModel> findAllAvailableCollection() {
        return null;
    }

    @Override
    public CollectionModel findByTitle(String title) {
        return null;
    }

    @Override
    public CollectionModel findByType(String type) {
        return null;
    }

    @Override
    public List<CollectionModel> showCollectionInfo() {
        return null;
    }

    @Override
    public boolean isAvailable(CollectionModel collection) {
        return false;
    }
}
