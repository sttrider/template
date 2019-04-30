package br.com.joao.repository;

import br.com.joao.entity.User;
import br.com.joao.repository.custom.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {

    Optional<User> findByEmail(String email);

}
