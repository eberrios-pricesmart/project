package com.example.demo.service;

import com.example.demo.repository.entity.Cliente;
import com.example.demo.repository.entity.Region;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

  public List<Cliente> findAll();

  public Page<Cliente> findAll(Pageable pageable);

  public Cliente findById(Long id);

  public Cliente save(Cliente cliente);

  public void delete(Long id);

  public List<Region> findAllRegiones();

}
