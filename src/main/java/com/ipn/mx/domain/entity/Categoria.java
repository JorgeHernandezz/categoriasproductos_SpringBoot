package com.ipn.mx.domain.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcategoria")
	private Long idcategoria;
	
	@Column(name = "nombrecategoria", length = 100, nullable=false)
	private String nombrecategoria;
	
	@Column(name = "descripcioncategoria", length = 250, nullable=false)
	private String descripcioncategoria;
	
	@Temporal(TemporalType.DATE)  // Esta anotación es importante para mapear correctamente el campo de fecha
    @Column(name = "fechacreacion", nullable = false)
    private Date fechacreacion;
	
	



}
