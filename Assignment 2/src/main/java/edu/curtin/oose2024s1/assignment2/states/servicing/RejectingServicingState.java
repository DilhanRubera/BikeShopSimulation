package edu.curtin.oose2024s1.assignment2.states.servicing;

import edu.curtin.oose2024s1.assignment2.BikeShop;

// RejectingServicingState class implements ServicingState
// Indicates the shop is rejecting bike servicing
public class RejectingServicingState implements ServicingState {

    private BikeShop shop; // BikeShop object

    // Constructor for RejectingServicingState
    public RejectingServicingState(BikeShop pShop) {
        this.shop = pShop;
    }

    // service method
    @Override
    public void service(String message) {
        // Notify the failure observer that the servicing was rejected
        shop.notifyFailureObserver("Servicing rejected for " + message + " due to full capacity.");

    }

}
