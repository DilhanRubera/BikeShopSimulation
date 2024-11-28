package edu.curtin.oose2024s1.assignment2.states.bike_purchase;

import edu.curtin.oose2024s1.assignment2.BikeShop;

// UnpurchasableState class implements BikePurchaseState
// Indicates there are no bikes available for purchase in the shop
public class UnpurchasableState implements BikePurchaseState {

    private BikeShop shop; // BikeShop object

    // Constructor for UnpurchasableState
    public UnpurchasableState(BikeShop pShop) {
        this.shop = pShop;
    }

    // purchase method
    @Override
    public void purchase(String message) {

        // Notify the failure observer
        shop.notifyFailureObserver("No bikes available for purchase.");

    }
}
