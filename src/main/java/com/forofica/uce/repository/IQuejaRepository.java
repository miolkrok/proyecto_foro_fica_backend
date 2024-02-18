package com.forofica.uce.repository;

import java.util.List;

import com.forofica.uce.repository.modelo.Queja;

public interface IQuejaRepository {
	public void insertaQueja(Queja queja);

	public Queja buscaQueja(Integer id);

	public List<Queja> buscaTodasQuejas();
	
	public void actualizar (Queja queja);
	
	public void eliminar(Integer id);

}
