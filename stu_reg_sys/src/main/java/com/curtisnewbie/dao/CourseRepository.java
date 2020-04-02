package com.curtisnewbie.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.curtisnewbie.model.Course;
import com.curtisnewbie.util.LoggerProducer;
import com.curtisnewbie.util.LoggerWrapper;

public class CourseRepository implements CourseDao {

    private final String SELECT_ALL = "SELECT * FROM course";

    private final String SELECT_BY_ID = "SELECT * FROM course WHERE id = ?";

    private final Connection conn = new DBManager().getConnection();
    private final LoggerWrapper logger = LoggerProducer.getLogger(this);

    @Override
    public List<Course> getAll() {
        logger.info("Get all courses");
        List<Course> list = new ArrayList<>();
        try {
            var stmt = conn.createStatement();
            var set = stmt.executeQuery(SELECT_ALL);
            while (set.next())
                list.add(new Course(set.getInt(1), set.getString(2), set.getInt(3)));
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
    public Course findById(int id) {
        logger.info(String.format("Find id: '%d'", id));
        Course cour = null;
        try {
            var stmt = conn.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, id);
            var set = stmt.executeQuery();
            if (set.next())
                cour = new Course(set.getInt(1), set.getString(2), set.getInt(3));
            return cour;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return cour;
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