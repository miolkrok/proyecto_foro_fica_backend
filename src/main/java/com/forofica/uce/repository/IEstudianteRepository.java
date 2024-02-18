package com.forofica.uce.repository;

import java.util.List;

import com.forofica.uce.repository.modelo.Estudiante;

public interface IEstudianteRepository {
	
	public Estudiante buscarPorId(Integer id);
	
	public Estudiante seleccionarPorCedula(String cedula);

	public void insertar(Estudiante estudiante);

	public void actualizar(Estudiante estudiante);

	public void actualizarParcial(Boolean suscripcion, String cedula);

	public void borrar(Integer id);
	
	public List<Estudiante> buscarTodos();
	
}
