package br.com.joao.repository.custom.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import br.com.joao.entity.User;
import br.com.joao.repository.custom.UserCustomRepository;
import br.com.joao.vo.UserVO;

public class UserCustomRepositoryImpl implements UserCustomRepository {

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public List<User> findByFiltro(UserVO filtro) {
		Query query = new Query();
		if (StringUtils.isNotBlank(filtro.getName())) {
			query.addCriteria(Criteria.where("name").regex(filtro.getName(), "i"));
		}
		return mongoOperations.find(query, User.class);
	}

}
