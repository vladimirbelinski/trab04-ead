package br.com.utfpr.libraryfive.DAO.impl;

import br.com.utfpr.libraryfive.DAO.UserDao;
import br.com.utfpr.libraryfive.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

    static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(UserModel user) {
        entityManager.persist(user);
    }

    @Override
    public void editUser(UserModel user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(UserModel user) {
        LOG.info("deleteUser started!");
        try {
            entityManager.remove(user);
            LOG.info("User success deleted!");
        } catch (NoResultException e) {
            LOG.info("User delete fail, because " + e.getMessage());
        }
    }

    @Override
    public void setActive(UserModel user, Boolean active) {

    }

    @Override
    public List<UserModel> findAllUsers() {
        LOG.info("findAllUsers started!");
        List<UserModel> users;

        try {
            users = entityManager.createQuery("select u from UserModel u", UserModel.class).getResultList();
            LOG.info("Users found!");
        } catch (NoResultException e) {
            users = null;
        }
        return users;
    }

    @Override
    public UserModel findById(Integer id) {
        LOG.info("findByName started!");

        List<UserModel> users = entityManager.createQuery("select u from UserModel u where u.id = :id", UserModel.class)
                .setParameter("id", id)
                .getResultList();

        if (users.isEmpty()) {
            LOG.info("The user " + id + " doesn't exist!");

            return null;
        }

        LOG.info("Success! User with name " + users.get(0).getName() + " found!");
        return users.get(0);
    }

    @Override
    public UserModel findByName(String name) {
        LOG.info("findByName started!");

        List<UserModel> users = entityManager.createQuery("select u from UserModel u where u.name = :name", UserModel.class)
                .setParameter("name", name)
                .getResultList();

        if (users.isEmpty()) {
            LOG.info("The user " + name + " doesn't exist!");

            return null;
        }

        LOG.info("Success! User with name " + users.get(0).getName() + " found!");
        return users.get(0);
    }

    @Override
    public UserModel findByType(String type) {
        return entityManager.find(UserModel.class, type);
    }

    @Override
    public UserModel findByStatus(String status) {
        return null;
    }

    @Override
    public UserModel findByEmail(String email) {
        LOG.info("findByName started!");

        List<UserModel> users = entityManager.createQuery("select u from UserModel u where u.email = :email", UserModel.class)
                .setParameter("email", email)
                .getResultList();

        if (users.isEmpty()) {
            LOG.info("The user with " + email + " doesn't exist!");

            return null;
        }

        LOG.info("Success! User with name " + users.get(0).getName() + " found!");
        return users.get(0);
    }

    @Override
    public UserModel doLogin(String email, String password) {
        LOG.info("doLogin started!");

        List<UserModel> users = entityManager.createQuery("select u from UserModel u where u.email = :email and u.password = :password", UserModel.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();

        if (users.isEmpty()) {
            LOG.info("The user with " + email + " doesn't exist!");

            return null;
        }

        LOG.info("Success! User with name " + users.get(0).getName() + " found!");
        return users.get(0);
    }
}
