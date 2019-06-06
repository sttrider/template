package br.com.joao.service.impl;

import br.com.joao.entity.User;
import br.com.joao.repository.UserRepository;
import br.com.joao.service.UserService;
import br.com.joao.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<String, User> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }


    @Override
    public List<User> findByFilter(UserVO filter) {
        return userRepository.findByFiltro(filter);
    }

    @Override
    protected UserRepository getRepository() {
        return userRepository;
    }
}
