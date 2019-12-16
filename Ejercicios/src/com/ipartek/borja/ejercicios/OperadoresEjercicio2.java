package com.ipartek.borja.ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OperadoresEjercicio2 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int opcionElegida = 0;
		boolean elegirOtraVez = true;
		
		
		do {
			System.out.println("Eliga: \n" + "1.- Primer ejercicio \n" + "2.- Segundo Ejercicio \n");
			String opcionMenu;
			try {
				opcionMenu = br.readLine();
				opcionElegida = Integer.parseInt(opcionMenu);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (opcionElegida >=1 && opcionElegida <= 2) {
				elegirOtraVez = false;
				if (opcionElegida == 1) {
					primeraParte(br);
				} else {
					segundaParte(br);
				}
			}

		}while (elegirOtraVez);

	}
	
	public static void primeraParte(BufferedReader br) {
		boolean X = true;
		boolean Y = false;
		boolean Z = true;
		int opcionElegida = 0;
		boolean elegirOtraVez = true;
		
		
		do {
			System.out.println("Eliga entre estas operaciones: \n"
					+ "1.- (X && Y) || (X && Z) \n" + "2.- (X || !Y)&& (!X || Z) \n"
					+ "3.- X || Y && Z \n" + "4.- !(X || Y) && Z \n"
					+ "5.- X || Y || X && !Z && !Y \n" + "6.- !X || !Y || Z && X && !Y \n");
			String opcionMenu;
			try {
				opcionMenu = br.readLine();
				opcionElegida = Integer.parseInt(opcionMenu);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (opcionElegida >=1 && opcionElegida <= 6) {
				elegirOtraVez = false;
			}

		}while (elegirOtraVez);
		
		switch (opcionElegida) {
		case 1:
			System.out.println((X && Y) || (X && Z));
			break;
		case 2:
			System.out.println((X || !Y)&& (!X || Z));
			break;
		case 3:
			System.out.println(X || Y && Z);
			break;
		case 4:
			System.out.println(!(X || Y) && Z);
			break;
		case 5:
			System.out.println(X || Y || X && !Z && !Y);
			break;
		case 6:
			System.out.println(!X || !Y || Z && X && !Y);
			break;
		}

	}
	
	public static void segundaParte(BufferedReader br) {
		boolean W = false;
		boolean X = true;
		boolean Y = true;
		boolean Z = false;
		int opcionElegida = 0;
		boolean elegirOtraVez = true;
		
		
		do {
			System.out.println("Eliga entre estas operaciones: \n"
					+ "1.- W || Y && X && W || Z \n" + "2.- X && !Y && !X || !W && Y \n"
					+ "3.- !(W || !Y) && X || Z \n" + "4.- X && Y && W || Z || X \n"
					+ "5.- Y || !(Y || Z && W) \n" + "6.- !X || !Y || Z && X && !Y \n");
			String opcionMenu;
			try {
				opcionMenu = br.readLine();
				opcionElegida = Integer.parseInt(opcionMenu);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (opcionElegida >=1 && opcionElegida <= 6) {
				elegirOtraVez = false;
			}

		}while (elegirOtraVez);
		
		switch (opcionElegida) {
		case 1:
			System.out.println(W || Y && X && W || Z);
			break;
		case 2:
			System.out.println(X && !Y && !X || !W && Y);
			break;
		case 3:
			System.out.println(!(W || !Y) && X || Z);
			break;
		case 4:
			System.out.println(X && Y && W || Z || X);
			break;
		case 5:
			System.out.println(Y || !(Y || Z && W));
			break;
		case 6:
			System.out.println(!X && Y && (!Z || !X));
			break;
		}

	}

}
