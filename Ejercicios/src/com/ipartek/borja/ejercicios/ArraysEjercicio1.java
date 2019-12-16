package com.ipartek.borja.ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArraysEjercicio1 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int opcionElegida = 0;
		boolean elegirOtraVez = true;
		
		//Comentario
		do {
			System.out.println("Eliga: \n" + "1.- Media \n" + "2.- Nota media de la clase \n");
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
					media(br);
					break;
				case 2:
					mediaClase(br);
				}
			}

		}while (elegirOtraVez);

	}
	
	public static void media(BufferedReader br) {
		int i;
		int[] numeros = new int[10];
		double media = 0;
		for (i = 0; i < 10; i++) {
			System.out.println("Introduzca los numeros: ");
			try {
				String datosGrados = br.readLine();
				numeros[i] = Integer.parseInt(datosGrados);
			} catch (IOException e) {
				System.out.println("Fallo de entrada-salida");
			}
		}
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("Fallo al cerrar la entrada de texto");
		}
		
		for(i = 0; i < 10; i++) {
			if(i % 2 == 0) {
				media = media + numeros[i];
			}
		}
		
		System.out.println("La media es de: " + media/5);
		
	}
	
	public static void mediaClase(BufferedReader br) {
		int i;
		int alumnos = 0;
		double[] notas;
		double media = 0;
		double suma = 0;
		
		System.out.println("Introduzca el numero de alumnos: ");
		try {
			String numeroAlumnos = br.readLine();
			alumnos = Integer.parseInt(numeroAlumnos);
		} catch (IOException e) {
			System.out.println("Fallo de entrada-salida");
		}
		notas = new double[alumnos];
		
		for (i = 0; i < alumnos; i++) {
			System.out.println("Introduzca la nota de los alumnos: ");
			try {
				String datosGrados = br.readLine();
				notas[i] = Double.parseDouble(datosGrados);
			} catch (IOException e) {
				System.out.println("Fallo de entrada-salida");
			}
		}
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("Fallo al cerrar la entrada de texto");
		}
		
		for(i = 0; i < alumnos; i++) {
			suma = suma + notas[i];
		}
		
		media = suma / alumnos;
		
		System.out.println("La nota media es de: " + media);
		
		System.out.println("Los alumnos a continuacion tienen una nota superior a la media: ");
		for(i = 0; i < alumnos; i++) {
			if(notas[i] > media) {
				System.out.println("El alumno numero " + i + " tiene una nota de " + notas[i]);
			}
		}
	}

}
