package br.com.joacirjunior.caixaeletronico.validate;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CaixaEletronicoValidate {

	/**
	 * Valida o JSON de input do depósito.
	 * @param dados JSON a ser validado.
	 * @return true Caso não haja erros.
	 * 		   false Caso haja algum erro no layout do JSON. 
	 **/
	private boolean validateJsonDeposito(JSONObject dados) {
		if(!dados.containsKey("valor_monetario"))
			return false;
		if(!dados.containsKey("quantidade"))
			return false;
		return true;
	}
	
	/**
	 * Valida o JSON de input do saque.
	 * @param dados JSON a ser validado.
	 * @return true Caso não haja erros.
	 * 		   false Caso haja algum erro no layout do JSON. 
	 **/
	private boolean validateJsonSaque(JSONObject dados) {
		if(!dados.containsKey("valor"))
			return false;
		return true;
	}
	
	
	public boolean validateDeposito(JSONObject dados) {
		if(!this.validateJsonDeposito(dados))
			return false;
		return true;
	}
	
	public boolean validateSaque(JSONObject dados) {		
		if(!this.validateJsonSaque(dados))
			return false;
		if(Integer.parseInt((String) dados.get("valor"))%10 != 0)
			return false;
		return true;
	}
	
}
