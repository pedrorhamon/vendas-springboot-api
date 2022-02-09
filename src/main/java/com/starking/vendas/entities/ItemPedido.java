package com.starking.vendas.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ItemPedido {
	
	private Long id;
	
	private Pedido pedido;
	
	private Produto produto;
	
	private Integer quantidade;
}
