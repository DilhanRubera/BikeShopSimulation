package edu.curtin.oose2024s1.assignment2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

import edu.curtin.oose2024s1.assignment2.observer.MessageObserver;
import edu.curtin.oose2024s1.assignment2.states.bike_purchase.BikePurchaseState;
import edu.curtin.oose2024s1.assignment2.states.bike_purchase.PurchasableState;
import edu.curtin.oose2024s1.assignment2.states.delivery.AcceptingDeliveryState;
import edu.curtin.oose2024s1.assignment2.states.delivery.DeliveryState;
import edu.curtin.oose2024s1.assignment2.states.servicing.AcceptingServicingState;
import edu.curtin.oose2024s1.assignment2.states.servicing.ServicingState;

public class BikeShop {

    private int cash; // Cash in the bank
    private int totalBikes; // Total bikes in the shop
    private int purchasableBikes; // Bikes available for purchase
    private int servicingBikes; // Bikes being serviced
    private int soldBikes; // Bikes sold
    private int servicedBikes; // Bikes serviced

    private List<String> onlinePurchasedBikeEmails; // List of emails of bikes purchased online
    private Map<String, LocalDateTime> servicedBikeEmails; // Map of emails of bikes serviced and the time they were
                                                           // brought for servicing

    private List<MessageObserver> taskObservers = new ArrayList<>(); // List of observers
    private List<MessageObserver> failureObservers = new ArrayList<>(); // List of failure observers
    private List<MessageObserver> successObservers = new ArrayList<>(); // List of success observers

    private DeliveryState deliveryState = new AcceptingDeliveryState(this); // Initializing the delivery state to an
    // accepting delivery state
    private BikePurchaseState purchasableState = new PurchasableState(this); // Initializing the bike purchase state of
    // bikes to a purchasable state
    private ServicingState servicingState = new AcceptingServicingState(this); // Initializing the servicing state of
                                                                               // bikes
    // to an accepting servicing state

    private int failureCount = 0; // Count of failures

    public BikeShop() {
        cash = 15000; // Initial cash in the bank is 15000
        purchasableBikes = 50; // Initial bikes available for purchase is 50
        servicingBikes = 0; // Initial bikes being serviced is 0
        soldBikes = 0; // Initial bikes sold is 0
        totalBikes = 50; // Initial total bikes in the shop is 50

        onlinePurchasedBikeEmails = new ArrayList<>(); // Initializing the list of emails of bikes purchased online
        servicedBikeEmails = new HashMap<>(); // Initializing the map of emails of bikes serviced and the time they were
                                              // brought for servicing
    }

    // Method to process the message
    public void processMessage(String msg) {
        notifyTaskObservers(msg); // Notifying the task observers when process message is called.
    }

    // Adding a task observer to the list of task observers
    public void addTaskObserver(MessageObserver observer) {
        taskObservers.add(observer);
    }

    // Notifying the task observers when a message is received
    public void notifyTaskObservers(String message) {
        for (MessageObserver taskObserver : taskObservers) {
            taskObserver.update(message);
        }
    }

    // Adding a failure observer to the list of failure observers
    public void addFailureObserver(MessageObserver observer) {
        failureObservers.add(observer);
    }

    // Notifying the failure observers when a failure has occured
    public void notifyFailureObserver(String message) {
        for (MessageObserver failureObserver : failureObservers) {
            failureObserver.update(message);
        }
    }

    // Adding a success observer to the list of success observers
    public void addSuccessObserver(MessageObserver observer) {
        successObservers.add(observer);
    }

    // Notifying the success observers when a task has been done successfully
    public void notifySuccessObserver(String message) {
        for (MessageObserver successObserver : successObservers) {
            successObserver.update(message);
        }
    }

    // Removing all observers from the list of observers
    public void removerObservers() {
        taskObservers.clear();
        failureObservers.clear();
        successObservers.clear();
    }

