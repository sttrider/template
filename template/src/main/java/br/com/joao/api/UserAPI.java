package br.com.joao.api;

import br.com.joao.entity.User;
import br.com.joao.service.UserService;
import br.com.joao.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserAPI {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public UserAPI(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/findAll")
    public List<UserVO> findAll() {

        return userService.findAll().stream().map(User::toVO).collect(Collectors.toList());
    }

    @PostMapping("/findByFilter")
    public List<UserVO> findAll(@RequestBody UserVO filter) {

        return userService.findByFilter(filter).stream().map(User::toVO).collect(Collectors.toList());
    }

    @PutMapping(path = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable(name = "id") Long id, @RequestBody UserVO userVO) {

        log.debug("Inicializando o update [{}].", userVO);

        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userVO.getName());
            user.setEmail(userVO.getEmail());
            user.setStatus(userVO.getStatus());
            user.setCreationDateTime(LocalDateTime.now());
            user.setPassword(passwordEncoder.encode(userVO.getPassword()));

            return ResponseEntity.noContent().build();
        } else {
            log.debug("Entidade não encontrada [{}].", id);
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserVO> findById(@PathVariable("id") Long id) {

        log.debug("Buscando entidade [{}].", id);

        Optional<User> entity = userService.findById(id);

        if (entity.isEmpty()) {
            log.debug("Entidade não encontrada [{}].", id);
            return ResponseEntity.notFound().build();
        }
        try {
            log.debug("Entidade encontrada [{}].", entity);
            UserVO vo = entity.get().toVO();
            log.debug("VO gerado [{}].", vo);
            return ResponseEntity.ok(vo);
        } catch (UnsupportedOperationException e) {
            log.error(e.toString());
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {

        log.debug("Excluindo entidade [{}].", id);
        if (userService.existsById(id)) {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
