package Controller;

import DAO.DVDDao;
import DTO.DVD;
import UI.ConsoleIO;
import java.util.List;

public class DVDController {

    ConsoleIO io = new ConsoleIO();
    DVDDao dao = new DVDDao();

    public void run() {

        String newLine = System.getProperty("line.separator");
        boolean runAgain = true;

        int[] options = {1, 2, 3, 4, 5, 6};
        String error = " Please enter a valid menu choice. ";
        String menu = "" + newLine + " [1] Search Collection "
                + newLine + " [2] Display All "
                + newLine + " [3] Add DVD "
                + newLine + " [4] Edit DVD "
                + newLine + " [5] Remove DVD "
                + newLine + " [6] Quit ";
        int menuSelection = 0;

        while (runAgain) {
            io.println(" Welcome to your DVD collection. ");

            int menuChoice = io.getUserInputInt(menu, 0, 7);

            switch (menuChoice) {

                case 1:
                    searchDVDByTitle();
                    break;
                case 2:
                    listDVD();
                    break;
                case 3:
                    addDVD();
                    break;
                case 4:
                    editDVD();
                    break;
                case 5:
                    removeDVD();
                    break;
                case 6:
                    runAgain = quit();

                default:
                    break;

            }

        }//END run()
    }

    //CASE 1: SEARCH COLLECTION
    //
    //
    private void searchDVDByTitle() {
        String userInput = io.getUserInputString("Search Movie Titles: ");
        List<DVD> DVDs = dao.getDVDsByTitle(userInput);
        boolean isValid = false;
        if (DVDs.size() > 0) {
            for (DVD dvd : DVDs) {
                printDVD(dvd);

            }
        } else {
            io.println(" No titles matching search. \n");
        }
    }

    private void printDVD(DVD dvd) {

        io.println("");
        io.println(dvd.getTitle() + "   (ID# " + dvd.getId() + ")");
        io.println("Released: " + dvd.getDate());
        io.println("Directed By: " + dvd.getDirector());
        io.println(dvd.getStudio());
        io.println("Rated: " + dvd.getRating());
        io.println("");
        io.println("******** - NOTES - ********");
        io.println(dvd.getNote());
        io.println("");
        if (dvd.getNote2() != null) {
            io.println(dvd.getNote2());
        }
        io.println("");
        io.println("---------------------------");
    }

    private void printDVDnoID(DVD dvd) {

        io.println("");
        io.println(dvd.getTitle());
        io.println("Released: " + dvd.getDate());
        io.println("Directed By: " + dvd.getDirector());
        io.println(dvd.getStudio());
        io.println("Rated: " + dvd.getRating());
        io.println("");
        io.println("******** - NOTES - ********");
        io.println(dvd.getNote());
        io.println("");
        if (dvd.getNote2() != null) {
            io.println(dvd.getNote2());
        }
        io.println("");
        io.println("---------------------------");
    }

    //CASE 2: DISPLAY ALL DVDs IN COLLECTION
    //
    //
    private void listDVD() {
        List<DVD> DVDs = dao.getDVDs();

        for (DVD dvd : DVDs) {
            printDVD(dvd);
        }
    }

    //CASE 3: ADD DVD TO COLLECTION
    //
    //
    private void addDVD() {

        //New DVD object created:
        DVD dvd = new DVD();

        //User input written to object:
        String title = io.getUserInputString(" Title: ");
        while (title.equals("")) {
            io.print(" Invalid input. ");
            title = io.getUserInputString(" Title: ");
        }
        String date = io.getUserInputString(" Year Of Release: ");

        while (date.equals("")) {
            io.print(" Invalid input. ");
            date = io.getUserInputString(" Enter year this movie released: ");
        }
        String director = io.getUserInputString(" Director: ");
        while (director.equals("")) {
            io.print(" Who directed this movie? Enter the director here: ");
            director = io.getUserInputString(" Director: ");
        }
        String studio = io.getUserInputString(" Studio: ");
        while (studio.equals("")) {
            io.print(" What studio released this movie? Enter it here: ");
            studio = io.getUserInputString(" Studio: ");
        }

        String rating = io.getUserInputString(" MPAA Rating: ");
        while (rating.equals("")) {
            io.print(" Please add a rating: ");
            rating = io.getUserInputString(" MPAA RATING: ");
        }

        String note = io.getUserInputString(" Additional Note: ");
        while (note.equals("")) {
            io.print(" Add any note about this movie you would like. ");
            note = io.getUserInputString(" Additional Note: ");
        }

        boolean wantsAnotherNote = (!io.getTrueFalse("Would you like to add an additional note? Type y or n. ", "Invalid Innput."));

        if (!wantsAnotherNote) {
            String note2 = io.getUserInputString(" Additional Note: ");
            dvd.setNote2(note2);

            if (note2.equals("")) {
                dvd.setNote2(note2);

            }

        }

        dvd.setDirector(director);
        dvd.setRating(rating);
        dvd.setNote(note);
        dvd.setStudio(studio);
        dvd.setTitle(title);
        dvd.setDate(date);

        io.println("Your movie has been added!");

        dao.addDVD(dvd);
    }

