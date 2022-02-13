package isa.lab1.simpleshop.configuration;

import isa.lab1.simpleshop.entity.Brand;
import isa.lab1.simpleshop.entity.Product;
import isa.lab1.simpleshop.service.BrandService;
import isa.lab1.simpleshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializator {
    private final ProductService productService;
    private final BrandService brandService;

    @Autowired
    public DataInitializator(ProductService productService, BrandService brandService) {
        this.productService = productService;
        this.brandService = brandService;
    }

    @PostConstruct
    private synchronized void init() {
        Brand ginoRossi = new Brand("Gino-Rossi", "Poland", 1992);
        Brand nike = new Brand("Nike", "USA", 1964);
        Brand puma = new Brand("Puma", "Germany", 1948);
        Brand lacoste = new Brand("Lacoste", "France", 1933);

        brandService.saveNew(ginoRossi);
        brandService.saveNew(nike);
        brandService.saveNew(puma);
        brandService.saveNew(lacoste);

        Product trousers = new Product(87871118, 38, "trousers", nike);
        Product trousers1 = new Product(55661100, 42, "trousers", puma);
        Product tShirt = new Product(77123400, 36, "T-shirt", puma);
        Product blouse = new Product(51239977, 44, "Blouse", nike);
        Product heels = new Product(43781235, 39, "Heels", ginoRossi);
        Product shoes = new Product(53209911, 45, "Shoes", ginoRossi);
        Product shoes1 = new Product(54628999, 36, "Shoes", ginoRossi);
        Product tShirt1 = new Product(43678921, 40,"T-shirt", lacoste);

        productService.saveNew(trousers);
        productService.saveNew(trousers1);
        productService.saveNew(tShirt);
        productService.saveNew(blouse);
        productService.saveNew(heels);
        productService.saveNew(shoes);
        productService.saveNew(shoes1);
        productService.saveNew(tShirt1);
    }
}
