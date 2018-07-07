package com.progress;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ravi
 */
public class ProgressIndicator {

    private final char echochar;
    private final int repeat;
    private final String message;
    private volatile boolean stopIndicator = false;

    /**
     * Construct ProgressIndicator.
     * @param msg Message to display with progress indicator
     * @param echo character will be displayed as progress
     * @param repeat how many times will be repeated as progress. 
     *               It should be greater than 1.
     */
    ProgressIndicator(String msg, char echo, int repeat) {
        this.echochar = echo;
        this.repeat = repeat;
        this.message = msg;
    }

    /**
     * Construct ProgressIndicator.
     * @param echo character will be displayed as progress
     * @param repeat how many times will be repeated as progress. 
     *               It should be greater than 1.
     */
    ProgressIndicator(char echo, int repeat) {
        this("", echo, repeat);
    }

    /**
     * Start progress indicator.
     */
    public void printProgress() {
        int counter = 0;

        System.out.print(message);

        try {
            while (!stopIndicator) {
                while (counter < repeat) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ProgressIndicator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //echo character
                    System.out.print(echochar + " ");
                    counter++;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProgressIndicator.class.getName()).log(Level.SEVERE, null, ex);
                }

                while (counter > 0) {
                    //clean up
                    System.out.print("\010 \010");
                    System.out.print("\010 \010");
                    counter--;
                }
            }
        } finally {
            cleanUp();
        }
    }

    /**
     * Stop progress indicator.
     */
    public void stopIndicator() {
        stopIndicator = true;
    }

    /**
     * Cleanup everything at the end.
     */
    private void cleanUp() {
        int counter = 0;
        int len = message == null ? 0 : message.length();
        while (counter < ((repeat * 2) + len)) {
            System.out.print("\010 \010");
            counter++;
        }

    }
}
