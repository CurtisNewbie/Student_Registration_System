package com.curtisnewbie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.curtisnewbie.model.Lecturer;
import com.curtisnewbie.util.LoggerProducer;
import com.curtisnewbie.util.LoggerWrapper;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Implementation of {@link com.curtisnewbie.dao.LecturerDao} for
 * {@link com.curtisnewbie.model.Lecturer} resources
 * </p>
 */
public class LecturerRepository implements LecturerDao {

    private final String SELECT_ALL = "SELECT * FROM lecturer";
    private final String SELECT_BY_ID = "SELECT * FROM lecturer WHERE id = ?";
    private final String SELECT_BY_FNAME = "SELECT * FROM lecturer WHERE firstname LIKE ?";
    private final String SELECT_BY_LNAME = "SELECT * FROM lecturer WHERE lastname LIKE ?";
    private final String SELECT_BY_POSITION = "SELECT * FROM lecturer WHERE position LIKE ?";
    private final String DELETE_BY_ID = "DELETE FROM lecturer WHERE id = ?";
    private final String UPDATE_FIRSTNAME = "UPDATE lecturer SET firstname = ? WHERE id = ?";
    private final String UPDATE_LASTNAME = "UPDATE lecturer SET lastname = ? WHERE id = ?";
    private final String UPDATE_POSITION = "UPDATE lecturer SET position = ? WHERE id = ?";
    private final String CREATE_LECTURER_WITH_ID = "INSERT INTO lecturer VALUES (?,?,?,?)";
    private final String CREATE_LECTURER_WITHOUT_ID = "INSERT INTO lecturer (firstname, lastname, position) VALUES (?,?,?)";
    private final String UPDATE_LECTURER = "UPDATE lecturer SET firstname = ?, lastname = ?, position = ? WHERE id = ?";

    private final Connection conn = DBManager.getDBManager().getConnection();
    private final LoggerWrapper logger = LoggerProducer.getLogger(this);

    @Override
    public List<Lecturer> getAll() {
        logger.info("Get all lecturer");
        List<Lecturer> list = new ArrayList<>();
        try {
            var stmt = conn.createStatement();
            ResultSet set = stmt.executeQuery(SELECT_ALL);
            while (set.next()) {
                var lec = new Lecturer(set.getInt(1), set.getString(2), set.getString(3), set.getString(4));
                list.add(lec);
            }
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
    public Lecturer findById(int id) {
        logger.info(String.format("Find id: '%d'", id));
        try {
            var stmt = conn.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, id);
            var set = stmt.executeQuery();
            Lecturer lec = null;
            if (set.next())
                lec = new Lecturer(set.getInt(1), set.getString(2), set.getString(3), set.getString(4));
            return lec;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(Lecturer lect) {
        if (lect == null) {
            logger.severe("The lecturer to be updated is null!");
            return false;
        }
        return update(lect.getFirstname(), lect.getLastname(), lect.getPosition(), lect.getId());
    }

    @Override
    public boolean update(String firstname, String lastname, String position, int id) {
        logger.info(String.format("Update lecturer to: 'id: %d, firstname: %s, lastname: %s, position: %s'", id,
                firstname, lastname, position));
        try {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_LECTURER);
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setString(3, position);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean create(Lecturer lect) {
        logger.info("Create lecturer: '" + lect.toString() + "'");
        boolean withId = true;
        if (lect.getId() == UnitDao.GENERATED_ID)
            withId = false;

        try {
            PreparedStatement stmt;
            int i = 1;
            if (withId) {
                stmt = conn.prepareStatement(CREATE_LECTURER_WITH_ID);
                stmt.setInt(i++, lect.getId());
            } else {
                stmt = conn.prepareStatement(CREATE_LECTURER_WITHOUT_ID);
            }
            stmt.setString(i++, lect.getFirstname());
            stmt.setString(i++, lect.getLastname());
            stmt.setString(i++, lect.getPosition());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateFirstname(int id, String fname) {
        logger.info(String.format("Update id: '%d', firstname updated to '%s'", id, fname));
        try {
            var stmt = conn.prepareStatement(UPDATE_FIRSTNAME);
            stmt.setString(1, fname);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateLastname(int id, String lname) {
        logger.info(String.format("Update id: '%d', lastname updated to '%s'", id, lname));
        try {
            var stmt = conn.prepareStatement(UPDATE_LASTNAME);
            stmt.setString(1, lname);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updatePosition(int id, String pos) {
        logger.info(String.format("Update id: '%d', position updated to '%s'", id, pos));
        try {
            var stmt = conn.prepareStatement(UPDATE_POSITION);
            stmt.setString(1, pos);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Lecturer> findByFirstname(String fname) {
        logger.info(String.format("Find firstname: '%s'", fname));
        List<Lecturer> list = new ArrayList<>();
        try {
            var stmt = conn.prepareStatement(SELECT_BY_FNAME);
            stmt.setString(1, "%" + fname + "%");
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                var lect = new Lecturer(set.getInt(1), set.getString(2), set.getString(3), set.getString(4));
                list.add(lect);
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Lecturer> findByLastname(String lname) {
        logger.info(String.format("Find lastname: '%s'", lname));
        List<Lecturer> list = new ArrayList<>();
        try {
            var stmt = conn.prepareStatement(SELECT_BY_LNAME);
            stmt.setString(1, "%" + lname + "%");
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                var lect = new Lecturer(set.getInt(1), set.getString(2), set.getString(3), set.getString(4));
                list.add(lect);
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Lecturer> findByPosition(String pos) {
        logger.info(String.format("Find position: '%s'", pos));
        List<Lecturer> list = new ArrayList<>();
        try {
            var stmt = conn.prepareStatement(SELECT_BY_POSITION);
            stmt.setString(1, "%" + pos + "%");
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                var lect = new Lecturer(set.getInt(1), set.getString(2), set.getString(3), set.getString(4));
                list.add(lect);
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
    }

}