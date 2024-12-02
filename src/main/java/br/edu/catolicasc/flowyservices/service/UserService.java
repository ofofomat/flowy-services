package br.edu.catolicasc.flowyservices.service;

import br.edu.catolicasc.flowyservices.entity.FlowyUser;
import br.edu.catolicasc.flowyservices.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        return userRepository.findByUserName(userName).stream().findFirst();
    }
}
