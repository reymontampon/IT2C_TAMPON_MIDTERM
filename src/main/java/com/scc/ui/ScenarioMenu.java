package com.scc.ui;

import com.scc.dao.ScenarioDao;
import com.scc.model.ScenarioEntity;
import java.util.List;
import java.util.Scanner;

public class ScenarioMenu {
    private final Scanner input = new Scanner(System.in);
    private final ScenarioDao dao = new ScenarioDao();

    public void show() {
        while (true) {
            printHeader();
            System.out.println("[1] Add New Order");
            System.out.println("[2] View Active Orders");
            System.out.println("[3] Exit System");
            printLine();
            System.out.print("Enter choice: ");

            String choiceText = input.nextLine().trim();
            if (choiceText.isBlank()) continue;

            try {
                int choice = Integer.parseInt(choiceText);
                switch (choice) {
                    case 1 -> addOrderFlow();
                    case 2 -> viewOrdersFlow();
                    case 3 -> {
                        System.out.println("\nExiting system... Thank you!");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Enter 1, 2, or 3 only.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.\n");
            }
        }
    }

    private void addOrderFlow() {
        System.out.println("\n--- ADD NEW ORDER ---");

        System.out.print("Enter Customer Name: ");
        String name = input.nextLine().trim();
        if (name.isBlank()) {
            System.out.println("Name cannot be empty!\n");
            return;
        }

        System.out.print("Enter Drink Item: ");
        String drink = input.nextLine().trim();
        if (drink.isBlank()) {
            System.out.println("Drink item cannot be empty!\n");
            return;
        }

        System.out.print("Enter Price: ");
        double price;
        try {
            price = Double.parseDouble(input.nextLine().trim());
            if (price <= 0) {
                System.out.println("Price must be greater than 0!\n");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid price! Enter a number.\n");
            return;
        }

        System.out.print("Enter Status (PENDING / PREPARING / COMPLETED): ");
        String status = input.nextLine().trim().toUpperCase();
        if (!status.matches("PENDING|PREPARING|COMPLETED")) {
            System.out.println("Invalid status! Use only: PENDING / PREPARING / COMPLETED\n");
            return;
        }

        dao.addOrder(name, drink, price, status);
        System.out.println("\nSUCCESS: Order added to queue!\n");
    }

    private void viewOrdersFlow() {
        List<ScenarioEntity> list = dao.getAllOrders();

        System.out.println("\n========================================");
        System.out.println("         ACTIVE COFFEE ORDERS           ");
        System.out.println("========================================");
        System.out.println("+---+----------------+--------------------+--------+-----------+");
        System.out.println("| ID| CUSTOMER NAME  | DRINK ITEM         | PRICE  | STATUS    |");
        System.out.println("+---+----------------+--------------------+--------+-----------+");

        for (ScenarioEntity o : list) {
            System.out.printf("| %d | %-14s | %-18s | %6.2f | %-9s |%n",
                    o.id(),
                    o.customerName(),
                    o.drinkItem(),
                    o.price(),
                    o.status());
        }

        System.out.println("+---+----------------+--------------------+--------+-----------+");
        System.out.println("Total Records Displayed: " + list.size() + "\n");
    }

    private void printHeader() {
        printLine();
        System.out.println("        COFFEE SHOP ORDERS SYSTEM       ");
        printLine();
    }

    private void printLine() {
        System.out.println("========================================");
    }
}