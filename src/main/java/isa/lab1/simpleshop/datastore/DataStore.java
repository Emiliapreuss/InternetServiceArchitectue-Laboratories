package isa.lab1.simpleshop.datastore;

import isa.lab1.simpleshop.entity.Brand;
import isa.lab1.simpleshop.entity.Product;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataStore {
    private Set<Product> products = new HashSet<>();
    private Set<Brand> brands = new HashSet<>();

    public synchronized Optional<Product> findProduct(long numberGTIN) {
        return products.stream()
                .filter(product -> product.getGTIN() == numberGTIN)
                .findFirst();
    }

    public synchronized List<Product> findAllProducts() {
        return new ArrayList<>(products);
    }

    public synchronized void saveNewProduct(Product product) throws IllegalArgumentException {
        findProduct(product.getGTIN()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The product number  \"%d\" is not unique", product.getGTIN()));
                },
                () -> products.add(product));
    }

    public synchronized void deleteProduct(long numberGTIN) throws IllegalArgumentException {
        findProduct(numberGTIN).ifPresentOrElse(
                original -> products.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("There is no product with number \"%c\"", numberGTIN));
                });
    }

    public synchronized void deleteProductByBrand(String brandName) {
        List<Product> list = findAllProducts();
        for (Product p : list) {
            if (p.getBrand().getName().equals(brandName)) {
                deleteProduct(p.getGTIN());
            }
        }
    }

    public synchronized Optional<Brand> findBrand(String name) {
        return brands.stream()
                .filter(brand -> brand.getName().equals(name))
                .findFirst();
    }

    public synchronized List<Brand> findAllBrands() {
        return new ArrayList<>(brands);
    }

    public synchronized void saveNewBrand(Brand brand) throws IllegalArgumentException {
        findBrand(brand.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The brand name \"%s\" is not unique", brand.getName()));
                },
                () -> brands.add(brand));
    }

    public synchronized void deleteBrand(String name) throws IllegalArgumentException {
        findBrand(name).ifPresentOrElse(
                original -> brands.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("There is no brand with name \"%s\"", name));
                });
    }
}
