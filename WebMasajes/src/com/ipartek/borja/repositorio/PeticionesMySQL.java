package com.ipartek.borja.repositorio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.ipartek.borja.modelos.Cliente;
import com.ipartek.borja.modelos.Peticiones;
import com.ipartek.borja.modelos.Resena;
import com.ipartek.borja.modelos.Servicio;
import com.ipartek.borja.modelos.Trabajador;

public class PeticionesMySQL implements Dao<Peticiones>{
	
	private static final String SELECT_ACTUACIONES = "SELECT * FROM actuacionesgetall";
	private String url;
	private String usuario;
	private String contraseña;
	private static final String SELECT_VALORACION = "SELECT valoracion.idValoracion, valoracion, reseña, nombre FROM valoracion"
	+ " INNER JOIN actuaciones ON valoracion.idValoracion = actuaciones.idValoracion INNER JOIN servicio ON actuaciones.idServicio = servicio.idServicio";
	
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
		
		@SuppressWarnings("unused")
		private Connection getConexion(){
			//System.out.println(url + "\n" + usuario + "\n" + contraseña + "\n");
			try {
				return DriverManager.getConnection(url, usuario, contraseña);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Error al conectar con la base de datos", e);
			}
		}
		
		public Iterable<Peticiones> obtenerTodos() {
			
			try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
				try(PreparedStatement ps = con.prepareStatement(SELECT_VALORACION)) {
					//ps.setInt(1, id);
					try(ResultSet rs = ps.executeQuery()){
						ArrayList<Peticiones> peticiones = new ArrayList<>();
						while(rs.next()) {
							/*idV = ;
							valoracion = rs.getString("valoracion");
							resena = rs.getString("reseña");
							nombre = rs.getString("servicio.nombre");*/
							peticiones.add(new Peticiones(rs.getInt("idValoracion"),
									rs.getString("valoracion"),
									rs.getString("reseña"),
									new Servicio(rs.getString("servicio.nombre"))));
						}
						return peticiones;
					}
				}
			} catch (SQLException e) {
				throw new RuntimeException("Error al obtener la persona id: ", e);
			}
		}
		
		public Iterable<Peticiones> obtenerTodosActuaciones() {
			
			try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
				try(PreparedStatement ps = con.prepareStatement(SELECT_ACTUACIONES)) {
					//ps.setInt(1, id);
					try(ResultSet rs = ps.executeQuery()){
						ArrayList<Peticiones> peticiones = new ArrayList<>();
						while(rs.next()) {
							/*idV = ;
							valoracion = rs.getString("valoracion");
							resena = rs.getString("reseña");
							nombre = rs.getString("servicio.nombre");*/
							peticiones.add(new Peticiones(rs.getInt("idValoracion"),
									new Servicio(rs.getString("servicio.nombre")),
									new Cliente(rs.getString("cliente.nombre")),
									new Trabajador(rs.getString("trabajador.nombre")),
									new Resena(rs.getString("resena.valoracion")),
									rs.getDate("fecha")));
						}
						return peticiones;
					}
				}
			} catch (SQLException e) {
				throw new RuntimeException("Error al obtener la persona id: ", e);
			}
		}

		@Override
		public Peticiones obtenerPorId(int id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Integer agregar(Peticiones objeto) {
			return null;
			// TODO Auto-generated method stub
			
		}

		@Override
		public void eliminar(int id) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actualizar(Peticiones objeto) {
			// TODO Auto-generated method stub
			
		}

}
