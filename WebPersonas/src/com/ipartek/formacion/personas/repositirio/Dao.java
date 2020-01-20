package com.ipartek.formacion.personas.repositirio;

public interface Dao<T> {
	Iterable<T> obtenerTodos();
	T obtenerPorId(Long id);
	void agregar(T objeto);
	void eliminar(Long id);
	void actualizar(T objeto);
}
