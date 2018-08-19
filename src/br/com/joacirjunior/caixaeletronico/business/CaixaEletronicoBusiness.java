package br.com.joacirjunior.caixaeletronico.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.joacirjunior.caixaeletronico.constants.Constant;
import br.com.joacirjunior.caixaeletronico.model.Caixa;
import br.com.joacirjunior.caixaeletronico.model.Cedula;
import br.com.joacirjunior.caixaeletronico.util.Response;
import br.com.joacirjunior.caixaeletronico.validate.CaixaEletronicoValidate;


@Component
public class CaixaEletronicoBusiness {

	private Caixa caixa;
	
	@Autowired 
	private Response caixaEletronicoResponse;
	
	@Autowired
	private CaixaEletronicoValidate caixaEletronicoValidate;
	
	@PostConstruct
    public void init() {
        this.caixa = new Caixa();
    }

	/**
	 * Realiza o processo de abastecimento das notas no Caixa.
	 * @return JSONObject retorna indicando status do processamento.
	 **/
	public JSONObject abastecer() {
		
		JSONObject objResponse = new JSONObject();
		
		try {
			
			// Limpa os valores anteriores
			this.caixa.zerar();
			
			// Monta as cédulas
			Cedula cedulaCem = new Cedula("CEM", 100, Constant.CAPACIDADE_CEDULAS_NO_CAIXA);
			Cedula cedulaCinquenta = new Cedula("CINQUENTA", 50, Constant.CAPACIDADE_CEDULAS_NO_CAIXA);
			Cedula cedulaVinte = new Cedula("VINTE", 20, Constant.CAPACIDADE_CEDULAS_NO_CAIXA);
			Cedula cedulaDez = new Cedula("DEZ", 10, Constant.CAPACIDADE_CEDULAS_NO_CAIXA);
			
			// Repassa a lista ao Caixa
			this.caixa.adicionarCedula(cedulaCem);
			this.caixa.adicionarCedula(cedulaCinquenta);
			this.caixa.adicionarCedula(cedulaVinte);
			this.caixa.adicionarCedula(cedulaDez);
			
			// Obtém o status/extrato do caixa eletrônico
			JSONObject obj = this.status();
			
			// Monta o objeto de retorno
			objResponse = caixaEletronicoResponse.retornarSucesso(obj);
			
		} catch(Exception e) {
			e.printStackTrace();
			return caixaEletronicoResponse.retornarErroInterno(e.getMessage());
		}
			
		return objResponse;
	}
	
	/**
	 * Realiza o processo de deposito.
	 * @param dados Objeto JSON contendo os valores a serem depositados.
	 * @return JSONObject retorna indicando status do processamento.
	 **/
	public JSONObject depositar(JSONObject dados) {
		JSONObject objResponse = new JSONObject();
		// Valida o JSON recepcionado por parâmetro
		boolean validacaoJson = this.caixaEletronicoValidate.validateDeposito(dados);
		// Se o Json estiver válido, continua com o processamento...
		if(validacaoJson == true) {
			
			try {
				
				// Obtém parâmetros e cria objeto da cédula
				Integer valor = Integer.parseInt((String) dados.get("valor_monetario"));
				Integer qtd = Integer.parseInt((String) dados.get("quantidade"));
				Cedula cedula = new Cedula(valor, qtd);
				
				// Realiza o depósito da cédula
				JSONObject obj = new JSONObject();
				obj.put("response", this.caixa.depositar(cedula));
				
				// Monta o objeto de retorno
				objResponse = caixaEletronicoResponse.retornarSucesso(obj);
				
			} catch(Exception e) {
				e.printStackTrace();
				return caixaEletronicoResponse.retornarErroInterno(e.getMessage());
			}
			
		// Json está em formato inválido
		} else {
			return this.caixaEletronicoResponse.retornarErroInterno("JSON de entrada em formato inválido");
		}
			
		return objResponse;
	}
	
	
	/**
	 * Realiza o processo de saque.
	 * @param dados Objeto JSON contendo o valor a ser sacado.
	 * @return JSONObject retorna indicando o status do processamento.
	 **/
	public JSONObject sacar(JSONObject dados) {
		
		JSONObject objResponse = new JSONObject();
		
		// Valida o JSON recepcionado por parâmetro
		boolean validacaoJson = this.caixaEletronicoValidate.validateSaque(dados);
		
		// Se o Json estiver válido, continua com o processamento...
		if(validacaoJson == true) {
		
			try {
				
				Integer valorSaque = Integer.parseInt((String) dados.get("valor"));
				
				JSONObject obj = new JSONObject();
				JSONArray listaSacadas = new JSONArray();
				
				// Percorre as cedulas existentes no caixa
				for(Cedula cedula : this.caixa.getCedulas()) {
					// Verifica se ainda há alguma cedula
					if(cedula.getQuantidade() > 0) {
						
						if(valorSaque >= cedula.getValorMonetario()){
							
							int quantidadeSacada = valorSaque / cedula.getValorMonetario();
							cedula.setQuantidade(cedula.getQuantidade() - quantidadeSacada);
							
							JSONObject cedulaSacada = new JSONObject();
							cedulaSacada.put("valor_cedula", String.valueOf(cedula.getValorMonetario()));
							cedulaSacada.put("quantidade", quantidadeSacada); 
							listaSacadas.add(cedulaSacada);
							
							valorSaque = valorSaque % cedula.getValorMonetario();
							
						}
						
					}
				}
				
				obj.put("response", listaSacadas);
				
				// Monta o objeto de retorno
				objResponse = caixaEletronicoResponse.retornarSucesso(obj);
				
			} catch(Exception e) {
				e.printStackTrace();
				return caixaEletronicoResponse.retornarErroInterno(e.getMessage());
			}
			
		// Json está em formato inválido
		} else {
			return this.caixaEletronicoResponse.retornarErroInterno("JSON de entrada em formato inválido");
		}
			
		return objResponse;
	}
	
	
	/**
	 * Retorna um panorama do Caixa, indicando as cédulas que 
	 * estão armazenadas, assim como suas quantidades.
	 *  
	 **/
	public JSONObject status() {
		JSONObject response = new JSONObject();
		JSONArray lista = new JSONArray();
		for(Cedula cedula : caixa.getCedulas()) {
			lista.add(cedula);
		}
		response.put("response", lista);
		return caixaEletronicoResponse.retornarSucesso(response);
	}
	
}
