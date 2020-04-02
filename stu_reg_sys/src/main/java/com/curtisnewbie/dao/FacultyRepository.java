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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Faculty findById(int id) {
        // TODO Auto-generated method stub
        return null;
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