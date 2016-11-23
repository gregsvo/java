/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.flooringmvc.dao;

import com.thesoftwarequild.flooringmvc.models.Order;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrderDao {

    Order add(Order dvd);
    Order get(Integer id);
    List<Order> list();
    void remove(Integer id);
    void update(Order dvd);
   public List<Order> searchAll(String search);
    
}
