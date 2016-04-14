package com.fortim.ddcode.controller;

import Email_DAO.EmailDAO;
import Email_DTO.Email;
import com.fortim.ddcode.ui.ConsoleIO;
import java.time.LocalDate;
import java.util.List;

public class Controller {

    ConsoleIO io = new ConsoleIO();
    EmailDAO dao = new EmailDAO();

    private final String newLine = System.getProperty("line.separator");

    public void run() {

        boolean runAgain = true;
        int[] options = {1, 2, 3, 4, 5};
        String error = " Please enter a valid menu choice. ";
        String menu = "" + newLine + " [1] Add New Email Addresses "
                + newLine + " [2] Unsubscribe Email Address "
                + newLine + " [3] Show Email Subscribers "
                + newLine + " [4] Have Greg Join Your Team "
                + newLine + " [5] Quit " + newLine;

        int menuSelection = 0;

        while (runAgain) {
            io.println(" T.I.M. Subscribe 4000: MAIN menu ");

            int menuChoice = io.getUserInputInt(menu, 0, 6);

            switch (menuChoice) {

                case 1:
                    addEmail();
                    break;
                case 2:
                    unsubscribeEmail();
                    break;
                case 3:
                    showEmailViewOptions();
                    break;
                case 4:
                    hireGreg();
                    break;
                case 5:
                    io.println(" Goodbye, and thank you for using Greg's attempt at saving face.");
                    runAgain = false;
                default:
                    break;

            }
        }

    }//END run()

    private void hireGreg() {
        io.println(" Call : 216-403-7592 (AND/OR) Email : gregsvo@gmail.com");
    }

    private void showEmailViewOptions() {
        boolean runAgain = true;
        int[] options = {1, 2, 3, 4, 5};
        String error = " Please enter a valid menu choice. ";
        String menu = "" + newLine + " [1] List All Emails "
                + newLine + " [2] List All Subscribers "
                + newLine + " [3] List All Resubscribed "
                + newLine + " [4] List All Unsubscribed "
                + newLine + " [5] Get Email Stats "
                + newLine + " [6] Quit to Main Menu " + newLine;

        int menuSelection = 0;

        while (runAgain) {
            io.println(" T.I.M. Subscribe 4000: Email Menu ");

            int menuChoice = io.getUserInputInt(menu, 0, 7);

            switch (menuChoice) {

                case 1:
                    listAll();
                    break;
                case 2:
                    for(Email email : dao.listSubscribed()){
                        io.printEmail(email);
                    }
                    break;
                case 3:
                    for(Email email : dao.listResubscribed()){
                        io.printEmail(email);
                    }
                    break;
                case 4:
                    for(Email email : dao.listUnsubscribed()){
                        io.printEmail(email);
                    }
                    break;
                case 5:
                    stats();
                    break;
                case 6:
                    runAgain = false;
                default:
                    break;

            }
        }
    }

    private void listAll() {
        List<Email> emailList = dao.getEmailList();
        for (Email i : emailList) {
            io.printEmail(i);
        }
    }

    private void addEmail() {
        Email newEmail = new Email();
        String input = (io.getEmailInput(" Please enter the email you would like to add: ", " Invalid Email. Try again. "));
        newEmail.setAddress(input);
        
        Email email = dao.searchByEmail(input);
        if(email==null){
            email = new Email();
            email.setAddress(input);
            email.setId(Integer.SIZE);
            email.setStatus(Email.SUBSCRIBED);
            email.setLastUpdated(LocalDate.now());
            dao.create(email);
            io.println(email.getAddress()+" has been created.");
            
        }else if(email.getStatus()==(Email.UNSUBSCRIBED)){
            dao.update(Email.RESUBSCRIBED, email);
            io.println(email.getAddress()+" has been resubscribed. Welcome back.");
  
        } else{
               System.out.println(" This email is already subscribed.");
        }
       
    }

    private void unsubscribeEmail() {
       Email email = dao.searchByEmail(io.getEmailInput(" Please enter the email address you would like to delete: ", " Invalid entry. "));
     
        if (email == null) {
            io.println(" This address is not in the database. ");
        } else {
            email.setStatus(Email.UNSUBSCRIBED);
            dao.update(email.getId(), email);
            dao.encode();
            io.println(email.getAddress() + " has been unsubscribed. ");
            
        }

    }

    private void stats() {
        int numOfsubs = dao.listSubscribed().size();
        int numOfunsub = dao.listUnsubscribed().size();
        int numOfresub = dao.listResubscribed().size();
        io.printStats(numOfsubs, numOfunsub, numOfresub);

    }
}
