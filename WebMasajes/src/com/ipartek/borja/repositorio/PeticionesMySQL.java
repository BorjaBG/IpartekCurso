package com.ipartek.borja.repositorio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PeticionesMySQL {
	
	private String url;
	private String usuario;
	private String contraseña;
	
	// SINGLETON
		private PeticionesMySQL(String url, String usuario, String contraseña) {
			this.url = url;
			this.usuario = usuario;
			this.contraseña = contraseña;
					
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("No se ha encontrado el driver de MySQL");
			}
					
		}
				
			private static PeticionesMySQL INSTANCIA = null;
				
		public static PeticionesMySQL getInstancia(String pathConfiguracion) {
			try{
				if(INSTANCIA == null) {
					Properties configuracion = new Properties();
					configuracion.load(new FileInputStream(pathConfiguracion));
					INSTANCIA = new PeticionesMySQL(configuracion.getProperty("mysql.url"),
								configuracion.getProperty("mysql.usuario"), configuracion.getProperty("mysql.contraseña"));
				}
				return INSTANCIA;
			}catch(FileNotFoundException e) {
				throw new RuntimeException("No se ha podido encontrar el archivo", e);
			}catch (IOException e) {
				throw new RuntimeException("Fallo de lectura/escritura del archivo", e);
			}
					
		}
				
		//FIN SINGLETON
		
		private Connection getConexion(){
			//System.out.println(url + "\n" + usuario + "\n" + contraseña + "\n");
			try {
				return DriverManager.getConnection(url, usuario, contraseña);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Error al conectar con la base de datos", e);
			}
		}

}
