package br.com.joao.api;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import br.com.joao.entity.User;
import br.com.joao.service.UserService;
import br.com.joao.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicAPI {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping(path = { "/" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@Valid @RequestBody UserVO userVO) {

		log.debug("Inicializando o novo usuario [{}].", userVO);
		
		User user = new User();
		user.setName(userVO.getName());
		user.setEmail(userVO.getEmail());
		user.setStatus(userVO.getStatus());
		user.setCreationDateTime(LocalDateTime.now());
		user.setPassword(passwordEncoder.encode(userVO.getPassword()));

		if (userVO.getAuthorities() != null) {

            user.setAuthorities(AuthorityUtils.createAuthorityList(userVO.getAuthorities()));
        }
		
		String idSaved = userService.create(user).getId();

		UriComponents uriComponents = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(idSaved);

		return ResponseEntity.created(uriComponents.toUri()).build();

	}
}
