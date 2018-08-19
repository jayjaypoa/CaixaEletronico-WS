package br.com.joacirjunior.caixaeletronico.util;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

@Component
public class Response {
	
	public JSONObject retornarSucesso(JSONObject dados){
	    JSONObject response = new JSONObject();
	    if(dados.containsKey("response")) {
	    	response.put("response", dados.get("response"));
	    } else {
	    	response.put("response", dados);
	    }
	    response.put("responseMessage", "Sucesso");
	    response.put("responseStatus", HttpServletResponse.SC_OK);
	    return response;
	}
	
	public JSONObject retornarErroInterno(JSONObject dados){
	    JSONObject response = new JSONObject();
	    response.put("response", dados);
	    response.put("responseMessage", "Falha interna");
	    response.put("responseStatus", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    return response;
	}
	
	public JSONObject retornarErroInterno(String msgError){
	    JSONObject response = new JSONObject();
	    response.put("response", null);
	    response.put("responseMessage", msgError);
	    response.put("responseStatus", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    return response;
	}
		
}
