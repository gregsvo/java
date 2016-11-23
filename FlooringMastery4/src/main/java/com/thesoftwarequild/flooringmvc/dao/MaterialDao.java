package com.thesoftwarequild.flooringmvc.dao;

import com.thesoftwarequild.flooringmvc.models.Material;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface MaterialDao {

    Material add(Material dvd);

    Material get(Integer id);

    List<Material> list();

    void remove(Integer id);

    void update(Material dvd);

    public List<Material> searchAll(String search);

}
