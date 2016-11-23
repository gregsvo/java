/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.baseballleague.dao.PlayerDao;
import com.mycompany.baseballleague.dto.Team;
import com.mycompany.baseballleague.dao.TeamDao;
import com.mycompany.baseballleague.dto.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class TestBaseballLeague {

    public TestBaseballLeague() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
//        TeamDao tDao = new TeamDao();
//        Map<Integer, Team> league = tDao.getLeague();
//        
//        Set <Integer> keys = league.keySet();
//        for (Integer k : keys) {
//            if (league.get(k).getName().equals("Test")) {
//                league.remove(k);
//            }
//        }
//        tDao.cleanUpTest(league);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testCreateTeam() {

        Team aTeam = new Team();
        aTeam.setId(5785);
        aTeam.setName("Test");
        TeamDao range = new TeamDao();
        Team target = new Team();

        List<Team> testArrayList = new ArrayList<>();
        testArrayList = range.diplayAllTeams();
        int expected = 1;
        if (testArrayList.size() != 0) {
            expected = testArrayList.get(testArrayList.size() - 1).getId() + 1;
        }

        target = range.createTeam(aTeam);

        int result = target.getId();
        Assert.assertEquals(expected, result);

    }

    @Test
    public void testCreatePlayer() {

        Player aPlayer = new Player();
        aPlayer.setId(5785);
        aPlayer.setName("Test");
        aPlayer.setTeamId(1);
        PlayerDao range = new PlayerDao();
        Player target = new Player();

        List<Player> testArrayList = new ArrayList<>();
        testArrayList = range.listAllPlayers();
        int expected = 1;
        if (testArrayList.size() != 0) {
            expected = testArrayList.get(testArrayList.size() - 1).getId() + 1;
        }
        target = range.createPlayer(aPlayer);

        int result = target.getId();
        Assert.assertEquals(expected, result);

    }

    @Test
    public void testDisplayAllTeams() {
        //Tests when one team is in the .txt file
        List<Team> target = new ArrayList<>();
        TeamDao range = new TeamDao();
        target = range.diplayAllTeams();

        int result = target.size();
        Map<Integer, Team> tMap = range.getLeague();
        List<Team> expected = new ArrayList<>();
        expected.addAll(tMap.values());

        Assert.assertEquals(expected, target);

    }

    @Test
    public void testListPlayers() {
        PlayerDao playerData = new PlayerDao();
        Integer teamId = 1;
        List<Player> target = playerData.listPlayers(teamId);
        List<Player> test = playerData.listAllPlayers();
        int expected = 0;
        for (Player p : test) {
            if (p.getTeamId().equals(teamId)) {
                expected++;
            }
        }
        int result = target.size();
        Assert.assertEquals(expected, result);

    }

    @Test
    public void testMovePlayer() {
        Team aTeam = new Team();
        aTeam.setId(5785);
        aTeam.setName("Test");
        TeamDao range = new TeamDao();
        range.createTeam(aTeam);
        PlayerDao target = new PlayerDao();
        
        Player aPlayer = new Player();
        Integer teamId = 0;
        String name = "Blah";
        Integer id = 1;
        aPlayer.setId(id);
        aPlayer.setTeamId(teamId);
        aPlayer.setName(name);
        target.createPlayer(aPlayer);
        
        List<Player> test = target.listAllPlayers();
        Player bPlayer = test.get(0);
        Integer expected = bPlayer.getTeamId();
        expected++;
        
        aPlayer.setTeamId(expected);

        target.updateTeam(aPlayer, 0);
        List<Player>anotherList = target.listAllPlayers();
        Player cPlayer = anotherList.get(0);
        Integer result = cPlayer.getTeamId();
        
        Assert.assertEquals(expected, result);
    }
    
    @Test
    public void testDeletePlayer() {
        PlayerDao playerData = new PlayerDao();
        List<Player> testList = playerData.listAllPlayers();
        int expected = testList.size();
        playerData.deletePlayer(0);
        List<Player> testList2 = playerData.listAllPlayers();
        int result = testList2.size();
        expected--;
        
        Assert.assertEquals(expected, result);
    }

}
