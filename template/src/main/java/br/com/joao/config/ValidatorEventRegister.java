package br.com.joao.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
public class ValidatorEventRegister implements InitializingBean {

    private final ValidatingRepositoryEventListener validatingRepositoryEventListener;

    private final Map<String, Validator> validators;

    public ValidatorEventRegister(ValidatingRepositoryEventListener validatingRepositoryEventListener, Map<String, Validator> validators) {
        this.validatingRepositoryEventListener = validatingRepositoryEventListener;
        this.validators = validators;
    }

    @Override
    public void afterPropertiesSet() {
        List<String> events = Collections.singletonList("beforeCreate");
        for (Map.Entry<String, Validator> entry : validators.entrySet()) {
            events.stream()
                    .filter(p -> entry.getKey().startsWith(p))
                    .findFirst()
                    .ifPresent(
                            p -> validatingRepositoryEventListener
                                    .addValidator(p, entry.getValue()));
        }
    }
}

