package com.starking.vendas.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping
	public List<Cliente> buscarTodos(){
		return this.clienteService.buscarTodos();
	}
	
	@PostMapping
	public void salvar(@RequestBody Cliente cliente) {
		this.clienteService.salvar(cliente);
	}
	
	@DeleteMapping
	public void deletar(@PathVariable Long id) {
		this.clienteService.deletar(id);
	}
	

	@PutMapping("/{Id}")
	public void  atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		BeanUtils.copyProperties(cliente, id, "id");
		this.clienteService.salvar(cliente);
	}
}
