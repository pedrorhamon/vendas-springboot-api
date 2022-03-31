package com.starking.vendas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioServiceImpl implements UserDetailsService{
	
	@Autowired
	private PasswordEncoder password;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return User
				.builder()
				.username("usuario")
				.password(password.encode("123"))
				.roles("USER", "ADMIN")
				.build()
				;
	}

}
