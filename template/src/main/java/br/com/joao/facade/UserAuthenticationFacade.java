package br.com.joao.facade;

import org.springframework.security.core.Authentication;

import br.com.joao.entity.User;

public interface UserAuthenticationFacade {

	Authentication getAuthentication();

	/**
	 * 
	 * @return
	 */
	User getUser();

}
