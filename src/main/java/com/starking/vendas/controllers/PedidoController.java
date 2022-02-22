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

import com.starking.vendas.entities.Pedido;
import com.starking.vendas.entities.Produto;
import com.starking.vendas.repositories.PedidoRepository;
import com.starking.vendas.repositories.ProdutoRepository;
import com.starking.vendas.services.PedidoService;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("/{id}")
	public Pedido buscarPorId(@PathVariable Long id){
		return this.pedidoService.buscarTodos(id);
	}
	
	@GetMapping
	public List<Pedido>  findAll(){
		 return this.pedidoService.findAll();
	}
		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pedido> salvar(@RequestBody Pedido pedido) {
		return this.pedidoService.salvar(pedido);
	}
	
	@DeleteMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		this.pedidoService.deletar(id);
	}
	
	@PutMapping("/{id}")
	public void atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
		BeanUtils.copyProperties(pedido, id, "id");
		this.pedidoService.salvar(pedido);
	}
}
