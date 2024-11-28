package edu.curtin.oose2024s1.assignment2.states.delivery;

import edu.curtin.oose2024s1.assignment2.BikeShop;

// RejectingDeliveryState class implements DeliveryState
// Indicates the shop is rejecting deliveries
public class RejectingDeliveryState implements DeliveryState {

    private BikeShop shop; // BikeShop object

    // Constructor for RejectingDeliveryState
    public RejectingDeliveryState(BikeShop pShop) {
        this.shop = pShop;
    }

    // delivery method
    @Override
    public void delivery() {
        // Notify the failure observer that the delivery was rejected
        shop.notifyFailureObserver("Delivery rejected. Not enough cash or space.");
    }
}
