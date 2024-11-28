package edu.curtin.oose2024s1.assignment2.states.bike_purchase;

// BikePurchaseState interface
// This interface is used to implement the state pattern for the bike purchase state
// A PurchaseableState indicates their are bikes available for purchase
// An UnpurchaseableState indicates their are no bikes available for purchase
public interface BikePurchaseState {
    void purchase(String message);
}
