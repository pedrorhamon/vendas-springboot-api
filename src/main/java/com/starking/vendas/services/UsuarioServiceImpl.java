package com.starking.vendas.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.vendas.entities.Usuario;
import com.starking.vendas.repositories.UsuarioRepository;


@Service
public class UsuarioServiceImpl implements UserDetailsService{
	
	@Autowired
	private PasswordEncoder password;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioRepository.findByLogin(username)
		.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado na base"));
		
		String[] roles = usuario.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};
		
		return org.springframework.security.core.userdetails.User
				.builder()
				.username(usuario.getLogin())
				.password(usuario.getSenha())
				.roles(roles)
				.build();
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

}
