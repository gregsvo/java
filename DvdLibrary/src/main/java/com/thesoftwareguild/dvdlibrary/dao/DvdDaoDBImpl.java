package com.thesoftwareguild.dvdlibrary.dao;

import com.thesoftwareguild.dvdlibrary.models.Movie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ollie
 */
public class DvdDaoDBImpl implements DvdDao {

    private static final String SQL_GET_MOVIE = "SELECT * FROM movie WHERE id = ?;";
    private static final String SQL_INSERT_MOVIE = "INSERT INTO `movie` (`title`, `director`, `studio`, `mpaa_rating`, `release_year`) VALUES (?, ?, ?, ?, ?);";
    private static final String SQL_REMOVE_MOVIE = "DELETE FROM movie WHERE id = ?;";
    private static final String SQL_UPDATE_MOVIE = "UPDATE movie SET title = ?, director = ?, studio = ?, mpaa_rating = ?, release_year = ? WHERE id = ?";
    private static final String SQL_LIST_MOVIE = "SELECT * FROM movie";
    private static final String SQL_GET_DIRECTORS = "SELECT * FROM company c INNER JOIN contact_company cc ON c.id = cc.company_id WHERE cc.contact_id = ?";
    private static final String SQL_SEARCH_MOVIES = "SELECT * FROM movie WHERE `title` LIKE CONCAT('%',?,'%') OR `release_year` LIKE CONCAT('%',?,'%') OR `mpaa_rating` LIKE CONCAT('%',?,'%') OR `studio` LIKE CONCAT('%',?,'%') OR `director` LIKE CONCAT('%',?,'%')";
    
    JdbcTemplate template;

    public DvdDaoDBImpl(JdbcTemplate template) {

        this.template = template;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Movie add(Movie dvd) {
        template.update(SQL_INSERT_MOVIE,
                dvd.getTitle(),
                dvd.getDirector(),
                dvd.getStudio(),
                dvd.getMpaaRating(),
                dvd.getReleaseYear());

        Integer newId = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        dvd.setId(newId);

        return dvd;
    }

    @Override
    public Movie get(Integer id) {

        Movie movie = template.queryForObject(SQL_GET_MOVIE, new MovieMapper(), id);

        return movie;

    }

    @Override
    public List<Movie> list() {

        List<Movie> movieList = template.query(SQL_LIST_MOVIE, new MovieMapper());

        return movieList;

    }

    @Override
    public void remove(Integer id) {

        template.update(SQL_REMOVE_MOVIE, id);

    }

    @Override
    public void update(Movie dvd) {

        template.update(SQL_UPDATE_MOVIE,
                dvd.getTitle(),
                dvd.getDirector(),
                dvd.getStudio(),
                dvd.getMpaaRating(),
                dvd.getReleaseYear(),
                dvd.getId());

    }

    @Override
    public List<Movie> searchAll(String search) {
        List<Movie> movieList = template.query(SQL_SEARCH_MOVIES, new Object[]{search, search, search, search, search}, new MovieMapper());
    return movieList;
    }

    private static class MovieMapper implements RowMapper<Movie> {

        public MovieMapper() {
        }

        @Override
        public Movie mapRow(ResultSet rs, int i) throws SQLException {

            Movie movie = new Movie();

            movie.setId(rs.getInt("id"));
            movie.setTitle(rs.getString("title"));
            movie.setDirector(rs.getString("director"));
            movie.setMpaaRating(rs.getString("mpaa_rating"));
            movie.setReleaseYear(rs.getString("release_year"));
            movie.setStudio(rs.getString("studio"));

            return movie;

        }
    }

}
