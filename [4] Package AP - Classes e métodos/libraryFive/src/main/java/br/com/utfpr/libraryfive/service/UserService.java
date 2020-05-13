package br.com.utfpr.libraryfive.service;

import br.com.utfpr.libraryfive.model.UserModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    void createUser(UserModel user);

    void editUser(UserModel user);

    void deleteUser(UserModel user);

    void setActive(UserModel user, Boolean active);

    List<UserModel> listAllUsers();

    UserModel findById(Integer id);

    UserModel findByName(String name);

    UserModel findByType(String type);

    UserModel findByStatus(String status);

    UserModel findByEmail(String email);

    UserModel doLogin(String email, String password);

    UserModel getUserByRegisterForm(HttpServletRequest request, Boolean isNewUser);
}
