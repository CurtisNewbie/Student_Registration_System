package com.curtisnewbie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.curtisnewbie.model.School;
import com.curtisnewbie.util.LoggerProducer;
import com.curtisnewbie.util.LoggerWrapper;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Implementation of {@link com.curtisnewbie.dao.SchoolDao} for
 * {@link com.curtisnewbie.model.School} resources
 * </p>
 */
public class SchoolRepository implements SchoolDao {

    private final String SELECT_ALL = "SELECT * FROM school";
    private final String SELECT_BY_ID = "SELECT * FROM school WHERE id = ?";
    private final String SELECT_BY_NAME = "SELECT * FROM school WHERE name = ?";
    private final String DELETE_BY_ID = "DELETE FROM school WHERE id = ?";
    private final String UPDATE_NAME = "UPDATE school SET name = ? WHERE id = ?";
    private final String CREATE_SCHOOL_WITH_ID = "INSERT INTO school VALUES (?,?,?)";
    private final String CREATE_SCHOOL_WITHOUT_ID = "INSERT INTO school (name, fac_fk) VALUES (?,?)";
    private final String UPDATE_FACULTYFK = "UPDATE school SET fac_fk = ? WHERE id = ?";
    private final String UPDATE_SCHOOL = "UPDATE school SET name = ?, fac_fk = ? WHERE id = ?";

    private final Connection conn = DBManager.getDBManager().getConnection();
    private final LoggerWrapper logger = LoggerProducer.getLogger(this);

    @Override
    public List<School> getAll() {
        logger.info("Get all schools");
        List<School> list = new ArrayList<>();
        try {
            var stmt = conn.createStatement();
            var set = stmt.executeQuery(SELECT_ALL);
            while (set.next())
                list.add(new School(set.getInt(1), set.getString(2), set.getInt(3)));
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
    public School findById(int id) {
        logger.info(String.format("Find id: '%d'", id));
        School scho = null;
        try {
            var stmt = conn.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, id);
            var set = stmt.executeQuery();
            if (set.next())
                scho = new School(set.getInt(1), set.getString(2), set.getInt(3));
            return scho;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return scho;
    }

    @Override
    public boolean update(School scho) {
        if (scho == null) {
            logger.severe("The school to be updated is null!");
            return false;
        }

        logger.info(String.format("Update school to: '%s'", scho.toString()));
        return update(scho.getId(), scho.getName(), scho.getFacultyFk());
    }

    @Override
    public boolean update(int id, String name, int facultyId) {
        if (name == null) {
            logger.severe("The name to be updated is null!");
            return false;
        }

        logger.info(
                String.format("Update id: '%d', school updated to 'name: %s, faculty_fk: %d'", id, name, facultyId));
        try {
            var stmt = conn.prepareStatement(UPDATE_SCHOOL);
            stmt.setString(1, name);
            if (facultyId != NULL_INT)
                stmt.setInt(2, facultyId);
            else
                stmt.setNull(2, Types.INTEGER);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean create(School scho) {
        if (scho == null) {
            logger.severe("The school to be created is null!");
            return false;
        }

        logger.info("Create school: '" + scho.toString() + "'");
        boolean withId = true;
        if (scho.getId() == UnitDao.GENERATED_ID)
            withId = false;

        try {
            PreparedStatement stmt;
            int i = 1;
            if (withId) {
                stmt = conn.prepareStatement(CREATE_SCHOOL_WITH_ID);
                stmt.setInt(i++, scho.getId());
            } else {
                stmt = conn.prepareStatement(CREATE_SCHOOL_WITHOUT_ID);
            }
            stmt.setString(i++, scho.getName());
            stmt.setInt(i++, scho.getFacultyFk());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public School findByName(String name) {
        School scho = null;
        if (name == null) {
            logger.severe("The name to be found is null!");
            return scho;
        }

        logger.info(String.format("Find name: '%s'", name));
        try {
            var stmt = conn.prepareStatement(SELECT_BY_NAME);
            stmt.setString(1, name);
            var set = stmt.executeQuery();
            if (set.next()) {
                scho = new School(set.getInt(1), set.getString(2), set.getInt(3));
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return scho;
    }

    @Override
    public boolean updateName(int id, String name) {
        if (name == null) {
            logger.severe("The name to be updated is null!");
            return false;
        }

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
    public boolean updateFacultyFk(int id, int facultyId) {
        try {
            var stmt = conn.prepareStatement(UPDATE_FACULTYFK);
            if (facultyId != NULL_INT && facultyId > 0)
                stmt.setInt(1, facultyId);
            else
                stmt.setNull(1, Types.INTEGER);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }
}