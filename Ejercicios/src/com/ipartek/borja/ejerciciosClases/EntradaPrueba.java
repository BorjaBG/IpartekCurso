package com.ipartek.borja.ejerciciosClases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EntradaPrueba {
	
	//static EntradaEstandar ee = new EntradaEstandar();

	private static final String FALLO_DE_ENTRADA_SALIDA = "Ha ocurrido un fallo de Entrada-Salida";

	public static void main(String[] args) {
		String numeroIntroducido = "";
		boolean error = false;
		int operando1 = 0;
		int operando2 = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			do {
				for(int i = 0; i < 2; i++) {
					if (i == 0) {
						System.out.println("Operando entero 1: ");
					}else {
						System.out.println("Operando entero 2: ");
					}
					try {
						numeroIntroducido = br.readLine();
						if (i == 0) {
							operando1 = EntradaEstandar.leerInt(numeroIntroducido);
						} else {
							operando2 = EntradaEstandar.leerInt(numeroIntroducido);
						}
					} catch (IOException e) {
						System.out.println(FALLO_DE_ENTRADA_SALIDA);
					}
					
				}
			}while (error);
		
		System.out.println(operando1 + operando2);
		
		//Parte de los numeros float
		float operando1Float = 0;
		float operando2Float = 0;
		
		do {
			for(int i = 0; i < 2; i++) {
				if (i == 0) {
					System.out.println("Operando flotante 1: ");
				}else {
					System.out.println("Operando flotante 2: ");
				}
				try {
					numeroIntroducido = br.readLine();
					if (i == 0) {
						operando1Float = EntradaEstandar.leerFloat(numeroIntroducido);
					} else {
						operando2Float = EntradaEstandar.leerFloat(numeroIntroducido);
					}
				} catch (IOException e) {
					System.out.println(FALLO_DE_ENTRADA_SALIDA);
				}
				
			}
		}while (error);
		
		System.out.println(operando1Float / operando2Float);
		
		//Parte del string final
		String nombre = "";
		
		System.out.println("Introduzca su nombre: ");
		try {
			nombre = br.readLine();
			nombre = EntradaEstandar.leerString(nombre);
		} catch (IOException e) {
			System.out.println(FALLO_DE_ENTRADA_SALIDA);
		}
		
		System.out.println("Hola " + nombre + " ¡Bienvenido a Java!");

	}
}
