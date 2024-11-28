package edu.curtin.oose2024s1.assignment2.observer;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.App;
import edu.curtin.oose2024s1.assignment2.BikeShop;
import edu.curtin.oose2024s1.assignment2.utils.Utils;

// FailureObserver class that implements MessageObserver interface
public class FailureObserver implements MessageObserver {

    // Logger object
    private static final Logger logger = Logger.getLogger(App.class.getName());

    private BikeShop shop; // BikeShop object

    public FailureObserver(BikeShop pShop) {
        this.shop = pShop;
    }

    // update method for FailureObserver
    @Override
    public void update(String message) {

        // Print the failure message and write it to a file
        System.out.println("FAILURE- " + message);
        Utils.writeToFile("FAILURE- " + message);
        logger.info(() -> "FAILURE- " + message); // Log the failure message

        shop.setFailureCount(shop.getFailureCount() + 1); // Increment the failure count
        logger.info(() -> "Failure count is now " + shop.getFailureCount());
    }

}
