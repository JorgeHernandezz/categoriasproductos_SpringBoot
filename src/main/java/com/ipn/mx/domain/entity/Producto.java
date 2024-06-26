package com.ipn.mx.domain.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"producto\"")
public class Producto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"idproducto\"")
	private Long idproducto;
	
	@Column(name = "nombreproducto", length = 50, nullable = false)
	private String nombreproducto;
	
	@Column(name = "descripcionproducto", length = 250, nullable = false)
	private String descripcionproducto;
	
	@Column(name = "existencia", nullable = false)
	private double existencia;
	
	@Column(name = "precio", nullable = false)
	private int precio;
	
	@ManyToOne
	@JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
	private Categoria idcategoria;
}
 







