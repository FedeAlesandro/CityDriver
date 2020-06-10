package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.License;
import net.avalith.carDriver.models.User;
import net.avalith.carDriver.models.dtos.requests.UserDtoRequest;
import net.avalith.carDriver.models.dtos.requests.UserDtoUpdateRequest;
import net.avalith.carDriver.repositories.LicenseRepository;
import net.avalith.carDriver.repositories.UserRepository;
import net.avalith.carDriver.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.avalith.carDriver.utils.Constants.NOT_FOUND_LICENSE;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_USER;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LicenseRepository licenseRepository;

    public User save(UserDtoRequest user){
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
        Long id = userRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_USER))
                .getId();

        User userUpdate = new User(user);
        userUpdate.setId(id);

        return userRepository.save(userUpdate);
    }
}
