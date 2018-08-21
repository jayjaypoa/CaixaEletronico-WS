import static org.junit.Assert.*;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import br.com.joacirjunior.caixaeletronico.business.CaixaEletronicoBusiness;

@TestComponent
public class CaixaEletronicoTest {

	@Autowired
	private CaixaEletronicoBusiness caixaEletronicoBusiness;		
	
	@Test
	public void testExemploJSONObject(){
		JSONObject jsonObject1 = new JSONObject();		
		jsonObject1.put("chave", "valor");
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("chave", "valor");
		assertEquals(jsonObject1.get("teste"), jsonObject2.get("teste"));
	}
	
	@Test
	public void testSaqueOitentaReais() {
		
		// Cria objeto de parâmetro, indicando o saque de 80 reais
		JSONObject objParam = new JSONObject();
		objParam.put("valor", "80");
		
		// Cria objeto esperado de retorno
		JSONObject objEsperado = new JSONObject();
		JSONArray listaCedulas = this.criarJSONArrayCedulas(0, 1, 1, 1);
		objEsperado.put("response", listaCedulas);
		objEsperado.put("responseMessage", "Sucesso");
		objEsperado.put("responseStatus", HttpStatus.SC_OK);
		
		// Gera o objeto de retorno a partir do objeto de parâmetro
		JSONObject objRetorno = this.caixaEletronicoBusiness.sacar(objParam);
		        
		// Compara os objetos de retorno com o objeto esperado
		assertTrue("This will succeed", objRetorno.equals(objEsperado));
	
	}
	
	@Test
	public void testSaqueTrintaReais() {
		
		// Cria objeto de parâmetro, indicando o saque de 30 reais
		JSONObject objParam = new JSONObject();
		objParam.put("valor", "30");
		
		// Cria objeto esperado de retorno
		JSONObject objEsperado = new JSONObject();
		JSONArray listaCedulas = this.criarJSONArrayCedulas(0, 0, 1, 1);
		objEsperado.put("response", listaCedulas);
		objEsperado.put("responseMessage", "Sucesso");
		objEsperado.put("responseStatus", HttpStatus.SC_OK);
		
		// Gera o objeto de retorno a partir do objeto de parâmetro
		JSONObject objRetorno = this.caixaEletronicoBusiness.sacar(objParam);
		        
		// Compara os objetos de retorno com o objeto esperado
		assertTrue("This will succeed", objRetorno.equals(objEsperado));
	
	}
	
	@Test
	public void testSaqueDuzentosReais() {
		
		// Cria objeto de parâmetro, indicando o saque de 200 reais
		JSONObject objParam = new JSONObject();
		objParam.put("valor", "200");
		
		// Cria objeto esperado de retorno
		JSONObject objEsperado = new JSONObject();
		JSONArray listaCedulas = this.criarJSONArrayCedulas(2, 0, 0, 0);
		objEsperado.put("response", listaCedulas);
		objEsperado.put("responseMessage", "Sucesso");
		objEsperado.put("responseStatus", HttpStatus.SC_OK);
		
		// Gera o objeto de retorno a partir do objeto de parâmetro
		JSONObject objRetorno = this.caixaEletronicoBusiness.sacar(objParam);
		        
		// Compara os objetos de retorno com o objeto esperado
		assertTrue("This will succeed", objRetorno.equals(objEsperado));
	
	}
	
	private JSONArray criarJSONArrayCedulas(Integer qtdCedula100, Integer qtdCedula50, Integer qtdCedula20, Integer qtdCedula10){
		
		JSONArray lista = new JSONArray();
		
		// Cria cédula de 100 reais
		if(qtdCedula100 > 0){
			JSONObject objCedula100 = this.criarJSONObjCedula(100, qtdCedula100);
			lista.put(objCedula100);
		}
		
		// Cria cédula de 50 reais
		if(qtdCedula50 > 0){
			JSONObject objCedula50 = this.criarJSONObjCedula(50, qtdCedula50);
			lista.put(objCedula50);
		}
		
		// Cria cédula de 20 reais
		if(qtdCedula20 > 0){
			JSONObject objCedula20 = this.criarJSONObjCedula(20, qtdCedula20);
			lista.put(objCedula20);
		}
		
		// Cria cédula de 10 reais
		if(qtdCedula10 > 0){
			JSONObject objCedula10 = this.criarJSONObjCedula(10, qtdCedula10);
			lista.put(objCedula10);
		}
		
		return lista;
	}
	
	private JSONObject criarJSONObjCedula(Integer valorCedula, Integer quantidade){
		JSONObject objCedula = new JSONObject();
		objCedula.put("valor_cedula", String.valueOf(valorCedula));
		objCedula.put("quantidade", String.valueOf(quantidade));
		return objCedula;
	}
	
}
