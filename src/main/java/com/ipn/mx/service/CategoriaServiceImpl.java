package com.ipn.mx.service;
import com.ipn.mx.domain.entity.Categoria;
import com.ipn.mx.domain.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ipn.mx.domain.dto.CategoriaProductosDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    //@Override
    @Transactional(readOnly = true)
    public List<Categoria> findAll() {
        return (List<Categoria>) repository.findAll();
    }

    //@Override
    @Transactional(readOnly = true)
    public Optional<Categoria> findById(Long id) {
        return repository.findById(id);
    }

    //@Override
    @Transactional
    public Categoria save(Categoria categoria) {
        return repository.save(categoria);
    }

    //@Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    //@Override
    @Transactional
    public Categoria update(Categoria categoria) {
        return repository.save(categoria);
    }
    
    @Override
    public List<CategoriaProductosDTO> contarProductosPorCategoria() {
        List<Object[]> resultList = repository.contarProductosPorCategoria();
        List<CategoriaProductosDTO> dtos = new ArrayList<>();

        for (Object[] result : resultList) {
            String nombreCategoria = (String) result[0];
            Long cantidadProductos = (Long) result[1];
            CategoriaProductosDTO dto = new CategoriaProductosDTO(nombreCategoria, cantidadProductos);
            dtos.add(dto);
        }

        return dtos;
    }



}
