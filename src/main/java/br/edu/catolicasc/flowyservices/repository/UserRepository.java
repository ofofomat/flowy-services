package br.edu.catolicasc.flowyservices.repository;

import br.edu.catolicasc.flowyservices.entity.FlowyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<FlowyUser, Long> {

    Optional<FlowyUser> findByUserName(String userName);

}
