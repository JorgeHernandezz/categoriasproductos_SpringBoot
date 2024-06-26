package com.ipn.mx.domain.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ipn.mx.domain.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
		
	/*
	 * delimiter $$
	 * create procedure spProductosCategoria()
	 * begin
	 * 	select c.nombreCategoria as categoria, count(*) as Cantidad from producto p, categoria as c where c.idCategoria = p.idCategoria group by c.idCategoria;
	 * end $$
	 * delimiter ;
	 *  call spProductosCategoria();
	 */
	
	@Query(
			value = "CALL ContarProductosPorCategoria()", 
			nativeQuery = true
			)
	
	public List<Object[]> contarProductosPorCategoria();

}
