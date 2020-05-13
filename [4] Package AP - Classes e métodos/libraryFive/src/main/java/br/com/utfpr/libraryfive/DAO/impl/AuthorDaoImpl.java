package br.com.utfpr.libraryfive.DAO.impl;

import br.com.utfpr.libraryfive.DAO.AuthorDao;
import br.com.utfpr.libraryfive.model.AuthorModel;
import br.com.utfpr.libraryfive.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("authorDao")
@Transactional
public class AuthorDaoImpl implements AuthorDao {

    static final Logger LOG = LoggerFactory.getLogger(AuthorDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AuthorModel> listAllAuthors() {
        LOG.info("listAllAuthors started!");
        List<AuthorModel> users;

        try {
            users = entityManager.createQuery("select a from AuthorModel a", AuthorModel.class).getResultList();
            LOG.info("Authors found!");
        } catch (NoResultException e) {
            users = null;
        }
        return users;
    }

    @Override
    public AuthorModel findById(Integer id) {
        LOG.info("findById started!");

        List<AuthorModel> authors = entityManager.createQuery("select a from AuthorModel a where a.id = :id", AuthorModel.class)
                .setParameter("id", id)
                .getResultList();

        if (authors.isEmpty()) {
            LOG.info("The author " + id + " doesn't exist!");

            return null;
        }

        LOG.info("Success! Author with name " + authors.get(0).getName() + " found!");
        return authors.get(0);
    }

    @Override
    public void createAuthor(AuthorModel author) {
        entityManager.persist(author);
    }

    @Override
    public void editAuthor(AuthorModel author) {

    }

    @Override
    public void deleteAuthor(AuthorModel author) {
        LOG.info("deleteAuthor started!");
        try {
            entityManager.remove(author);
            LOG.info("Author success deleted!");
        } catch (NoResultException e) {
            LOG.info("Author delete fail, because " + e.getMessage());
        }
    }
}
