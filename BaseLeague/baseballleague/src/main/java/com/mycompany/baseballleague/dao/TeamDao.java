/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseballleague.dao;

import com.mycompany.baseballleague.dto.Player;
import com.mycompany.baseballleague.dto.Team;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class TeamDao {

    private Map<Integer, Team> league = new HashMap<>();
    private final static String DELIMETER = "::";
    private final static String FILENAME = "team.txt";

    public TeamDao() {

        Map<Integer, Team> dataFromFile = new HashMap<>();
        dataFromFile = decode();

        Set<Integer> aSet = dataFromFile.keySet();

        for (Integer k : aSet) {
            Team aTeam = dataFromFile.get(k);
            league.put(aTeam.getId(), aTeam);
        }

    }

    public Team createTeam(Team aTeam) {
        Integer nextId = 1;
        List<Team> test = new ArrayList<>();
        test.addAll(getLeague().values());
        if (test.size() != 0) {
            Team bTeam = test.get(test.size() - 1);
            if (bTeam != null) {
                nextId = bTeam.getId() + 1;
            }
        }
        aTeam.setId(nextId);
        getLeague().put(nextId, aTeam);
        encode();
        return aTeam;
    }

    private Map<Integer, Team> decode() {

        Map<Integer, Team> aLeague = new HashMap<>();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();
                String[] values = currentLine.split(DELIMETER);

                Team aTeam = new Team();

                aTeam.setId(Integer.parseInt(values[0]));
                aTeam.setName(values[1]);

                aLeague.put(aTeam.getId(), aTeam);

            }

        } catch (Exception ex) {

        }

        return aLeague;
        //
    }
    public void cleanUpTest(Map<Integer, Team> cleanLeague) {
        this.league = cleanLeague;
        encode();
    }
    
    private void encode() {

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILENAME));

            Set<Integer> keys = getLeague().keySet();

            for (Integer k : keys) {
                List<Player> players = new ArrayList<>();
                players = getLeague().get(k).getRoster();
                String playerTxt = "";
//                if (players != null) {
//                    for (Player p : players) {
//                        playerTxt = playerTxt + p.getId() + DELIMETER
//                                + p.getName() + DELIMETER;
//                    }
//
//                    String line = league.get(k).getId() + DELIMETER
//                            + league.get(k).getName() + playerTxt;
//                    pw.println(line);
//                    pw.flush();
//                } else {
                String line = getLeague().get(k).getId() + DELIMETER
                        + getLeague().get(k).getName();

                pw.println(line);
                pw.flush();
//                }

            }

//            for (Student dude : studentList) {
//
//                String line = dude.getId() + DELIMETER
//                        + dude.getFirstName() + DELIMETER
//                        + dude.getLastName() + DELIMETER
//                        + dude.getCohort();
//            }
        } catch (Exception ex) {

        }

    }

    public List<Team> diplayAllTeams() {

        List<Team> teamList = new ArrayList<>();

        teamList.addAll(getLeague().values());

        return teamList;
    }

    public Team diplayTeam(Integer teamId) {

        List<Team> teamList = new ArrayList<>();

        teamList.addAll(getLeague().values());
        for (Team a : teamList) {
            if (a.getId() == teamId) {
                return a;
            }
        }

        return null;
    }

    /**
     * @return the league
     */
    public Map<Integer, Team> getLeague() {
        return league;
    }

   
}
