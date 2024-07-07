package br.edu.catolicasc.flowyservices.service;

import br.edu.catolicasc.flowyservices.entity.FlowyUser;
import br.edu.catolicasc.flowyservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<FlowyUser> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<FlowyUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public FlowyUser saveUser(FlowyUser user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<FlowyUser> getUserByUserName(String userName) {
        Optional<FlowyUser> users = userRepository.findByUserName(userName);
        if (users.stream().count() == 1) {
            return Optional.of(users.get());
        } else if (users.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.empty();
        }
    }

}
