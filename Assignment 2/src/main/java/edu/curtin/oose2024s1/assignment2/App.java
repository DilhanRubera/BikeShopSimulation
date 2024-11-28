package edu.curtin.oose2024s1.assignment2;

import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.observer.EmployeeObserver;
import edu.curtin.oose2024s1.assignment2.observer.FailureObserver;
import edu.curtin.oose2024s1.assignment2.observer.SuccessObserver;
import edu.curtin.oose2024s1.assignment2.observer.TaskObserver;
import edu.curtin.oose2024s1.assignment2.utils.Utils;

/**
 * Use this code to get started on Assignment 2. You are free to modify or
 * replace this file as
 * needed (to fulfil the assignment requirements, of course).
 */

// Suppressing the PMD.CloseResource warning for the ScheduledExecutorService
@SuppressWarnings("PMD.CloseResource")

public class App {

    // Setting up the logger
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        BikeShopInput inp = new BikeShopInput();
        // BikeShopInput inp = new BikeShopInput(123); // Seed for the random number
        // generator

        // Intializing the BikeShop
        logger.info("Intializing the BikeShop");
        BikeShop shop = new BikeShop();

        // Intializing observers
        logger.info("Intializing observers");
        EmployeeObserver employeeObserver = new EmployeeObserver(shop);
        TaskObserver taskObserver = new TaskObserver(shop);
        FailureObserver failureObserver = new FailureObserver(shop);
        SuccessObserver sucessObserver = new SuccessObserver();

        logger.info("Adding observers to the BikeShop");
        // Adding observers to the BikeShop
        shop.addTaskObserver(employeeObserver);
        shop.addTaskObserver(taskObserver);
        shop.addFailureObserver(failureObserver);
        shop.addSuccessObserver(sucessObserver);

        // Intializing the scheduled executor
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        // Initializing the day countt
        int dayCount = 1;

        // Initializing the message count
        int messageCount = 0;
        final int finalMessageCount;

        try {

            // Sending a message to the BikeShop to pay the Employee every 7 days
            executor.scheduleAtFixedRate(() -> shop.processMessage("E"), 7, 7, TimeUnit.SECONDS);

            while (System.in.available() == 0) {
                // ... ?

                // For illustration purposes -- this just prints out the messages as they come
                // in.

                // Printing daily stats and writing them to the file
                shop.notifySuccessObserver("---");
                shop.notifySuccessObserver("Day " + dayCount);
                dayCount++;
                shop.dailyStats();
                Utils.writeToFile(shop.getStats());

                String msg = inp.nextMessage();

                while (msg != null) {

                    // Checking if the message from the BikeShopInput file is valid
                    if (Utils.isValidMessage(msg) == true) {

                        // Processing the message
                        shop.processMessage(msg);
                    } else {

                        // Notifying the failure observer if the message is invalid
                        shop.notifyFailureObserver("Invalid message " + msg);
                    }
                    messageCount++;

                    // Getting the next message
                    msg = inp.nextMessage();
                }
                System.out.println("\n");

                // Wait 1 second
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new AssertionError(e);
                }

            }

            // Printing out the total number of input messages and the total number of
            // failure messages
            finalMessageCount = messageCount;
            logger.info(() -> "Total number of input messages: " + finalMessageCount);
            logger.info(() -> "Total number of failure messages: " + shop.getFailureCount());
            System.out.println("Total number of input messages: " + finalMessageCount);
            System.out.println("Total number of failure messages: " + shop.getFailureCount());

            // Removing all the observers
            logger.info("Removing all the observers");
            shop.removerObservers();

        } catch (IOException e) {
            System.out.println("Error reading user input");
        }

    }

}
