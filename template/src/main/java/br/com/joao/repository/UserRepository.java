package br.com.joao.repository;

import br.com.joao.entity.User;
import br.com.joao.repository.custom.UserCustomRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends MongoRepository<User, String>, UserCustomRepository {

    Optional<User> findByEmail(String email);

}
