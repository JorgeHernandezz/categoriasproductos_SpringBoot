package com.ipn.mx.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.ipn.mx.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
}
