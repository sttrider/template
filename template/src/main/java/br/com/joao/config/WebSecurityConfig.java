package br.com.joao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.joao.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService usuarioService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//@formatter:off
		http.authorizeRequests()
			.antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/resources/**", "/public/**", "/webjars/**", "/oauth/**", "/error").permitAll()
			.anyRequest().hasRole("ADMIN")
			.and().formLogin().loginPage("/login").defaultSuccessUrl("/index").permitAll()
			.and().logout().logoutUrl("/appLogout").logoutSuccessUrl("/login").permitAll()
			.and().exceptionHandling().accessDeniedPage("/login?error=accessDenied")
			.and().headers().frameOptions().disable().and().csrf().disable();
		//@formatter:on
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

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
		authProvider.setUserDetailsService(usuarioService);
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

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}

	public static void main(String[] args) {

		System.out.println(new BCryptPasswordEncoder(11).encode("senha"));
	}
}