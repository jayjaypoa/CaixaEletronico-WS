package br.com.joacirjunior.caixaeletronico.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
public class Caixa {
	
	private List<Cedula> cedulas;
	
	public Caixa() {
		this.cedulas = new ArrayList<Cedula>();
	}
	
	public Caixa(List<Cedula> cedulas) {
		this.cedulas = cedulas;
	}

	public void adicionarCedula(Cedula cedula) {
		cedulas.add(cedula);
	}
		
	public List<Cedula> getCedulas(){
		return this.cedulas;
	}
	
	public void zerar() {
		this.cedulas.clear();
	}
	
	public boolean depositar(Cedula cedula) {
		// TODO Programar método para depositar cédulas
		return false;
	}
	
}
