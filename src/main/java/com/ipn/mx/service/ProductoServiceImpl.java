package com.ipn.mx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ipn.mx.domain.dto.ProductosCategoria;
import com.ipn.mx.domain.entity.Producto;
import com.ipn.mx.domain.repository.ProductoRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService{
	@Autowired
	ProductoRepository repository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<ProductosCategoria> ProductoPorCategoria() {
		List<Object[]> resultados = repository.contarProductoPorCategoria();
		List<ProductosCategoria> lista = new ArrayList<>();
		for(Object[] registro : resultados) {
			ProductosCategoria pc = new ProductosCategoria();
			pc.setCategoria((String) registro[0]);
			pc.setCantidad((Long) registro[1]);
			lista.add(pc);
		}
		return lista;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
	    return repository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
	}

	@Override
	public Producto save(Producto producto) {
		return repository.save(producto);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

	
	
}



