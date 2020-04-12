package com.curtisnewbie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private final String SELECT_BY_NAME = "SELECT * FROM faculty WHERE name = ?";
    private final String DELETE_BY_ID = "DELETE FROM faculty WHERE id = ?";
    private final String UPDATE_NAME = "UPDATE faculty SET name = ? WHERE id = ?";
    private final String CREATE_FACULTY_WITH_ID = "INSERT INTO faculty VALUES (?,?)";
    private final String CREATE_FACULTY_WITHOUT_ID = "INSERT INTO faculty (name) VALUES (?)";

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
    public boolean update(Faculty facu) {
        if (facu == null) {
            logger.severe("The faculty to be updated is null!");
            return false;
        }

        logger.info(String.format("Update faculty to: '%s'", facu.toString()));
        return updateName(facu.getId(), facu.getName());
    }

    @Override
    public boolean create(Faculty facu) {
        logger.info("Create faculty: '" + facu.toString() + "'");
        boolean withId = true;
        if (facu.getId() == UnitDao.GENERATED_ID)
            withId = false;

        try {
            PreparedStatement stmt;
            int i = 1;
            if (withId) {
                stmt = conn.prepareStatement(CREATE_FACULTY_WITH_ID);
                stmt.setInt(i++, facu.getId());
            } else {
                stmt = conn.prepareStatement(CREATE_FACULTY_WITHOUT_ID);
            }
            stmt.setString(i++, facu.getName());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public Faculty findByName(String name) {
        logger.info(String.format("Find name: '%s'", name));
        Faculty facu = null;
        try {
            var stmt = conn.prepareStatement(SELECT_BY_NAME);
            stmt.setString(1, name);
            var set = stmt.executeQuery();
            if (set.next()) {
                facu = new Faculty(set.getInt(1), set.getString(2));
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return facu;
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
}