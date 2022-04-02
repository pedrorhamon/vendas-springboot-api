package com.starking.vendas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starking.vendas.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
		
	Optional<Usuario> findByLogin(String login);
}
