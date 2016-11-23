/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbook.controllers;

import com.mycompany.addressbook.DAO.AddressBookDaoPats;
import com.mycompany.addressbook.DTO.Address;
import com.mycompany.addressbook.UI.ConsoleIO;

/**
 *
 * @author apprentice
 */
public class AddressController {

    private ConsoleIO io;
    private final AddressBookDaoPats addressDAO;

    public AddressController(ConsoleIO ioBean, AddressBookDaoPats addressDAOBean) {
        this.io = ioBean;
        this.addressDAO = addressDAOBean;
    }

    public void run() {
        boolean running = true;

        while (running) {
            String selection = io.getMultiChoiceString("\nWhat would you like to do with your address book?", new String[]{"Create a new address", "Delete an address", "See a count of your addresses", "List all addresses", "Find an address by last name", "Search by City", "Search by State", "Search by Zip code", "Edit an address", "Exit"});
            switch (selection) {
                case "Create a new address":
                    createAddress();
                    io.pause();
                    break;
                case "Delete an address":
                    deleteAddress();
                    io.pause();
                    break;
                case "See a count of your addresses":
                    countOfAddresses();
                    io.pause();
                    break;
                case "List all addresses":
                    listAllAddresses();
                    io.pause();
                    break;
                case "Find an address by last name":
                    findAddressByLastName();
                    io.pause();
                    break;
                case "Search by Zip code":
                    findAddressByZip();
                    io.pause();
                    break;
                case "Search by State":
                    findAddressByStateProvince();
                    io.pause();
                    break;
                case "Search by City":
                    findAddressByCity();
                    io.pause();
                    break;
                case "Edit an address":
                    editAddress();
                    break;
                case "Exit":
                    running = false;
                    break;
            }//end switch(selection)
        }//end while(running)
    }//end run()

    private void editAddress() {
        String firstName, lastName, streetNumber, streetName, city, state, zip, country, selection = "";
        Integer id;

        if (!io.getTrueFalse("Do you know the ID of the address you wish to edit?\n(yes or no): ")) {
            listAllAddresses();
        }

        id = Integer.parseInt(io.getUserInputString("What is the ID of the address you wish to delete: ", addressDAO.getKeys(), "Sorry, there is no ID that matches that..."));
        Address revisedAddress = addressDAO.get(id);

        while (!selection.equals("Return to menu")) {
            selection = io.getMultiChoiceString("Which portion of the address would you like to revise?", new String[]{"First Name", "Last Name", "Street Number", "Street Name", "City", "State", "Return to menu"});
            switch (selection) {
                case "First Name":
                    do {
                        firstName = io.getUserInputString("\tFirst name: ");
                    } while (isCorrect());
                    revisedAddress.setFirstName(firstName);
                    break;
                case "Last Name":
                    do {
                        lastName = io.getUserInputString("\tLastName: ");
                    } while (isCorrect());
                    revisedAddress.setLastName(lastName);
                    break;
                case "Street Number":
                    do {
                        streetNumber = io.getUserInputString("\tStreet Number: ");
                    } while (isCorrect());
                    revisedAddress.setStreetNumber(streetNumber);
                    break;
                case "Street Name":
                    do {
                        streetName = io.getUserInputString("\tStreet Name: ");
                    } while (isCorrect());
                    revisedAddress.setStreetNumber(streetName);
                    break;
                case "City":
                    do {
                        city = io.getUserInputString("\tCity: ");
                    } while (isCorrect());
                    revisedAddress.setCity(city);
                    break;
                case "State":
                    do {
                        state = io.getUserInputString("\tState: ");
                    } while (isCorrect());
                    revisedAddress.setState(state);
                    break;
                case "Zip":
                    do {
                        zip = io.getUserInputString("\tZip: ");
                    } while (isCorrect());
                    revisedAddress.setZip(zip);
                    break;
                case "Return to menu":
                    addressDAO.update(revisedAddress);
                    break;
            }
        }
    }

