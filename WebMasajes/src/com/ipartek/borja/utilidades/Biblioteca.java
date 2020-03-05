package com.ipartek.borja.utilidades;

public class Biblioteca {
	
	private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
	
	public static boolean dniValido(String dni) {
		int numero;
		char letra;
		char letraCalculada;
		
		letra = dni.charAt(8);
		
		numero = Integer.parseInt(dni.substring(0, 8).replace('X',  '0').replace('Y', '1').replace('Z', '2'));
		
		letraCalculada = LETRAS_DNI.charAt(numero % 23);
		
		return letraCalculada == letra;
	}

}
