package com.starking.vendas.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.starking.vendas.entities.Cliente;
import com.starking.vendas.repositories.ClienteRepository;
import com.starking.vendas.services.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/{id}")
	public Cliente buscarPorId(@PathVariable Long id){
		return this.clienteRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Cliente não encontrado"));
		
	}
	
	@GetMapping
	public List<Cliente>  findAll(){
		 return this.clienteService.findAll();
	}
		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
		Cliente clienteNew = this.clienteService.salvar(cliente);
		if(clienteNew != null) {
			return ResponseEntity.ok(clienteNew);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		this.clienteService.deletar(id);
	}
	

	@PutMapping("/{id}")
	public void atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		BeanUtils.copyProperties(cliente, id, "id");
		this.clienteService.salvar(cliente);
	}
}
