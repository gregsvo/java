//*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//


import com.mycompany.dao.DvdLibraryDao;
import com.mycompany.dto.DVD;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

///**
//Ollie, Geoff, John
// *
public class TestDVDDao {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    DvdLibraryDao dvdDao;
    DVD dvd;
    int id;

    public TestDVDDao() {
        dvdDao = ctx.getBean("dvdDao", DvdLibraryDao.class);
        dvd = new DVD();

    }

    @Before
    public void setUp() {

//      dvdDao = new DVDDaoInter();
        dvd.setTitle("Test Movie");
        dvd.setReleaseDate(2015);
        dvd.setStudio("Fake Studio");
        dvd.setMpaaRating("Fake Rating");
        dvdDao.add(dvd);
        id = dvd.getId();

    }

    @After
    public void tearDown() {

        dvdDao.remove(id);

    }

// TODO add test methods here.
// The methods must be annotated with annotation @Test. For example:
//
// @Test
// public void hello() {}
    @Test
    public void checkAdd() {

        assertTrue(dvdDao.listAll().contains(dvd));
    }

    @Test
    public void searchTitle() {

        List<DVD> list = dvdDao.getByTitle("Test Movie");

        assertTrue(list.size() >= 1);

    }

    @Test
    public void searchRating() {

        assertEquals("Fake Rating", dvdDao.getById(id).getMpaaRating());

    }

    @Test
    public void searchStudio() {

        assertEquals("Fake Studio", dvdDao.getById(id).getStudio());
    }

    @Test
    public void testRemoveDvd() {

        int sizePreRemove = dvdDao.listAll().size();

        dvdDao.remove(dvd.getId());

        assertFalse(sizePreRemove == dvdDao.listAll().size());
    }

}
