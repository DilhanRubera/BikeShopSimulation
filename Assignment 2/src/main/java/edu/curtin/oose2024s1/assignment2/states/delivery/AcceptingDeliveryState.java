package edu.curtin.oose2024s1.assignment2.states.delivery;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.App;
import edu.curtin.oose2024s1.assignment2.BikeShop;

// AcceptingDeliveryState class implements DeliveryState interface
// This class is used to implement the state pattern for the delivery state
// An AcceptingDeliveryState indicates the shop is accepting deliveries
public class AcceptingDeliveryState implements DeliveryState {

    // Logger object
    private static final Logger logger = Logger.getLogger(App.class.getName());

    private BikeShop shop; // BikeShop object

    // Constructor for AcceptingDeliveryState
    public AcceptingDeliveryState(BikeShop pShop) {
        this.shop = pShop;
    }

    // delivery method
    @Override
    public void delivery() {

        // If the total bikes in the shop is less than or equal to 90 and the cash in
        // the shop is greater than or equal to 10000
        if (shop.getTotalBikes() <= 90 && shop.getCash() >= 10000) {
            shop.setPurchasableBikes(shop.getPurchasableBikes() + 10); // Add 10 purchasable bikes
            shop.setCash(shop.getCash() - 5000); // Subtract 5000 from the cash to pay for the delivery
            shop.setTotalBikes(shop.getTotalBikes() + 10); // Add 10 bikes to the total bikes in the shop
            shop.notifySuccessObserver("Delivery made. Total bikes in shop : " + shop.getTotalBikes()); // Notify the
                                                                                                        // success
                                                                                                        // observer that
                                                                                                        // the delivery
                                                                                                        // was made
        }

        // Updating the delivery state if the total bikes in the shop is greater than 90
        // or the cash in the shop is
        // less than 10000
        updateAcceptingDeliveryState();

    }

    // Method to update the DeliveryState if shop has more than 90 bikes and cash
    // less than 10000
    private void updateAcceptingDeliveryState() {
        if (shop.getTotalBikes() > 90 || shop.getCash() < 10000) {
            shop.setDeliveryState(new RejectingDeliveryState(shop)); // Set the DeliveryState to RejectingDeliveryState
            logger.info("DeliveryState is now in RejectingDeliveryState");
        }
    }
}
