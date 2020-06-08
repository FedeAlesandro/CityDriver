package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.User;
import net.avalith.carDriver.models.dtos.requests.UserDtoRequest;
import net.avalith.carDriver.models.dtos.responses.UserDtoResponse;
import net.avalith.carDriver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDtoRequest> save(@RequestBody @Valid UserDtoRequest user){
        User userAux = userService.save(user);
        UserDtoRequest userDto = new UserDtoRequest(userAux);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDtoResponse>> getAll(){
        List<User> users = userService.getAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        else{
            List<UserDtoResponse>userResponses = users.stream()
                    .map(UserDtoResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(userResponses);
        }
    }
}
