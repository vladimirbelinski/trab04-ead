package br.com.utfpr.libraryfive.DAO.impl;

import br.com.utfpr.libraryfive.DAO.CollectionCopyDao;
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

@Repository("collectionCopyDao")
@Transactional
public class CollectionCopyDaoImpl implements CollectionCopyDao {

    static final Logger LOG = LoggerFactory.getLogger(CollectionCopyDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createCollectionCopy(CollectionCopyModel collectionCopy) {
        entityManager.persist(collectionCopy);
    }

    @Override
    public List<CollectionCopyModel> listAllCollectionCopy() {
        LOG.info("listAllCollectionCopy started!");
        List<CollectionCopyModel> collectionCopyList;

        try {
            collectionCopyList = entityManager. createQuery("select c from CollectionCopyModel c", CollectionCopyModel.class).
                    getResultList();
            LOG.info("Collection copies found!");
        } catch (NoResultException e) {
            collectionCopyList = null;
        }
        return collectionCopyList;
    }

    @Override
    public void findCollectionCopyByCollectionTitle(String collectionTitle) {

    }

    @Override
    public CollectionCopyModel findById(Integer id) {
        LOG.info("findById started!");

        List<CollectionCopyModel> collections = entityManager.createQuery("select cc from CollectionCopyModel cc where cc.id = :id", CollectionCopyModel.class)
                .setParameter("id", id)
                .getResultList();

        if (collections.isEmpty()) {
            LOG.info("The collection copy " + id + " doesn't exist!");

            return null;
        }

        LOG.info("Success! Collection with ID " + collections.get(0).getId() + " found!");
        return collections.get(0);
    }
}
