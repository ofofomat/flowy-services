package br.edu.catolicasc.flowyservices.controller;

import br.edu.catolicasc.flowyservices.entity.FlowyUser;
import br.edu.catolicasc.flowyservices.dto.LoginRequest;
import br.edu.catolicasc.flowyservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<FlowyUser> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<FlowyUser> getUserById(@PathVariable Long userId) {
        Optional<FlowyUser> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody FlowyUser user) {
        // Verifica se o usuário já existe
        Optional<FlowyUser> existingUser = userService.getUserByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existe");
        }

        // Caso não exista, salva o usuário
        FlowyUser savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<FlowyUser> updateUser(@PathVariable Long userId, @RequestBody FlowyUser userDetails) {
        Optional<FlowyUser> user = userService.getUserById(userId);
        if (user.isPresent()) {
            FlowyUser updatedUser = user.get();
            updatedUser.setUserName(userDetails.getUserName());
            userService.saveUser(updatedUser);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        Optional<FlowyUser> user = userService.getUserById(userId);
        if (user.isPresent()) {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/findUser")
    public ResponseEntity<FlowyUser> login(@RequestBody LoginRequest loginRequest) {
        Optional<FlowyUser> user = userService.getUserByUserName(loginRequest.getUserName());
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
