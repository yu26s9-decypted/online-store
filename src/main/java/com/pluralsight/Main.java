package com.pluralsight;

import com.pluralsight.data.Product;
import com.pluralsight.data.ProductFileManager;
import com.pluralsight.ui.Console;

import java.util.ArrayList;

public class Main {
    private final static ProductFileManager productFileManager = new ProductFileManager("src/main/java/com/pluralsight/data/products.csv");
    private static ArrayList<Product> userCart = new ArrayList<>();
    private static ArrayList<Product> productsInventory = productFileManager.loadAllProduct();

    public static void main(String[] arg) {



        String storeHomeScreenMenu = """
                Welcome! What would you like to do?
                1. Display Products
                2. Display Cart
                3. Exit out of the application
                
                Enter a command:
                """;

        int userInput;
        do {
            userInput = Console.askForInt(storeHomeScreenMenu, 1, 3);

            switch (userInput) {
                case 1:
                    listAllProducts(productsInventory);
                    break;
                case 2:
                    displayCartMenu();
                    break;
                case 3:
                    return;
            }
        } while (userInput != 3);


    }

    public static void listAllProducts(ArrayList<Product> products) {
        String displayProductMenu = """
                What would you like to do?
                1. Search/Filter a product
                2. Add a product to cart
                3. Return to home screen.
                
                """;

        int userInput;
        System.out.println("====== PRODUCT CATALOG ======");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("%s %s %s $%.2f\n", p.getSku(), p.getProductName(), p.getDepartment(), p.getProducePrice());
        }
        System.out.println("====== PRODUCT CATALOG END ======");

        do {
            userInput = Console.askForInt(displayProductMenu, 1, 3);
            switch (userInput) {
                case 1:
                    searchForProduct(products);
                    break;
                case 2:
                    addProductToCart(products);
                    break;
                case 3:
                    return;

            }
        } while (userInput != 3);
    }

    public static void searchForProduct(ArrayList<Product> product) {
        String userSearchInput = Console.askForString("What are you looking for: ");
        boolean hasFound = false;
        for (Product p : product) {
            if (p.getProductName().toLowerCase().contains(userSearchInput.toLowerCase()) ||
                    p.getSku().equalsIgnoreCase(userSearchInput) ||
                    p.getDepartment().equalsIgnoreCase(userSearchInput)


            ) {
                System.out.printf("%s %s %s $%.2f\n", p.getSku(), p.getProductName(), p.getDepartment(), p.getProducePrice());
                hasFound = true;

            }
        }

        if (!hasFound) {
            System.out.println("No result was found!");
        }
    }


    public static void addProductToCart(ArrayList<Product> product) {

        while (true) {
            String checkOutMenu = """
                    What's the SKU of the item?
                    """;
            String userSkuInput;
            userSkuInput = Console.askForString("Enter SKU (or type x to exit): ");

            if (userSkuInput.equalsIgnoreCase("X")) {
                break;
            }

            boolean hasFoundSku = false;


            for (Product p : product) {
                if (p.getSku().equalsIgnoreCase(userSkuInput.toLowerCase())) {
                    System.out.printf("Added %s %s %s $%.2f to your cart.\n", p.getSku(), p.getProductName(), p.getDepartment(), p.getProducePrice());
                    userCart.add(p);
                    hasFoundSku = true;
                }
            }
            if (!hasFoundSku) {
                System.out.println("This item doesn't exist at our store.");
            }
        }
    }

    public static void displayCartMenu() {
        System.out.println("Your cart");
        if (userCart.isEmpty()) {
            System.out.println("Cart empty.");
        }
        ;


        String cartMenuMsg = """
                Ready to checkout?
                1. Check Out
                2. Remove Items
                3. Back to home
                
                Enter your command:
                """;

        for (Product p : userCart) {
            System.out.printf("%s %s %s $%.2f \n", p.getSku(), p.getProductName(), p.getDepartment(), p.getProducePrice());

        }

        int userInput;
        do {
            userInput = Console.askForInt(cartMenuMsg, 1, 3);
            switch (userInput) {
                case 1:
                    checkOutItem();
                    return;
                case 2:
                    removeProductFromCart();
                    break;
                case 3:
                    return;
            }
        } while (userInput != 3);

    }

    public static void removeProductFromCart() {
        if (userCart.isEmpty()) {
            System.out.printf("You don't have any items in your cart yet!");
        }
        while (true) {

            String userSkuInput;
            userSkuInput = Console.askForString("Enter Item SKU to remove (or type x to exit): ");

            if (userSkuInput.equalsIgnoreCase("X")) {
                break;
            }

            for (Product p : userCart) {
                System.out.printf("%s %s %s $%.2f \n", p.getSku(), p.getProductName(), p.getDepartment(), p.getProducePrice());
            }
            System.out.printf("\n =============== \n");

            boolean skuExist = false;

            for (Product p : userCart) {
                if (p.getSku().equalsIgnoreCase(userSkuInput.toLowerCase())) {
                    System.out.printf("Removed %s %s %s $%.2f to your cart.\n", p.getSku(), p.getProductName(), p.getDepartment(), p.getProducePrice());
                    userCart.remove(p);
                    skuExist = true;
                    break;
                }
            }
            if (!skuExist) {
                System.out.println("The SKU of this item doesn't exist.");
            }


        }

    }

    public static void checkOutItem(){

        double totalPrice = 0;
        System.out.printf("| SKU |   | Item |     | Dep |   | Price |\n");

        for (Product p : userCart) {
            System.out.printf("%s %10s %10s $%.2f \n", p.getSku(), p.getProductName(), p.getDepartment(), p.getProducePrice());
            totalPrice += p.getProducePrice();
        }
        System.out.printf("Total price: $%.2f", totalPrice);

        double userPaymentAmount;
        double change;
        while (true) {
            userPaymentAmount = Console.askForDouble("Enter cash amount");
            if(userPaymentAmount >= totalPrice){
                change = userPaymentAmount - totalPrice ;
                System.out.printf("\n Thank you for shopping with us!" +
                        "\n Your total change: $%.2f \n", change);
                break;
            } else {
                double difference = totalPrice - userPaymentAmount;
                System.out.printf("You don't have enough!" +
                        "You need $%.2f to make up the deficit \n", difference);
            };
        }
    }

}