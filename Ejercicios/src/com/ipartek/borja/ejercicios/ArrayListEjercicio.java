package com.ipartek.borja.ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ArrayListEjercicio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Double> al = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String leerTexto = "";
		Double altura = 0.0;
		
		System.out.println("Introduzca la altura: ");
		try {
			leerTexto = br.readLine();
			altura = Double.parseDouble(leerTexto);
			al.add(altura);
		} catch (IOException e) {
			System.out.println("Error");
		}
		System.out.println(al.get(0));

	}

}
