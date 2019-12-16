package com.ipartek.borja.ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class OperadoresEjercicio1 {
	

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
	
	public static void primeraParte(BufferedReader br){
		int a = 8;
		int b = 3;
		int c = -5;
		int opcionElegida = 0;
		int resultadoOperacion;
		boolean elegirOtraVez = true;
		
		
		do {
			System.out.print("Elige una de las siguientes operaciones: \n"
					+ "1.- a + b + c \n"
					+ "2.- 2 * b + 3 * (a – c) \n"
					+ "3.- a / b \n"
					+ "4.- a % b \n"
					+ "5.- a / c \n"
					+ "6.- a % c \n"
					+ "7.- a * b / c \n"
					+ "8.- a * (b / c) \n"
					+ "9.- (a * c) % b \n"
					+ "10.- a * (c % b) \n"
					+ "11.- (3 * a – 2 * b) % (2 * a – c) \n"
					+ "12.- 2 * ( a / 5 + (4 - b * 3)) % (a + c - 2) \n"
					+ "13.- (a - 3 * b) % (c + 2 * a) / (a - c) \n"
					+ "14.- a - b - c * 2 \n");
			try {
				String opcionMenu = br.readLine();
				opcionElegida = Integer.parseInt(opcionMenu);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (opcionElegida >= 1 && opcionElegida <= 14) {
				elegirOtraVez = false;
			}
			
		}while(elegirOtraVez);
		
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch (opcionElegida) {
		case 1:
			resultadoOperacion = a + b + c;
			System.out.println("a + b + c = " + resultadoOperacion);
			break;
		case 2:
			resultadoOperacion = 2 * b + 3 * (a - c);
			System.out.println("2 * b + 3 * (a – c) = " + resultadoOperacion);
			break;
		case 3:
			resultadoOperacion = a / b;
			System.out.println("a / b = " + resultadoOperacion);
			break;
		case 4:
			resultadoOperacion = a % b;
			System.out.println("a % b = " + resultadoOperacion);
			break;
		case 5:
			resultadoOperacion = a / c;
			System.out.println("a / c = " + resultadoOperacion);
			break;
		case 6:
			resultadoOperacion = a % c;
			System.out.println("a % c = " + resultadoOperacion);
			break;
		case 7:
			resultadoOperacion = a * b / c;
			System.out.println("a * b / c = " + resultadoOperacion);
			break;
		case 8:
			resultadoOperacion = a * (b / c);
			System.out.println("a * (b / c) = " + resultadoOperacion);
			break;
		case 9:
			resultadoOperacion = (a * c) % b;
			System.out.println("(a * c) % b = " + resultadoOperacion);
			break;
		case 10:
			resultadoOperacion = a * (c % b);
			System.out.println("a * (c % b) = " + resultadoOperacion);
			break;
		case 11:
			resultadoOperacion = (3 * a - 2 * b) % (2 * a - c);
			System.out.println("(3 * a – 2 * b) % (2 * a – c) = " + resultadoOperacion);
			break;
		case 12:
			resultadoOperacion = 2 * ( a / 5 + (4 - b * 3)) % (a + c - 2);
			System.out.println("2 * ( a / 5 + (4 - b * 3)) % (a + c - 2) = " + resultadoOperacion);
			break;
		case 13:
			resultadoOperacion = (a - 3 * b) % (c + 2 * a) / (a - c);
			System.out.println("(a - 3 * b) % (c + 2 * a) / (a - c) = " + resultadoOperacion);
			break;
		case 14:
			resultadoOperacion = a - b - c * 2;
			System.out.println("a - b - c * 2 = " + resultadoOperacion);
			break;
		}

	}
	
	public static void segundaParte(BufferedReader br) {
		double x = 88;
		double y = 3.5;
		double z = -5.2;
		double resultadoOperacion;
		int opcionElegida = 0;
		boolean elegirOtraVez = true;
		DecimalFormat df = new DecimalFormat("#.0000");
		
		do {
			System.out.print("Elige una de las siguientes operaciones: \n"
					+ "1.- x + y + z \n" 
					+ "2.- 2 * y + 3 * (x – z) \n"
					+ "3.- x / y \n"
					+ "4.- x % y \n"
					+ "5.- x / (y + z) \n"
					+ "6.- (x / y) + z \n"
					+ "7.- 2 * x / 3 * y \n"
					+ "8.- 2 * x / (3 * y) \n"
					+ "9.- x * y % z \n"
					+ "10.- x * (y % z) \n"
					+ "11.- 3 * x – z – 2 * x \n"
					+ "12.- 2 * x / 5 % y \n"
					+ "13.- x - 100 % y % z \n"
					+ "14.- x - y - z * 2 \n");
			try {
				String opcionMenu = br.readLine();
				opcionElegida = Integer.parseInt(opcionMenu);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (opcionElegida >= 1 && opcionElegida <= 14) {
				elegirOtraVez = false;
			}
			
		}while(elegirOtraVez);
		
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch (opcionElegida) {
		case 1:
			resultadoOperacion = x + y + z;
			System.out.println("x + y + z = " + df.format(resultadoOperacion));
			break;
		case 2:
			resultadoOperacion = 2 * y + 3 * (x - z);
			System.out.println("2 * y + 3 * (x – z) = " + df.format(resultadoOperacion));
			break;
		case 3:
			resultadoOperacion = x / y;
			System.out.println("x / y = " + df.format(resultadoOperacion));
			break;
		case 4:
			resultadoOperacion = x % y;
			System.out.println("x % y = " + df.format(resultadoOperacion));
			break;
		case 5:
			resultadoOperacion = x / (y + z);
			System.out.println("x / (y + z) = " + df.format(resultadoOperacion));
			break;
		case 6:
			resultadoOperacion = (x / y) + z;
			System.out.println("(x / y) + z = " + df.format(resultadoOperacion));
			break;
		case 7:
			resultadoOperacion = 2 * x / 3 * y;
			System.out.println("a * b / c = " + df.format(resultadoOperacion));
			break;
		case 8:
			resultadoOperacion = 2 * x / (3 * y);
			System.out.println("2 * x / (3 * y) = " + df.format(resultadoOperacion));
			break;
		case 9:
			resultadoOperacion = x * y % z;
			System.out.println("x * y % z = " + df.format(resultadoOperacion));
			break;
		case 10:
			resultadoOperacion = x * (y % z);
			System.out.println("x * (y % z) = " + df.format(resultadoOperacion));
			break;
		case 11:
			resultadoOperacion = 3 * x - z - 2 * x;
			System.out.println("3 * x – z – 2 * x = " + df.format(resultadoOperacion));
			break;
		case 12:
			resultadoOperacion = 2 * x / 5 % y;
			System.out.println("2 * x / 5 % y = " + df.format(resultadoOperacion));
			break;
		case 13:
			resultadoOperacion = x - 100 % y % z;
			System.out.println("x - 100 % y % z = " + df.format(resultadoOperacion));
			break;
		case 14:
			resultadoOperacion = x - y - z * 2;
			System.out.println("x - y - z * 2 = " + df.format(resultadoOperacion));
			break;
		}
	}
}
