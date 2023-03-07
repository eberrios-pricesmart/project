package com.example.demo.service.impl;

import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.entity.Producto;
import com.example.demo.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoDao;

    @Override
    public List<Producto> findAllProductos() {
        return productoDao.findAll();
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        return productoDao.save(producto);
    }

    @Override
    @Transactional
    public Producto findById(Long id){
        return productoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id){
        productoDao.deleteById(id);
    }
}
