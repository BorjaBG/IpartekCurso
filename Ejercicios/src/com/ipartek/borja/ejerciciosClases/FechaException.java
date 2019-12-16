package com.ipartek.borja.ejerciciosClases;

public class FechaException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FechaException() {
		
	}
	
	public FechaException(String message) {
		super (message);
		//System.out.println(message);
	}
	
	public FechaException(String message, Throwable cause) {
		super (message, cause);
		/*cause.getCause();
		System.out.println(message + "\n" + "El error ha sido debido a: \n" + cause);*/
	}
	
	public FechaException(Throwable cause) {
		super (cause);
		//System.out.println("Error en: \n" + cause);
	}

}
