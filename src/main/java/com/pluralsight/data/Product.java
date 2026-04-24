package com.pluralsight.data;

public class Product {
    String sku;
    String productName;
    double producePrice;
    String department;

    public Product(String sku, String productName, double producePrice, String department) {
        this.sku = sku;
        this.productName = productName;
        this.producePrice = producePrice;
        this.department = department;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProducePrice() {
        return producePrice;
    }

    public void setProducePrice(double producePrice) {
        this.producePrice = producePrice;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }





}
