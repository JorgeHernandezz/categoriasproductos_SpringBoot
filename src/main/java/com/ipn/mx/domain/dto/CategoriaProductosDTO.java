package com.ipn.mx.domain.dto;

import java.io.Serializable;

public class CategoriaProductosDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreCategoria;
    private Long cantidadProductos;

    // Constructor vacío
    public CategoriaProductosDTO() {}

    // Constructor con parámetros
    public CategoriaProductosDTO(String nombreCategoria, Long cantidadProductos) {
        this.nombreCategoria = nombreCategoria;
        this.cantidadProductos = cantidadProductos;
    }

    // Getters y setters
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Long getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(Long cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }
}
