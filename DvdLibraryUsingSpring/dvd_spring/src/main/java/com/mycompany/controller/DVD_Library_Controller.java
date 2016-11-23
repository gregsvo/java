/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.Comments_DAO_Impl;
import com.mycompany.dao.Dvd_DAO_Impl;
import com.mycompany.dto.DVD;
import com.mycompany.ui.ConsoleIO;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class DVD_Library_Controller {

    Comments_DAO_Impl commentDao;
    private final ConsoleIO console;
    private final Dvd_DAO_Impl dao;

    public DVD_Library_Controller(ConsoleIO iobean, Dvd_DAO_Impl dvdBean, Comments_DAO_Impl commentBean) {
        this.console = iobean;
        this.dao = dvdBean;
        this.commentDao = commentBean;
    }

    public void run() {
        boolean running = true;
        while (running) {
            //print the selection
            String selection = console.getMultiChoiceString("Welcome to your DVD collection. What would "
                    + "you like to do?", new String[]{"Add a DVD", "Remove a DVD", "Retrieve a DVD's Info",
                        "Print you Collection of DVD's", "Update a DVD", "Update A DVD Comments"}, true, "");

            switch (selection) {
                case "Add a DVD":
                    addDvd();
                    break;
                case "Remove a DVD":
                    removeDvd();
                    break;
                case "Retrieve a DVD's Info":
                    retrieveDvd();
                    break;
                case "Print you Collection of DVD's":
                    printLibrary();
                    break;
                case "Update a DVD":
                    updateDVD();
                    break;
                case "Update A DVD Comments":
                    updateComment();
                    break;
                case "Exit":
                    running = false;
            }
        }
    }

    private void addDvd() {
        //declared outside so I can create DVD with constructor
        String title;
        //validate whether or not the title they choose is right?
        do {
            title = console.getUserInputString("What is the DVD's title?: ");
        } while (isCorrect());

        //validate whether or not the release they choose is right?
        Integer releaseDate;
        do {
            releaseDate = console.getUserInputInt("What year was it released? ", 1920, 2016);
        } while (isCorrect());

        //validate whether or not the rating they choose is right?
        String rating;
        do {
            rating = console.getMultiChoiceString("What is the MPAA rating", new String[]{"G", "PG",
                "PG13", "R", "NC-17"});
        } while (isCorrect());

        //validate whether or not the studio they choose is right?
        String studio;
        do {
            studio = console.getUserInputString("What studio created the DVD?:");
        } while (isCorrect());

        //creates a List of strings to stor potential comments
        boolean addComments = true;
        while (addComments) {
            //asks if the user wants to add a comment
            String commentConsent = console.getMultiChoiceString("Would you like to add a comment",
                    new String[]{"Yes", "No"});
            switch (commentConsent) {
                case "Yes":
                    //Asks for a comment
                    String commentToAdd = console.getUserInputString("Comment>>> ");
                    //stores the comment
                    commentDao.createComment(commentToAdd);

                    break;
                case "No":
                    addComments = false;
                    break;
            }
        }

        //creates new dvd object with appropriate constructor
        DVD newDvd;
        newDvd = new DVD(title, releaseDate, rating, studio, commentDao.getComments());
        //creates new Dvd in DVD Dao's Map
        dao.add(newDvd);

    }

    private void removeDvd() {
        //prompts the user what dvd they want to delete, must be its integer;
        String idToRemove = console.getMultiChoiceString("What dvd would you like to delete? "
                + "Enter its Id #>>>", createDvdListWithIds(), true, "Exit");
        boolean deletedADVD = false;

        while (!deletedADVD) {
            //if they choose to exit, exit the loop
            if (idToRemove.equals("Exit")) {
                deletedADVD = true;
            } else {
                //split the user input by the ", " 
                //the response will always look like 'id#, TITLE_NAME'
                String[] idAccesor = idToRemove.split(", ");
                //make the first half of the array an integer
                int actualIdToRemove = Integer.parseInt(idAccesor[0]);
                //iterate through the dvd's
                for (DVD currentDVD : dao.listAll()) {

                    //if the integer = the current Dvd's id
                    if (actualIdToRemove == currentDVD.getId()) {
                        //deletes the dvd with the id
                        dao.remove(currentDVD.getId());
                        //stops the loop
                        deletedADVD = true;
                        //stops the iteration
                        break;
                    }
                }
            }
        }
    }

    private void retrieveDvd() {
        // asks if they want to get the dvd by title or id
        String howTo = console.getMultiChoiceString("How would you like to find the DVD?", new String[]{
            "By Title", "By Id", "By Rating", "By Studio"
        });
        //if they chose "By Title"
        if (howTo.equals("By Title")) {
            //asks for a title name
            String initialTitle = console.getMultiChoiceString("Which title would you like to see? ", createDvdListWithIds(), true, "");
            if (initialTitle.equals("Exit")) {
            } else {
                //seperates my answer, will always be 'id#, TITLE_NAME'
                String[] seperatedAnswer = initialTitle.split(", ");
                //want to grab the second half of the array
                String title = seperatedAnswer[1];
                for (DVD dvd : dao.listAll()) {
                    if (title.contains(dvd.getTitle())) {
                        List<DVD> toPrint = dao.getByTitle(title);
                        for (DVD print : toPrint) {
                            console.println(print.getTitle() + "\nRelased: " + print.getRealseDate() + "\nRating: "
                                    + print.getMpaaRating() + "\nStudio: " + print.getStudio() + "\nComments:"
                                    + print.getComments());
                        }
                        break;
                    }
                }
            }
            //if they choose Id
        } else if (howTo.equals("By Id")) {
            //Asks from a list which one they want to delete
            String initialId = console.getMultiChoiceString("Which title, by id number, would you like to see? ",
                    createDvdListWithIds(), true, "");
            //return to the main menu if they choose the Exit option
            if (initialId.equals("Exit")) {
            } else {
                //split the answer
                String[] seperated = initialId.split(", ");
                //grab the first part, and make it an int
                int id = Integer.parseInt(seperated[0]);
                //iterate through the dvds that exist
                for (DVD dvd : dao.listAll()) {
                    if (id == dvd.getId()) {
                        // store found dvd in reference variable
                        DVD toPrint = dao.getById(id);
                        //print the attriibutes
                        console.println(toPrint.getTitle() + "\nRelased: " + toPrint.getRealseDate() + "\nRating: "
                                + toPrint.getMpaaRating() + "\nStudio: " + toPrint.getStudio() + "\nComments: "
                                + toPrint.getComments());
                    }
                }
            }
        } else if (howTo.equals("By Rating")) {
            String initialRating = console.getMultiChoiceString("Which Rating would you like to see? ", createDvdListWithIds(), true, "");
            if (initialRating.equals("Exit")) {
            } else {
                String[] seperatedAnswer = initialRating.split(", ");
                String rating = seperatedAnswer[1];
                for (DVD dvd : dao.listAll()) {
                    if (rating.contains(dvd.getMpaaRating())) {
                        List<DVD> toPrint = dao.getByTitle(rating);
                        for (DVD print : toPrint) {
                            console.println(print.getTitle() + "\nRelased: " + print.getRealseDate() + "\nRating: "
                                    + print.getMpaaRating() + "\nStudio: " + print.getStudio() + "\nComments:"
                                    + print.getComments());
                        }
                        break;
                    }
                }
            }
        } else if (howTo.equals("By Studio")) {
            String initialStudio = console.getMultiChoiceString("Which Rating would you like to see? ", createDvdListWithIds(), true, "");
            if (initialStudio.equals("Exit")) {
            } else {
                String[] seperatedAnswer = initialStudio.split(", ");
                String studio = seperatedAnswer[1];
                for (DVD dvd : dao.listAll()) {
                    if (studio.contains(dvd.getMpaaRating())) {
                        List<DVD> toPrint = dao.getByTitle(studio);
                        for (DVD print : toPrint) {
                            console.println(print.getTitle() + "\nRelased: " + print.getRealseDate() + "\nRating: "
                                    + print.getMpaaRating() + "\nStudio: " + print.getStudio() + "\nComments:"
                                    + print.getComments());
                        }
                        break;
                    }
                }
            }
        }
    }

    private void printLibrary() {

        //for the dvds that exist, stream through them
        dao.listAll().stream().forEach((dvd) -> {
            //for every dvd print the title plus " id #" and the actual id
            System.out.println(dvd.getTitle() + " id #" + dvd.getId());
        });

    }

    private boolean isCorrect() {
        // return whether or not is correct
        return !console.getTrueFalse("Is this correct?>>> ");
    }

    private String[] createDvdListWithIds() {
        //check that there are dvds
        if (dao.listAll().size() > 0) {
            //create string array set to the size of the dvds that exist
            String[] acceptable = new String[dao.listAll().size()];
            //index counter
            int i = 0;
            //iterate through dvds that exist
            for (DVD currentDVD : dao.listAll()) {
                //at the index set the current Dvd's id, a delimeter, and the title
                acceptable[i] = currentDVD.getId() + ", " + currentDVD.getTitle();
                //increase the index counter
                i++;
            }
            //return the string array
            return acceptable;
        }
        return null;
    }

    private void updateComment() {
        //
        String initialTitle = console.getMultiChoiceString("Which title would you like to see? ", createDvdListWithIds(), true, "");
        if (!initialTitle.equals("Exit")) {
            //seperates my answer, will always be 'id#, TITLE_NAME'
            String[] seperatedAnswer = initialTitle.split(", ");
            String dvdTitle = seperatedAnswer[1];
            for (DVD currentDVD : dao.listAll()) {
                if (currentDVD.getTitle().equals(dvdTitle)) {

                    boolean keepUsing = true;
                    while (keepUsing) {
                        List<String> currentDVDComments = currentDVD.getComments();
                        String[] prompt = commentDao.getUpdateCommentPrompt(currentDVDComments);

                        String userAnswer = console.getMultiChoiceString("What would you like to do?", prompt, true, "");
                        String[] splitAnswer = userAnswer.split(": ", 2);
                        if (userAnswer.equals("Add a comment")) {
                            String comment = console.getUserInputString(">>> ");
                            commentDao.addCommentToExistingList(comment);
                            currentDVD.setComments(commentDao.getComments());
                            dao.encodeLibrary();

                        } else if (userAnswer.equals("Exit")) {
                            keepUsing = false;
                        } else if (splitAnswer[0].equals("Modify")) {

                            String howToModify = console.getMultiChoiceString("How would you like to modify? ",
                                    new String[]{"Add Something Before", "Add Something After", "Delete A Selection"});
                            switch (howToModify) {
                                case "Add Something Before":
                                    String additionPre = console.getUserInputString("What would you like to add?: ");
                                    commentDao.modifyComment(splitAnswer[1], additionPre, true);
                                    currentDVD.setComments(commentDao.getComments());
                                    dao.encodeLibrary();
                                    break;
                                case "Add Something After":
                                    String additionPost = console.getUserInputString("What would you like to add?: ");
                                    commentDao.modifyComment(splitAnswer[1], additionPost, false);
                                    currentDVD.setComments(commentDao.getComments());
                                    dao.encodeLibrary();
                                    break;
                                case "Delete A Selection":
                                    boolean keepUsingDelete = true;
                                    while (keepUsingDelete) {
                                        String toDelete = console.getUserInputString("What section would you like to delete?: ");
                                        if (splitAnswer[1].contains(toDelete)) {
                                            commentDao.deleteSelectionInComment(splitAnswer[1], toDelete);
                                            currentDVD.setComments(commentDao.getComments());
                                            dao.encodeLibrary();
                                            keepUsingDelete = false;
                                        } else {
                                            String consentToRePrompt = console.getMultiChoiceString("Not part of the "
                                                    + "current comment. Would you like to try again?", new String[]{"Yes", "No"});
                                            switch (consentToRePrompt) {
                                                case "Yes":
                                                    break;
                                                case "No":
                                                    keepUsingDelete = false;
                                            }
                                        }
                                    }
                                    break;

                            }
                            currentDVD.setComments(commentDao.getComments());

                        } else if (splitAnswer[0].equals("Delete")) {
                            commentDao.deleteComment(splitAnswer[1]);
                            currentDVD.setComments(commentDao.getComments());
                            dao.encodeLibrary();

                        }
                    }

                }
            }

        }
    }

    private void updateDVD() {
        boolean editValid = false;
        String newLine = System.getProperty("line.separator");
        String menu = "" + newLine + " [1] Search by TITLE "
                + newLine + " [2] Quit to Menu ";
        while (!editValid) {
            console.println(" Please Choose A Menu Selection: ");
            int editChoice = console.getUserInputInt(menu, 0, 3);
            switch (editChoice) {
                case 1:
                    String userInput = console.getUserInputString("Search Movie Titles: ");
                    List<DVD> DVDs = dao.getByTitle(userInput);
                    boolean isValid = false;
                    if (DVDs.size() > 0) {
                        for (DVD x : DVDs) {
                            printDVDnoID(x);
                        }
                        int movieToEdit = console.getUserInputInt(" Movie ID to edit: ");
                        DVD editingDVD = dao.getById(movieToEdit);
                        String submenu = "" + newLine + " [1] Edit TITLE "
                                + newLine + " [2] Edit RELEASE YEAR "
                                + newLine + " [3] Edit STUDIO "
                                + newLine + " [4] Edit RATING "
                                + newLine + " [5] Quit to MENU";
                        while (!editValid) {
                            console.println(" Please Choose A Menu Selection: ");
                            int subMenuchoice = console.getUserInputInt(submenu, 0, 8);

                            switch (subMenuchoice) {
                                case 1: //EDIT TITLE
                                    console.println("Current Title: " + editingDVD.getTitle());
                                    editingDVD.setTitle(console.getUserInputString("New Title: "));
                                    break;
                                case 2:// EDIT DATE
                                    console.println("Current Release Year: " + editingDVD.getRealseDate());
                                    editingDVD.setReleaseDate(console.getUserInputInt("New Date: "));
                                    break;
                                case 3: //EDIT STUDIO
                                    console.println("Current Studio: " + editingDVD.getStudio());
                                    editingDVD.setStudio(console.getUserInputString("New Studio: "));
                                    break;
                                case 4: //EDIT RATING
                                    console.println("Current Rating: " + editingDVD.getMpaaRating());
                                    editingDVD.setMpaaRating(console.getUserInputString("New Rating: "));
                                    break;
                                case 5: //QUIT TO MENU
                                    editValid = true;
                            }
                            dao.update(editingDVD);
                        }
                    } else {
                        console.println(" No titles matching search. \n");
                    }
                    break;
                case 2:
                    return;
            }
        }
    }

    private void printDVDnoID(DVD dvd) {
        console.println(dvd.getTitle() + " - ID: " + dvd.getId());
        console.println("Year Released: " + dvd.getRealseDate());
        console.println("Studio: " + dvd.getStudio());
        console.println("Rated: " + dvd.getMpaaRating());
    }
}
