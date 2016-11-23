/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseballleague.controllers;

import com.mycompany.baseballleague.dao.PlayerDao;
import com.mycompany.baseballleague.dao.TeamDao;
import com.mycompany.baseballleague.dto.Player;
import com.mycompany.baseballleague.dto.Team;
import com.mycompany.baseballleague.ui.ConsoleIO;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class BaseController {

    private final ConsoleIO io = new ConsoleIO();
    private final TeamDao teamDao = new TeamDao();
    private final PlayerDao playerDao = new PlayerDao();
    private final String error = "Error.  Enter a valid entry.";

    public void run() {
        //start program and display menu

        boolean runAgain = true;
        int[] options = {1, 2, 3, 4, 5, 6, 7};

        String menu = ("Welcome Commisioner\nPlease enter the number\nnext "
                + "to the option you want to perform\n"
                + "1: Create a team\n"
                + "2: Create a player\n"
                + "3: Show all teams\n"
                + "4: List all players on a team\n"
                + "5: Move a player to a different team\n"
                + "6: Delete a player\n"
                + "7: Exit\n");

        while (runAgain) {
            int choice = 0;
            choice = io.getUserInputIntMinMax(menu, 0, 8);

            switch (choice) {

                case 1:
                    createTeam();
                    break;
                case 2:
                    createPlayer();
                    break;
                case 3:
                    displayAllTeams();
                    break;
                case 4:
                    displayAllPlayers();
                    break;
                case 5:
                    movePlayer();
                    break;
                case 6:
                    deletePlayer();
                    break;
                case 7:
                    runAgain = false;
                    io.print("Thanks, bye!");
                    break;
                default:
                    io.print("Please enter a valid menu option.");
                    //choice = io.getMenuChoice(menu, error, options);
                    break;
            }

        }

    }

    private void createTeam() {
        Team aTeam = new Team();

        String teamName = io.askUserToValidateStringInput("Please enter the team's name: ", error);
        while (teamName.equals("")) {
            io.print("You didn't enter anything.\nBefore hitting enter this time around,\ntry pressing a few of those\nkeys with letters and numbers on them!");
            teamName = io.askUserToValidateStringInput("Please enter the team's name: ", error);
        }

        aTeam.setName(teamName);

        teamDao.createTeam(aTeam);

    }

    private void createPlayer() {
        Player aPlayer = new Player();

        String playerName = io.askUserToValidateStringInput("Please enter the player's first and last name:", error);
        while (playerName.equals("")) {
            io.print("You didn't enter anything.\nBefore hitting enter this time around,\ntry pressing a few of those\nkeys with letters and numbers on them!");
            playerName = io.askUserToValidateStringInput("Please enter the player's name: ", error);
        }

        
        Integer TeamId = selectTeam();

        aPlayer.setName(playerName);
        aPlayer.setTeamId(TeamId);

        playerDao.createPlayer(aPlayer);

    }

    private Integer selectTeam() {
        io.print("Select the the team from the following list: ");
        displayAllTeams();
        Integer TeamId = io.getUserInputIntMinMax("", 0, teamDao.diplayAllTeams().size() + 1);
        return TeamId;
    }

    private void displayAllTeams() {
        List<Team> teams = teamDao.diplayAllTeams();
        int i = 1;
        for (Team t : teams) {
            io.print(i + ": " + t.getName());
            i++;
        }
        
    }

    private void displayAllPlayers() {
        Integer teamId = selectTeam();
        List<Player> teamRoster = playerDao.listPlayers(teamId);

        if (teamRoster != null) {
            io.print("The players for team #" + teamId + " are:\n"
                    + "======================\n\n");
            for (Player p : teamRoster) {
                io.print(p.getName() + "\n");
            }
        } else {
            io.print("No players are on that team.  I'd bet against them.");
        }
    }

    private void movePlayer() {
        List<Player> allPlayers = listAllPlayers();
        int playerIndex = io.getUserInputIntMinMax("Enter the player ID number located next to the name of the player you would like to move", 0, allPlayers.size() + 1);
        playerIndex -= 1;
        Player playerToMove = playerDao.getPlayer(playerIndex);
        io.print("Where do you want to move the player: \n");
        Integer newTeamId = selectTeam();
        playerToMove.setTeamId(newTeamId);
        boolean whatEvs = playerDao.updateTeam(playerToMove, playerIndex);

    }

    private List<Player> listAllPlayers() {
        io.print("Here's all the players in the League:\n\n");
        List<Player> allPlayers = playerDao.listAllPlayers();
        int i = 1;
        for (Player p : allPlayers) {
                Team aTeam = teamDao.diplayTeam(p.getTeamId());
                io.print(i + ": " + p.getName() + " - " + aTeam.getName());
                i++;
            }
        return allPlayers;
    }

    private void deletePlayer() {

        io.print("Select the the player you wish to delete \nand enter the number next to their name: ");
        List<Player> allPlayers = listAllPlayers();
        Integer playerIndex = io.getUserInputIntMinMax("", 0, playerDao.listAllPlayers().size() + 1);
        playerIndex -= 1;
        Player aPlayer = allPlayers.get(playerIndex);
        boolean reallyDelete = io.askUserYesOrNo("Really delete: " + aPlayer.getName() + "?\n1: Yes\n2: No\n");
        while (reallyDelete) {
            boolean isGone = playerDao.deletePlayer(playerIndex);
            if (isGone) {
                io.print(aPlayer.getName() + " yerrrrrrrrrrr outtt!");
            }

            break;
        }

    }

}
