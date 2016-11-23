package Controller;

//import DAO.DVDDaoImpl;
import DAO.DVDDao;
import DAO.DVDDaoLambdaImpl;
import DAO.NoteDao;
import DTO.DVD;
import DTO.Note;
import UI.ConsoleIO;
import java.util.Calendar;
import java.util.List;

public class DVDController {

    ConsoleIO io = new ConsoleIO();
    DVDDao dao = new DVDDaoLambdaImpl();
    NoteDao noteDao = new NoteDao();

    public void run() {

        boolean runAgain = true;

        int[] options = {1, 2, 3, 4, 5, 6, 7};
        String error = " Please enter a valid menu choice. ";
        String menu = "\n [1] Search Collection "
                + "\n [2] Display All "
                + "\n [3] Add DVD "
                + "\n [4] Edit DVD "
                + "\n [5] Remove DVD "
                + "\n [6] Show Library Stats "
                + "\n [7] Quit "
                + "\n Your selection: ";

        while (runAgain) {
            io.println(" Welcome to your DVD collection. ");

            int menuChoice = io.getUserInputInt(menu, 0, 8);

            switch (menuChoice) {

                case 1:

                    int[] searchOptions = {1, 2, 3, 4, 5, 6, 7};

                    String searchMenu = " [1] TITLE "
                            + "\n [2] ID NUMBER "
                            + "\n [3] DIRECTOR "
                            + "\n [4] STUDIO "
                            + "\n [5] RATING "
                            + "\n [6] YEAR "
                            + "\n [7] Main Menu "
                            + "\n Your selection: ";

                    io.println(" Search by: ");

                    int searchMenuChoice = io.getUserInputInt(searchMenu, 0, 8);

                    switch (searchMenuChoice) {

                        case 1:
                            searchDVDByTitle();
                            break;
                        case 2:
                            getDVDById();
                            break;
                        case 3:
                            findAllDvdsByDirector();
                            break;
                        case 4:
                            findAllDvdsByStudio();
                            break;
                        case 5:
                            findAllDvdsWithMPAA();
                            break;
                        case 6:

                            //findDvdsByYear();
                            break;
                        case 7:
                            run();
                        default:
                            break;
                    }
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
                    //showLibraryStats();
                    break;
                case 7:
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
        io.println("Year Released: " + dvd.getDate());
        io.println("Directed By: " + dvd.getDirector());
        io.println(dvd.getStudio());
        io.println("Rated: " + dvd.getRating());
        io.println("");
        io.println("******** - NOTES - ********");
        for (Note i : noteDao.getNotes(dvd.getId())) {
            io.println(i.getContent());

        }

        io.println("");
        io.println("---------------------------");
    }

    private void printDVDnoID(DVD dvd) {

        io.println("");
        io.println(dvd.getTitle());
        io.println("Year Released: " + dvd.getDate());
        io.println("Directed By: " + dvd.getDirector());
        io.println(dvd.getStudio());
        io.println("Rated: " + dvd.getRating());
        io.println("");
        io.println("******** - NOTES - ********");
        io.println(dvd.getNote());
        io.println("");
        //FOR LOOP HERE PRINTING NOTES FROM NOTEDAO
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
        Note note = new Note();

        //User input written to object:
        String title = io.getUserInputString(" Title: ");
        while (title.equals("")) {
            io.print(" Invalid input. ");
            title = io.getUserInputString(" Title: ");
        }

        int date = io.getUserInputYear(" Enter the year this movie released: ");

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

        String noteToAdd = io.getUserInputString("Note: ");
        while (noteToAdd.equals("")) {
            io.print(" Add any note about this movie you would like. ");
            noteToAdd = io.getUserInputString("Note: ");
        }
        dvd.setDirector(director);
        dvd.setDate(date);
        dvd.setRating(rating);
        note.setContent(noteToAdd);
        dvd.setStudio(studio);
        dvd.setTitle(title);
        DVD dvdWithID = new DVD();

        dvdWithID = dao.addDVD(dvd);
        Integer movieID = dvdWithID.getId();
        note.setMovieId(movieID);
        noteDao.addNote(note);

        boolean wantsAnotherNote = false;
        wantsAnotherNote = (io.getTrueFalse("Would you like to add an additional note? Type y or n. ", "Invalid Input."));
        while (wantsAnotherNote == true) {
            String noteToAdd2 = io.getUserInputString("Note: ");
            while (noteToAdd2.equals("")) {
                io.print(" Add any note about this movie you would like. ");
                noteToAdd2 = io.getUserInputString("Note: ");
            }
            Note bNote = new Note();
            bNote.setContent(noteToAdd2);
            bNote.setMovieId(movieID);
            noteDao.addNote(bNote);
            wantsAnotherNote = (io.getTrueFalse("Would you like to add an additional note? Type y or n. ", "Invalid Input."));
        }

        io.println("Your movie has been added!");

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
                                + newLine + " [2] Edit RELEASE YEAR "
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
                                    io.println("Current Release Year: " + editingDVD.getDate());

//                                    editingDVD.setDate(io.getUserInputString("New Date: "));
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

    private void getDVDById() {
        dao.getDVDById(io.getUserInputInt(" Movie ID: ", " That's not an ID on file. Try again! "));
    }

    private void findAllDvdsByDirector() {
        String userInput = io.getUserInputString("Search Movies By Director: ");
        List<DVD> DVDs = dao.findAllDvdsByDirector(userInput);
        boolean isValid = false;
        if (DVDs.size() > 0) {
            for (DVD dvd : DVDs) {
                printDVD(dvd);

            }
        } else {
            io.println(" No titles matching search. \n");
        }
    }

    private void findAllDvdsByStudio() {
        String userInput = io.getUserInputString("Search Movies By Studio: ");
        List<DVD> DVDs = dao.findAllDvdsByStudio(userInput);
        boolean isValid = false;
        if (DVDs.size() > 0) {
            for (DVD dvd : DVDs) {
                printDVD(dvd);

            }
        } else {
            io.println(" No titles matching search. \n");
        }
    }

    private void findAllDvdsWithMPAA() {

        String[] mpaaRatings = {"G", "PG", "PG", "PG-13", "PG13", "R", "X", "XXX", "NC-17", "NC17", "UNRATED", "NR"};

        String userInput = io.getUserInputString("Search Movies By Rating: ", mpaaRatings, " That is not an official MPAA rating. Please try again. ");
        List<DVD> DVDs = dao.findAllDvdsWithMPAA(userInput);
        boolean isValid = false;
        if (DVDs.size() > 0) {
            for (DVD dvd : DVDs) {
                printDVD(dvd);

            }
        } else {
            io.println(" No titles matching ratings search. \n");
        }
    }

    private void findDvdsNewerThan() {
        io.println("GREG HASN'T BUILT THIS PART YET.");
    }

    private void findDvdsByYear() {
        int userInput = io.getUserInputYear("Search Movies By Release Year: ");
        List<DVD> DVDs = dao.findDvdsByYear(userInput);
        boolean isValid = false;
        if (DVDs.size() > 0) {
            for (DVD dvd : DVDs) {
                printDVD(dvd);

            }
        } else {
            io.println(" No titles in the database with that release year.");
        }
    }

}//END of ControllerClass

