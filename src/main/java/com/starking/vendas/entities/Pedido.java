package com.starking.vendas.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Pedido {
	
	private Long id;
	
	private Cliente cliente;
	
	private LocalDate dataPedido;
	
	private BigDecimal total;

}
