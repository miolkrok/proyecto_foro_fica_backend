package com.forofica.uce.service;

import java.util.List;

import com.forofica.uce.repository.modelo.Noticia;
import com.forofica.uce.service.to.NoticiaTO;

public interface INoticiaService {

	public void guardarNoticia(Noticia noticia);

	public NoticiaTO buscarPorId(Integer id);

	public List<NoticiaTO> buscarTodas();
	
	public void actualizar (Noticia noticia);
	
	public void eliminar(Integer id);
	public List<NoticiaTO> buscarNoticiaNombre(String nombre);
}
