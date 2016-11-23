/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dto.Product;
import java.lang.reflect.InvocationTargetException;


/**
 *
 * @author apprentice
 */
public interface iDAO {
    public void create(String... args);
    public boolean update (String... args);
    public <T> T get(String... args);
    public void delete(String... args);
    public void encode();
    public void decode();
    public String getName();
    public String[] getExistingProducts();
    public String[] getMethods();
    
    
}
