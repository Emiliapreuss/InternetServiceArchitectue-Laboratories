package isa.lab1.simpleshop.repository;

import isa.lab1.simpleshop.datastore.DataStore;
import isa.lab1.simpleshop.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BrandRepository {
    private DataStore dataStore;

    @Autowired
    public BrandRepository(DataStore store) {
        this.dataStore = store;
    }

    public void saveNew(Brand brand) {
        dataStore.saveNewBrand(brand);
    }

    public Optional<Brand> findByPKey(String name) {
        return dataStore.findBrand(name);
    }

    public List<Brand> findAll() {
        return dataStore.findAllBrands();
    }

    public void deleteExisting(Brand brand) {
        dataStore.deleteBrand(brand.getName());
    }
}
