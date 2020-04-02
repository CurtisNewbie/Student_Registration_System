package com.curtisnewbie.dao;

import java.sql.Connection;
import java.util.List;

import com.curtisnewbie.model.Course;
import com.curtisnewbie.util.LoggerProducer;
import com.curtisnewbie.util.LoggerWrapper;

public class CourseRepository implements CourseDao {

    private final Connection conn = new DBManager().getConnection();
    private final LoggerWrapper logger = LoggerProducer.getLogger(this);

    @Override
    public List<Course> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Course findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean update(Course obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean create(Course obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Course findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean updateName(int id, String name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Course> findByCredit(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean updateCredit(int id, int credit) {
        // TODO Auto-generated method stub
        return false;
    }

}