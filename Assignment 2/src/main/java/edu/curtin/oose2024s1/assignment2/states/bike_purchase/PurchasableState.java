package edu.curtin.oose2024s1.assignment2.states.bike_purchase;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.App;
import edu.curtin.oose2024s1.assignment2.BikeShop;

// PurchaseableState class implements BikePurchaseState
// Indicates that there are bikes available for purchase in the shop
public class PurchasableState implements BikePurchaseState {

    // Logger object
    private static final Logger logger = Logger.getLogger(App.class.getName());

    private BikeShop shop; // BikeShop object

    // Constructor for PurchasableState
    public PurchasableState(BikeShop pShop) {
        this.shop = pShop;
    }

    // purchase method
    @Override
    public void purchase(String message) {

        // If there are bikes available for purchase
        if (shop.getPurchasableBikes() >= 1) {
            shop.setPurchasableBikes(shop.getPurchasableBikes() - 1); // Decrement the number of purchasable bikes
            shop.setCash(shop.getCash() + 1000); // Add $1000 to the shop's cash
            shop.setTotalSoldBikes(shop.getTotalSoldBikes() + 1); // Increment the total number of sold bikes

            // Check if the bike was purchased in store or online
            if (message.equals("IN")) {
                shop.notifySuccessObserver("Bike purchased in store."); // Notify the observer that the bike was
                                                                        // purchased in store
                shop.setTotalBikes(shop.getTotalBikes() - 1); // Decrement the total number of bikes in the shop right
                                                              // now

            } else {
                shop.setOnlinePurchasedBikeEmails(message); // Add the email of the bike purchased online
                shop.notifySuccessObserver("Bike purchased online by " + message + "."); // Notify the observer that the
                                                                                         // bike was purchased online
            }
        }

        // Check if there are no bikes available for purchase and update the state
        updatePurchasbleState();

    }

    // Method to update the PurchasableState if shop does not have enough
    // purchasable bikes
    private void updatePurchasbleState() {
        if (shop.getPurchasableBikes() < 1) {
            shop.setPurchasableState(new UnpurchasableState(shop)); // Set the BikePurchaseState to UnpurchasableState
            logger.info("BikePurchaseState is now in UnpurchasableState");
        }

    }
}
