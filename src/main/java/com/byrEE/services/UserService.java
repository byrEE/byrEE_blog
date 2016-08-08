package com.byrEE.services;

/**
 * @author byrEE
 */

import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.byrEE.Constants;
import com.byrEE.models.User;
import com.byrEE.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Inject
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	protected void initialize(){
		getSuperUser();
	}

	public User createUser(User user){
		//user 
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User getSuperUser(){
		User user=userRepository.findByEmail(Constants.DEFAULT_ADMIN_EMAIL);

		if(user==null)
			user=createUser(new User(Constants.DEFAULT_ADMIN_EMAIL, Constants.DEFAULT_ADMIN_PASSWORD, User.ROLE_ADMIN));

		return user;
	}

	public User currentUser(){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth==null || auth instanceof AnonymousAuthenticationToken){
			return null;
		}
		String email=((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
		return userRepository.findByEmail(email);
	}

	public boolean changePassword(User user,String password,String newPassword){
		if(password==null || password.isEmpty() || newPassword==null || newPassword.isEmpty())
			return false;
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);

		logger.info("User @"+user.getEmail()+"changed password.");
		return true;
	}

	public void login(User user){
        SecurityContextHolder.getContext().setAuthentication(authenticate(user));
	}

	private Authentication authenticate(User user) {
        return new UsernamePasswordAuthenticationToken(createSpringUser(user), null, Collections.singleton(createAuthority(user)));
    }

    private org.springframework.security.core.userdetails.User createSpringUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(createAuthority(user)));
    }

    private GrantedAuthority createAuthority(User user) {
        return new SimpleGrantedAuthority(user.getRole());
    }

}