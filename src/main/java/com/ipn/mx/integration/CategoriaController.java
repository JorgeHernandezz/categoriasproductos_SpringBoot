package com.ipn.mx.integration;
import com.ipn.mx.domain.dto.CategoriaProductosDTO;
import com.ipn.mx.domain.entity.Categoria;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import com.ipn.mx.service.PdfReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ipn.mx.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Random;


@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class CategoriaController {
	@Autowired
	CategoriaService service;
	
	@Autowired
	PdfReportService pdfReportService;
	
	@CrossOrigin(origins = {"*"})
	@GetMapping("/categorias")
	public List<Categoria> readAll(){
		return service.findAll();
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
	    String mensaje = ex.getReason();
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
	}

	@CrossOrigin(origins = {"*"})
	@GetMapping("/categoria/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
	    try {
	        Categoria categoria = service.findById(id)
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la categoría con ID: " + id));
	        return ResponseEntity.ok().body(categoria);
	    } catch (Exception e) {
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar la categoría con ID: " + id);
	    }
	}


	@CrossOrigin(origins = {"*"})
	@PostMapping("/categoria/insertar")
	public ResponseEntity<String> insertCategoria(@RequestBody Categoria categoria) {
	    try {
	        Categoria nuevaCategoria = service.save(categoria);
	        String mensaje = "Categoría insertada con ID " + nuevaCategoria.getIdcategoria();
	        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
	    } catch (Exception e) {
	        String mensajeError = "No se pudo insertar la categoría";
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
	    }
	}


	@CrossOrigin(origins = {"*"})
	@PutMapping("/categoria/actualizar/{id}")
	public ResponseEntity<String> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
	    // Verificar si la categoría con el ID especificado existe
	    Optional<Categoria> optionalCategoria = service.findById(id);
	    if (!optionalCategoria.isPresent()) {
	        String mensaje = "No se encuentra categoría con ID " + id;
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
	    }

	    try {
	        categoria.setIdcategoria(id);
	        Categoria categoriaActualizada = service.save(categoria);
	        String mensaje = "Categoría actualizada correctamente: " + categoriaActualizada.getNombrecategoria();
	        return ResponseEntity.ok().body(mensaje);
	    } catch (Exception e) {
	        String mensajeError = "No se pudo actualizar la categoría";
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
	    }
	}


	

	@CrossOrigin(origins = {"*"})
	@DeleteMapping("/categoria/eliminar/{id}")
	public ResponseEntity<String> deleteCategoria(@PathVariable Long id) {
	    // Verificar si la categoría con el ID especificado existe
	    Optional<Categoria> optionalCategoria = service.findById(id);
	    if (!optionalCategoria.isPresent()) {
	        String mensaje = "No se encuentra categoría con ID " + id;
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
	    }

	    // La categoría existe, entonces intenta eliminarla
	    try {
	        service.deleteById(id);
	        String mensaje = "Categoría con ID " + id + " eliminada";
	        return ResponseEntity.ok(mensaje);
	    } catch (Exception e) {
	        String mensajeError = "No se pudo eliminar la categoría con ID " + id;
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
	    }
	}
	
	

	@CrossOrigin(origins = {"*"})
	@GetMapping("/categoria_aleatoria")
	public ResponseEntity<Categoria> getCategoriaAleatoria() {
	    try {
	        List<Categoria> categorias = service.findAll();
	        if (categorias.isEmpty()) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay categorías disponibles");
	        }

	        Random random = new Random();
	        int index = random.nextInt(categorias.size());

	        Categoria categoriaAleatoria = categorias.get(index);

	        return ResponseEntity.ok().body(categoriaAleatoria);
	    } catch (Exception e) {
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener la categoría aleatoria");
	    }
	}

	
	@CrossOrigin(origins = {"*"})
	@GetMapping("/categorias/inventario")
	public List<CategoriaProductosDTO> contarProductosPorCategoria() {
	    return service.contarProductosPorCategoria();
	}

	
	@CrossOrigin(origins = {"*"})
	@GetMapping("/generar-reporte-pdf")
	public ResponseEntity<byte[]> generarReportePDF() {
	    List<Categoria> categorias = service.findAll();
	    ByteArrayOutputStream pdfBytes = pdfReportService.generarReportePDF(categorias);
	    return ResponseEntity.ok()
	            .header("Content-Disposition", "attachment; filename=reporte_categorias.pdf")
	            .body(pdfBytes.toByteArray());
	}




}
