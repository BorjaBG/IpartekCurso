package com.ipartek.borja.modelos;

public class ModelosException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	public ModelosException() {
		super();
	}

	public ModelosException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public ModelosException(String mensaje) {
		super(mensaje);
	}

	public ModelosException(Throwable causa) {
		super(causa);
	}
	

}
