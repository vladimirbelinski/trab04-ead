package br.com.utfpr.libraryfive.populator;

import br.com.utfpr.libraryfive.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserPopulator {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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
        userModel.setUserStatus(UserModel.UserStatus.ATIVO);
        userModel.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userModel.setAdmin(user.getAdmin());

        // Librarian: userType == servidor AND admin == true
        // teacher:  userType == servidor AND admin == false
    }
}
