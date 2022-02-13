package isa.lab1.simpleshop.service;

import isa.lab1.simpleshop.entity.Brand;
import isa.lab1.simpleshop.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private BrandRepository repository;

    @Autowired
    public BrandService(BrandRepository repository) {
        this.repository = repository;
    }

    public void saveNew(Brand brand) {
        repository.saveNew(brand);
    }

    public Optional<Brand> findByPKey(String name) {
        return repository.findByPKey(name);
    }

    public List<Brand> findAll() {
        return repository.findAll();
    }

    public void deleteExisting(Brand brand) {
        repository.deleteExisting(repository.findByPKey(brand.getName()).orElseThrow());
    }
}
