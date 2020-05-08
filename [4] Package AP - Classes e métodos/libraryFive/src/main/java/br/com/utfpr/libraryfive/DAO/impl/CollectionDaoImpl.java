package br.com.utfpr.libraryfive.DAO.impl;

import br.com.utfpr.libraryfive.DAO.CollectionDao;
import br.com.utfpr.libraryfive.model.CollectionCopyModel;
import br.com.utfpr.libraryfive.model.CollectionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("collectionDao")
@Transactional
public class CollectionDaoImpl implements CollectionDao {

    static final Logger LOG = LoggerFactory.getLogger(CollectionDaoImpl.class);

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
    public List<CollectionModel> listAllCollections() {
        LOG.info("listAllCollections started!");
        List<CollectionModel> collections;

        try {
            collections = entityManager. createQuery("select c from CollectionModel c" +
                    " INNER JOIN c.collectionCopyList cp").
                    getResultList();
            LOG.info("Collections found!");
        } catch (NoResultException e) {
            collections = null;
        }
        return collections;
    }

    @Override
    public List<CollectionModel> findAllAvailableCollection() {
        LOG.info("findAllAvailableCollection started!");
        List<CollectionModel> collections;

        try {
            collections = entityManager. createQuery("select c from CollectionModel c" +
                                                             " INNER JOIN c.collectionCopyList cp" +
                                                             " WHERE cp.collectionCopySituation = :collectionCopySituation").
                                                             setParameter("collectionCopySituation", CollectionCopyModel.CollectionCopySituation.Disponível).
                                                             getResultList();
            LOG.info("Collections found!");
        } catch (NoResultException e) {
            collections = null;
        }
        return collections;
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
    public boolean isAvailable(CollectionModel collection, Integer quantity) {
        return false;
    }
}
