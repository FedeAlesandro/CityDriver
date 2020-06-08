package net.avalith.carDriver.services;

import net.avalith.carDriver.models.License;
import net.avalith.carDriver.models.User;
import net.avalith.carDriver.models.dtos.requests.UserDtoRequest;
import net.avalith.carDriver.repositories.LicenseRepository;
import net.avalith.carDriver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(UserDtoRequest user){
        User userToSave = User.userFromUserDtoRequest(user);
        return userRepository.save(userToSave);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
}
