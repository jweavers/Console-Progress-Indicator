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
public class Test {

    public static void main(String[] args) {
        ProgressIndicator progress = new ProgressIndicator("In Progress ", '*', 5);
        new Thread() {
            public void run() {
                progress.printProgress();
            }
        }.start();
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                }
                progress.stopIndicator();
            }
        }.start();
    }
}
