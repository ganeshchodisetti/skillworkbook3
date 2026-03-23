package com.inventory.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class HQLDemo {

    // Task 3a - Sort by price ascending
    public static void sortProductsByPriceAscending(Session session) {

        String hql = "FROM Product p ORDER BY p.price ASC";
        Query<Product> query = session.createQuery(hql, Product.class);

        List<Product> products = query.list();

        System.out.println("\nProducts Sorted by Price (Ascending)");
        for(Product p : products) {
            System.out.println(p.getName() + " - " + p.getPrice());
        }
    }

    // Task 3b - Sort by price descending
    public static void sortProductsByPriceDescending(Session session) {

        String hql = "FROM Product p ORDER BY p.price DESC";
        Query<Product> query = session.createQuery(hql, Product.class);

        List<Product> products = query.list();

        System.out.println("\nProducts Sorted by Price (Descending)");
        for(Product p : products) {
            System.out.println(p.getName() + " - " + p.getPrice());
        }
    }

    // Task 4 - Sort by quantity
    public static void sortProductsByQuantity(Session session) {

        String hql = "FROM Product p ORDER BY p.quantity DESC";
        Query<Product> query = session.createQuery(hql, Product.class);

        List<Product> products = query.list();

        System.out.println("\nProducts Sorted by Quantity");
        for(Product p : products) {
            System.out.println(p.getName() + " - Quantity: " + p.getQuantity());
        }
    }

    // Task 5a - First 3 products
    public static void getFirstThreeProducts(Session session) {

        Query<Product> query = session.createQuery("FROM Product", Product.class);
        query.setFirstResult(0);
        query.setMaxResults(3);

        List<Product> products = query.list();

        System.out.println("\nFirst 3 Products");
        for(Product p : products) {
            System.out.println(p.getName());
        }
    }

    // Task 5b - Next 3 products
    public static void getNextThreeProducts(Session session) {

        Query<Product> query = session.createQuery("FROM Product", Product.class);
        query.setFirstResult(3);
        query.setMaxResults(3);

        List<Product> products = query.list();

        System.out.println("\nNext 3 Products");
        for(Product p : products) {
            System.out.println(p.getName());
        }
    }

    // Task 6a - Count total products
    public static void countTotalProducts(Session session) {

        Long count = session.createQuery(
                "SELECT COUNT(p) FROM Product p", Long.class).uniqueResult();

        System.out.println("\nTotal Products: " + count);
    }

    // Task 6b - Count products with quantity > 0
    public static void countProductsInStock(Session session) {

        Long count = session.createQuery(
                "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0",
                Long.class).uniqueResult();

        System.out.println("Products In Stock: " + count);
    }

    // Task 6c - Count products by description
    public static void countProductsByDescription(Session session) {

        String hql = "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description";

        List<Object[]> results = session.createQuery(hql, Object[].class).list();

        System.out.println("\nProducts Count By Description");

        for(Object[] row : results) {
            System.out.println(row[0] + " : " + row[1]);
        }
    }

    // Task 6d - Min and max price
    public static void findMinMaxPrice(Session session) {

        Object[] result = session.createQuery(
                "SELECT MIN(p.price), MAX(p.price) FROM Product p",
                Object[].class).uniqueResult();

        System.out.println("\nMinimum Price: " + result[0]);
        System.out.println("Maximum Price: " + result[1]);
    }

    // Task 8 - Filter by price range
    public static void filterProductsByPriceRange(Session session) {

        Query<Product> query = session.createQuery(
                "FROM Product p WHERE p.price BETWEEN :min AND :max",
                Product.class);

        query.setParameter("min", 100.0);
        query.setParameter("max", 10000.0);

        List<Product> products = query.list();

        System.out.println("\nProducts Between Price Range");

        for(Product p : products) {
            System.out.println(p.getName() + " - " + p.getPrice());
        }
    }

    // Task 9a - Names starting with D
    public static void findProductsStartingWith(Session session) {

        Query<Product> query = session.createQuery(
                "FROM Product p WHERE p.name LIKE 'D%'", Product.class);

        List<Product> products = query.list();

        System.out.println("\nProducts Starting With D");

        for(Product p : products) {
            System.out.println(p.getName());
        }
    }

    // Task 9c - Names containing Desk
    public static void findProductsContaining(Session session) {

        Query<Product> query = session.createQuery(
                "FROM Product p WHERE p.name LIKE '%Desk%'", Product.class);

        List<Product> products = query.list();

        System.out.println("\nProducts Containing 'Desk'");

        for(Product p : products) {
            System.out.println(p.getName());
        }
    }

    // Main method
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            sortProductsByPriceAscending(session);
            sortProductsByPriceDescending(session);

            sortProductsByQuantity(session);

            getFirstThreeProducts(session);
            getNextThreeProducts(session);

            countTotalProducts(session);
            countProductsInStock(session);

            countProductsByDescription(session);

            findMinMaxPrice(session);

            filterProductsByPriceRange(session);

            findProductsStartingWith(session);
            findProductsContaining(session);

        } finally {
            session.close();
        }
    }
}