    private void findAddressByLastName() {
        String lastName;
        do {
            lastName = io.getUserInputString("What is the last name of the address you wish to find: ");
        } while (isCorrect());

        for (Address current : addressDAO.searchByLastName(lastName)) {
            listAddress(current);
        }

        if (addressDAO.searchByLastName(lastName).isEmpty()) {
            io.println("Sorry, no addresses were found...");
        }
    }

    private void listAllAddresses() {
        if (addressDAO.list().size() > 0) {
            for (Address currentAddress : addressDAO.list()) {
                listAddress(currentAddress);
            }
        } else {
            io.println("Sorry, no addresses were found...");
        }
    }//end listAllAddresses

    private void countOfAddresses() {
        io.println("There are currently " + addressDAO.list().size() + " addresses in your address book");
    }

    private void deleteAddress() {
        if (!io.getTrueFalse("Do you know the ID of the address you wish to delete?\n(yes or no): ")) {
            io.println("Here is a list of all the addresses and their corresponding ID's:");
            listAllAddresses();
        }
        addressDAO.delete(Integer.parseInt(io.getUserInputString("What is the ID of the address you wish to delete: ", addressDAO.getKeys(), "Sorry, there is no ID that matches that...")));
    }

    private void createAddress() {
        String firstName, lastName, streetNumber, streetName, city, state, zip, country;
        io.println("New Address:");

        do {
            firstName = io.getUserInputString("\tFirst name: ");
        } while (isCorrect());
        do {
            lastName = io.getUserInputString("\tLastName: ");
        } while (isCorrect());
        do {
            streetNumber = io.getUserInputString("\tStreet Number: ");
        } while (isCorrect());
        do {
            streetName = io.getUserInputString("\tStreet Name: ");
        } while (isCorrect());
        do {
            city = io.getUserInputString("\tCity: ");
        } while (isCorrect());
        do {
            state = io.getUserInputString("\tState: ");
        } while (isCorrect());
        do {
            zip = io.getUserInputString("\tZip: ");
        } while (isCorrect());

        Address newAddress = new Address();

        newAddress.setFirstName(firstName);
        newAddress.setLastName(lastName);
        newAddress.setStreetNumber(streetNumber);
        newAddress.setStreetName(streetName);
        newAddress.setCity(city);
        newAddress.setState(state);
        newAddress.setZip(zip);
        newAddress = addressDAO.create(newAddress);

        listAddress(newAddress);
    }

    private void listAddress(Address currentAddress) {
        io.println("\nName: " + currentAddress.getFirstName() + " " + currentAddress.getLastName() + " - ID: " + currentAddress.getId());
        io.println("\tStreet Number: \t" + currentAddress.getStreetNumber());
        io.println("\tStreet Name: \t" + currentAddress.getStreetName());
        io.println("\tCity: \t\t" + currentAddress.getCity());
        io.println("\tState: \t\t" + currentAddress.getState());
        io.println("\tZip: \t\t" + currentAddress.getZip());
    }//end listAddress()

    private boolean isCorrect() {
        return !io.getTrueFalse("Is this correct? (yes or no): ");
    }//end isCorrect()

    private void findAddressByZip() {
        String zip;
        do {
            zip = io.getUserInputString("What is the zip of the address you wish to find: ");
                    
        } while (isCorrect());

        for (Address current : addressDAO.searchByZip(zip)) {
            listAddress(current);
        }

        if (addressDAO.searchByZip(zip).isEmpty()) {
            io.println("Sorry, no addresses were found...");
        }
    }

    private void findAddressByStateProvince() {
        String state;
        do {
            state = io.getUserInputString("What is the state/province of the address you wish to find: ");
        } while (isCorrect());

        for (Address current : addressDAO.searchByState(state)) {
            listAddress(current);
        }

        if (addressDAO.searchByState(state).isEmpty()) {
            io.println("Sorry, no addresses were found...");
        }
    }

    private void findAddressByCity() {
        String city;
        do {
            city = io.getUserInputString("What is the city of the address you wish to find: ");
        } while (isCorrect());

        for (Address current : addressDAO.searchByCity(city)) {
            listAddress(current);
        }

        if (addressDAO.searchByCity(city).isEmpty()) {
            io.println("Sorry, no addresses were found...");
        }
    }
}//FIN
