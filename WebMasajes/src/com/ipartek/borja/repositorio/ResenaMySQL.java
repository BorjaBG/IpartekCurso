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
import com.ipartek.borja.modelos.Resena;

public class ResenaMySQL implements Dao<Resena>{
	
	//Cambiar llamadas
	private String sqlSelect = "SELECT * FROM valoracion";
	private String sqlSelectId = "SELECT * FROM valoracion WHERE idValoracion=?";
	private String sqlInsert = "INSERT INTO valoracion (valoracion, reseña) VALUES (?,?)";
	private String sqlDelete = "DELETE FROM valoracion WHERE idValoracion=?";
	private String sqlUpdate = "UPDATE valoracion SET valoracion=?, reseña=? WHERE idValoracion=?";
	
	private String url;
	private String usuario;
	private String contraseña;
	
	
	// SINGLETON
	private ResenaMySQL(String url, String usuario, String contraseña) {
		this.url = url;
		this.usuario = usuario;
		this.contraseña = contraseña;
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("No se ha encontrado el driver de MySQL");
		}
				
	}
			
		private static ResenaMySQL INSTANCIA = null;
			
	public static ResenaMySQL getInstancia(String pathConfiguracion) {
		try{
			if(INSTANCIA == null) {
				Properties configuracion = new Properties();
				configuracion.load(new FileInputStream(pathConfiguracion));
				INSTANCIA = new ResenaMySQL(configuracion.getProperty("mysql.url"),
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
	
	public Iterable<Resena> obtenerTodos() {
		
		try(Connection con = getConexion()){
			try (PreparedStatement ps = con.prepareStatement(sqlSelect)) {
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<Resena> resenas = new ArrayList<>();
					while (rs.next()) {
						resenas.add(new Resena(rs.getInt("idValoracion"), rs.getString("valoracion"), rs.getString("reseña")));
					}
					return resenas;
				}
			}
			
		}catch(SQLException e) {
			throw new RuntimeException("Error al obtener todos los registros", e);
		}
	}
	
	
	public Resena obtenerPorId(int id) {
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(sqlSelectId)) {
				ps.setLong(1, id);
				try(ResultSet rs = ps.executeQuery()){

					if(rs.next()) {
						return new Resena(rs.getInt("idValoracion"), rs.getString("valoracion"), rs.getString("reseña"));
					} else {
						return null;
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la persona id: " + id, e);
		}
	}
	

	public void agregar(Resena resena) {
		try(Connection con = getConexion()){
			try(PreparedStatement ps = con.prepareStatement(sqlInsert)){
				ps.setString(1, resena.getValoracion());
				ps.setString(2, resena.getReseña());
				
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
	
	public void actualizar(Resena resena) {
		
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
				ps.setString(1, resena.getValoracion());
				ps.setString(2, resena.getReseña());
				ps.setInt(3, resena.getId());

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
