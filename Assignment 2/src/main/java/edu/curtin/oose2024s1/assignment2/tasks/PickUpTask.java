package edu.curtin.oose2024s1.assignment2.tasks;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.App;
import edu.curtin.oose2024s1.assignment2.BikeShop;
import edu.curtin.oose2024s1.assignment2.states.delivery.AcceptingDeliveryState;
import edu.curtin.oose2024s1.assignment2.states.delivery.RejectingDeliveryState;
import edu.curtin.oose2024s1.assignment2.states.servicing.AcceptingServicingState;
import edu.curtin.oose2024s1.assignment2.states.servicing.RejectingServicingState;

public class PickUpTask implements Task {

    private String email; // Email of the customer that wants to pickup the bike

    private BikeShop shop; // BikeShop object
    // Logger object
    private static final Logger logger = Logger.getLogger(App.class.getName());

    // Constructor for PickUpTask
    public PickUpTask(BikeShop pShop, String email) {
        this.shop = pShop;
        this.email = email;
    }

    // doTask method for PickUpTask
    @Override
    public void doTask() {

        // Check if the customer has dropped off the bike
        if (shop.getServicedBikeEmails().containsKey(getEmail())) {
            pickUpServicedBike(); // Call pickUpServicedBike method
        }

        // Check if the customer has purchased the bike online
        else if (shop.getOnlinePurchasedBikeEmails().contains(getEmail())) {
            pickUpOnlinePurchasedBike(); // Call pickUpOnlinePurchasedBike method
        }

        // If the customer has not dropped off or purchased a bike
        else {
            // Notify the failure observer that the person has not dropped off or purchased
            // a bike
            shop.notifyFailureObserver("This person has not dropped off or purchased a bike. " + getEmail());
        }

        // Check if the shop has enough cash, bikes and if the DeliveryState is in
        // RejectingDeliveryState and update the delivery state
        updateRejectingDeliveryState();

        // Check if the shop has less than 100 bikes and if the ServicingState is in
        // RejectingServicingState and update the servicing state
        updateRejectingServicingState();

    }

    // getEmail method
    public String getEmail() {
        return email;
    }

    // pickUpServicedBike method that checks if the bike has been dropped off two
    // days ago and collects the cash and updates the shop
    private void pickUpServicedBike() {

        // Check if two days have passed since the bike was dropped off
        if (shop.getServicedBikeEmails().get(getEmail()).isBefore(LocalDateTime.now().minusSeconds(2))) {
            shop.getServicedBikeEmails().remove(getEmail()); // Remove the email from the servicedBikeEmails
            shop.setCash(shop.getCash() + 100); // Collect service fee
            shop.setServicingBikes(shop.getServicingBikes() - 1); // Decrement the number servicing bikes
            shop.setTotalBikes(shop.getTotalBikes() - 1); // Decrement the total number of bikes in the shop
            shop.setTotalServicedBikes(shop.getTotalServicedBikes() + 1); // Increment the total number of serviced
                                                                          // bikes
            shop.notifySuccessObserver("Serviced bike has been picked up by " + getEmail()); // Notify the success
                                                                                             // observer that the bike
                                                                                             // has been picked up
        } else {

            // Notify the failure observer that the bike is not serviced yet
            shop.notifyFailureObserver("Bike is not serviced yet. Please pick it up in 2 days");

        }
    }

    // pickUpOnlinePurchasedBike method that removes the email from the
    // onlinePurchasedBikeEmails and updates the shop
    private void pickUpOnlinePurchasedBike() {
        shop.getOnlinePurchasedBikeEmails().remove(getEmail()); // Remove the email from the onlinePurchasedBikeEmails
        shop.setTotalBikes(shop.getTotalBikes() - 1); // Decrement the total number of bikes in the shop
        shop.notifySuccessObserver("Bike purchased online has been picked up by " + getEmail()); // Notify the success
                                                                                                 // observer that the
                                                                                                 // bike has been picked
                                                                                                 // up
    }

    // Method to update the DeliveryState if shop has enough cash and bikes
    private void updateRejectingDeliveryState() {

        if (shop.getCash() >= 10000 && shop.getTotalBikes() <= 90
                && shop.getDeliveryState() instanceof RejectingDeliveryState) {
            shop.setDeliveryState(new AcceptingDeliveryState(shop)); // Set the DeliveryStateto AcceptingDeliveryState
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
