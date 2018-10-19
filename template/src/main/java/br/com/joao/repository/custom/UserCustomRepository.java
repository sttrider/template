package br.com.joao.repository.custom;

import java.util.List;

import br.com.joao.entity.User;
import br.com.joao.vo.UserVO;

public interface UserCustomRepository {

	List<User> findByFiltro(UserVO filtro);
}
