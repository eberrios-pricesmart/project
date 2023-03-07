package com.example.demo.service;

import com.example.demo.repository.entity.Distribuidor;
import java.util.List;

public interface DistribuidorService {

  public List<Distribuidor> findAll();

  public Distribuidor save(Distribuidor distribuidor);

  public Distribuidor findById(Long id);

  public void delete(Long id);
}
