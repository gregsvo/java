/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseballleague.dao;

import com.mycompany.baseballleague.dto.Player;
import com.mycompany.baseballleague.dto.Team;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class PlayerDao {

    private List<Player> allPlayers = new ArrayList<>();
    private final static String DELIMETER = "::";
    private final static String FILENAME = "players.txt";
    private static Integer playerAssignedId = 1;

    public PlayerDao() {
        List<Player> dataFromFile = new ArrayList<>();
        dataFromFile = decode();

        for (Player p : dataFromFile) {

            allPlayers.add(p);

        }

    }

    public Player createPlayer(Player aPlayer) {
        Integer nextId = 1;
        List<Player> test = new ArrayList<>();
        test = this.listAllPlayers();
        if (test.size() != 0) {
            Player bPlayer = test.get(test.size() - 1);
            if (bPlayer != null) {
                nextId = bPlayer.getId() + 1;
            }
        }

        aPlayer.setId(nextId);

        allPlayers.add(aPlayer);
        encode();
        return aPlayer;
    }

    public static List<Player> decode() {
        List<Player> allPlayers = new ArrayList<Player>();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();
                String[] values = currentLine.split(DELIMETER);

                Player aPlayer = new Player();

                aPlayer.setId(Integer.parseInt(values[0]));
                aPlayer.setName(values[1]);
                aPlayer.setTeamId(Integer.parseInt(values[2]));

                allPlayers.add(aPlayer);

            }

        } catch (FileNotFoundException ex) {
            //
        }

        return allPlayers;
    }

    private void encode() {

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILENAME));

            String line = "";
            for (Player aPlayer : allPlayers) {

                //Student aStudent = studentMap.get(idNumber);
                //List<Integer> quizScores = aStudent.getQuizScores();
                //String quizString = quizScores.toString();
                line = aPlayer.getId() + DELIMETER
                        + aPlayer.getName() + DELIMETER
                        + aPlayer.getTeamId();
                pw.println(line);
                pw.flush();

            }

        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

//        } catch (IOException ex) {
//            //
//        }
    }

    public List<Player> listPlayers(Integer teamId) {
        List<Player> teamRoster = new ArrayList<>();

        for (Player p : allPlayers) {

            if (p.getTeamId().equals(teamId)) {
                Integer playerId = p.getId();
                Integer team = p.getTeamId();
                String name = p.getName();

                Player aPlayer = new Player();

                aPlayer.setId(playerId);
                aPlayer.setTeamId(team);
                aPlayer.setName(name);

                teamRoster.add(aPlayer);
            }
        }

        return teamRoster;
    }

    public boolean updateTeam(Player aPlayer, Integer index) {
        allPlayers.set((int) index, aPlayer);
        encode();
        return true;
    }

    public Player getPlayer(Integer playerIndex) {
        int i = 0;
        for (Player p : allPlayers) {

            if (i == playerIndex) {
                return p;
            }
            i++;
        }

        return null;
    }

    public boolean deletePlayer(Integer playerIndex) {
        allPlayers.remove((int) playerIndex);

        encode();

        return true;
    }

    public List<Player> listAllPlayers() {
        List<Player> teamRoster = new ArrayList<>();

        for (Player p : allPlayers) {

            Integer playerId = p.getId();
            Integer team = p.getTeamId();
            String name = p.getName();

            Player aPlayer = new Player();

            aPlayer.setId(playerId);
            aPlayer.setTeamId(team);
            aPlayer.setName(name);

            teamRoster.add(aPlayer);

        }

        return teamRoster;
    }

}
