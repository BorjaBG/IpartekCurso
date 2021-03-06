package com.ipartek.formacion.ejemploaccesodatos.accesodatos;

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
import com.ipartek.formacion.ejemploaccesodatos.entidades.Persona;

public class PersonaMySQL implements Crudable<Persona>{
	private String sqlSelect = "SELECT * FROM personas";
	private String sqlInsert = "INSERT INTO personas (nombre, apellidos) VALUES (?,?)";
	private String sqlDelete = "DELETE FROM personas WHERE id = ?";
	private String sqlUpdateNombre = "UPDATE personas SET nombre = ? WHERE (id = ?)";
	private String sqlUpdateApellidos = "UPDATE personas SET apellidos = ? WHERE (id = ?)";
	private String sqlUpdateAmbos = "UPDATE personas SET nombre = ?, apellidos = ? WHERE (id = ?)";

	
	private String url;
	private String usuario;
	private String contraseña;
	
	// SINGLETON
	private PersonaMySQL(String url, String usuario, String contraseña) {
		this.url = url;
		this.usuario = usuario;
		this.contraseña = contraseña;
	}
	
	private static PersonaMySQL INSTANCIA = null;
	
	public static PersonaMySQL getInstancia() {
		try{
			if(INSTANCIA == null) {
				Properties configuracion = new Properties();
				configuracion.load(new FileInputStream("configuracion.properties"));
				INSTANCIA = new PersonaMySQL(configuracion.getProperty("mysql.url"),
							configuracion.getProperty("mysql.usuario"), configuracion.getProperty("mysql.contraseña"));
			}
			
			return INSTANCIA;
		}catch(FileNotFoundException e) {
			throw new AccesoDatosException("Fichero de configuración no encontrado", e);
		}catch (IOException e) {
			throw new AccesoDatosException("Fallo de lectura/escritura al fichero", e);
		}
		
	}
	
	//FIN SINGLETON
	
	private Connection getConexion() throws SQLException{
		//System.out.println(url + "\n" + usuario + "\n" + contraseña + "\n");
		return DriverManager.getConnection(url, usuario, contraseña);
	}
	
	@Override
	public Iterable<Persona> getAll() {
		ArrayList<Persona> personas = new ArrayList<>();
		
		try(Connection con = getConexion()){
			try (PreparedStatement ps = con.prepareStatement(sqlSelect)) {
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						personas.add(new Persona(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos")));
					}
				}
			}
			return personas;
		}catch(SQLException e) {
			throw new AccesoDatosException("Error al obtener todos los registros", e);
		}
	}

	@Override
	public Persona getById(Long id) {
		throw new UnsupportedOperationException("NO ESTÁ IMPLEMENTADO");
	}

	@Override
	public Persona insert(Persona persona) {
		try(Connection con = getConexion()){
			try(PreparedStatement ps = con.prepareStatement(sqlInsert)){
				ps.setString(1, persona.getNombre());
				ps.setString(2, persona.getApellidos());
				
				int numeroRegistrosModificados = ps.executeUpdate();
				if(numeroRegistrosModificados != 1) {
					throw new AccesoDatosException("Resultado no esperado en la INSERT: " +
							numeroRegistrosModificados);
				}
				
				persona.setId(null);
			}
			
			return persona;
		}catch(SQLException e) {
			throw new AccesoDatosException("Error al obtener todos los registros", e);
		}
	}

	@Override
	public Persona update(Persona persona, String nombre, String apellidos, String operacion, Long id) {
		try(Connection con = getConexion()){
			switch(operacion) {
			case "nombre":
				try(PreparedStatement ps = con.prepareStatement(sqlUpdateNombre)){
					ps.setString(1, nombre);
					ps.setLong(2, id);
					
					int numeroRegistrosModificados = ps.executeUpdate();
					if(numeroRegistrosModificados != 1) {
						throw new AccesoDatosException("Resultado no esperado en la UPDATE: " +
								numeroRegistrosModificados);
					}
				}
				break;
			case "apellidos":
				try(PreparedStatement ps = con.prepareStatement(sqlUpdateApellidos)){
					ps.setString(1, apellidos);
					ps.setLong(2, id);
					
					int numeroRegistrosModificados = ps.executeUpdate();
					if(numeroRegistrosModificados != 1) {
						throw new AccesoDatosException("Resultado no esperado en la UPDATE: " +
								numeroRegistrosModificados);
					}
				}
				break;
			case "ambos":
				try(PreparedStatement ps = con.prepareStatement(sqlUpdateAmbos)){
					ps.setString(1, nombre);
					ps.setString(2, apellidos);
					ps.setLong(3, id);
					
					int numeroRegistrosModificados = ps.executeUpdate();
					if(numeroRegistrosModificados != 1) {
						throw new AccesoDatosException("Resultado no esperado en la UPDATE: " +
								numeroRegistrosModificados);
					}
				}
				break;
			default:
				throw new RuntimeException("No hemos podido identificar esa operacion");
			}
			return persona;
		}catch(SQLException e) {
			throw new AccesoDatosException("Error al obtener todos los registros", e);
		}
		
		//throw new UnsupportedOperationException("NO ESTÁ IMPLEMENTADO");
	}

	@Override
	public Persona delete(Persona persona) {
		throw new UnsupportedOperationException("NO ESTÁ IMPLEMENTADO");
	}

	@Override
	public Persona delete(Long id) {
		
		try(Connection con = getConexion()){
			try(PreparedStatement ps = con.prepareStatement(sqlDelete)){
				ps.setLong(1, id);	
				int numeroRegistrosModificados = ps.executeUpdate();
				if(numeroRegistrosModificados != 1) {
					throw new AccesoDatosException("Resultado no esperado en la DELETE: " +
							numeroRegistrosModificados);
				}
			}
			
			return null;
		}catch(SQLException e) {
			throw new AccesoDatosException("Error al obtener todos los registros", e);
		}
		
		//throw new UnsupportedOperationException("NO ESTÁ IMPLEMENTADO");
	}

}
