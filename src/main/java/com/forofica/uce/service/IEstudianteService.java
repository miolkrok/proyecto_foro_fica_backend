package com.forofica.uce.service;

import java.util.List;

import com.forofica.uce.repository.modelo.Estudiante;
import com.forofica.uce.service.to.EstudianteTO;

public interface IEstudianteService {

	public void insertarEstudiante(Estudiante estudiante);

	public Estudiante buscarPorId(Integer id);

	public EstudianteTO seleccionarPorCedula(String cedula);

	public void guardar(Estudiante estudiante);

	public void actualizar(Estudiante estudiante);
	
	public void actualizarParcial(Boolean suscripcion, String cedula);

	public void eliminar(Integer id);

	public List<EstudianteTO> buscarTodos();
	
	public EstudianteTO convertir(Estudiante estudiante);

}
