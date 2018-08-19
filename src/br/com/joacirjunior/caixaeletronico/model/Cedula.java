package br.com.joacirjunior.caixaeletronico.model;

import javax.persistence.Entity;

import br.com.joacirjunior.caixaeletronico.constants.Constant;

public class Cedula implements ICedula {

	private String simbolo;
	private String descricao;
	private Integer valorMonetario;
	private Integer quantidade;
	
	public Cedula(Integer valorMonetario, Integer quantidade) {
		this.simbolo = Constant.SIMBOLO;
		this.valorMonetario = valorMonetario;
		this.quantidade = quantidade;
	}
	
	public Cedula(String descricao, Integer valorMonetario, Integer quantidade) {
		this.simbolo = Constant.SIMBOLO;
		this.descricao = descricao;
		this.valorMonetario = valorMonetario;
		this.quantidade = quantidade;
	}
	
	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getValorMonetario() {
		return valorMonetario;
	}

	public void setValorMonetario(Integer valorMonetario) {
		this.valorMonetario = valorMonetario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
