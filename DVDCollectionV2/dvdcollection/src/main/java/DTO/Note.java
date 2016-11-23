package DTO;

/**
 *
 * @author apprentice
 */
public class Note {

    private Integer noteId;

    private Integer movieId;

    private String content;

    /**
     * @return the noteId
     */
    public Integer getNoteId() {
        return noteId;
    }

    /**
     * @param noteId the noteId to set
     */
    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    /**
     * @return the movieId
     */
    public Integer getMovieId() {
        return movieId;
    }

    /**
     * @param movieId the movieId to set
     */
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

}
