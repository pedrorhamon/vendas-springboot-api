package com.starking.vendas.services.dtos;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.starking.vendas.services.validation.NotEmptyList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
	
	@NotNull(message = "Informe o codigo do cliente")
	private Integer cliente;
	
	@NotNull(message = "Campo total do pedido é obrigatório")
	private BigDecimal total;
	
	@NotEmptyList(message = "Pedido não pode ser realizado sem items.")
	private List<ItemPedidoDTO> items;
	
	private String status;
}
