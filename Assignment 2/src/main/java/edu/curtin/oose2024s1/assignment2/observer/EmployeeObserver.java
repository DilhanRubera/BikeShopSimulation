package edu.curtin.oose2024s1.assignment2.observer;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.App;
import edu.curtin.oose2024s1.assignment2.BikeShop;
import edu.curtin.oose2024s1.assignment2.states.delivery.AcceptingDeliveryState;
import edu.curtin.oose2024s1.assignment2.states.delivery.RejectingDeliveryState;

// EmployeeObserver class that implements MessageObserver interface
public class EmployeeObserver implements MessageObserver {

    // Logger object
    private static final Logger logger = Logger.getLogger(App.class.getName());

    private BikeShop shop; // BikeShop object

    public EmployeeObserver(BikeShop pShop) {
        this.shop = pShop;
    }

    // update method for EmployeeObserver
    @Override
    public void update(String message) {

        // Split the message into parts
        String[] parts = message.split("[-\\s]+");

        // Check if the first part of the message is "E"
        if (parts[0].equals("E")) {
            shop.setCash(shop.getCash() - 1000); // Deduct 1000 from the shop's cash and pay the employee
            shop.notifySuccessObserver("Employee got paid"); // Notify the success observer that the employee got paid
        }

        // Check if the shop has less than 10000 cash and if the DeliveryState is in
        // AcceptingDeliveryState
        updateAcceptingDeliveryState();

    }

    // Method to update the DeliveryState if shop has less than 10000 cash
    private void updateAcceptingDeliveryState() {
        if (shop.getCash() < 10000 && shop.getDeliveryState() instanceof AcceptingDeliveryState) {
            shop.setDeliveryState(new RejectingDeliveryState(shop)); // Set the DeliveryState to RejectingDeliveryState
            logger.info("DeliveryState is now in RejectingDeliveryState");
        }
    }
}
