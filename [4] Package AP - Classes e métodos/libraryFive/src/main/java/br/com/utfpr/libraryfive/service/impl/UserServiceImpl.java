package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.DAO.UserDao;
import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.populator.UserPopulator;
import br.com.utfpr.libraryfive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public List<UserModel> listAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public UserModel findById(Integer id) {
        return userDao.findById(id);
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

    public UserModel doLogin(String email, String password) {
        return userDao.doLogin(email, password);
    }

    @Override
    public UserModel getUserByRegisterForm(HttpServletRequest request, Boolean isNewUser) {

        UserModel user = new UserModel();

        if (isNewUser) {
            user.setId(getIntegerValue(request.getParameter("id")));
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setUserType(request.getParameter("userType").equals("ALUNO") ? UserModel.UserType.ALUNO : UserModel.UserType.SERVIDOR);
            user.setStreet(request.getParameter("street"));
            user.setStreetNumber(getIntegerValue(request.getParameter("streetNumber")));
            user.setAdditionalAddress(request.getParameter("additionalAddress"));
            user.setNeighborhood(request.getParameter("neighborhood"));
            user.setCity(request.getParameter("city"));
            user.setState(request.getParameter("state"));
            user.setBirthDate(convertDate(request.getParameter("birthDate")));
            user.setAdmin(request.getParameter("admin").equals("SIM") ? true : false);
            user.setUserStatus(request.getParameter("userStatus").equals("ATIVO") ? UserModel.UserStatus.ATIVO : UserModel.UserStatus.INATIVO);

        } else {
            user = findById(getIntegerValue(request.getParameter("userToEditId")));

            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setUserType(request.getParameter("userType").equals("ALUNO") ? UserModel.UserType.ALUNO : UserModel.UserType.SERVIDOR);
            user.setAdmin(request.getParameter("admin").equals("SIM") ? true : false);
            user.setUserStatus(request.getParameter("userStatus").equals("ATIVO") ? UserModel.UserStatus.ATIVO : UserModel.UserStatus.INATIVO);
        }
        return user;
    }

    private Integer getIntegerValue(String stringValue) {
        return Integer.valueOf(stringValue);
    }

    private Date convertDate(final String data) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String regexAddress() {
        return null;
    }
}
