package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.DAO.UserDao;
import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.populator.UserPopulator;
import br.com.utfpr.libraryfive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserPopulator userPopulator;

    @Override
    public void createUser(UserModel user) {
        userPopulator.populeUser(user);

        userDao.createUser(user);
    }

    @Override
    public void editUser(UserModel user) {
        userDao.editUser(user);
    }

    @Override
    public void deleteUser(UserModel user) {
        userDao.deleteUser(user);
    }

    @Override
    public void setActive(UserModel user, Boolean active) {
        userDao.setActive(user, active);
    }

    @Override
    public List<UserModel> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public UserModel findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public UserModel findByType(String type) {
        return userDao.findByType(type);
    }

    @Override
    public UserModel findByStatus(String status) {
        return userDao.findByStatus(status);
    }

    public UserModel findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
