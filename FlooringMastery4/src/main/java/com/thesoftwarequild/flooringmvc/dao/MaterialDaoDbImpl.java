/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.flooringmvc.dao;

import com.thesoftwarequild.flooringmvc.models.Material;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class MaterialDaoDbImpl implements MaterialDao {

    private static final String SQL_GET_MATERIAL = "SELECT * FROM material WHERE material_id = ?;";
    private static final String SQL_INSERT_MATERIAL = "INSERT INTO `material` (`state_name`, `tax_rate`) VALUES (?, ?);";
    private static final String SQL_REMOVE_MATERIAL = "DELETE FROM material WHERE material_id = ?;";
    private static final String SQL_UPDATE_MATERIAL = "UPDATE material SET state_name = ?, tax_rate = ? WHERE material_id = ?";
    private static final String SQL_LIST_MATERIALS = "SELECT * FROM material";
    private static final String SQL_SEARCH_MATERIALS = "SELECT * FROM material WHERE `material_name` LIKE CONCAT('%',?,'%')";

    JdbcTemplate template;

    public MaterialDaoDbImpl(JdbcTemplate template) {

        this.template = template;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Material add(Material material) {
        template.update(SQL_INSERT_MATERIAL,
                material.getMaterial_id(),
                material.getMaterial_name(),
                material.getMaterial_cpsf(),
                material.getLabor_cpsf());

        Integer newId = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        material.setMaterial_id(newId);

        return material;
    }

    @Override
    public Material get(Integer id) {

        Material material = template.queryForObject(SQL_GET_MATERIAL, new MaterialMapper(), id);

        return material;

    }

    @Override
    public List<Material> list() {

        List<Material> movieList = template.query(SQL_LIST_MATERIALS, new MaterialMapper());

        return movieList;

    }

    @Override
    public void remove(Integer id) {

        template.update(SQL_REMOVE_MATERIAL, id);

    }

    @Override
    public void update(Material material) {

        template.update(SQL_UPDATE_MATERIAL,
                material.getMaterial_id(),
                material.getMaterial_name(),
                material.getMaterial_cpsf(),
                material.getLabor_cpsf());

    }

    @Override
    public List<Material> searchAll(String search) {
        List<Material> materialList = template.query(SQL_SEARCH_MATERIALS, new Object[]{search}, new MaterialMapper());
        return materialList;
    }

    private static class MaterialMapper implements RowMapper<Material> {

        public MaterialMapper() {
        }

        @Override
        public Material mapRow(ResultSet rs, int i) throws SQLException {

            Material material = new Material();

            material.setMaterial_id(rs.getInt("material_id"));
            material.setMaterial_name(rs.getString("material_name"));
            material.setMaterial_cpsf(rs.getDouble("material_cpsf"));
            material.setLabor_cpsf(rs.getDouble("labor_cpsf"));

            return material;

        }
    }

}