    // Setter method for the delivery state
    public void setDeliveryState(DeliveryState state) {
        deliveryState = state;
    }

    // Getter method for the delivery state
    public DeliveryState getDeliveryState() {
        return deliveryState;
    }

    // Setter method for the bike purchase state
    public void setPurchasableState(BikePurchaseState state) {
        purchasableState = state;
    }

    // Getter method for the bike purchase state
    public BikePurchaseState getPurchasableState() {
        return purchasableState;
    }

    // Setter method for the servicing state
    public void setServicingState(ServicingState state) {
        servicingState = state;
    }

    // Getter method for the servicing state
    public ServicingState getServicingState() {
        return servicingState;
    }

    // Getter method to get bikes being serviced
    public int getServicingBikes() {
        return servicingBikes;
    }

    // Getter method to get bikes available for purchase
    public int getPurchasableBikes() {
        return purchasableBikes;
    }

    // Getter method to get total bikes
    public int getTotalBikes() {
        return totalBikes;
    }

    // Setter method to set no of bikes being serviced
    public void setServicingBikes(int servicingBikes) {
        this.servicingBikes = servicingBikes;
    }

    // Setter method to set no of bikes available for purchase
    public void setPurchasableBikes(int purchasableBikes) {
        this.purchasableBikes = purchasableBikes;
    }

    // Setter method to set total no of bikes
    public void setTotalBikes(int totalBikes) {
        this.totalBikes = totalBikes;
    }

    // Getter method to get total no of bikes sold
    public int getTotalSoldBikes() {
        return soldBikes;
    }

    // Setter method to set total no of bikes sold
    public void setTotalSoldBikes(int soldBikes) {
        this.soldBikes = soldBikes;
    }

    // Getter method to get total no of bikes serviced
    public int getTotalServicedBikes() {
        return servicedBikes;
    }

    // Setter method to set total no of bikes serviced
    public void setTotalServicedBikes(int servicedBikes) {
        this.servicedBikes = servicedBikes;
    }

    // Getter method to get cash in the bank
    public int getCash() {
        return cash;
    }

    // Setter method to set cash in the bank
    public void setCash(int cash) {
        this.cash = cash;
    }

    // Setter method to add email of bikes purchased online
    public void setOnlinePurchasedBikeEmails(String email) {
        onlinePurchasedBikeEmails.add(email);
    }

    // Setter method to add email of bike that was dropped off for servicing
    public void setServicedBikeEmails(String email, LocalDateTime time) {
        servicedBikeEmails.put(email, time);
    }

    // Getter method to get list of emails of bikes purchased online
    public List<String> getOnlinePurchasedBikeEmails() {
        return onlinePurchasedBikeEmails;
    }

    // Getter method to get map of emails of bikes serviced and the time they were
    // brought for servicing
    public Map<String, LocalDateTime> getServicedBikeEmails() {
        return servicedBikeEmails;
    }

    // Setter method to set count of failures
    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }

    // Getter method to get count of failures
    public int getFailureCount() {
        return failureCount;
    }

    // Method to display the daily stats
    public void dailyStats() {
        System.out.println("Total cash in bank : " + getCash());
        System.out.println("Number of bikes available for purchase : " + getPurchasableBikes());
        System.out.println("Number of bikes being serviced : " + getServicingBikes());
        System.out.println(
                "Number of bikes awaiting pick-up : " + (getServicingBikes() + getOnlinePurchasedBikeEmails().size())
                        + "\n");

    }

    // Method to get the daily stats
    public String getStats() {

        String stats = "Total cash in bank : " + getCash() + "\n" + "Number of bikes available for purchase : "
                + getPurchasableBikes() + "\n" + "Number of bikes being serviced : " + getServicingBikes() + "\n"
                + "Number of bikes awaiting pick-up : " + (getServicingBikes() + getOnlinePurchasedBikeEmails().size())
                + "\n";

        return stats;
    }
}
