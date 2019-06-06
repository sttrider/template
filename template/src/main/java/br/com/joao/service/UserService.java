package br.com.joao.service;

import br.com.joao.entity.User;
import br.com.joao.vo.UserVO;

import java.util.List;

public interface UserService extends BaseService<String, User> {

	User create(User user);

	List<User> findByFilter(UserVO filter);
}
