package com.example.demo.service.impl;

import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.entity.Cliente;
import com.example.demo.repository.entity.Region;
import com.example.demo.service.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public List<Cliente> findAll() {
    return (List<Cliente>) clienteRepository.findAll();
  }

  @Override
  public Page<Cliente> findAll(Pageable pageable) {
    return clienteRepository.findAll(pageable);
  }

  @Override
  public Cliente findById(Long id) {
    return clienteRepository.findById(id).orElse(null);
  }

  @Override
  public Cliente save(Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  @Override
  public void delete(Long id) {
    clienteRepository.deleteById(id);
  }

  @Override
  public List<Region> findAllRegiones() {
    return clienteRepository.findAllRegions();
  }
}
