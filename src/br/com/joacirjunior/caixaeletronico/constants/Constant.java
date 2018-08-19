package br.com.joacirjunior.caixaeletronico.constants;

import org.springframework.http.HttpStatus;

public final class Constant {
	
	public static String MOEDA = "Real";
	public static String SIMBOLO = "R$";
	
	/**
	 * Indica a quantidade de cédulas que o Caixa Eletrônico 
	 * pode armazenar para cada um dos valores monteários.
	 * Exemplo : 100 (cem), significa que pode haver no máximo
	 * 100 notas de cada tipo/valor.
	 * */
	public static Integer CAPACIDADE_CEDULAS_NO_CAIXA = 200;	
	
}
