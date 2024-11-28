package edu.curtin.oose2024s1.assignment2.observer;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.App;
import edu.curtin.oose2024s1.assignment2.BikeShop;
import edu.curtin.oose2024s1.assignment2.factory.TaskFactory;
import edu.curtin.oose2024s1.assignment2.tasks.Task;

// TaskObserver class that implements MessageObserver interface
public class TaskObserver implements MessageObserver {

    // Logger object
    private static final Logger logger = Logger.getLogger(App.class.getName());

    private BikeShop shop; // BikeShop object

    public TaskObserver(BikeShop pShop) {
        this.shop = pShop;
    }

    // update method for TaskObserver
    @Override
    public void update(String message) {
        Task task;

        // Split the message into parts
        String[] parts = message.split("[-\\s]+");

        // If the message is not an Employee message then create a task and do it
        if (!parts[0].equals("E")) {

            task = TaskFactory.createTask(message, shop);// Create a task based on the message in the TaskFactory
            logger.info(() -> "Task created: " + message);
            task.doTask(); // Do the task

        }
    }

}
