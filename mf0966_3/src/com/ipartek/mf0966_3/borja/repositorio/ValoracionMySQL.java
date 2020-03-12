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
import com.ipartek.mf0966_3.borja.modelos.Valoracion;

public class ValoracionMySQL implements Dao<Valoracion>{
	
	private String sqlSelect = "CALL valoraciongetAll()";
	private String sqlInsert = "CALL valoracionCreate(?,?,?,?)";
	private String sqlUpdate = "CALL valoracionUpdate(?,?,?,?)";
	private String sqlDelete = "CALL valoracionDelete(?)";
	
	private static DataSource pool;
	
	private String url;
	private String usuario;
	private String contraseña;
	
	// SINGLETON
		private ValoracionMySQL(String url, String usuario, String contraseña) {
			this.url = url;
			this.usuario = usuario;
			this.contraseña = contraseña;

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("No se ha encontrado el driver de MySQL");
			}

		}

		private static ValoracionMySQL INSTANCIA = null;		

		public static ValoracionMySQL getInstancia(String entorno) {
			InitialContext initCtx;
			try {
				initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				DataSource dataSource = (DataSource) envCtx.lookup(entorno);

				ValoracionMySQL.pool = dataSource;

				if (INSTANCIA == null) {
					INSTANCIA = new ValoracionMySQL(null, null, null);
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
	public Iterable<Valoracion> obtenerTodos() {
		try(Connection con = getConexion()){
			try (CallableStatement cs = con.prepareCall(sqlSelect)) {
				try (ResultSet rs = cs.executeQuery()) {
					ArrayList<Valoracion> valoraciones = new ArrayList<>();
					while (rs.next()) {
						valoraciones.add(new Valoracion(rs.getInt("idValoracion"), rs.getInt("alumno_codigo"), rs.getInt("curso_codigo"), rs.getString("opinion")));
					}
					return valoraciones;
				}
			}
			
		}catch(SQLException e) {
			throw new RuntimeException("Error al obtener todos los registros", e);
		}
	}

	@Override
	public Valoracion obtenerPorId(int id) {
		 return null;
	}

	@Override
	public Valoracion obtenerPorIdentificador(String identificador) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer agregar(Valoracion valoracion) {
		try(Connection con = getConexion()){
			try(CallableStatement cs = con.prepareCall(sqlInsert)){
				cs.setInt(1, valoracion.getAlumno_codigo());
				cs.setInt(2, valoracion.getCurso_codigo());
				cs.setString(3, valoracion.getOpinion());
				cs.registerOutParameter(4, java.sql.Types.INTEGER);
				
				int numeroRegistrosModificados = cs.executeUpdate();
				
				if(numeroRegistrosModificados != 1) {
					throw new RuntimeException("Resultado no esperado en la INSERT: " +
							numeroRegistrosModificados);
				}
				return cs.getInt(4);
			}
		}catch(SQLException e) {
			throw new RuntimeException("Error al obtener todos los registros", e);
		}
	}

	@Override
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

	@Override
	public void actualizar(Valoracion valoracion) {
		try (Connection con = getConexion()) {
			try(CallableStatement cs = con.prepareCall(sqlUpdate)) {
				cs.setInt(1, valoracion.getAlumno_codigo());
				cs.setInt(2, valoracion.getCurso_codigo());
				cs.setString(3, valoracion.getOpinion());
				cs.setInt(4, valoracion.getIdValoracion());

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
