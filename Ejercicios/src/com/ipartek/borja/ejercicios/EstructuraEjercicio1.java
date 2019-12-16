package com.ipartek.borja.ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EstructuraEjercicio1 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int opcionElegida = 0;
		boolean elegirOtraVez = true;
		
		
		do {
			System.out.println("Eliga: \n" + "1.- Fahrenheit \n" + "2.- Circunferencia \n"
								+ "3.- Velocidad \n" + "4.- Triangulo \n");
			String opcionMenu;
			try {
				opcionMenu = br.readLine();
				opcionElegida = Integer.parseInt(opcionMenu);
			} catch (IOException e) {
				System.out.println("Fallo de entrada-salida");
			}
			if (opcionElegida >=1 && opcionElegida <= 4) {
				elegirOtraVez = false;
				switch(opcionElegida) {
				case 1:
					fahrenheit(br);
					break;
				case 2:
					circunferencia(br);
					break;
				case 3:
					velocidad(br);
					break;
				case 4:
					triangulo(br);
					break;
				}
			}

		}while (elegirOtraVez);


	}
	
	public static void fahrenheit(BufferedReader br) {
		
		double centigrados = 0;
		double fahrenheit = 0;
		
		System.out.println("Introduzca la temperatura en Grados Centigrados: ");
		try {
			String datosGrados = br.readLine();
			centigrados = Double.parseDouble(datosGrados);
			br.close();
		} catch (IOException e) {
			System.out.println("Fallo de entrada-salida");
		}
		
		fahrenheit = 32 + (9 * centigrados / 5);
		System.out.println(centigrados + "ºC = " + fahrenheit + "F" );
		
	}
	
	public static void circunferencia(BufferedReader br) {
		
		double radio = 0;
		double longitud = 0;
		double area = 0;
		double Pi = Math.PI;
		
		System.out.println("Introduzca el radio de la circunferencia: ");
		try {
			String circunferencia = br.readLine();
			radio = Double.parseDouble(circunferencia);
			br.close();
		} catch (IOException e) {
			System.out.println("Fallo de entrada-salida");
		}
		
		longitud = 2 * Pi * radio;
		area = Pi * Math.pow(radio, 2);
		System.out.println("Longitud de la circunferencia: " + longitud + "\n"
				+ "Area de la circunferencia: " + area );
		
	}
	
	public static void velocidad(BufferedReader br) {
		
		double velocidad = 0;
		
		System.out.println("Introduzca la velocidad en Km/h: ");
		try {
			String velocidadKm = br.readLine();
			velocidad = Double.parseDouble(velocidadKm);
			br.close();
		} catch (IOException e) {
			System.out.println("Fallo de entrada-salida");
		}
		System.out.println("Km/h: " + velocidad + "\n"
							+ "m/s: " + velocidad * 1000 / 3600);
		
	}
	
	public static void triangulo(BufferedReader br) {
		
		double cateto1 = 0;
		double cateto2 = 0;
		
		System.out.println("Introduzca la longitud del primer cateto: ");
		try {
			String primerCateto = br.readLine();
			cateto1 = Double.parseDouble(primerCateto);
		} catch (IOException e) {
			System.out.println("Fallo de entrada-salida");
		}
		System.out.println("Introduzca la longitud del segundo cateto: ");
		try {
			String segundoCateto = br.readLine();
			cateto2 = Double.parseDouble(segundoCateto);
			br.close();
		} catch (IOException e) {
			System.out.println("Fallo de entrada-salida");
		}
		System.out.println("Hipotenusa: " + Math.sqrt(Math.pow(cateto1, 2) + Math.pow(cateto2, 2)));
		
	}

}
