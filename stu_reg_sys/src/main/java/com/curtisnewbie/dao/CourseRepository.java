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
    private final String SELECT_BY_NAME = "SELECT * FROM course WHERE name = ?";
    private final String DELETE_BY_ID = "DELETE FROM course WHERE id = ?";
    private final String UPDATE_NAME = "UPDATE course SET name = ? WHERE id = ?";
    private final String UPDATE_CREDIT = "UPDATE course SET credit = ? WHERE id = ?";

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
        return false;
    }

    @Override
    public boolean create(Course obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Course findByName(String name) {
        logger.info(String.format("Find name: '%s'", name));
        Course cour = null;
        try {
            var stmt = conn.prepareStatement(SELECT_BY_NAME);
            stmt.setString(1, name);
            var set = stmt.executeQuery();
            if (set.next()) {
                cour = new Course(set.getInt(1), set.getString(2), set.getInt(3));
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return cour;
    }

    @Override
    public boolean updateName(int id, String name) {
        logger.info(String.format("Update id: '%d', name updated to '%s'", id, name));
        try {
            var stmt = conn.prepareStatement(UPDATE_NAME);
            stmt.setString(1, name);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Course> findByCredit(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean updateCredit(int id, int credit) {
        logger.info(String.format("Update id: '%d', credit updated to '%s'", id, credit));
        try {
            var stmt = conn.prepareStatement(UPDATE_CREDIT);
            stmt.setInt(1, credit);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

}