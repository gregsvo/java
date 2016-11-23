/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JunitTests;

import com.mycompany.dao.DvdLibraryDao;
import com.mycompany.dao.Dvd_DAO_Impl;
import com.mycompany.dto.DVD;
import java.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class DVDUnitTest_KyleAndGreg {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    private final DvdLibraryDao dvd = ctx.getBean("dvdDao", DvdLibraryDao.class);

    public DVDUnitTest_KyleAndGreg() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateDVD() {
        DVD newDvd = new DVD();
        newDvd.setTitle("The Shining");
        newDvd.setReleaseDate(1980);
        newDvd.setMpaaRating("R");
        newDvd.setStudio("Steven King INC");
        newDvd.setComments(Arrays.asList("Jack", "Freaking", "Nicholson"));
        dvd.add(newDvd);
        int year = 0;
        for (DVD onTheShelf : dvd.listAll()) {
            if ("The Shining".equals(onTheShelf.getTitle())) {
                year = onTheShelf.getRealseDate();
            }
        }
        int expected = 1980;

        Assert.assertEquals(expected, year);

    }

    @Test
    public void testCreateDVDNull() {
        DVD newDvd2 = null;
        int before = dvd.listAll().size();
        dvd.add(newDvd2);
        int after = dvd.listAll().size();

        Assert.assertEquals(after, before);
    }
    
    @Test
    public void testListAllNone() {
        int result = dvd.listAll().size();

        Assert.assertEquals(0, result);
    }
    
    @Test
    public void testListAll() {
        DVD newDvd = new DVD();
        newDvd.setTitle("The Shining");
        newDvd.setReleaseDate(1980);
        newDvd.setMpaaRating("R");
        newDvd.setStudio("Steven King INC");
        newDvd.setComments(Arrays.asList("Jack", "Freaking", "Nicholson"));
        dvd.add(newDvd);

        int result = dvd.listAll().size();

        Assert.assertEquals(1, result);
    }

    @Test
    public void testGetById() {
        DVD newDvd = new DVD();
        newDvd.setTitle("The Shining");
        newDvd.setReleaseDate(1980);
        newDvd.setMpaaRating("R");
        newDvd.setStudio("Steven King INC");
        newDvd.setComments(Arrays.asList("Jack", "Freaking", "Nicholson"));
        dvd.add(newDvd);

        int result = newDvd.getId();

        Assert.assertEquals(1, result);
    }

    @Test
    public void testDeleteDVD() {
        DVD newDvd = new DVD();
        newDvd.setTitle("The Shining");
        newDvd.setReleaseDate(1980);
        newDvd.setMpaaRating("R");
        newDvd.setStudio("Steven King INC");
        newDvd.setComments(Arrays.asList("Jack", "Freaking", "Nicholson"));
        dvd.add(newDvd);
        int x = newDvd.getId();
        dvd.remove(x);
        boolean success = true;
        for (DVD y : dvd.listAll()) {
            if (y.getId() == x) {
                success = false;
            }
        }
        Assert.assertTrue(success);
    }
    @Test
    public void testDeleteDVDNone() {
        DVD newDvd = new DVD();
        newDvd.setTitle("The Shining");
        newDvd.setReleaseDate(1980);
        newDvd.setMpaaRating("R");
        newDvd.setStudio("Steven King INC");
        newDvd.setComments(Arrays.asList("Jack", "Freaking", "Nicholson"));
        dvd.add(newDvd);
        int x = newDvd.getId();
        dvd.remove(x+1);
        boolean success = true;
        for (DVD y : dvd.listAll()) {
            if (y.getId() == x) {
                success = false;
            }
        }
        Assert.assertFalse(success);
    }

    @Test
    public void testGetByRating() {
        DVD newDvd = new DVD();
        newDvd.setTitle("The Shining");
        newDvd.setReleaseDate(1980);
        newDvd.setMpaaRating("R");
        newDvd.setStudio("Steven King INC");
        newDvd.setComments(Arrays.asList("Jack", "Freaking", "Nicholson"));
        dvd.add(newDvd);
        int result = dvd.getByRating("R").size();

        Assert.assertEquals(1, result);
    }
    
    @Test
    public void testGetByRatingNone() {
        DVD newDvd = new DVD();
        newDvd.setTitle("The Shining");
        newDvd.setReleaseDate(1980);
        newDvd.setMpaaRating("R");
        newDvd.setStudio("Steven King INC");
        newDvd.setComments(Arrays.asList("Jack", "Freaking", "Nicholson"));
        dvd.add(newDvd);
        int result = dvd.getByRating("G").size();

        Assert.assertEquals(0, result);
    }

    @Test
    public void testGetByTitle() {
        DVD newDvd = new DVD();
        newDvd.setTitle("The Shining");
        newDvd.setReleaseDate(1980);
        newDvd.setMpaaRating("R");
        newDvd.setStudio("Steven King INC");
        newDvd.setComments(Arrays.asList("Jack", "Freaking", "Nicholson"));
        dvd.add(newDvd);
        int result = dvd.getByTitle("The Shining").size();

        Assert.assertEquals(1, result);
    }
    
    @Test
    public void testGetByTitleNone() {
        DVD newDvd = new DVD();
        newDvd.setTitle("The Shining");
        newDvd.setReleaseDate(1980);
        newDvd.setMpaaRating("R");
        newDvd.setStudio("Steven King INC");
        newDvd.setComments(Arrays.asList("Jack", "Freaking", "Nicholson"));
        dvd.add(newDvd);
        int result = dvd.getByTitle("The Rainbows").size();

        Assert.assertEquals(0, result);
    }

    @Test
    public void testGetByStudio() {
        DVD newDvd = new DVD();
        newDvd.setTitle("The Shining");
        newDvd.setReleaseDate(1980);
        newDvd.setMpaaRating("R");
        newDvd.setStudio("Steven King INC");
        newDvd.setComments(Arrays.asList("Jack", "Freaking", "Nicholson"));
        dvd.add(newDvd);
        int result = dvd.getByStudio("Steven King INC").size();

        Assert.assertEquals(1, result);
    }
    
    @Test
    public void testGetByStudioNone() {
        DVD newDvd = new DVD();
        newDvd.setTitle("The Shining");
        newDvd.setReleaseDate(1980);
        newDvd.setMpaaRating("R");
        newDvd.setStudio("Steven King INC");
        newDvd.setComments(Arrays.asList("Jack", "Freaking", "Nicholson"));
        dvd.add(newDvd);
        int result = dvd.getByStudio("Merica").size();

        Assert.assertEquals(0, result);
    }
}
