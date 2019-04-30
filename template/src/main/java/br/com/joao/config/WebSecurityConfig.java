package br.com.joao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final DaoAuthenticationProvider authenticationProvider;

	public WebSecurityConfig(DaoAuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//@formatter:off
		http.authorizeRequests()
			.antMatchers("/login").permitAll()
			.anyRequest().authenticated();
		//@formatter:on
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {

		auth.authenticationProvider(authenticationProvider);

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