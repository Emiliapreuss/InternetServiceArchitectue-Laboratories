package isa.lab1.simpleshop.service;

import isa.lab1.simpleshop.entity.Brand;
import isa.lab1.simpleshop.entity.Product;
import isa.lab1.simpleshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void saveNew(Product product) {
        repository.saveNew(product);
    }

    public Optional<Product> findByPKey(long GTIN) {
        return repository.findByPKey(GTIN);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public void deleteExisting(Product product) {
        repository.deleteExisting(repository.findByPKey(product.getGTIN()).orElseThrow());
    }

    public void deleteByBrand(Brand brand) {
        repository.deleteByBrand(brand);
    }
}
