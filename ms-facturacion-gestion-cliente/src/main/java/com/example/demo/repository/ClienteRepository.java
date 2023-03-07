package com.example.demo.repository;

import com.example.demo.repository.entity.Cliente;
import com.example.demo.repository.entity.Region;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  @Query("from Region")
  List<Region> findAllRegions();
}
