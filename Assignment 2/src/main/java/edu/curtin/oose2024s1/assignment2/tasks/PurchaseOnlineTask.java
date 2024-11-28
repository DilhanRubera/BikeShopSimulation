package edu.curtin.oose2024s1.assignment2.tasks;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.App;
import edu.curtin.oose2024s1.assignment2.BikeShop;
import edu.curtin.oose2024s1.assignment2.states.delivery.AcceptingDeliveryState;
import edu.curtin.oose2024s1.assignment2.states.delivery.RejectingDeliveryState;

// PurchaseOnlineTask class that implements Task interface
public class PurchaseOnlineTask implements Task {

    private String email; // Email of the customer that purchased the bike online

    private BikeShop shop; // BikeShop object

    // Constructor for PurchaseOnlineTask
    public PurchaseOnlineTask(BikeShop pShop, String email) {
        this.shop = pShop;
        this.email = email;
    }

    // Logger object
    private static final Logger logger = Logger.getLogger(App.class.getName());

    @Override
    public void doTask() {

        // Call purchase method from PurchasableState
        shop.getPurchasableState().purchase(getEmail());

        // Check if the shop has enough cash, bikes and if the DeliveryState is in
        // RejectingDeliveryState and update the delivery state
        updateRejectingDeliveryState();

    }

    public String getEmail() {
        return email;
    }

    // Method to update the DeliveryState if shop has enough cash and bikes
    private void updateRejectingDeliveryState() {
        if (shop.getCash() >= 10000 && shop.getTotalBikes() <= 90
                && shop.getDeliveryState() instanceof RejectingDeliveryState) {

            shop.setDeliveryState(new AcceptingDeliveryState(shop)); // Set the DeliveryState to AcceptingDeliveryState
            logger.info("DeliveryState is now in AcceptingDeliveryState");
        }
    }
}
