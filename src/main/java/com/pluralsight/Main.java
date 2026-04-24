package com.pluralsight;

import com.pluralsight.data.Product;
import com.pluralsight.data.ProductFileManager;
import com.pluralsight.ui.Console;

import java.util.ArrayList;

public class Main {
    private final static ProductFileManager productFileManager = new ProductFileManager("src/main/java/com/pluralsight/data/products.csv");
    public static void main(String[] arg){
        ArrayList<Product> productsInventory = productFileManager.loadAllProduct();

//        System.out.println(productsInventory.toString());

        String storeHomeScreenMenu = """
                Welcome! What would you like to do?
                1. Display Products
                2. Display Cart
                3. Exit out of the application
                
                Enter a command:
                """;

        int userInput;
        do {
            userInput = Console.askForInt(storeHomeScreenMenu, 1,3);

            switch (userInput){
                case 1:
                    System.out.println("case 1");
                    break;
                case 2:
                    System.out.println("case 2");
                    break;
                case 3:
                    System.out.println("Thank you!");
                    return;
            }
        } while (userInput != 3);








    }

}
