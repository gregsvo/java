/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app;

import com.mycompany.controller.User_Controller;


/**
 *
 * @author apprentice
 */
public class App {
    public static void main(String[] args) {
        User_Controller contr = new User_Controller();
        contr.run();
    }
}
