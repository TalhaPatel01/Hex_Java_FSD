package com.trig.main;

import com.trig.db.ProductDB;
import java.util.*;

public class App {
    public static void main(String[] args){
        ProductDB productDB = new ProductDB();
        productDB.getAllProducts()
                .forEach(System.out :: println);
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Please enter the id of the product u wish to buy: ");
            int productId = sc.nextInt();
            System.out.println("How many items du u wish yo purchase(Qty): ");
            int qty = sc.nextInt();
            productDB.addTransaction(productId,qty);
            System.out.println("Transaction Success!!!!!");
        }
    }
}