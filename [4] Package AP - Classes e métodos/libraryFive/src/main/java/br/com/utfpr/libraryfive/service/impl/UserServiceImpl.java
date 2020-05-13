package br.com.utfpr.libraryfive.service.impl;

import br.com.utfpr.libraryfive.DAO.UserDao;
import br.com.utfpr.libraryfive.model.UserModel;
import br.com.utfpr.libraryfive.service.UserService;
import br.com.utfpr.libraryfive.util.DateUtils;
import br.com.utfpr.libraryfive.util.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private FormatUtils formatUtils;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void createUser(UserModel user) {
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
            user.setId(formatUtils.getIntegerValue(request.getParameter("id")));
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(new BCryptPasswordEncoder().encode(request.getParameter("password")));
            user.setUserType(request.getParameter("userType").equals("ALUNO") ? UserModel.UserType.ALUNO : UserModel.UserType.SERVIDOR);
            user.setStreet(request.getParameter("street"));
            user.setStreetNumber(formatUtils.getIntegerValue(request.getParameter("streetNumber")));
            user.setAdditionalAddress(request.getParameter("additionalAddress"));
            user.setNeighborhood(request.getParameter("neighborhood"));
            user.setCity(request.getParameter("city"));
            user.setState(request.getParameter("state"));
            user.setBirthDate(dateUtils.convertDate(request.getParameter("birthDate")));
            user.setAdmin(request.getParameter("admin").equals("SIM") ? true : false);
            user.setUserStatus(request.getParameter("userStatus").equals("ATIVO") ? UserModel.UserStatus.ATIVO : UserModel.UserStatus.INATIVO);

        } else {
            user = findById(formatUtils.getIntegerValue(request.getParameter("userToEditId")));

            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setUserType(request.getParameter("userType").equals("ALUNO") ? UserModel.UserType.ALUNO : UserModel.UserType.SERVIDOR);
            user.setAdmin(request.getParameter("admin").equals("SIM") ? true : false);
            user.setUserStatus(request.getParameter("userStatus").equals("ATIVO") ? UserModel.UserStatus.ATIVO : UserModel.UserStatus.INATIVO);
        }
        return user;
    }

    private String regexAddress() {
        return null;
    }
}
