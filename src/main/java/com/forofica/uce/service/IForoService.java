package com.forofica.uce.service;

import java.util.List;

import com.forofica.uce.repository.modelo.Foro;
import com.forofica.uce.service.to.ForoTO;

public interface IForoService {
	public void guardar(ForoTO foro);

	public ForoTO buscarPorId(Integer id);

	public Foro buscarPorIdNormal(Integer id);

	public List<ForoTO> buscarTodos();

	public void actualizar(Foro foro);

	public void eliminar(Integer id);

	public List<ForoTO> buscarPorCedulaEstudiante(String cedula);
}
