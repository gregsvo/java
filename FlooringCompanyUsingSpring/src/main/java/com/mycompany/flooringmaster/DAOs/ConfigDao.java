/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.DAOs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public final class ConfigDao {

    private final String FILENAME = "config.txt";
    private boolean testMode;
    private boolean lockMode = false;
    EncodeDecode ed = new EncodeDecode();

    public ConfigDao(boolean mode) {
        this.testMode = mode;
        //ed.decode();
    }

    public void updateTestMode() {
        if (!lockMode) {
            testMode = !testMode;
        }
        //ed.encode();
    }

    public boolean getTestMode() {
        return testMode;
    }

    public void setTestMode(boolean testMode) {
        if (!lockMode) {
            this.testMode = testMode;
        }
    }

    /**
     * @param lockMode the lockMode to set
     */
    public void setLockMode(boolean lockMode) {
        this.lockMode = lockMode;
    }

    private class EncodeDecode implements FileIO {

        @Override
        public void encode() {
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(FILENAME));

                pw.print(testMode ? "test" : "prod");
                pw.flush();

                pw.close();
            } catch (IOException ex) {
                Logger.getLogger(ConfigDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public List decode() {
            try {
                //create a Scanner object that can read the file saved as an attribute
                Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));
                //while the scanner has a line
                while (sc.hasNextLine()) {
                    //store the next line as a string
                    String currentLine = sc.nextLine();

                    if (currentLine.equalsIgnoreCase("test")) {
                        testMode = true;
                    } else if (currentLine.equalsIgnoreCase("production") || currentLine.equalsIgnoreCase("prod")) {
                        testMode = false;
                    }
                }
            } catch (FileNotFoundException ex) {
            }
            return null;
        }
    }
}
