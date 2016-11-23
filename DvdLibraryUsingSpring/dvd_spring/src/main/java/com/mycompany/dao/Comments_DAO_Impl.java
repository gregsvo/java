/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dto.Comments;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class Comments_DAO_Impl implements DaoInterfaceComments {

    private Comments commentToDo = new Comments();

    @Override
    public void createComment(String comment) {
        commentToDo.addComment(comment);
    }

    @Override
    public List<String> getComments() {
        return commentToDo.getComments();
    }

    @Override
    public String[] getUpdateCommentPrompt(List<String> currentComments) {
        commentToDo.setComments(currentComments);
        String[] promptList = new String[currentComments.size()*2 + 1];

        String add = "Add a comment";
        promptList[0] = add;
        int i = 1;
        for (String currentString : currentComments) {
            promptList[i] = "Delete: " + currentString;
            i++;
        }
        for (String currentString : currentComments) {
            promptList[i] = "Modify: " + currentString;
            i++;
        }

        return promptList;

    }

    @Override
    public void modifyComment(String commentBefore, String addition, boolean modifyIsBefore) {
        List<String> oldComments = commentToDo.getComments();
        List<String> mutable = new LinkedList<>(oldComments);
        if (modifyIsBefore) {
            mutable
                    .stream()
                    .filter(x -> x.equals(commentBefore))
                    .forEach(z -> mutable.set(mutable.indexOf(z), addition + " " + commentBefore));
        } else if (!modifyIsBefore) {
            mutable
                    .stream()
                    .filter(x -> x.equals(commentBefore))
                    .forEach(z -> mutable.set(mutable.indexOf(z), addition + " " + commentBefore));
        }
        commentToDo.setComments(mutable);
    }

    @Override
    public void deleteSelectionInComment(String commentBefore, String whatToDelete) {
        List<String> oldComments = commentToDo.getComments();
        List<String> mutable = new LinkedList<>(oldComments);
        
        mutable
                .stream()
                .filter(x -> x.equals(commentBefore))
                .filter(y -> y.contains(whatToDelete))
                .forEach(z -> {
                    mutable.set(z.indexOf(z), z.replaceFirst(whatToDelete, ""));
                    commentToDo.setComments(mutable);
                });

    }

    @Override
    public void deleteComment(String comment) {
        List<String> oldComments = commentToDo.getComments();
        
        List<String> mutable = new LinkedList<>(oldComments);
        
        int index = mutable.indexOf(comment);
        mutable.remove(index);
        
        oldComments = mutable;
        commentToDo.setComments(oldComments);
    }
    
    @Override
    public void addCommentToExistingList(String whatToAdd){
        List<String> oldComments = commentToDo.getComments();
        List<String> mutable = new LinkedList<>(oldComments);
        
        mutable.add(whatToAdd);
        
        commentToDo.setComments(mutable);
    }

}
