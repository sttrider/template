package br.com.joao.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.joao.entity.User;
import br.com.joao.vo.UserVO;

public interface UserService extends BaseService<String, User>, UserDetailsService {

	User create(User user);

	List<User> findByFilter(UserVO filter);
}
