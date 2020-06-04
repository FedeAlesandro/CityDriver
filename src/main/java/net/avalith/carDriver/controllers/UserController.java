package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.User;
import net.avalith.carDriver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    public ResponseEntity<User> save(User user){
        return ResponseEntity.ok(userService.save(user));
    }

    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(users);
    }
}