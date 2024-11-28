package edu.curtin.oose2024s1.assignment2.observer;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.App;
import edu.curtin.oose2024s1.assignment2.utils.Utils;

// SucessObserver class that implements MessageObserver interface
public class SuccessObserver implements MessageObserver {

    // Logger object
    private static final Logger logger = Logger.getLogger(App.class.getName());

    // update method for SucessObserver
    @Override
    public void update(String message) {

        // Print the success message and write it to a file
        System.out.println(message);
        Utils.writeToFile(message);
        logger.info(message); // Log the success message
    }

}
