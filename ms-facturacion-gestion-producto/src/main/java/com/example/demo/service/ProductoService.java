package com.example.demo.service;

import com.example.demo.repository.entity.Producto;
import java.util.List;

public interface ProductoService {

  List<Producto> findAllProductos();

  Producto save(Producto producto);

  Producto findById(Long id);

  void delete(Long id);

}
