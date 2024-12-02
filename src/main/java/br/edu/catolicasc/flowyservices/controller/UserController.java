package br.edu.catolicasc.flowyservices.controller;

import br.edu.catolicasc.flowyservices.entity.FlowyUser;
import br.edu.catolicasc.flowyservices.exception.ResourceNotFoundException;
import br.edu.catolicasc.flowyservices.dto.LoginRequest;
import br.edu.catolicasc.flowyservices.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<FlowyUser> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<FlowyUser> getUserById(@PathVariable Long userId) {
        FlowyUser user = userService.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<FlowyUser> createUser(@RequestBody FlowyUser user) {
        if (userService.getUserByUserName(user.getUserName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        FlowyUser savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{userId}")
    @Transactional
    public ResponseEntity<FlowyUser> updateUser(@PathVariable Long userId, @RequestBody FlowyUser userDetails) {
        FlowyUser user = userService.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setUserName(userDetails.getUserName());
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/findUser")
    public ResponseEntity<FlowyUser> login(@RequestBody LoginRequest loginRequest) {
        FlowyUser user = userService.getUserByUserName(loginRequest.getUserName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return ResponseEntity.ok(user);
    }
}
