package edu.curtin.oose2024s1.assignment2.states.servicing;

// ServicingState interface
// This interface is used to implement the state pattern for the servicing state
// An AcceptingServicingState indicates the shop is accepting bike servicing
// A RejectingServicingState indicates the shop is rejecting bike servicing
public interface ServicingState {

    // service method
    public void service(String message);

}
