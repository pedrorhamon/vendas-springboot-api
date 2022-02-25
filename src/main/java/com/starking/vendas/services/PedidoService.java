package com.starking.vendas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.starking.vendas.entities.ItemPedido;
import com.starking.vendas.entities.Pedido;
import com.starking.vendas.entities.Produto;
import com.starking.vendas.exception.RegraNegocioException;
import com.starking.vendas.repositories.PedidoRepository;
import com.starking.vendas.repositories.ProdutoRepository;
import com.starking.vendas.services.dtos.ItemPedidoDTO;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Pedido buscarTodos(Long id) {
		return this.pedidoRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Cliente não encontrado"));
	}

	public List<Pedido> findAll(){
		return this.pedidoRepository.findAll();
	}
		
	@Transactional
	public  ResponseEntity<Pedido> salvar(Pedido pedido) {
		Pedido pedidoNew = this.pedidoRepository.save(pedido);
		if(pedido != null) {
			return ResponseEntity.ok(pedidoNew);
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional(readOnly = true)
	public void deletar(Long id) {
		this.pedidoRepository.deleteById(id);
	}

	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> itemPedidoDTO) {
		if( itemPedidoDTO.isEmpty()) {
			throw new RegraNegocioException("Não possivel realizar um pedido");
		}
		return itemPedidoDTO
				.stream()
				.map(dto -> {
					Long idProduto = dto.getProduto();
					Produto produto = produtoRepository
					.findById(idProduto)
					.orElseThrow(
							() -> new RegraNegocioException("Codigo não encontrado: " + idProduto
									));
					ItemPedido itemPedido = new ItemPedido();
					itemPedido.setQuantidade(dto.getQuantidade());
					itemPedido.setPedido(pedido);
					itemPedido.setProduto(produto);
					return itemPedido;
				}).collect(Collectors.toList());
	}

}
