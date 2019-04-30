package br.com.joao.repository.custom.impl;

import br.com.joao.entity.User;
import br.com.joao.repository.custom.UserCustomRepository;
import br.com.joao.vo.UserVO;

import java.util.List;

public class UserCustomRepositoryImpl implements UserCustomRepository {


	@Override
	public List<User> findByFiltro(UserVO filtro) {
		return null;
	}

}
