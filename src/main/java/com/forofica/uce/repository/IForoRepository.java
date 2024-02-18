package com.forofica.uce.repository;

import java.util.List;

import com.forofica.uce.repository.modelo.Foro;

public interface IForoRepository {
	public void insertar(Foro foro);

	public Foro buscarPorId(Integer id);

	public List<Foro> buscarTodos();

	public void actualizar(Foro foro);

	public void eliminar(Integer id);

	public List<Foro> buscarPorCedulaEstudiante(String cedula);
}
