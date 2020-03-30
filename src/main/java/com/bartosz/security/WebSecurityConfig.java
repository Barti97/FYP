package com.bartosz.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()//.antMatchers("/**").permitAll();
//			.antMatchers("/h2").hasRole("ADMIN")
//			.antMatchers("/users").hasRole("ADMIN")
//			.antMatchers("/register").permitAll()
//			.antMatchers("/api/**").permitAll()
//			.anyRequest().authenticated()
//			.and()
//			.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/")
//			.and()
//			.logout().permitAll()
//			.and()
//			.httpBasic();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("SELECT user.email, user.password, user.enabled FROM user WHERE user.email = ?")
			.authoritiesByUsernameQuery("SELECT role.userEmail, role.roleDescription FROM role WHERE role.userEmail = ?");
	}

}
