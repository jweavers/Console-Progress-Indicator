/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    ProgressIndicator(String msg, char echo, int repeat) {
        this.echochar = echo;
        this.repeat = repeat;
        this.message = msg;
    }

    ProgressIndicator(char echo, int repeat) {
        this("", echo, repeat);
    }

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
                    System.out.print(echochar + " ");
                    counter++;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProgressIndicator.class.getName()).log(Level.SEVERE, null, ex);
                }

                while (counter > 0) {

                    System.out.print("\010 \010");
                    System.out.print("\010 \010");
                    counter--;
                }
            }
        } finally {
            cleanUp();
        }
    }

    public void stopIndicator() {
        stopIndicator = true;
    }

    private void cleanUp() {
        int counter = 0;
        int len = message == null ? 0 : message.length();
        while (counter < ((repeat * 2) + len)) {
            System.out.print("\010 \010");
            counter++;
        }

    }
}
