package com.ipn.mx.service;

import java.util.List;
import com.ipn.mx.domain.dto.ProductosCategoria;
import com.ipn.mx.domain.entity.Producto;


public interface ProductoService {
	public List<ProductosCategoria> ProductoPorCategoria();
	
	public List<Producto> findAll();
	public Producto findById(Long id);
	public Producto save (Producto producto);
	public void deleteById(Long id);
	
}




