package br.com.joacirjunior.caixaeletronico.model;

public interface ICedula {
	
	public String getSimbolo();
	public void setSimbolo(String simbolo);
	
	public String getDescricao();
	public void setDescricao(String descricao);

	public Integer getValorMonetario();
	public void setValorMonetario(Integer valorMonetario);

	public Integer getQuantidade();
	public void setQuantidade(Integer quantidade);
	
}
