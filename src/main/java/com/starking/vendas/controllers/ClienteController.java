package com.starking.vendas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starking.vendas.entities.Cliente;
import com.starking.vendas.services.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public void salva(@RequestBody Cliente cliente) {
		this.clienteService.salvar(cliente);
	}
	
	@DeleteMapping
	public void deletar(@PathVariable Long id) {
		this.clienteService.deletar(id);
	}
}
