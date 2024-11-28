package edu.curtin.oose2024s1.assignment2.tasks;

import edu.curtin.oose2024s1.assignment2.BikeShop;

// DropOffTask class that implements Task interface
public class DropOffTask implements Task {
    private String email; // Email of the customer that dropped off the bike

    private BikeShop shop; // BikeShop object

    // Constructor for DropOffTask
    public DropOffTask(BikeShop pShop, String email) {
        this.shop = pShop;
        this.email = email;
    }

    // doTask method for DropOffTask
    @Override
    public void doTask() {

        // Call service method from ServicingState
        shop.getServicingState().service(getEmail());

    }

    // getEmail method
    public String getEmail() {
        return email;
    }
}
