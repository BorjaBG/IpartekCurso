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
import com.ipartek.borja.modelos.Servicio;

public class ServicioMySQL implements Dao<Servicio> {
	
	//Cambiar llamadas
	private String sqlSelect = "SELECT * FROM servicio";
	private String sqlSelectId = "SELECT * FROM servicio WHERE idServicio=?";
	private String sqlInsert = "INSERT INTO servicio (nombre, precio) VALUES (?,?)";
	private String sqlDelete = "DELETE FROM servicio WHERE idServicio=?";
	private String sqlUpdate = "UPDATE servicio SET nombre=?, precio=? WHERE idServicio=?";
	
	private String url;
	private String usuario;
	private String contraseña;
	
	
	// SINGLETON
	private ServicioMySQL(String url, String usuario, String contraseña) {
		this.url = url;
		this.usuario = usuario;
		this.contraseña = contraseña;
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("No se ha encontrado el driver de MySQL");
		}
				
	}
			
		private static ServicioMySQL INSTANCIA = null;
			
	public static ServicioMySQL getInstancia(String pathConfiguracion) {
		try{
			if(INSTANCIA == null) {
				Properties configuracion = new Properties();
				configuracion.load(new FileInputStream(pathConfiguracion));
				INSTANCIA = new ServicioMySQL(configuracion.getProperty("mysql.url"),
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
	
	public Iterable<Servicio> obtenerTodos() {
		
		try(Connection con = getConexion()){
			try (PreparedStatement ps = con.prepareStatement(sqlSelect)) {
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<Servicio> servicios = new ArrayList<>();
					while (rs.next()) {
						servicios.add(new Servicio(rs.getInt("idServicio"), rs.getString("nombre"), rs.getDouble("precio")));
					}
					return servicios;
				}
			}
			
		}catch(SQLException e) {
			throw new RuntimeException("Error al obtener todos los registros", e);
		}
	}
	
	
	public Servicio obtenerPorId(int id) {
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(sqlSelectId)) {
				ps.setInt(1, id);

				try(ResultSet rs = ps.executeQuery()){

					if(rs.next()) {
						return new Servicio(rs.getInt("idServicio"), rs.getString("nombre"), rs.getDouble("precio"));
					} else {
						return null;
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la persona id: " + id, e);
		}
	}
	

	public void agregar(Servicio servicio) {
		try(Connection con = getConexion()){
			try(PreparedStatement ps = con.prepareStatement(sqlInsert)){
				ps.setString(1, servicio.getNombre());
				ps.setDouble(2, servicio.getPrecio());
				
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
	
	public void actualizar(Servicio servicio) {
		
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
				ps.setString(1, servicio.getNombre());
				ps.setDouble(2, servicio.getPrecio());
				ps.setInt(3, servicio.getId());

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
