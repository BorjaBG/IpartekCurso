package com.ipartek.formacion.personas.repositirio;

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
import com.ipartek.formacion.personas.modelos.Persona;

public class PersonaMySQL implements Dao<Persona> {
	
	private String sqlSelect = "SELECT * FROM personas";
	private String sqlSelectId = "SELECT * FROM personas WHERE id=?";
	private String sqlInsert = "INSERT INTO personas (nombre, apellidos) VALUES (?,?)";
	private String sqlDelete = "DELETE FROM personas WHERE id = ?";
	private String sqlUpdate = "UPDATE personas SET nombre=?, apellidos=? WHERE id=?";
	/*private String sqlUpdateNombre = "UPDATE personas SET nombre = ? WHERE (id = ?)";
	private String sqlUpdateApellidos = "UPDATE personas SET apellidos = ? WHERE (id = ?)";
	private String sqlUpdateAmbos = "UPDATE personas SET nombre = ?, apellidos = ? WHERE (id = ?)";*/

	
	private String url;
	private String usuario;
	private String contraseña;
	
	// SINGLETON
		private PersonaMySQL(String url, String usuario, String contraseña) {
			this.url = url;
			this.usuario = usuario;
			this.contraseña = contraseña;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("No se ha encontrado el driver de MySQL");
			}
			
		}
		
		private static PersonaMySQL instancia = null;
		
		public static PersonaMySQL getInstancia(String pathConfiguracion) {
			try{
				if(instancia == null) {
					Properties configuracion = new Properties();
					configuracion.load(new FileInputStream(pathConfiguracion));
					instancia = new PersonaMySQL(configuracion.getProperty("mysql.url"),
								configuracion.getProperty("mysql.usuario"), configuracion.getProperty("mysql.contraseña"));
				}
				return instancia;
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

	@Override
	public Iterable<Persona> obtenerTodos() {
		
		try(Connection con = getConexion()){
			try (PreparedStatement ps = con.prepareStatement(sqlSelect)) {
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<Persona> personas = new ArrayList<>();
					while (rs.next()) {
						personas.add(new Persona(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos")));
					}
					return personas;
				}
			}
			
		}catch(SQLException e) {
			throw new RuntimeException("Error al obtener todos los registros", e);
		}
	}

	@Override
	public Persona obtenerPorId(Long id) {
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(sqlSelectId)) {
				ps.setLong(1, id);

				try(ResultSet rs = ps.executeQuery()){

					if(rs.next()) {
						return new Persona(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"));
					} else {
						return null;
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la persona id: " + id, e);
		}
		
		
	}

	@Override
	public void agregar(Persona persona) {
		try(Connection con = getConexion()){
			try(PreparedStatement ps = con.prepareStatement(sqlInsert)){
				ps.setString(1, persona.getNombre());
				ps.setString(2, persona.getApellidos());
				
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

	@Override
	public void eliminar(Long id) {
		
		try(Connection con = getConexion()){
			try(PreparedStatement ps = con.prepareStatement(sqlDelete)){
				ps.setLong(1, id);	
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

	@Override
	public void actualizar(Persona persona) {
		
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
				ps.setString(1, persona.getNombre());
				ps.setString(2, persona.getApellidos());
				ps.setLong(3, persona.getId());

				int numeroRegistrosModificados = ps.executeUpdate();

				if(numeroRegistrosModificados != 1) {
					throw new RuntimeException("Resultado no esperado en la UPDATE: " +
							numeroRegistrosModificados);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al modificar la persona", e);
		}
		//
	}

}
