package com.curtisnewbie.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.curtisnewbie.model.Faculty;
import com.curtisnewbie.util.LoggerProducer;
import com.curtisnewbie.util.LoggerWrapper;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Implementation of {@link com.curtisnewbie.dao.FacultyDao} for
 * {@link com.curtisnewbie.model.Faculty} resources
 * </p>
 */
public class FacultyRepository implements FacultyDao {

    private final String SELECT_ALL = "SELECT * FROM faculty";
    private final String SELECT_BY_ID = "SELECT * FROM faculty WHERE id = ?";
    private final String DELETE_BY_ID = "DELETE FROM faculty WHERE id = ?";

    private final Connection conn = DBManager.getDBManager().getConnection();
    private final LoggerWrapper logger = LoggerProducer.getLogger(this);

    @Override
    public List<Faculty> getAll() {
        logger.info("Get all faculties");
        List<Faculty> list = new ArrayList<>();
        try {
            var stmt = conn.createStatement();
            var set = stmt.executeQuery(SELECT_ALL);
            while (set.next())
                list.add(new Faculty(set.getInt(1), set.getString(2)));
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
    }

    @Override
    public boolean deleteById(int id) {
        logger.info(String.format("Delete id: '%d'", id));
        try {
            var stmt = conn.prepareStatement(DELETE_BY_ID);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public Faculty findById(int id) {
        logger.info(String.format("Find id: '%d'", id));
        Faculty facu = null;
        try {
            var stmt = conn.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, id);
            var set = stmt.executeQuery();
            if (set.next())
                facu = new Faculty(set.getInt(1), set.getString(2));
            return facu;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return facu;
    }

    @Override
    public boolean update(Faculty obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean create(Faculty obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Faculty findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean updateName(int id, String name) {
        // TODO Auto-generated method stub
        return false;
    }
}