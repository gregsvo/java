
import com.mycompany.dao.DvdLibraryDao;
import com.mycompany.dto.DVD;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class DvdUnitTestsHankQavi {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    DvdLibraryDao dvdDao;
    DVD dvd;

    public DvdUnitTestsHankQavi() {

        dvdDao = ctx.getBean("dvdDao", DvdLibraryDao.class);
    }

    @Before
    public void setUp() {
        dvd = new DVD();
        dvd.setTitle("Test");
        dvd.setReleaseDate(2000);
        dvd.setMpaaRating("R");
        dvd.setStudio("Fox");
        dvdDao.add(dvd);

    }

    @After
    public void tearDown() {
        dvdDao.remove(dvd.getId());
    }

    @Test
    public void testAdd() {
        assertTrue(dvdDao.listAll().contains(dvd));
    }

    @Test
    public void testRemove() {
        dvdDao.remove(dvd.getId());
        assertFalse(dvdDao.listAll().contains(dvd));
    }

    @Test
    public void testGetByTitle() {
        List<DVD> result = dvdDao.getByTitle("Test");
        assertTrue(result.size() >= 1);
    }

//    @Test
//    public void testSearchByDirector() {
//        List<Dvd> result = dvdDao.searchByDirector("Timmy");
//        assertTrue(result.size() >= 1);
//    }
    @Test
    public void testUpdate() {
        dvd.setTitle("Testeeeeeeeeeeeee");

        dvdDao.update(dvd);
        Assert.assertTrue(dvd.getTitle().equals("Testeeeeeeeeeeeee"));
    }
}
