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

public class ClienteMySQL implements Dao<Cliente>{
	private String sqlSelect = "SELECT * FROM cliente";
	private String sqlSelectId = "SELECT * FROM cliente WHERE idCliente=?";
	private String sqlInsert = "INSERT INTO cliente (nombre, apellidos, dni, telefono, idServicio, idTrabajador) VALUES (?,?,?,?,?,?)";
	private String sqlDelete = "DELETE FROM cliente WHERE idValoracion=?";
	private String sqlUpdate = "UPDATE cliente SET nombre=?, apellidos=?, dni=?, telefono=?, idServicio=?, idTrabajador=? WHERE idCliente=?";
	
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
			
	public static ClienteMySQL getInstancia(String pathConfiguracion) {
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
	
	public Iterable<Cliente> obtenerTodos() {
		
		try(Connection con = getConexion()){
			try (PreparedStatement ps = con.prepareStatement(sqlSelect)) {
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<Cliente> clientes = new ArrayList<>();
					while (rs.next()) {
						clientes.add(new Cliente(rs.getInt("idCliente"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("dni"), rs.getInt("telefono"), rs.getInt("idServicio"), rs.getInt("idTrabajador")));
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
			try(PreparedStatement ps = con.prepareStatement(sqlSelectId)) {
				ps.setInt(1, id);
				try(ResultSet rs = ps.executeQuery()){

					if(rs.next()) {
						return new Cliente(rs.getInt("idCliente"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("dni"), rs.getInt("telefono"), rs.getInt("idServicio"), rs.getInt("idTrabajador"));
					} else {
						return null;
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la persona id: " + id, e);
		}
	}
	

	public void agregar(Cliente cliente) {
		try(Connection con = getConexion()){
			try(PreparedStatement ps = con.prepareStatement(sqlInsert)){
				ps.setString(1, cliente.getNombre());
				ps.setString(2, cliente.getApellidos());
				ps.setString(3, cliente.getDni());
				ps.setInt(4, cliente.getTelefono());
				ps.setInt(5, cliente.getIdServicio());
				ps.setInt(6, cliente.getIdTrabajador());
				
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
	
	public void actualizar(Cliente cliente) {
		
		try (Connection con = getConexion()) {
			try(PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
				ps.setString(1, cliente.getNombre());
				ps.setString(2, cliente.getApellidos());
				ps.setString(3, cliente.getDni());
				ps.setInt(4, cliente.getTelefono());
				ps.setInt(5, cliente.getIdServicio());
				ps.setInt(6, cliente.getIdTrabajador());
				ps.setInt(7, cliente.getId());

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
