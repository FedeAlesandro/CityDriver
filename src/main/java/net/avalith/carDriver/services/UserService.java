package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.AlreadyExistsException;
import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.User;
import net.avalith.carDriver.models.dtos.requests.UserDtoRequest;
import net.avalith.carDriver.models.dtos.requests.UserDtoUpdateRequest;
import net.avalith.carDriver.repositories.LicenseRepository;
import net.avalith.carDriver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.avalith.carDriver.utils.Constants.NOT_FOUND_USER;
import static net.avalith.carDriver.utils.Constants.USER_ALREADY_EXISTS;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LicenseRepository licenseRepository;

    public User save(UserDtoRequest user){

        if(userRepository.getByDni(user.getDni()).isPresent())
            throw new AlreadyExistsException(USER_ALREADY_EXISTS);
        
        return userRepository.save(new User(user));
    }

    public List<User> getAll(){

        return userRepository.getAll();
    }

    public void delete(String dni){

        if(userRepository.delete(dni) < 1)
            throw new NotFoundException(NOT_FOUND_USER);
    }

    public User update(String dni, UserDtoUpdateRequest user){
        User oldUser = userRepository.getByDni(dni)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_USER));

        User userUpdate = new User(user);
        userUpdate.setId(oldUser.getId());
        userUpdate.setDni(dni);
        userUpdate.setIsActive(Boolean.TRUE);
        userUpdate.setLicense(oldUser.getLicense());

        return userRepository.save(userUpdate);
    }
}
