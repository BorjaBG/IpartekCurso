package com.ipartek.borja.repositorio;

public interface Dao<T> {
	Iterable<T> obtenerTodos();
	T obtenerPorId(int id);
	void agregar(T objeto);
	void eliminar(int id);
	void actualizar(T objeto);
}
