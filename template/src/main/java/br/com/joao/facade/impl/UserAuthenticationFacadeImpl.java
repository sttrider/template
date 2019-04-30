package br.com.joao.facade.impl;

import java.util.HashMap;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import br.com.joao.entity.User;
import br.com.joao.facade.UserAuthenticationFacade;

@Component
public class UserAuthenticationFacadeImpl implements UserAuthenticationFacade {

	@SuppressWarnings("unchecked")
	@Override
	public User getUser() {

		HashMap<String, Object> details = (HashMap<String, Object>) ((OAuth2AuthenticationDetails) getAuthentication()
				.getDetails()).getDecodedDetails();
		HashMap<String, Object> user = (HashMap<String, Object>) details.get("user");

		User userEntity = new User();
		userEntity.setId(Long.valueOf(user.get("id").toString()));
		return userEntity;
	}

	@Override
	public Authentication getAuthentication() {

		return SecurityContextHolder.getContext().getAuthentication();
	}
}
