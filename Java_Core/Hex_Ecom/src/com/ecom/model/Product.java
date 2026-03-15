package com.ecom.model;

import java.math.BigDecimal;

public class Product implements Comparable<Product>{
    private int id;
    private String title;
    private BigDecimal price;
    private String details;
    private int numberStock;

    private Category category;  //Injecting Category in Product
    /* Product now depends on Category */

    private Vendor vendor;//Injecting Vendor in Product
    /* Product now depends on Vendor */

    public Product() {
    }

    public Product(int id, String title, BigDecimal price, String details, int numberStock, Category category, Vendor vendor) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.details = details;
        this.numberStock = numberStock;
        this.category = category;
        this.vendor = vendor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getNumberStock() {
        return numberStock;
    }

    public void setNumberStock(int numberStock) {
        this.numberStock = numberStock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", details='" + details + '\'' +
                ", numberStock=" + numberStock +
                ", category=" + category +
                ", vendor=" + vendor +
                '}';
    }

    @Override
    public int compareTo(Product p2) {
        int n1 = this.getNumberStock();
        int n2 = p2.getNumberStock();
        if(n1>n2){
            return 1;
        }
        if(n1<n2){
            return -1;
        }
        return 0;

        /* return n1-n2; */
    }
}