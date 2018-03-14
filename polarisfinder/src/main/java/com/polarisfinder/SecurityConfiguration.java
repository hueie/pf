package com.polarisfinder;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private DataSource dataSource;
	//jdbc Auth
	//@Value("${spring.queries.users-query}")
	//private String usersQuery;
	//@Value("${spring.queries.roles-query}")
	//private String rolesQuery;
	@Autowired
	private CustomAuthenticationProvider authProvider;
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
	    return new CustomLoginSuccessHandler("/");//defaulturl
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//auth.authenticationProvider(authProvider);
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
		/* jdbc Auth
		.jdbcAuthentication().usersByUsernameQuery(usersQuery)
		.authoritiesByUsernameQuery(rolesQuery)
		.dataSource(dataSource)
		.passwordEncoder(bCryptPasswordEncoder);
		*/
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.headers()
		.contentTypeOptions().disable()
		.xssProtection().disable()
		.cacheControl().disable()
		.frameOptions().disable()
		.addHeaderWriter(new XFrameOptionsHeaderWriter(new WhiteListedAllowFromStrategy(Arrays.asList("localhost:8888","www.youtube.com", "www.google.com"))))
		.and()
		.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
		.and()
			.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
			.csrf().csrfTokenRepository(csrfTokenRepository())
		.and()
		   .logout();
		   //.frameOptions().disable()
           //.addHeaderWriter(new XFrameOptionsHeaderWriter(new WhiteListedAllowFromStrategy(Arrays.asList("www.youbube.com"))));
		   /* 
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
			*/
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
	
	private CsrfTokenRepository csrfTokenRepository() {
		  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		  repository.setHeaderName("X-XSRF-TOKEN");
		  return repository;
		}
	
	
}
