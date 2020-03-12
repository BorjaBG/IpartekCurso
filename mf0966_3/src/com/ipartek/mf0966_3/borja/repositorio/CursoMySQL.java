package com.ipartek.mf0966_3.borja.repositorio;

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

import com.ipartek.mf0966_3.borja.modelos.Curso;

public class CursoMySQL implements Dao<Curso>{
	
	private String sqlSelect = "CALL cursogetAll()";
	private String sqlSelectIdentificador = "CALL clientegetByIdentificador(?)";
	
	private static DataSource pool;
	
	private String url;
	private String usuario;
	private String contraseña;
	
	
	// SINGLETON
	private CursoMySQL(String url, String usuario, String contraseña) {
		this.url = url;
		this.usuario = usuario;
		this.contraseña = contraseña;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("No se ha encontrado el driver de MySQL");
		}

	}

	private static CursoMySQL INSTANCIA = null;		

	public static CursoMySQL getInstancia(String entorno) {
		InitialContext initCtx;
		try {
			initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource dataSource = (DataSource) envCtx.lookup(entorno);

			CursoMySQL.pool = dataSource;

			if (INSTANCIA == null) {
				INSTANCIA = new CursoMySQL(null, null, null);
			}

			return INSTANCIA;
		} catch (NamingException e) {
			throw new RuntimeException("No se ha podido conectar al Pool de conexiones " + entorno);
		}
	}

	//FIN SINGLETON

	private Connection getConexion(){
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
		

	@Override
	public Iterable<Curso> obtenerTodos() {
		try(Connection con = getConexion()){
			try (CallableStatement cs = con.prepareCall(sqlSelect)) {
				try (ResultSet rs = cs.executeQuery()) {
					ArrayList<Curso> cursos = new ArrayList<>();
					while (rs.next()) {
						cursos.add(new Curso(rs.getString("identificador"), rs.getString("nombre"), rs.getInt("nHoras")));
					}
					return cursos;
				}
			}
			
		}catch(SQLException e) {
			throw new RuntimeException("Error al obtener todos los registros", e);
		}
	}
	
	@Override
	public Curso obtenerPorIdentificador(String identificador) {
		try (Connection con = getConexion()) {
			try(CallableStatement cs = con.prepareCall(sqlSelectIdentificador)) {
				cs.setString(1, identificador);
				try(ResultSet rs = cs.executeQuery()){

					if(rs.next()) {
						return new Curso(rs.getString("identificador"), rs.getString("nombre"), rs.getInt("nHoras"));
					} else {
						return null;
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la persona id: " + identificador, e);
		}
	}

	@Override
	public Curso obtenerPorId(int id) {
		throw new RuntimeException("Metodo no implementado");
		//return null;
	}

	@Override
	public Integer agregar(Curso objeto) {
		throw new RuntimeException("Metodo no implementado");
		//return null;
	}

	@Override
	public void eliminar(int id) {
		throw new RuntimeException("Metodo no implementado");
	}

	@Override
	public void actualizar(Curso objeto) {
		throw new RuntimeException("Metodo no implementado");
	}
	
}
