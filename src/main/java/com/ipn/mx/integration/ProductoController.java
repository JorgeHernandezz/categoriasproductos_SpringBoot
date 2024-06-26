package com.ipn.mx.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ipn.mx.domain.dto.ProductosCategoria;
import com.ipn.mx.domain.entity.Producto;
import com.ipn.mx.service.ProductoService;

//localhost:8080/apiProducto/productosPorCategoria

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/apiProducto")
public class ProductoController {
	@Autowired
	ProductoService service;
	
	@GetMapping("/productosPorCategoria")
	public List<ProductosCategoria> productosPorCategoria(){
		return service.ProductoPorCategoria();
	}
	
	
	@GetMapping("/productos")
	public List<Producto> mostrarProductos(){
		return service.findAll();
	}
	
	@PostMapping("/productos")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto save (@RequestBody Producto producto) {
		return service.save(producto);
	}
	
	@DeleteMapping("/productos/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
	
	
	@PutMapping("/productos/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        Producto productoExistente = service.findById(id);
        if (productoExistente != null) {
            productoExistente.setNombreproducto(productoActualizado.getNombreproducto());
            productoExistente.setDescripcionproducto(productoActualizado.getDescripcionproducto());
            productoExistente.setExistencia(productoActualizado.getExistencia());
            productoExistente.setPrecio(productoActualizado.getPrecio());
            productoExistente.setIdcategoria(productoActualizado.getIdcategoria()); // Ajustar según tu lógica de actualización
            return service.save(productoExistente);
        } else {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
    }
	
	
}


