package br.com.joao.validator;

import br.com.joao.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RepositoryEventHandler
@Slf4j
public class UserEventHandler {

    private final PasswordEncoder encoder;

    public UserEventHandler(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @HandleBeforeCreate
    public void handleUserSave(User user) {
        if (user.getPassword() != null) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        user.setCreationDateTime(LocalDateTime.now());
    }
}
