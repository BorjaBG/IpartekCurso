package com.ipartek.mf0966_3.borja.repositorio;

public interface Dao<T> {
	Iterable<T> obtenerTodos();
	T obtenerPorId(int id);
	T obtenerPorIdentificador(String identificador);
	Integer agregar(T objeto);
	void eliminar(int id);
	void actualizar(T objeto);
}
