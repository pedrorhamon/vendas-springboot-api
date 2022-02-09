package com.starking.vendas.entities;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Produto {
	
	private Long id;
	
	private String descricao;
	
	private BigDecimal preco;
}
