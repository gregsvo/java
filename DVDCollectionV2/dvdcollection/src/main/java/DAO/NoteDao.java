package DAO;

import DTO.Note;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class NoteDao {
    
    private ArrayList<Note> NotesArrayList = new ArrayList();
    final String DELIMETER = "::";
    final String FILENAME = "NotesLibrary.txt";
    private Integer nextId = 1;

    public NoteDao() {
        this.NotesArrayList = decode();
    }
    
   
    
    public ArrayList<Note> getAllNotesList()    {
        
        return NotesArrayList;
    }

    public ArrayList<Note> getNotes(Integer movieId) {
        ArrayList<Note> result = new ArrayList();
        
        for (Note i : NotesArrayList) {
            i.getMovieId();
            if (Objects.equals(movieId, i.getMovieId())) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    private int getNextID() {
        nextId++;
        return nextId;
    }

    // DECODE NOTES  
    public ArrayList<Note> decode() {
        ArrayList<Note> notes = new ArrayList<Note>();
        
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));
            
            while (sc.hasNextLine()) {
                
                String currentLine = sc.nextLine();
                String[] values = currentLine.split(DELIMETER);
                
                Note note = new Note();
                
                note.setNoteId(Integer.parseInt(values[0]));
                note.setMovieId(Integer.parseInt(values[1]));
                note.setContent(values[2]);
                notes.add(note);
            }
            
        } catch (FileNotFoundException ex) {
            
        }
        return notes;
    }

    // ENCODE NOTES
    private void encode() {
        
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILENAME)));
            
            String line = "";
            for (Note note : this.NotesArrayList) {
                
                line = note.getNoteId() + DELIMETER
                        + note.getMovieId() + DELIMETER
                        + note.getContent();
                
                out.println(line);
                out.flush();
            }
            
            out.close();
        } catch (IOException e) {
            
        }
        
    }

    public void addNote(Note aNote) {
        int noteID = getNextID();
        aNote.setNoteId(noteID);
        NotesArrayList.add(aNote);
        
        encode();
    }
}
