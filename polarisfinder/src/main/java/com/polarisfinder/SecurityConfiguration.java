package com.polarisfinder;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;

import com.polarisfinder.user.service.RoleService;
import com.polarisfinder.user.service.UserService;

@Configuration
@EnableOAuth2Client
//@EnableAuthorizationServer
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	OAuth2ClientContext oauth2ClientContext;
	@Autowired
	private DataSource dataSource;
	//jdbc Auth
	//@Value("${spring.queries.users-query}")
	//private String usersQuery;
	//@Value("${spring.queries.roles-query}")
	//private String rolesQuery;
	//@Autowired
	//private CustomAuthenticationProvider authProvider;
	
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
		.httpBasic()
		/* 
		.headers()
		.contentTypeOptions().disable()
		.xssProtection().disable()
		.cacheControl().disable()
		.frameOptions().disable()
		.addHeaderWriter(new XFrameOptionsHeaderWriter(new WhiteListedAllowFromStrategy(Arrays.asList("localhost:8888","www.youtube.com", "www.google.com"))))
		*/
		.and()
		.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
		.and()
			.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
			//.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
			.csrf()
			.disable();
			/* csrf 토큰을 사용하면 에디터에서 이미지 업로드가 안됨
			.csrfTokenRepository(csrfTokenRepository())
		.and()
		    .logout()
		    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
		    .logoutSuccessUrl("/pf-main.html");
			*/
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
	/* Example
	private OAuth2ClientAuthenticationProcessingFilter filter() {
		// Creating the filter for "/facebook/login" url
		OAuth2ClientAuthenticationProcessingFilter oAuth2Filter = new OAuth2ClientAuthenticationProcessingFilter(
				"/facebook/login");

		// Creating the rest template for getting connected with OAuth service.
		// The configuration parameters will inject while creating the bean.
		OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(authorizationCodeResourceDetails,
				oauth2ClientContext);
		oAuth2Filter.setRestTemplate(oAuth2RestTemplate);

		// setting the token service. It will help for getting the token and
		// user details from the OAuth Service
		oAuth2Filter.setTokenServices(new UserInfoTokenServices(resourceServerProperties.getUserInfoUri(),
				resourceServerProperties.getClientId()));

		return oAuth2Filter;
	}
	*/
	private Filter ssoFilter() {
		  CompositeFilter filter = new CompositeFilter();
		  List<Filter> filters = new ArrayList<>();
		  filters.add(ssoFilter(facebook(), "facebook"));
		  filters.add(ssoFilter(github(), "github"));
		  filter.setFilters(filters);
		  return filter;
	}
	
	private Filter ssoFilter(ClientResources client, String name) {
		  OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter("/login/"+name);
		  OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		  filter.setRestTemplate(template);
		  UserInfoTokenServices tokenServices = new UserInfoTokenServices(
		      client.getResource().getUserInfoUri(), client.getClient().getClientId());
		  //System.out.println("SSO Client ID : "+client.getClient().getClientId());
		  tokenServices.setRestTemplate(template);
		  filter.setTokenServices(tokenServices);
		  filter.setAuthenticationSuccessHandler(new OAuth2SuccessHandler(name,userService,roleService));
		  return filter;
	}

	class ClientResources {
		@NestedConfigurationProperty
		private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();
		@NestedConfigurationProperty
		private ResourceServerProperties resource = new ResourceServerProperties();
		public AuthorizationCodeResourceDetails getClient() {
		    return client;
		}
		public ResourceServerProperties getResource() {
		    return resource;
		}
	}
	
	
	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(filter);
	    registration.setOrder(-100);
	    return registration;
	}
	
	@Bean
	@ConfigurationProperties("github")
	public ClientResources github() {
		//System.out.println("github client resource");
		return new ClientResources();
	}

	@Bean
	@ConfigurationProperties("facebook")
	public ClientResources facebook() {
		//System.out.println("facebook client resource");
		return new ClientResources();
	}
}
