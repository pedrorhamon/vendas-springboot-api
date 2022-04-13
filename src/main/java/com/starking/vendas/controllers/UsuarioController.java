package com.starking.vendas.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.starking.vendas.entities.Usuario;
import com.starking.vendas.exception.SenhaInvalidaException;
import com.starking.vendas.services.UsuarioServiceImpl;
import com.starking.vendas.services.dtos.CredenciaisDTO;
import com.starking.vendas.services.dtos.TokenDTO;
import com.starking.vendas.services.security.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioServiceImpl usuarioService;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private Usuario salvarUsuario(@RequestBody @Valid Usuario usuario) {
		String senhaCrypto = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCrypto);
		return this.usuarioService.salvar(usuario);
	}
	
	 @PostMapping("/auth")
	 public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
