package edu.curtin.oose2024s1.assignment2.factory;

import edu.curtin.oose2024s1.assignment2.BikeShop;
import edu.curtin.oose2024s1.assignment2.tasks.DeliveryTask;
import edu.curtin.oose2024s1.assignment2.tasks.DropOffTask;
import edu.curtin.oose2024s1.assignment2.tasks.PickUpTask;
import edu.curtin.oose2024s1.assignment2.tasks.PurchaseInStoreTask;
import edu.curtin.oose2024s1.assignment2.tasks.PurchaseOnlineTask;
import edu.curtin.oose2024s1.assignment2.tasks.Task;

// TaskFactory class that create Task objects and return them
public class TaskFactory {

    // createTask method that creates Task objects based on the message
    public static Task createTask(String message, BikeShop shop) {

        Task task = null;

        // Split the message into parts
        String[] parts = message.split("[-\\s]+");

        // Check if the message indicates a DELIVERY task
        if (parts[0].equals("DELIVERY")) {
            task = new DeliveryTask(shop); // Create DeliveryTask object
        }

        // Check if the message indicates a DROP OFF task
        else if (parts[0].equals("DROP")) {
            task = new DropOffTask(shop, parts[2]); // Create DropOffTask object with email
        }

        // Check if the message indicates a PICK UP task
        else if (parts[0].equals("PICK")) {
            task = new PickUpTask(shop, parts[2]); // Create PickUpTask object with email
        }

        // Check if the message indicates a PURCHASE IN STORE task
        else if (parts[0].equals("PURCHASE") && parts[1].equals("IN")) {
            task = new PurchaseInStoreTask(shop); // Create PurchaseInStoreTask object
        }

        // Check if the message indicates a PURCHASE ONLINE task
        else if (parts[0].equals("PURCHASE") && parts[1].equals("ONLINE")) {
            task = new PurchaseOnlineTask(shop, parts[2]); // Create PurchaseOnlineTask object with email
        }

        return task; // Return the Task object
    }

}
