package com.inventory.main;
import com.inventory.dao.ProductDAO;
import com.inventory.entity.Product;
public class App {
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
       Product p1 = new Product("Laptop", "Gaming Laptop", 75000, 10);
        Product p2 = new Product("Mouse", "Wireless Mouse", 1200, 50);
        Product p3 = new Product("Keyboard", "Mechanical Keyboard", 4500, 30);
        Product p4 = new Product("Monitor", "24 inch Monitor", 15000, 20);
        Product p5 = new Product("Desk Chair", "Office Chair", 7000, 0);
        Product p6 = new Product("Desk Lamp", "LED Lamp", 1200, 25);
        Product p7 = new Product("Notebook", "Spiral Notebook", 50, 100);
        Product p8 = new Product("Pen Set", "Ball Pen Set", 200, 75);

        dao.saveProduct(p3);
        dao.saveProduct(p4);
        dao.saveProduct(p5);
        dao.saveProduct(p6);
        dao.saveProduct(p7);
        dao.saveProduct(p8);
       dao.saveProduct(p1);
        dao.saveProduct(p2);
    }
}
