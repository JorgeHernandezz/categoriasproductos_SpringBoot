package com.ipn.mx.service;

import java.util.List;
import java.util.Optional;

import com.ipn.mx.domain.dto.CategoriaProductosDTO;
import com.ipn.mx.domain.entity.Categoria;

public interface CategoriaService {

	List<CategoriaProductosDTO> contarProductosPorCategoria();
	void deleteById(Long id);
	List<Categoria> findAll();
	Optional<Categoria> findById(Long id);
	Categoria save(Categoria categoria);

}
