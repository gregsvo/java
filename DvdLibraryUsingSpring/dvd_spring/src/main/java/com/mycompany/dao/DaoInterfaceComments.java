/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import java.util.List;

/**
 *
 * @author apprentice
 */
public interface DaoInterfaceComments {

    void addCommentToExistingList(String whatToAdd);

    void createComment(String comment);

    void deleteComment(String comment);

    void deleteSelectionInComment(String commentBefore, String whatToDelete);

    List<String> getComments();

    String[] getUpdateCommentPrompt(List<String> currentComments);

    void modifyComment(String commentBefore, String addition, boolean modifyIsBefore);
    
}
