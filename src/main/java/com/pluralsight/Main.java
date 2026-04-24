package com.pluralsight;

import com.pluralsight.data.Product;
import com.pluralsight.data.ProductFileManager;
import com.pluralsight.ui.Console;

import java.util.ArrayList;

public class Main {
    private final static ProductFileManager productFileManager = new ProductFileManager("src/main/java/com/pluralsight/data/products.csv");
    public static void main(String[] arg){
        ArrayList<Product> productsInventory = productFileManager.loadAllProduct();
        ArrayList<Product> userCart = new ArrayList<>();

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
                    listAllProducts(productsInventory);
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

    public static void listAllProducts(ArrayList<Product> products){
        String displayProductMenu = """
                What would you like to do?
                1. Search/Filter a product
                2. Add a product to cart
                3. Return to home screen.
                
                """;

        int userInputDisplayMenu;
        System.out.println("====== PRODUCT CATALOG ======");
        for (int i = 0; i < products.size(); i++){
            Product p = products.get(i);
            System.out.printf("%s %s %s $%.2f\n", p.getSku(), p.getProductName(),p.getDepartment(), p.getProducePrice());
        }
        System.out.println("====== PRODUCT CATALOG END ======");

        do {
            userInputDisplayMenu = Console.askForInt(displayProductMenu, 1,3);
            switch (userInputDisplayMenu){
                case 1:
                    searchForProduct(products);
                    break;
                case 2:
                    addProductToCart(products);
                    break;
                case 3:
                    return;

            }
        }   while (userInputDisplayMenu != 3);
    }

    public static void searchForProduct(ArrayList<Product> product){
        String userSearchInput = Console.askForString("What are you looking for: ");
        boolean hasFound = false;
        for(Product p : product) {
            if (p.getProductName().toLowerCase().contains(userSearchInput.toLowerCase())) {
                System.out.printf("%s %s %s $%.2f\n", p.getSku(), p.getProductName(),p.getDepartment(), p.getProducePrice());
                hasFound = true;

            }
        }

        if(!hasFound){
            System.out.println("No result was found!");
        }
    }

    public static void addProductToCart(ArrayList<Product> product){

        String checkOutMenu = """
                What's the SKU of the item?
                """;

        String userSkuInput;
        userSkuInput = Console.askForString("Enter SKU: ");
        boolean hasFoundSku = false;
        for(Product p : product) {
            if (p.getSku().toLowerCase().contains(userSkuInput.toLowerCase()))
            {
                System.out.printf("Added %s %s %s $%.2f to your cart.\n", p.getSku(), p.getProductName(),p.getDepartment(), p.getProducePrice());
                hasFoundSku = true;
            }
            if (!hasFoundSku) {
                System.out.println("This item doesn't exist at our store.");
            }
        }








    }



}
