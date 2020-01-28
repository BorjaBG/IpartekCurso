package com.ipartek.borja.prueba;

import java.sql.*;

public class EjemploJDBC {

	public static void main(String[] args) {
		final String usuario = "root";
		final String password = "admin";
		final String URL = "jdbc:mysql://localhost:3306/uf2213?serverTimezone=UTC";  //Cadena de conexion MySQL
		
		try (Connection con = DriverManager.getConnection(URL, usuario, password)) {
			System.out.println("Conectando");
			
			System.out.println("Conectado");
			
			System.out.println("LISTADO DE USUARIOS");
			
			String sql = "SELECT * FROM usuarios";
			
			Statement s = con.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(rs.getString("email") + " , " + rs.getString("contraseña"));
			}
			
			
			
			System.out.println("BUSQUEDA DE USUSARIOS POR ID");
			
			Long id = 2L;

			sql = "SELECT * FROM usuarios WHERE id = ?";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setLong(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println(rs.getString("email") + "\t" + rs.getString("contraseña"));
			} else {
				System.out.println("No encontrado");
			}
			
			System.out.println("INSERTAR");
			
			String email = "juan@email.com";
			String contraseña = "ju12347";
			
			sql = "INSERT INTO usuarios (email, contraseña) VALUES (?, ?)";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, email);
			ps.setString(2, contraseña);
			
			int numeroRegistrosModificados = ps.executeUpdate();
			
			System.out.println("ACTUALIZAR");
			
			email = "modificado@email.com";
			contraseña = "modificado";
			
			sql = "UPDATE usuarios SET email = ?, contraseña = ? WHERE id = ?";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, email);
			ps.setString(2, contraseña);
			ps.setLong(3, id);
			
			numeroRegistrosModificados = ps.executeUpdate();
			
			System.out.println("ELIMINAR");
			
			sql = "DELETE FROM usuarios WHERE email = ?";

			ps = con.prepareStatement(sql);

			ps.setString(1, email);

			numeroRegistrosModificados = ps.executeUpdate();

			if(numeroRegistrosModificados == 1) {
				System.out.println("Borrado correctamente");
			} else {
				System.err.println("No se ha borrado correctamente");
				System.err.println(numeroRegistrosModificados);
			}
			
			ps.close();
			rs.close();
			s.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
