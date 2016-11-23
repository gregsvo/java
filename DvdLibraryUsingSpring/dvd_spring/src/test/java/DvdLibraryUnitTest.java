/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.dao.DvdLibraryDao;
import com.mycompany.dto.DVD;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
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
public class DvdLibraryUnitTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    DvdLibraryDao dao;

    DVD dvd1 = new DVD();
    DVD dvd2 = new DVD();
    DVD dvd3 = new DVD();

    public DvdLibraryUnitTest() {
    }

    @Before
    public void setUp() {
        dao = ctx.getBean("dvdDao", DvdLibraryDao.class);

        dvd1.setTitle("Lock Stock and Two Smoking Barrels");
        dvd1.setId(1);
        dvd1.setMpaaRating("R");
        dvd1.setReleaseDate(1997);
        dvd1.setStudio("Bullshit Way");

        dvd2.setTitle("Cheech And Chong Go To Paris");
        dvd2.setId(2);
        dvd2.setMpaaRating("R");
        dvd2.setReleaseDate(1976-03-01);
        dvd2.setStudio("Doobs");

        dvd3.setTitle("Beauty and the Beast");
        dvd3.setId(3);
        dvd3.setMpaaRating("NC-17");
        dvd3.setReleaseDate(1992-05-01);
        dvd3.setStudio("Disney");

    }

    @After
    public void tearDown() {
    }

    @Test
    public void addDvdMultiple() {

        dao.add(dvd1);
        dao.add(dvd2);
        dao.add(dvd3);

        int size = dao.listAll().size();

        Assert.assertEquals(size, 3);
    }

    @Test
    public void addDvdSingle() {
        dao.add(dvd1);

        int size = dao.listAll().size();

        Assert.assertEquals(size, 1);
    }

    @Test
    public void getByIdMultiple() {
        dao.add(dvd1);
        dao.add(dvd2);
        dao.add(dvd3);

        DVD thisDVD = dao.getById(1);
        DVD thatDVD = dao.getById(2);
        DVD theOtherDVD = dao.getById(3);

        Assert.assertEquals(thisDVD, dvd1);
        Assert.assertEquals(dvd2, thatDVD);
        Assert.assertEquals(dvd3, theOtherDVD);

    }

    @Test
    public void getByIdSingle() {
        dao.add(dvd1);

        DVD thisDVD = dao.getById(1);

        Assert.assertEquals(thisDVD, dvd1);

    }

    @Test
    public void getByIdNull() {
            DVD thisDVD = dao.getById(1);
            
            Assert.assertEquals(null, thisDVD);
    }

    @Test
    public void getByTitleMultiple() {
        dao.add(dvd1);
        dao.add(dvd2);
        dao.add(dvd3);

        List<DVD> titleList1 = dao.getByTitle(dvd1.getTitle());
        List<DVD> titleList2 = dao.getByTitle(dvd2.getTitle());
        List<DVD> titleList3 = dao.getByTitle(dvd3.getTitle());

        Assert.assertEquals("Lock Stock and Two Smoking Barrels", titleList1.get(0).getTitle());
        Assert.assertEquals("Cheech And Chong Go To Paris", titleList2.get(0).getTitle());
        Assert.assertEquals("Beauty and the Beast", titleList3.get(0).getTitle());
    }

    @Test
    public void getByTitleSingle() {
        dao.add(dvd1);

        List<DVD> titleList1 = dao.getByTitle(dvd1.getTitle());

        Assert.assertEquals("Lock Stock and Two Smoking Barrels", titleList1.get(0).getTitle());
    }

    @Test
    public void getByTitleEmpty() {

        List<DVD> thisDVD = dao.getByTitle("Bad Title");
        Assert.assertTrue(thisDVD.isEmpty());

    }

    @Test
    public void getByRatingMultiple() {
        dao.add(dvd1);
        dao.add(dvd2);
        dao.add(dvd3);

        List<DVD> ratingList1 = dao.getByRating(dvd1.getMpaaRating());
        List<DVD> ratingList2 = dao.getByRating(dvd2.getMpaaRating());
        List<DVD> ratingList3 = dao.getByRating(dvd3.getMpaaRating());

        Assert.assertEquals("R", ratingList1.get(0).getMpaaRating());
        Assert.assertEquals("R", ratingList2.get(0).getMpaaRating());
        Assert.assertEquals("NC-17", ratingList3.get(0).getMpaaRating());

    }

    @Test
    public void getByRatingSingle() {
        dao.add(dvd1);

        List<DVD> ratingList1 = dao.getByRating(dvd1.getMpaaRating());

        Assert.assertEquals("R", ratingList1.get(0).getMpaaRating());
    }
    
 @Test
    public void getByRatingEmpty() {

        List<DVD> thisDVD = dao.getByRating("G");
        Assert.assertTrue(thisDVD.isEmpty());

    }
    
    @Test
    public void getByStudioMultiple() {
        dao.add(dvd1);
        dao.add(dvd2);
        dao.add(dvd3);

        List<DVD> studioList1 = dao.getByStudio(dvd1.getStudio());
        List<DVD> studioList2 = dao.getByStudio(dvd2.getStudio());
        List<DVD> studioList3 = dao.getByStudio(dvd3.getStudio());

        Assert.assertEquals("Bullshit Way", studioList1.get(0).getStudio());
        Assert.assertEquals("Doobs", studioList2.get(0).getStudio());
        Assert.assertEquals("Disney", studioList3.get(0).getStudio());

    }

    @Test
    public void getByStudioSingle() {
        dao.add(dvd1);

        List<DVD> studioList1 = dao.getByStudio(dvd1.getStudio());

        Assert.assertEquals("Bullshit Way", studioList1.get(0).getStudio());
    }
    
 @Test
    public void getByStudioEmpty() {

        List<DVD> thisDVD = dao.getByStudio("NonExistant Studio");
        Assert.assertTrue(thisDVD.isEmpty());

    }
}
