package com.curtisnewbie.dao;

import java.sql.Connection;
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

    private final Connection conn = DBManager.getDBManager().getConnection();
    private final LoggerWrapper logger = LoggerProducer.getLogger(this);

    @Override
    public List<Faculty> getAll() {
        // TODO Auto-generated method stub
        return null;
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