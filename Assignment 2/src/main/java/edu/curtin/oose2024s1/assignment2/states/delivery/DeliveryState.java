package edu.curtin.oose2024s1.assignment2.states.delivery;

// DeliveryState interface
// This interface is used to implement the state pattern for the delivery state
// An AcceptingDeliveryState indicates the shop is accepting deliveries
// A RejectingDeliveryState indicates the shop is rejecting deliveries
public interface DeliveryState {

    // delivery method
    void delivery();
}
