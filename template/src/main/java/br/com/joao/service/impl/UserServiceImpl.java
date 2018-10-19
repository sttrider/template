package br.com.joao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.joao.entity.User;
import br.com.joao.repository.UserRepository;
import br.com.joao.service.UserService;
import br.com.joao.vo.UserVO;

@Service
public class UserServiceImpl extends BaseServiceImpl<String, User> implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findByEmail(username);

		return user.orElseThrow(() -> new UsernameNotFoundException("No user found with username " + username));
	}

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	protected MongoRepository<User, String> getRepository() {
		return userRepository;
	}

	@Override
	public List<User> findByFilter(UserVO filter) {
		return userRepository.findByFiltro(filter);
	}

}
