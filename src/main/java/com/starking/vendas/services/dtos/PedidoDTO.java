package com.starking.vendas.services.dtos;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
	
	private Integer cliente;
	private BigDecimal total;
	private List<ItemPedidoDTO> items;
}
