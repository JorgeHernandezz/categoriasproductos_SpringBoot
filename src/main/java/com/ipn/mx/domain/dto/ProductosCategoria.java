package com.ipn.mx.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductosCategoria implements Serializable {
    private static final long serialVersionUID = 1L;

    private String categoria;
    private Long cantidad;

    public String getCategoria() {
        return categoria;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
