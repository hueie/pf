package com.polarisfinder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

import com.polarisfinder.user.entity.CurrentUser;
import com.polarisfinder.user.entity.Role;
import com.polarisfinder.user.entity.User;
import com.polarisfinder.user.service.RoleService;
import com.polarisfinder.user.service.UserService;

public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
	final String type;

	private UserService userService;
	private RoleService roleService;

	public OAuth2SuccessHandler(String type, UserService userService, RoleService roleService) {
		this.type = type;
		this.userService = userService;
		this.roleService = roleService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth)
			{

		// 연결되어 있는 계정이 있는지 확인합니다.
		// 이전 강의 AccountService.getAccountByOAuthId 참고!!
		String token = ((OAuth2AuthenticationDetails) auth.getDetails()).getTokenValue();
		System.out.println("token : " + token);
		String emailaddress = "";
		if (type.equals("facebook")) {
			Facebook facebook = new FacebookTemplate(token);
			if (facebook.isAuthorized()) {
				String[] fields = { "id", "email", "first_name", "last_name", "cover" };
				//String[] fields = { "email" };
				org.springframework.social.facebook.api.User userProfile = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
				System.out.println("email : "+userProfile.getEmail());
				emailaddress = userProfile.getEmail();
				byte[] userProfileImage = facebook.userOperations().getUserProfileImage();
				// rest of stuff
			}
		}
		
		User account = account = userService.findByOauthid(type, auth.getName());
		// 연결되어 있는 계정이 있는경우.
		if (account != null) {
			// 기존 인증을 바꿉니다.
			// 이전 강의의 일반 로그인과 동일한 방식으로 로그인한 것으로 간주하여 처리합니다.
			// 기존 인증이 날아가기 때문에 OAUTH ROLE은 증발하며, USER ROLE 이 적용됩니다.
			Collection<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>(
					account.getRoles().size());
			for (Role role : account.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(role.getRole()));
			}
			SecurityContextHolder.getContext()
					.setAuthentication(new UsernamePasswordAuthenticationToken(emailaddress, null, authorities));
			try {
				res.sendRedirect("/");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 연결된 계정이 없는경우
		else {
			User user = new User();
			user.setUsername(emailaddress);
			user.setNickname(emailaddress);
			user.setPassword(emailaddress);
			user.setOauthid(auth.getName());
			user.setType(type);
			Role userRole = roleService.findByRole("ADMIN");
			user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
			userService.createUser(user);
			// 회원가입 페이지로 보냅니다.
			// ROLE 은 OAUTH 상태입니다.
			try {
				res.sendRedirect("/");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 특별히 추가정보를 받아서 가입해야할 일이없다면,
			// 즉석으로 계정을 생성한 후 성공처리 해준다면 사용자 입장에서 좋을 것 같습니다.
		}
	}
}