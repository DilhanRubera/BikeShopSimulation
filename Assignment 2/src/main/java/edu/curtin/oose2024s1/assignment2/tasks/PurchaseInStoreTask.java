package edu.curtin.oose2024s1.assignment2.tasks;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.App;
import edu.curtin.oose2024s1.assignment2.BikeShop;
import edu.curtin.oose2024s1.assignment2.states.delivery.AcceptingDeliveryState;
import edu.curtin.oose2024s1.assignment2.states.delivery.RejectingDeliveryState;
import edu.curtin.oose2024s1.assignment2.states.servicing.AcceptingServicingState;
import edu.curtin.oose2024s1.assignment2.states.servicing.RejectingServicingState;

// PurchaseInStoreTask class that implements Task interface
public class PurchaseInStoreTask implements Task {

    // Logger object
    private static final Logger logger = Logger.getLogger(App.class.getName());

    private BikeShop shop; // BikeShop object

    // Constructor for PurchaseInStoreTask
    public PurchaseInStoreTask(BikeShop pShop) {
        this.shop = pShop;
    }

    // doTask method for PurchaseInStoreTask
    @Override
    public void doTask() {

        // Call purchase method from PurchasableState
        shop.getPurchasableState().purchase("IN");

        // Check if the shop has enough cash, bikes and if the DeliveryState is in
        // RejectingDeliveryState and update the delivery state
        updateRejectingDeliveryState();

        // Check if the shop has less than 100 bikes and if the ServicingState is in
        // RejectingServicingState and update the servicing state
        updateRejectingServicingState();

    }

    // Method to update the DeliveryState if shop has enough cash and bikes
    private void updateRejectingDeliveryState() {
        if (shop.getCash() >= 10000 && shop.getTotalBikes() <= 90
                && shop.getDeliveryState() instanceof RejectingDeliveryState) {
            shop.setDeliveryState(new AcceptingDeliveryState(shop)); // Set the DeliveryState to AcceptingDeliveryState
            logger.info("DeliveryState is now in AcceptingDeliveryState");
        }
    }

    // Method to update the ServicingState if shop has less than 100 bikes
    private void updateRejectingServicingState() {
        if (shop.getTotalBikes() <= 99 && shop.getServicingState() instanceof RejectingServicingState) {
            shop.setServicingState(new AcceptingServicingState(shop)); // Set the ServicingState to
                                                                       // AcceptingServicingState
            logger.info("ServicingState is now in AcceptingServicingState");
        }
    }

}
