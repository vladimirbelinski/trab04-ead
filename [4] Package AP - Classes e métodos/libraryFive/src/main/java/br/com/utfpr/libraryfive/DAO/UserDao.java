package br.com.utfpr.libraryfive.DAO;

import br.com.utfpr.libraryfive.model.UserModel;

import java.util.List;

public interface UserDao {

    void createUser(UserModel user);

    void editUser(UserModel user);

    void deleteUser(UserModel user);

    void setActive(UserModel user, Boolean active);

    List<UserModel> findAllUsers();

    UserModel findByName(String name);

    UserModel findByType(String type);

    UserModel findByStatus(String status);

    UserModel findByEmail(String email);
}
