package com.ipartek.borja.repositorio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.ipartek.borja.modelos.Resena;

public class ResenaMySQL implements Dao<Resena>{
	
	//Cambiar llamadas
	private String sqlSelect = "SELECT * FROM valoraciongetall";
	private String sqlSelectId = "CALL valoracionGetById(?)";
	private String sqlInsert = "CALL valoracionAñadir(?,?,?)";
	private String sqlDelete = "CALL valoracionDelete(?)";
	private String sqlUpdate = "CALL valoracionUpdate(?,?,?)";
	
	private static DataSource pool;
	
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
			
	/*public static ResenaMySQL getInstancia(String pathConfiguracion) {
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
				
	}*/
		
	public static ResenaMySQL getInstancia(String entorno) {
		InitialContext initCtx;
		try {
			initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource dataSource = (DataSource) envCtx.lookup(entorno);

			ResenaMySQL.pool = dataSource;

			if(INSTANCIA == null) {
				INSTANCIA = new ResenaMySQL(null, null, null);
			}

			return INSTANCIA;
		} catch (NamingException e) {
			throw new RuntimeException("No se ha podido conectar al Pool de conexiones " + entorno);
		}
	}
			
	//FIN SINGLETON
	
	private Connection getConexion(){
		//System.out.println(url + "\n" + usuario + "\n" + contraseña + "\n");
		try {
			if (pool == null) {
				new com.mysql.cj.jdbc.Driver();
				return DriverManager.getConnection(url, usuario, contraseña);
			} else {
				return pool.getConnection();
			}
		}catch(SQLException e) {
			System.err.println("IPARTEK: Error de conexión a la base de datos: " + url + ":" + usuario + ":" + contraseña);
			throw new RuntimeException("No se ha podido conectar a la base de datos", e);
		}
	}
	
	public Iterable<Resena> obtenerTodos() {
		
		try(Connection con = getConexion()){
			try (CallableStatement cs = con.prepareCall(sqlSelect)) {
				try (ResultSet rs = cs.executeQuery()) {
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
			try(CallableStatement cs = con.prepareCall(sqlSelectId)) {
				cs.setLong(1, id);
				try(ResultSet rs = cs.executeQuery()){

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
	

	public Integer agregar(Resena resena) {
		try(Connection con = getConexion()){
			try(CallableStatement cs = con.prepareCall(sqlInsert)){
				cs.setString(1, resena.getValoracion());
				cs.setString(2, resena.getReseña());
				cs.registerOutParameter(3, java.sql.Types.INTEGER);
				
				int numeroRegistrosModificados = cs.executeUpdate();
				
				if(numeroRegistrosModificados != 1) {
					throw new RuntimeException("Resultado no esperado en la INSERT: " +
							numeroRegistrosModificados);
				}
				return cs.getInt(3);
			}
		}catch(SQLException e) {
			throw new RuntimeException("Error al obtener todos los registros", e);
		}
		
	}
	
	public void eliminar(int id) {
		
		try(Connection con = getConexion()){
			try(CallableStatement cs = con.prepareCall(sqlDelete)){
				cs.setInt(1, id);	
				int numeroRegistrosModificados = cs.executeUpdate();
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
			try(CallableStatement cs = con.prepareCall(sqlUpdate)) {
				cs.setString(1, resena.getValoracion());
				cs.setString(2, resena.getReseña());
				cs.setInt(3, resena.getId());

				int numeroRegistrosModificados = cs.executeUpdate();

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
