package br.com.joacirjunior.caixaeletronico.exception;

public class CaixaEletronicoException extends Exception {

	private static final long serialVersionUID = 1L;

	public CaixaEletronicoException(String msg, boolean fatal) {
		super(msg);		
		// Aborta caso seja um erro fatal
		if(fatal){
			System.exit(1);
		}
    }
	
}
