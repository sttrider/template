package br.com.joao.config;

import br.com.joao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Configuração do servidor de recurso.
 *
 * @author joao.oliveira
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final UserService userService;

    public ResourceServerConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        //@formatter:off
        http.headers()
                .frameOptions().sameOrigin().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/public/**").permitAll()
                .antMatchers("/**").authenticated();
        //@formatter:on
    }

    /**
     * Configura a forma como o usuário vai ser recuperado.
     *
     * @param auth Builder contendo as configurações da autenticação.
     */
    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) {

        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * Configura um serviço que irá fazer a consulta de usuário no sistema.
     *
     * @return Configuração do serviço e qual o método de senha utilizado.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    /**
     * Configura uma forma global de como gerar o encoder da senha.
     *
     * @return retorna uma instancia do {@link BCryptPasswordEncoder}.
     */
    @Bean
    public PasswordEncoder encoder() {

        return new BCryptPasswordEncoder(11);
    }

}
