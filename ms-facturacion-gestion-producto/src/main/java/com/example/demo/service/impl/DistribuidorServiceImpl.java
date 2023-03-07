package com.example.demo.service.impl;

import com.example.demo.repository.DistribuidorRepository;
import com.example.demo.repository.entity.Distribuidor;
import com.example.demo.service.DistribuidorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DistribuidorServiceImpl implements DistribuidorService {

    @Autowired
    private DistribuidorRepository distribuidorDao;

    @Override
    @Transactional
    public List<Distribuidor> findAll() {
        return (List<Distribuidor>)distribuidorDao.findAll();
    }

    @Override
    @Transactional
    public Distribuidor save(Distribuidor distribuidor) {
        return distribuidorDao.save(distribuidor);
    }

    @Override
    @Transactional
    public Distribuidor findById(Long id) {
        return distribuidorDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        distribuidorDao.deleteById(id);
    }
}
