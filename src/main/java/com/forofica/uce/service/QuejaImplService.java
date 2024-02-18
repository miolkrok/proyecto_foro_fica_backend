package com.forofica.uce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forofica.uce.repository.IQuejaRepository;
import com.forofica.uce.repository.modelo.Queja;
import com.forofica.uce.service.to.EstudianteTO;
import com.forofica.uce.service.to.QuejaTO;

@Service
public class QuejaImplService implements IQuejaService {

	@Autowired
	private IQuejaRepository quejaRepository;

	@Override
	public void guardarQueja(Queja queja) {
		 this.quejaRepository.insertaQueja(queja);
	}

	@Override
	public QuejaTO buscarPorId(Integer id) {
		return this.convertirQuejaATo(this.quejaRepository.buscaQueja(id));
	}

	@Override
	public List<QuejaTO> buscarTodas() {
		List<Queja> lista = this.quejaRepository.buscaTodasQuejas();
		List<QuejaTO> listaTO = lista.stream().map(queja -> this.convertirQuejaATo(queja)).collect(Collectors.toList());
		return listaTO;
	}

	@Override
	public void actualizar(Queja queja) {
		this.quejaRepository.actualizar(queja);
	}

	@Override
	public void eliminar(Integer id) {
		this.quejaRepository.eliminar(id);
	}

	private QuejaTO convertirQuejaATo(Queja queja) {
		EstudianteTO estudiante = new EstudianteTO();
		estudiante.setCedula(queja.getEstudiante().getCedula());
		estudiante.setNombre(queja.getEstudiante().getNombre());
		estudiante.setApellido(queja.getEstudiante().getApellido());

		QuejaTO quejaTO = new QuejaTO();
		quejaTO.setId(queja.getId());
		quejaTO.setDescripcion(queja.getDescripcion());
		quejaTO.setFechaPublicacion(queja.getFechaPublicacion());
		quejaTO.setEstudianteTO(estudiante);
		return quejaTO;
	}

}