    //CASE 4: EDIT DVD IN COLLECTION
    //
    //
    private void editDVD() {

        boolean editValid = false;

        String newLine = System.getProperty("line.separator");
        String menu = "" + newLine + " [1] Search by TITLE "
                + newLine + " [2] Quit to Menu ";

        while (!editValid) {
            io.println(" Please Choose A Menu Selection: ");

            int editChoice = io.getUserInputInt(menu, 0, 3);

            switch (editChoice) {

                case 1:
                    String userInput = io.getUserInputString("Search Movie Titles: ");
                    List<DVD> DVDs = dao.getDVDsByTitle(userInput);
                    boolean isValid = false;
                    int i = 1;
                    if (DVDs.size() > 0) {
                        for (DVD dvd : DVDs) {

                            io.println("");
                            io.println("-------------------Movie ID: " + i);
                            i++;
                            printDVDnoID(dvd);

                        }
                        int movieToEdit = io.getUserInputInt(" Movie ID to edit: ", " That's not an ID on file. Try again! ");
                        movieToEdit = movieToEdit - 1;
                        DVD editingDVD = DVDs.get(movieToEdit);
                        String submenu = "" + newLine + " [1] Edit TITLE "
                                + newLine + " [2] Edit DATE "
                                + newLine + " [3] Edit STUDIO "
                                + newLine + " [4] Edit DIRECTOR "
                                + newLine + " [5] Edit RATING "
                                + newLine + " [6] Edit NOTE "
                                + newLine + " [7] Quit to MENU";

                        while (!editValid) {
                            io.println(" Please Choose A Menu Selection: ");

                            int subMenuchoice = io.getUserInputInt(submenu, 0, 8);
                            DVD editedDVD = new DVD();

                            switch (subMenuchoice) {

                                case 1: //EDIT TITLE
                                    io.println("Current Title: " + editingDVD.getTitle());

                                    editingDVD.setTitle(io.getUserInputString("New Title: "));
                                    break;

                                case 2:// EDIT DATE
                                    io.println("Current Date: " + editingDVD.getDate());

                                    editingDVD.setDate(io.getUserInputString("New Date: "));
                                    break;

                                case 3: //EDIT STUDIO
                                    io.println("Current Studio: " + editingDVD.getStudio());

                                    editingDVD.setStudio(io.getUserInputString("New Studio: "));
                                    break;

                                case 4: //EDIT DIRECTOR
                                    io.println("Current Director: " + editingDVD.getDirector());

                                    editingDVD.setDirector(io.getUserInputString("New Director: "));
                                    break;

                                case 5: //EDIT RATING
                                    io.println("Current Rating: " + editingDVD.getRating());

                                    editingDVD.setRating(io.getUserInputString("New Rating: "));
                                    break;

                                case 6: //EDIT NOTE
                                    io.println("Current Note: " + editingDVD.getNote());

                                    editingDVD.setNote(io.getUserInputString("New Note: "));

                                    if (editingDVD.getNote2() != null) {
                                        io.println("Current Second Note: " + editingDVD.getNote2());
                                        editingDVD.setNote2(io.getUserInputString("New Second Note: "));
                                    }

                                    break;

                                case 7: //QUIT TO MENU
                                    editValid = true;

                            }
                            dao.editDVD(editingDVD.getId(), editingDVD);
                        }

                    } else {
                        io.println(" No titles matching search. \n");
                    }

                    break;

                case 2:
                    run();
                    break;

            }
        }

    }

    //CASE 5: REMOVE DVD IN COLLECTION
    //
    //
    private void removeDVD() {

        if (!io.getTrueFalse("Do you know the ID# of the movie you'd like to delete? Type y or n. ")) {
            listDVD();
        }

        dao.removeDVD(io.getUserInputInt(" Movie ID to delete: ", " That's not an ID on file. Try again! "));

    }

    //CASE 6: QUIT
    //
    //
    private boolean quit() {
        io.println(" Happy Movie Watching! ");

        try {
            Thread.sleep(1100);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return false;

    }

}//END of ControllerClass

