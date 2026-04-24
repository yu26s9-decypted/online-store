package com.pluralsight.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductFileManager {
    private String productFilename;

    public ProductFileManager(String productFilename){
        this.productFilename = productFilename;
    }

    public String getProductFilename() {
        return productFilename;
    }

    public ArrayList<Product> loadAllProduct() {
        ArrayList<Product> product = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(this.productFilename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String productInventory;

            while((productInventory = bufferedReader.readLine()) != null){
                try {
                    String[] productData = productInventory.split("\\|");
                    String pSku = productData[0];
                    String pName = productData[1];
                    Double pPrice = Double.parseDouble(productData[2]);
                    String pDepartment = productData[3];

                    Product p = new Product(pSku, pName, pPrice, pDepartment);
                    product.add(p);



                } catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
        } catch (IOException e) {
            System.out.println("An error occured." + e.getMessage());
        }
        return product;

    }

    public void writeNewProduct(Product product){
       try {
           FileWriter fileWriter = new FileWriter(this.productFilename, true);
           String formattedProductLine = product.getSku() + "|" +
                   product.getProductName() + "|" +
                   product.getProducePrice() + "|" +
                   product.getDepartment();

           fileWriter.write(formattedProductLine + "\n");
           fileWriter.close();

       } catch (IOException e){
           System.out.println(e.getMessage());
       }
    }
}

