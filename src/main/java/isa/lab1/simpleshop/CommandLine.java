package isa.lab1.simpleshop;

import isa.lab1.simpleshop.entity.Brand;
import isa.lab1.simpleshop.entity.Product;
import isa.lab1.simpleshop.service.BrandService;
import isa.lab1.simpleshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {
    private final ProductService productService;
    private final BrandService brandService;

    @Autowired
    public CommandLine(ProductService productService, BrandService brandService) {
        this.brandService = brandService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        String option;

        while (true) {
            System.out.println("\nWelcome, choose one option(A/B):");
            System.out.println("Listing available commands: A");
            System.out.println("Exit: B");
            option = scanner.nextLine().toUpperCase();

            switch (option) {
                case "A":
                    while (true) {
                        System.out.println("\nTo choose one option type A, B, C, D or E and press enter");
                        System.out.println("Available commands:");
                        System.out.println("Listing all categories: A");
                        System.out.println("Listing all elements: B");
                        System.out.println("Add element: C");
                        System.out.println("Remove element: D");
                        System.out.println("Exit: E");
                        option = scanner.nextLine().toUpperCase();
                        switch (option) {
                            case "A":
                                listCategories();
                                break;
                            case "B":
                                listElements();
                                break;
                            case "C":
                                addElement();
                                break;
                            case "D":
                                removeElement();
                                break;
                            case "E":
                                System.out.println("See you");
                                return;
                            default:
                                System.out.println("Wrong option, choose A, B, C, D or E");
                        }
                    }
                case "B":
                    System.out.println("See you");
                    return;
                default:
                    System.out.println("Wrong option, type A or B");
            }
        }
    }

    private void listCategories() {
        System.out.println("Category 1: Product");
        System.out.println("Category 2: Brand");
    }

    private void listElements() {
        System.out.println("Products: ");
        productService.findAll().forEach(System.out::println);
        System.out.println();
        System.out.println("Brands: ");
        brandService.findAll().forEach(System.out::println);
    }

    private void addElement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to add? (1 or 2)");
        listCategories();
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                addProduct();
                break;
            case "2":
                addBrand();
                break;
            default:
                System.out.println("Wrong option");
        }
    }

    private void addProduct() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type GTIN (8 digits):");
            long number = scanner.nextLong();
            System.out.println("Type size (number):");
            int size = scanner.nextInt();
            System.out.println("Provide type:");
            scanner.nextLine();
            String type = scanner.nextLine();
            System.out.println("Type name of brand:");
            String brandName = scanner.nextLine();
            Brand brand = brandService.findByPKey(brandName).orElseThrow(
                    () -> new Exception("There is no brand with name: " + brandName));
            Product product = new Product(number, size, type, brand);
            productService.saveNew(product);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Can't add product");
        }
    }

    private void addBrand() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type name of brand:");
            String name = scanner.nextLine();
            System.out.println("Type country of origin:");
            String country = scanner.nextLine();
            System.out.println("Type year od foundation:");
            int year = scanner.nextInt();
            Brand brand = new Brand(name, country, year);
            brandService.saveNew(brand);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Can't add brand");
        }
    }

    private void removeElement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to remove? (1 or 2)");
        listCategories();
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                removeProduct();
                break;
            case "2":
                removeBrand();
                break;
            default:
                System.out.println("Wrong option");
        }
    }

    private void removeProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type GTIN number: ");
        long number = scanner.nextLong();
        try {
            productService.deleteExisting(productService.findByPKey(number).orElseThrow());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }
        System.out.println("Product removed");
    }

    private void removeBrand() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type brand name: ");
        String name = scanner.nextLine();
        try {
            productService.deleteByBrand(brandService.findByPKey(name).orElseThrow());
            brandService.deleteExisting(brandService.findByPKey(name).orElseThrow());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }
        System.out.println("Brand removed");
    }
}




