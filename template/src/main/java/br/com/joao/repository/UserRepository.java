package br.com.joao.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.joao.entity.User;
import br.com.joao.repository.custom.UserCustomRepository;

public interface UserRepository extends MongoRepository<User, String>, UserCustomRepository {

	Optional<User> findByEmail(String email);

	@Query(value = "{'email': ?0, _id: {$ne: ?1}}", exists = true)
	Boolean existsByEmail(String email, String id);
}
