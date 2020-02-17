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
import com.ipartek.borja.modelos.Actuaciones;

public class ActuacionesMySQL implements Dao<Actuaciones>{
	
	//Cambiar llamadas
	private String sqlSelect = "SELECT * FROM actuaciones";
	private String sqlSelectId = "SELECT * FROM actuaciones WHERE idServicio=? AND idCliente=? AND idValoracion=?";
	private String sqlInsert = "INSERT INTO actuaciones (idServicio, idTrabajador, idValoracion, fecha) VALUES (?,?,?,?)";
	private String sqlDelete = "DELETE FROM actuaciones WHERE idServicio=? AND idCliente=? AND idValoracion=?";
	private String sqlUpdate = "UPDATE actuaciones SET fecha=? WHERE idServicio=? AND idCliente=? AND idValoracion=?";
	
	private String url;
	private String usuario;
	private String contraseña;
	
	
	// SINGLETON
	private ActuacionesMySQL(String url, String usuario, String contraseña) {
		this.url = url;
		this.usuario = usuario;
		this.contraseña = contraseña;
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("No se ha encontrado el driver de MySQL");
		}
				
	}
			
		private static ActuacionesMySQL INSTANCIA = null;
			
	public static ActuacionesMySQL getInstancia(String pathConfiguracion) {
		try{
			if(INSTANCIA == null) {
				Properties configuracion = new Properties();
				configuracion.load(new FileInputStream(pathConfiguracion));
				INSTANCIA = new ActuacionesMySQL(configuracion.getProperty("mysql.url"),
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
	
	public Iterable<Actuaciones> obtenerTodos() {
		
		try(Connection con = getConexion()){
			try (PreparedStatement ps = con.prepareStatement(sqlSelect)) {
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<Actuaciones> actuaciones = new ArrayList<>();
					while (rs.next()) {
						actuaciones.add(new Actuaciones(rs.getInt("idServicio"), rs.getInt("idTrabajador"), rs.getInt("idValoracion"), rs.getDate("fecha")));
					}
					return actuaciones;
				}
			}
			
		}catch(SQLException e) {
			throw new RuntimeException("Error al obtener todos los registros", e);
		}
	}
	
	
	public Actuaciones obtenerPorId(int id) {
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(sqlSelectId)) {
				ps.setInt(1, id);
				try(ResultSet rs = ps.executeQuery()){

					if(rs.next()) {
						return new Actuaciones(rs.getInt("idServicio"), rs.getInt("idTrabajador"), rs.getInt("idValoracion"), rs.getDate("fecha"));
					} else {
						return null;
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la persona id: " + id, e);
		}
	}
	

	public void agregar(Actuaciones actuaciones) {
		try(Connection con = getConexion()){
			try(PreparedStatement ps = con.prepareStatement(sqlInsert)){
				ps.setInt(1, actuaciones.getIdServicio());
				ps.setInt(2, actuaciones.getIdTrabajador());
				ps.setInt(3, actuaciones.getIdValoracion());
				ps.setDate(4, actuaciones.getFecha());
				
				int numeroRegistrosModificados = ps.executeUpdate();
				
				if(numeroRegistrosModificados != 1) {
					throw new RuntimeException("Resultado no esperado en la INSERT: " +
							numeroRegistrosModificados);
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException("Error al obtener todos los registros", e);
		}
		
	}
	
	public void eliminar(int id) {
		
		try(Connection con = getConexion()){
			try(PreparedStatement ps = con.prepareStatement(sqlDelete)){
				ps.setInt(1, id);
				int numeroRegistrosModificados = ps.executeUpdate();
				if(numeroRegistrosModificados != 1) {
					throw new RuntimeException("Resultado no esperado en la DELETE: " +
							numeroRegistrosModificados);
				}
			}
			
		}catch(SQLException e) {
			throw new RuntimeException("Error al obtener todos los registros", e);
		}
		
	}
	
	public void actualizar(Actuaciones actuaciones) {
		
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
				ps.setInt(1, actuaciones.getIdServicio());
				ps.setInt(2, actuaciones.getIdTrabajador());
				ps.setInt(3, actuaciones.getIdValoracion());
				ps.setDate(4, actuaciones.getFecha());

				int numeroRegistrosModificados = ps.executeUpdate();

				if(numeroRegistrosModificados != 1) {
					throw new RuntimeException("Resultado no esperado en la UPDATE: " +
							numeroRegistrosModificados);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al modificar la persona", e);
		}

	}
}
