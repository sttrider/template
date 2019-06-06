package br.com.joao.api;

import br.com.joao.entity.User;
import br.com.joao.service.UserService;
import br.com.joao.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicAPI {

	@Autowired
	private UserService userService;
	
	@PostMapping(path = { "/" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@Valid @RequestBody UserVO userVO) {

		log.debug("Inicializando o novo usuario [{}].", userVO);
		
		User user = new User();
		user.setName(userVO.getName());
		user.setEmail(userVO.getEmail());
		user.setStatus(userVO.getStatus());
		user.setCreationDateTime(LocalDateTime.now());

		String idSaved = userService.create(user).getId();

		UriComponents uriComponents = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(idSaved);

		return ResponseEntity.created(uriComponents.toUri()).build();

	}
}
