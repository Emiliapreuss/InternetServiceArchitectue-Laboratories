package isa.lab1.simpleshop.repository;

import isa.lab1.simpleshop.datastore.DataStore;
import isa.lab1.simpleshop.entity.Brand;
import isa.lab1.simpleshop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class ProductRepository {
    private DataStore dataStore;

    @Autowired
    public ProductRepository(DataStore store) {
        this.dataStore = store;
    }

    public void saveNew(Product product) {
        dataStore.saveNewProduct(product);
    }

    public Optional<Product> findByPKey(long GTIN) {
        return dataStore.findProduct(GTIN);
    }

    public List<Product> findAll() {
        return dataStore.findAllProducts();
    }

    public void deleteExisting(Product product) {
        dataStore.deleteProduct(product.getGTIN());
    }

    public void deleteByBrand(Brand brand) {
        dataStore.deleteProductByBrand(brand.getName());
    }
}
