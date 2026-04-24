package com.pluralsight;

import com.pluralsight.data.Product;
import com.pluralsight.data.ProductFileManager;

import java.util.ArrayList;

public class Main {
    private final static ProductFileManager productFileManager = new ProductFileManager("src/main/java/com/pluralsight/data/products.csv");
    public static void main(String[] arg){
        ArrayList<Product> productsInventory = productFileManager.loadAllProduct();

//        System.out.println(productsInventory.toString());





    }

}
