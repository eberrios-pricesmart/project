package com.example.demo.repository;

import com.example.demo.repository.entity.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

  @Query("select p from Producto p where p.nombre like %?1%")
  List<Producto> findByNombre(String term);

  List<Producto> findByNombreContainingIgnoreCase(String term);

  List<Producto> findByNombreStartingWithIgnoreCase(String term);

  List<Producto> findAll();
}
