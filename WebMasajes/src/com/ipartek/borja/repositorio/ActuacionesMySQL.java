package com.ipartek.borja.repositorio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ipartek.borja.modelos.Actuaciones;
import com.ipartek.borja.modelos.Cliente;
import com.ipartek.borja.modelos.Resena;
import com.ipartek.borja.modelos.Servicio;
import com.ipartek.borja.modelos.Trabajador;

public class ActuacionesMySQL implements Dao<Actuaciones>{
	
	//Cambiar llamadas
	private String sqlSelect = "SELECT * FROM actuacionesgetall";
	private String sqlSelectId = "CALL actuacionesGetById(?)";
	private String sqlInsert = "CALL actuacionesAñadir(?,?,?,?,?,?,?,?)";
	private String sqlDelete = "CALL actuacionesDelete(?)";
	private String sqlUpdate = "CALL actuacionesUpdate(?,?,?,?,?,?,?,?)";
	
	private static DataSource pool;
	
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
			
	/*public static ActuacionesMySQL getInstancia(String pathConfiguracion) {
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
				
	}*/
	
	public static ActuacionesMySQL getInstancia(String entorno) {
		InitialContext initCtx;
		try {
			initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource dataSource = (DataSource) envCtx.lookup(entorno);

			ActuacionesMySQL.pool = dataSource;

			if (INSTANCIA == null) {
				INSTANCIA = new ActuacionesMySQL(null, null, null);
			}

			return INSTANCIA;
		} catch (NamingException e) {
			throw new RuntimeException("No se ha podido conectar al Pool de conexiones " + entorno);
		}
	}
			
	//FIN SINGLETON
	
	private Connection getConexion(){
		/*try {
			return DriverManager.getConnection(url, usuario, contraseña);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Error al conectar con la base de datos", e);
		}*/
		
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
	
	public Iterable<Actuaciones> obtenerTodos() {
		
		try(Connection con = getConexion()){
			try (CallableStatement cs = con.prepareCall(sqlSelect)) {
				try (ResultSet rs = cs.executeQuery()) {
					ArrayList<Actuaciones> actuaciones = new ArrayList<>();
					while (rs.next()) {
						actuaciones.add(new Actuaciones(rs.getInt("idActuaciones"),
								new Servicio(rs.getString("nombreS")),
								new Trabajador(rs.getString("nombreT"), rs.getString("apellidosT")),
								new Resena(rs.getString("valoracion")),
								new Cliente(rs.getString("nombreC"), rs.getString("apellidosC")),
								rs.getDate("fecha")));
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
			try(CallableStatement cs = con.prepareCall(sqlSelectId)) {
				cs.setInt(1, id);
				try(ResultSet rs = cs.executeQuery()){

					if(rs.next()) {
						return new Actuaciones(rs.getInt("idActuaciones"),
								new Servicio(rs.getString("nombreS")),
								new Trabajador(rs.getString("nombreT"), rs.getString("apellidosT")),
								new Resena(rs.getString("valoracion")),
								new Cliente(rs.getString("nombreC"), rs.getString("apellidosC")),
								rs.getDate("fecha"));
					} else {
						return null;
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la persona id: " + id, e);
		}
	}
	

	public Integer agregar(Actuaciones actuaciones) {
		try(Connection con = getConexion()){
			try(CallableStatement cs = con.prepareCall(sqlInsert)){
				cs.setInt(1, actuaciones.getIdActuaciones());
				cs.setInt(2, actuaciones.getIdServicio());
				cs.setInt(3, actuaciones.getIdTrabajador());
				cs.setInt(4, actuaciones.getIdValoracion());
				//cs.setDate(5, actuaciones.getFecha());
				cs.registerOutParameter(6, java.sql.Types.INTEGER);
				//8
				
				int numeroRegistrosModificados = cs.executeUpdate();
				
				if(numeroRegistrosModificados != 1) {
					throw new RuntimeException("Resultado no esperado en la INSERT: " +
							numeroRegistrosModificados);
				}
				return cs.getInt(6);
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
	
	public void actualizar(Actuaciones actuaciones) {
		
		try (Connection con = getConexion()) {
			try(CallableStatement cs = con.prepareCall(sqlUpdate)) {
				cs.setInt(1, actuaciones.getIdActuaciones());
				cs.setString(2, actuaciones.getServicio().getNombre());
				cs.setString(3, actuaciones.getCliente().getNombre());
				cs.setString(4, actuaciones.getCliente().getApellidos());
				cs.setString(5, actuaciones.getTrabajador().getNombre());
				cs.setString(6, actuaciones.getTrabajador().getApellidos());
				cs.setString(7, actuaciones.getResenas().getValoracion());
				cs.setTimestamp(8, new Timestamp(actuaciones.getFecha().getTime()));

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
