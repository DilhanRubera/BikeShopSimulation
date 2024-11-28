package edu.curtin.oose2024s1.assignment2.tasks;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.App;
import edu.curtin.oose2024s1.assignment2.BikeShop;
import edu.curtin.oose2024s1.assignment2.states.bike_purchase.PurchasableState;
import edu.curtin.oose2024s1.assignment2.states.bike_purchase.UnpurchasableState;
import edu.curtin.oose2024s1.assignment2.states.servicing.AcceptingServicingState;
import edu.curtin.oose2024s1.assignment2.states.servicing.RejectingServicingState;

// DeliveryTask class that implements Task interface
public class DeliveryTask implements Task {

    // Logger object
    private static final Logger logger = Logger.getLogger(App.class.getName());

    private BikeShop shop; // BikeShop object

    // Constructor for DeliveryTask
    public DeliveryTask(BikeShop pShop) {
        this.shop = pShop;
    }

    // doTask method for DeliveryTask
    @Override
    public void doTask() {

        // Call delivery method from DeliveryState
        shop.getDeliveryState().delivery();

        // Check if the shop has enough bikes to be purchasable and if the shop is in
        // UnpurchasableState and update the purchasable state
        updateUnpurchasableState();

        // Check if the shop has more than 100 bikes and if the shop is in
        // AcceptingServicingState and update the servicing state
        updateAcceptingServicingState();

    }

    // Method to update the PurchasableState if shop does not have enough
    // purchasable bikes
    private void updateUnpurchasableState() {
        if (shop.getPurchasableBikes() >= 1 && shop.getPurchasableState() instanceof UnpurchasableState) {
            shop.setPurchasableState(new PurchasableState(shop)); // Set the BikePurchaseState to PurchasableState
            logger.info("BikePurchaseState is now in PurchasableState");

        }
    }

    // Method to update the ServicingState if shop has more than 100 bikes
    private void updateAcceptingServicingState() {
        if (shop.getTotalBikes() >= 100 && shop.getServicingState() instanceof AcceptingServicingState) {
            shop.setServicingState(new RejectingServicingState(shop)); // Set the ServicingState to
                                                                       // RejectingServicingState
            logger.info("ServicingState is now in RejectingServicingState");
        }
    }

}
