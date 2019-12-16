package com.ipartek.borja.ejerciciosClases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EntradaEstandar{
	
	private static final String ERROR_ENTRADA_SALIDA = "Se ha producido un error de Entrada-Salida";
	private static final String ERROR_VALOR = "ERROR: El valor introducido no es correcto";
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static int leerInt(String mensaje) {
		int numeroRecogidoInt = 0;
		String numeroRecogidoFalloInt = "";
		boolean falloInt = false;
		do {
			try {
				if(!falloInt) {
					numeroRecogidoInt = Integer.parseInt(mensaje);
				} else {
					System.out.println("Operando entero: ");
					numeroRecogidoFalloInt = br.readLine();
					numeroRecogidoInt = Integer.parseInt(numeroRecogidoFalloInt);
				}
				falloInt = false;
			}catch(NumberFormatException nfe) {
				System.out.println(ERROR_VALOR);
				falloInt = true;
			} catch (IOException e) {
				System.out.println(ERROR_ENTRADA_SALIDA);
			}
		}while(falloInt);
		return numeroRecogidoInt;
	}

	public static float leerFloat(String mensaje) {
		float numeroRecogidoFloat = 0;
		String numeroRecogidoFalloFloat = "";
		boolean falloFloat = false;
		
		do {
			try {
				if(!falloFloat) {
					numeroRecogidoFloat = Float.parseFloat(mensaje);
				} else {
					System.out.println("Operando flotante: ");
					numeroRecogidoFalloFloat = br.readLine();
					numeroRecogidoFloat = Float.parseFloat(numeroRecogidoFalloFloat);
				}
				falloFloat = false;
			}catch(NumberFormatException nfe) {
				System.out.println(ERROR_VALOR);
				falloFloat = true;
			} catch (IOException e) {
				System.out.println(ERROR_ENTRADA_SALIDA);
			}
		}while(falloFloat);
		
		return numeroRecogidoFloat;
	}

	public static String leerString(String mensaje) {
		
		if(mensaje == null) {
			throw new RuntimeException("No se acepta un nombre nulo");
		}
		
		if(mensaje.trim().length() == 0) {
			throw new RuntimeException("No se aceptan nombres vacíos");
		}
		
		return mensaje;
	}
	

}
