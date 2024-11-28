package edu.curtin.oose2024s1.assignment2.states.servicing;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.App;
import edu.curtin.oose2024s1.assignment2.BikeShop;

// AcceptingServicingState class implements ServicingState
// Indicates the shop is accepting bike servicing
public class AcceptingServicingState implements ServicingState {

    // Logger object
    private static final Logger logger = Logger.getLogger(App.class.getName());

    private BikeShop shop; // BikeShop object

    public AcceptingServicingState(BikeShop pShop) {
        this.shop = pShop;
    }

    // service method
    @Override
    public void service(String message) {

        // If the total bikes in the shop is less than 100
        if (shop.getTotalBikes() <= 99) {
            shop.setServicingBikes(shop.getServicingBikes() + 1); // Increment the number of bikes being serviced
            shop.setTotalBikes(shop.getTotalBikes() + 1); // Increment the total number of bikes in the shop
            shop.setServicedBikeEmails(message, LocalDateTime.now()); // Add the email of the bike being serviced and
                                                                      // the
                                                                      // time it was brought for service
            shop.notifySuccessObserver("Servicing accepted for bike owned by: " + message); // Notify the observer that
                                                                                            // the bike was accepted for
                                                                                            // servicing
        }

        // update servicing state if the total bikes in the shop is greater than 99
        updateAcceptingServicingState();

    }

    // Method to update the ServicingState if shop has more than 99 bikes
    private void updateAcceptingServicingState() {
        if (shop.getTotalBikes() > 99) {
            shop.setServicingState(new RejectingServicingState(shop)); // Set the ServicingState to
                                                                       // RejectingServicingState
            logger.info("ServicingState is now in RejectingServicingState");
        }
    }
}
