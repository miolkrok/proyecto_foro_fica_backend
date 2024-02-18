package com.forofica.uce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forofica.uce.repository.IEstudianteRepository;
import com.forofica.uce.repository.modelo.Estudiante;
import com.forofica.uce.service.to.EstudianteTO;

@Service
public class EstudianteImplService implements IEstudianteService {

	@Autowired
	private IEstudianteRepository estudianteRepository;

	@Override
	public void insertarEstudiante(Estudiante estudiante) {
		this.estudianteRepository.insertar(estudiante);
	}

	@Override
	public EstudianteTO seleccionarPorCedula(String cedula) {
		return this.convertir(this.estudianteRepository.seleccionarPorCedula(cedula));
	}

	@Override
	public void guardar(Estudiante estudiante) {
		this.estudianteRepository.insertar(estudiante);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		this.estudianteRepository.actualizar(estudiante);
	}

	@Override
	public void eliminar(Integer id) {
		this.estudianteRepository.borrar(id);
	}

	@Override
	public Estudiante buscarPorId(Integer id) {
		return this.estudianteRepository.buscarPorId(id);
	}

	@Override
	public List<EstudianteTO> buscarTodos() {
		List<Estudiante> lista = this.estudianteRepository.buscarTodos();
		List<EstudianteTO> listaTO = lista.stream().map(estudiante -> this.convertir(estudiante))
				.collect(Collectors.toList());
		return listaTO;
	}

	public EstudianteTO convertir(Estudiante estudiante) {
		EstudianteTO estu = new EstudianteTO();
		estu.setId(estudiante.getId());
		estu.setCedula(estudiante.getCedula());
		estu.setNombre(estudiante.getNombre());
		estu.setSuscripcion(estudiante.getSuscripcion());
		estu.setApellido(estudiante.getApellido());
		estu.setImagen(estudiante.getImagen());

		return estu;
	}

	@Override
	public void actualizarParcial(Boolean suscripcion, String cedula) {
		this.estudianteRepository.actualizarParcial(suscripcion, cedula);
	}

}
