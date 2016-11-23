package Controller;

import DAO.AddressDAO;

import DAO.AddressDAOLambdaImpl;
import DTO.Address;
import UI.ConsoleIO;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    ConsoleIO io = new ConsoleIO();
    AddressDAO dao = new AddressDAOLambdaImpl();

    //Start AddressBook Application
    public void run() {
        String newLine = System.getProperty("line.separator");
        boolean runAgain = true;


        String error = " Please enter a valid menu choice. ";
        String menu = "" + newLine + " [1] Add Contact "
                + newLine + " [2] Remove "
                + newLine + " [3] Display All "
                + newLine + " [4] Search By Last Name "
                + newLine + " [5] Search by City "
                + newLine + " [6] Search by State "
                + newLine + " [7] Search By Zip "
                + newLine + " [8] Quit "
                + newLine;
        int menuSelection = 0;

        while (runAgain) {
            io.println("-----------------------------------");
            io.println("-  Welcome to your address book.  -");
            io.println("-----------------------------------");

            int menuChoice = io.getUserInputInt(menu, 0, 9);

            switch (menuChoice) {

                case 1:
                    addAddress();
                    break;
                case 2:
                    removeAddress();
                    break;
                case 3:
                    listAddress();
                    break;
                case 4:
                    searchByName();
                    break;
                case 5:
                    searchByCity();
                    break;
                case 6:
                    searchByState();
                    break;
                case 7:
                    searchByZip();
                    break;
                case 8:
                    io.println(" Goodbye! ");

                    try {
                        Thread.sleep(1100);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }

                    runAgain = false;
                default:
                    break;

            }
        }

    }//END run()

    //CASE 1: ADD ADDRESS
    //
    //
    private void addAddress() {

        Address address = new Address();

        String firstName = io.getUserInputString(" First name: ");
        while (firstName.equals("")) {
            io.print(" Invalid input. ");
            firstName = io.getUserInputString(" First name: ");
        }
        String lastName = io.getUserInputString(" Last name: ");
        while (lastName.equals("")) {
            io.print(" Invalid input. ");
            lastName = io.getUserInputString(" Last name: ");
        }
        String street = io.getUserInputString(" Street: ");
        while (street.equals("")) {
            io.print(" Enter a street. ");
            street = io.getUserInputString(" Street: ");
        }
        String city = io.getUserInputString(" City: ");
        while (city.equals("")) {
            io.print(" Enter a city. ");
            city = io.getUserInputString(" City: ");
        }
        String state = io.getUserInputString(" State: ");
        while (state.equals("")) {
            io.print(" Enter a state. ");
            state = io.getUserInputString(" State: ");
        }
        String zip = io.getUserInputString(" Zip: ");
        while (zip.equals("")) {
            io.print(" Enter a zip or postal code. ");
            zip = io.getUserInputString(" Zip: ");
        }
        address.setFirstName(firstName);
        address.setLastName(lastName);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZip(zip);
        dao.addAddress(address);

        io.println("ADDED!");

    }

    //CASE 2: REMOVE ADDRESS
    //
    //
    private void removeAddress() {

        if (!io.getTrueFalse("Do you know the ID of the address you want to delete? Type y or n. ")) {
            listAddress();
        }

        dao.deleteAddress(io.getUserInputInt(" ID to delete: ", " That's not an ID on file. Try again! "));

    }
    //CASE 3: LIST ADDRESS
    //
    //

    private void listAddress() {
        List<Address> addresslist = dao.list();
        for (Address indexOfAddress : addresslist) {
            printAddress(indexOfAddress);
        }
        io.getUserInputString("Press enter to continue ");
    }

    private void printAddress(Address address) {
        io.println("----------------\nID: " + address.getId());
        io.println("");
        io.println(address.getFirstName() + " " + address.getLastName());
        io.println(address.getStreet());
        io.println(address.getCity() + ", " + address.getState() + " " + address.getZip());
        io.println("----------------");

    }

    //CASE 4: SEARCH ADDRESS
    //
    //
    public Address searchAddress(String name) {
        String userInput = name;
        userInput = userInput.trim();
        List<Address> addressList = dao.list();
        boolean isValid = false;
        Address aAddress = new Address();
        for (Address indexOfAddress : addressList) {
            if (userInput.toLowerCase().equals(indexOfAddress.getLastName().toLowerCase())) {
                printAddress(indexOfAddress);
                isValid = true;
                aAddress = indexOfAddress;
            }
        }
        if (isValid == false) {
            io.println(" No names matching search. \n");
        }
        return aAddress;
    }

    public void searchByName() {
        List<Address> states = new ArrayList<>();
        String state = io.getUserInputString("Last Name? ");
        states = dao.searchByName(state);

        for (Address a : states) {
            printAddress(a);

        }

    }

    private void searchByZip() {
        List<Address> newList = dao.searchByZip(io.getUserInputString("What is the zip? "));
        for (Address a : newList) {
            printAddress(a);
        }

    }

    private void searchByCity() {
        List<Address> newList = dao.searchByCity(io.getUserInputString("What is the city? "));
        for (Address a : newList) {
            printAddress(a);
        }
    }

    private void searchByState() {
        List<Address> newList = dao.searchByState(io.getUserInputString("What is the State? "));
        for (Address a : newList) {
            printAddress(a);
        }
    }
}
