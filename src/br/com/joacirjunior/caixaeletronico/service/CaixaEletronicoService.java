package br.com.joacirjunior.caixaeletronico.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.joacirjunior.caixaeletronico.business.CaixaEletronicoBusiness;
import br.com.joacirjunior.caixaeletronico.util.Response;

@Service
@RequestMapping("/caixa-eletronico")
public class CaixaEletronicoService {
    
	@Autowired
	private CaixaEletronicoBusiness caixaEletronicoBusiness;
	
	/**
     * Realiza o processamento de abastecimento do Caixa.
     **/
    @RequestMapping(value = "/abastecer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JSONObject abastecer() {
    	// Realiza o abastecimento com cédulas e obtém retorno do processamento
    	return caixaEletronicoBusiness.abastecer();
    }
    
    /**
     * Obtém o status do Caixa, no qual é possível identificar
     * quais cédulas estão armazenadas no mesmo, assim como 
     * sua respectiva quantidade.
     * 
     **/
    @RequestMapping(value = "/status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JSONObject status() {
    	return caixaEletronicoBusiness.status();
    }
	
	/**
     * Realiza o processamento do deposito.
     * 
     **/
    @RequestMapping(value = "/depositar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JSONObject depositar(@RequestBody JSONObject dados) {
    	// Realiza o depósito e obtém retorno do processamento
    	JSONObject respDeposito = caixaEletronicoBusiness.depositar(dados);
    	// Retorna resultado do processamento
		return respDeposito;
    }
	
    /**
     * Realiza o processamento do saque
     **/
    @RequestMapping(value = "/sacar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JSONObject sacar(@RequestBody JSONObject dados) {
    	
    	// Realiza o saque e obtém retorno do processamento
    	JSONObject respSaque = caixaEletronicoBusiness.sacar(dados);
    	
    	// Retorna resultado do processamento
    	return respSaque;
    }
   
    
}
