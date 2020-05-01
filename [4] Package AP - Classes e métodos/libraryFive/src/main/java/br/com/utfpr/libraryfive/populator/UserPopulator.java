package br.com.utfpr.libraryfive.populator;

import br.com.utfpr.libraryfive.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserPopulator {

    public void populeUser(UserModel user) {
        UserModel userModel = new UserModel();
        userModel.setName(user.getName());
        userModel.setEmail(user.getEmail());
        userModel.setUserType(user.getUserType());
        userModel.setStreet(user.getStreet());
        userModel.setNeighborhood(user.getNeighborhood());
        userModel.setStreetNumber(user.getStreetNumber());
        userModel.setAdditionalAddress(user.getAdditionalAddress());
        userModel.setState(user.getState());
        userModel.setCity(user.getCity());
        userModel.setBirthDate(user.getBirthDate());
        userModel.setUserStatus(user.getUserStatus());
        userModel.setPassword(user.getPassword());
        // TODO - Verificar como colocar ADMIN
        userModel.setAdmin(user.getAdmin());
    }
}
