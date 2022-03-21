package com.starking.vendas.controllers;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.starking.vendas.entities.ItemPedido;
import com.starking.vendas.entities.Pedido;
import com.starking.vendas.entities.enums.StatusPedidoEnum;
import com.starking.vendas.services.PedidoService;
import com.starking.vendas.services.dtos.AtualizacaoStatusPedidoDTO;
import com.starking.vendas.services.dtos.InformacaoItemPedidoDTO;
import com.starking.vendas.services.dtos.InformacoesPedidoDTO;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
		
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
	public ResponseEntity<Pedido> salvar(@RequestBody @Valid Pedido pedido) {
		return this.pedidoService.salvar(pedido);
	}
	
	@DeleteMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		this.pedidoService.deletar(id);
	}
	
	@PutMapping("/{id}")
	public void atualizar(@PathVariable Long id, @RequestBody @Valid Pedido pedido) {
		BeanUtils.copyProperties(pedido, id, "id");
		this.pedidoService.salvar(pedido);
	}
	
	@PatchMapping("/{id}/status")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizaStatus(@PathVariable Long id, @RequestBody AtualizacaoStatusPedidoDTO dto) {
		String novoPedido = dto.getNovoStatus();
		this.pedidoService.atualizaStatus(id, StatusPedidoEnum.valueOf(novoPedido));
	}
	
	
	@GetMapping("/{id}")
	public InformacoesPedidoDTO getById(@PathVariable Long id) {
		return this.pedidoService.obterPedidoCompleto(id)
				.map(p -> converte(p))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PEDIDO N ENCONTRADO"));
	}
	
	private InformacoesPedidoDTO converte(Pedido pedido) {
		return InformacoesPedidoDTO.builder()
		.codigo(pedido.getId())
		.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
		.cpf(pedido.getCliente().getCpf())
		.nomeCliente(pedido.getCliente().getNome())
		.total(pedido.getTotal())
		.items(converte(pedido.getItens()))
		.build();
	}
	
	private List<InformacaoItemPedidoDTO> converte(List<ItemPedido> items) {
		if(org.springframework.util.CollectionUtils.isEmpty(items)) {
			return Collections.emptyList();	
			}
		return items.stream().map(item -> InformacaoItemPedidoDTO
				.builder()
				.descricaoProduto(item.getProduto().getDescricao())
				.precoUnitario(item.getProduto().getPreco())
				.quantidade(item.getQuantidade())
				.build()
				).collect(Collectors.toList());
	}
}
