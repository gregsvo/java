package com.mycompany.app;

import com.mycompany.controller.UserController;

/**
 *
 * @author apprentice
 */
public class App {
    public static void main(String[] args) {
        UserController user = new UserController();
        user.run();
    }
    
}
