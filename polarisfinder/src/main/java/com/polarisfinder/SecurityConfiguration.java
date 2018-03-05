package com.polarisfinder;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private DataSource dataSource;
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
	    return new CustomLoginSuccessHandler("/");//defaulturl
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery)
		.authoritiesByUsernameQuery(rolesQuery)
		.dataSource(dataSource)
		.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
		.and()
			.csrf().disable()
		.formLogin()
			.loginPage("/user/Signin")
			.failureUrl("/user/SigninFailure")
			.defaultSuccessUrl("/user/SigninSuccess")
			.successHandler(successHandler())
			.usernameParameter("email")
			.passwordParameter("password")
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/user/Signout"))
			.logoutSuccessUrl("/user/SignoutSuccess")
		.and()
			.exceptionHandling()
			.accessDeniedPage("/access-denied");
			
		/*
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/**").permitAll()
		.anyRequest()
		.authenticated().and().csrf().disable().formLogin()
		.loginPage("/user/Signin")
		.failureUrl("/user/SigninFailure")
		.defaultSuccessUrl("/user/SigninSuccess")
		.usernameParameter("email")
		.passwordParameter("password")
		.and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/user/Signout"))
		.logoutSuccessUrl("/user/SignoutSuccess").and().exceptionHandling()
		.accessDeniedPage("/access-denied");
		*/
	}
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring()
		.antMatchers("/resources/**", "/static/**", "/js/**", "/css/**", "/images/**", "/files/**", "/other-files/**");
	}
	
	
	
}
