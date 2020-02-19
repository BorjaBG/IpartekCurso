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
import com.ipartek.borja.modelos.Trabajador;

public class TrabajadorMySQL implements Dao<Trabajador>{
	
	//Cambiar llamadas
	private String sqlSelect = "SELECT * FROM trabajador";
	private String sqlSelectId = "SELECT * FROM trabajador WHERE idTrabajador=?";
	private String sqlInsert = "INSERT INTO trabajador (nombre, apellidos, dni) VALUES (?,?,?)";
	private String sqlDelete = "DELETE FROM trabajador WHERE idTrabajador=?";
	private String sqlUpdate = "UPDATE trabajador SET nombre=?, apellidos=?, dni=? WHERE idTrabajador=?";
	
	private String url;
	private String usuario;
	private String contraseña;
	
	
	// SINGLETON
	private TrabajadorMySQL(String url, String usuario, String contraseña) {
		this.url = url;
		this.usuario = usuario;
		this.contraseña = contraseña;
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("No se ha encontrado el driver de MySQL");
		}
				
	}
			
		private static TrabajadorMySQL INSTANCIA = null;
			
	public static TrabajadorMySQL getInstancia(String pathConfiguracion) {
		try{
			if(INSTANCIA == null) {
				Properties configuracion = new Properties();
				configuracion.load(new FileInputStream(pathConfiguracion));
				INSTANCIA = new TrabajadorMySQL(configuracion.getProperty("mysql.url"),
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
	
	public Iterable<Trabajador> obtenerTodos() {
		
		try(Connection con = getConexion()){
			try (PreparedStatement ps = con.prepareStatement(sqlSelect)) {
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<Trabajador> trabajadores = new ArrayList<>();
					while (rs.next()) {
						trabajadores.add(new Trabajador(rs.getInt("idTrabajador"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("dni")));
					}
					return trabajadores;
				}
			}
			
		}catch(SQLException e) {
			throw new RuntimeException("Error al obtener todos los registros", e);
		}
	}
	
	
	public Trabajador obtenerPorId(int id) {
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(sqlSelectId)) {
				ps.setLong(1, id);
				try(ResultSet rs = ps.executeQuery()){

					if(rs.next()) {
						return new Trabajador(rs.getInt("idTrabajador"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("dni"));
					} else {
						return null;
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la persona id: " + id, e);
		}
	}
	

	public Integer agregar(Trabajador trabajador) {
		try(Connection con = getConexion()){
			try(PreparedStatement ps = con.prepareStatement(sqlInsert)){
				ps.setString(1, trabajador.getNombre());
				ps.setString(2, trabajador.getApellidos());
				ps.setString(3, trabajador.getDni());
				
				int numeroRegistrosModificados = ps.executeUpdate();
				
				if(numeroRegistrosModificados != 1) {
					throw new RuntimeException("Resultado no esperado en la INSERT: " +
							numeroRegistrosModificados);
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException("Error al obtener todos los registros", e);
		}
		return null;
		
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
	
	public void actualizar(Trabajador trabajador) {
		
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
				ps.setString(1, trabajador.getNombre());
				ps.setString(2, trabajador.getApellidos());
				ps.setString(3, trabajador.getDni());
				ps.setInt(4, trabajador.getId());

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
