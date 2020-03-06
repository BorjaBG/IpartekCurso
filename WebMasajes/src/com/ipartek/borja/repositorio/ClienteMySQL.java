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

import com.ipartek.borja.modelos.Cliente;

public class ClienteMySQL implements Dao<Cliente>{
	
	//Cambiar llamadas
	private String sqlSelect = "SELECT * FROM clientegetall";
	private String sqlSelectId = "CALL clienteGetById(?)";
	private String sqlInsert = "CALL clienteAñadir(?,?,?,?,?)";
	private String sqlDelete = "CALL clienteDelete(?)";
	private String sqlUpdate = "CALL clienteUpdate(?,?,?,?,?)";
	
	private static DataSource pool;
	
	private String url;
	private String usuario;
	private String contraseña;
	
	
	// SINGLETON
	private ClienteMySQL(String url, String usuario, String contraseña) {
		this.url = url;
		this.usuario = usuario;
		this.contraseña = contraseña;
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("No se ha encontrado el driver de MySQL");
		}
				
	}
			
		private static ClienteMySQL INSTANCIA = null;
			
	/*public static ClienteMySQL getInstancia(String pathConfiguracion) {
		try{
			if(INSTANCIA == null) {
				Properties configuracion = new Properties();
				configuracion.load(new FileInputStream(pathConfiguracion));
				INSTANCIA = new ClienteMySQL(configuracion.getProperty("mysql.url"),
							configuracion.getProperty("mysql.usuario"), configuracion.getProperty("mysql.contraseña"));
			}
			return INSTANCIA;
		}catch(FileNotFoundException e) {
			throw new RuntimeException("No se ha podido encontrar el archivo", e);
		}catch (IOException e) {
			throw new RuntimeException("Fallo de lectura/escritura del archivo", e);
		}
				
	}*/
		
	
	public static ClienteMySQL getInstancia(String entorno) {
		InitialContext initCtx;
		try {
			initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource dataSource = (DataSource) envCtx.lookup(entorno);

			ClienteMySQL.pool = dataSource;

			if (INSTANCIA == null) {
				INSTANCIA = new ClienteMySQL(null, null, null);
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
	
	public Iterable<Cliente> obtenerTodos() {
		
		try(Connection con = getConexion()){
			try (CallableStatement cs = con.prepareCall(sqlSelect)) {
				try (ResultSet rs = cs.executeQuery()) {
					ArrayList<Cliente> clientes = new ArrayList<>();
					while (rs.next()) {
						clientes.add(new Cliente(rs.getInt("idCliente"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("dni"), rs.getInt("telefono")));
					}
					return clientes;
				}
			}
			
		}catch(SQLException e) {
			throw new RuntimeException("Error al obtener todos los registros", e);
		}
	}
	
	
	public Cliente obtenerPorId(int id) {
		try (Connection con = getConexion()) {
			try(CallableStatement cs = con.prepareCall(sqlSelectId)) {
				cs.setInt(1, id);
				try(ResultSet rs = cs.executeQuery()){

					if(rs.next()) {
						return new Cliente(rs.getInt("idCliente"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("dni"), rs.getInt("telefono"));
					} else {
						return null;
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la persona id: " + id, e);
		}
	}
	

	public Integer agregar(Cliente cliente) {
		try(Connection con = getConexion()){
			try(CallableStatement cs = con.prepareCall(sqlInsert)){
				cs.setString(1, cliente.getNombre());
				cs.setString(2, cliente.getApellidos());
				cs.setString(3, cliente.getDni());
				cs.setInt(4, cliente.getTelefono());
				cs.registerOutParameter(5, java.sql.Types.INTEGER);
				
				int numeroRegistrosModificados = cs.executeUpdate();
				
				if(numeroRegistrosModificados != 1) {
					throw new RuntimeException("Resultado no esperado en la INSERT: " +
							numeroRegistrosModificados);
				}
				return cs.getInt(5);
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
	
	public void actualizar(Cliente cliente) {
		
		try (Connection con = getConexion()) {
			try(CallableStatement cs = con.prepareCall(sqlUpdate)) {
				cs.setString(1, cliente.getNombre());
				cs.setString(2, cliente.getApellidos());
				cs.setString(3, cliente.getDni());
				cs.setInt(4, cliente.getTelefono());
				cs.setInt(5, cliente.getId());
				
				System.out.println("Estoy en el update de Clientes" + cliente.getNombre().toString());

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
