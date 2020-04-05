package com.curtisnewbie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.curtisnewbie.model.Module;
import com.curtisnewbie.util.LoggerProducer;
import com.curtisnewbie.util.LoggerWrapper;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Implementation of {@link com.curtisnewbie.dao.ModuleDao} for
 * {@link com.curtisnewbie.model.Module} resources
 * </p>
 */
public class ModuleRepository implements ModuleDao {

    private final String SELECT_ALL = "SELECT * FROM module";
    private final String SELECT_BY_ID = "SELECT * FROM module WHERE id = ?";
    private final String SELECT_BY_NAME = "SELECT * FROM module WHERE name = ?";
    private final String SELECT_BY_CREDIT = "SELECT * FROM module WHERE credit = ?";
    private final String DELETE_BY_ID = "DELETE FROM module WHERE id = ?";
    private final String UPDATE_NAME = "UPDATE module SET name = ? WHERE id = ?";
    private final String UPDATE_CREDIT = "UPDATE module SET credit = ? WHERE id = ?";
    private final String CREATE_MODULE_WITH_ID = "INSERT INTO module VALUES (?,?,?)";
    private final String CREATE_MODULE_WITHOUT_ID = "INSERT INTO module (name, credit) VALUES (?,?)";

    private final Connection conn = DBManager.getDBManager().getConnection();
    private final LoggerWrapper logger = LoggerProducer.getLogger(this);

    @Override
    public List<Module> getAll() {
        logger.info("Get all modules");
        List<Module> list = new ArrayList<>();
        try {
            var stmt = conn.createStatement();
            var set = stmt.executeQuery(SELECT_ALL);
            while (set.next())
                list.add(new Module(set.getInt(1), set.getString(2), set.getInt(3)));
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
    public Module findById(int id) {
        logger.info(String.format("Find id: '%d'", id));
        Module modu = null;
        try {
            var stmt = conn.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, id);
            var set = stmt.executeQuery();
            if (set.next())
                modu = new Module(set.getInt(1), set.getString(2), set.getInt(3));
            return modu;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return modu;
    }

    @Override
    public boolean update(Module modu) {
        logger.info(String.format("Update module to: '%s'", modu.toString()));
        try {
            conn.setAutoCommit(false);
            if (findById(modu.getId()) != null) {
                boolean succeeded = true;
                if (!updateName(modu.getId(), modu.getName()))
                    succeeded = false;

                if (succeeded && !updateCredit(modu.getId(), modu.getCredit()))
                    succeeded = false;

                if (succeeded) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                }
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException e1) {
                logger.severe(e1.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean create(Module modu) {
        logger.info("Create module: '" + modu.toString() + "'");
        boolean withId = true;
        if (modu.getId() == UnitDao.GENERATED_ID)
            withId = false;

        try {
            PreparedStatement stmt;
            int i = 1;
            if (withId) {
                stmt = conn.prepareStatement(CREATE_MODULE_WITH_ID);
                stmt.setInt(i++, modu.getId());
            } else {
                stmt = conn.prepareStatement(CREATE_MODULE_WITHOUT_ID);
            }
            stmt.setString(i++, modu.getName());
            stmt.setInt(i++, modu.getCredit());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public Module findByName(String name) {
        logger.info(String.format("Find name: '%s'", name));
        Module modu = null;
        try {
            var stmt = conn.prepareStatement(SELECT_BY_NAME);
            stmt.setString(1, name);
            var set = stmt.executeQuery();
            if (set.next()) {
                modu = new Module(set.getInt(1), set.getString(2), set.getInt(3));
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return modu;
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
    public List<Module> findByCredit(int credit) {
        logger.info(String.format("Find credit: %d", credit));
        List<Module> list = new ArrayList<>();
        try {
            var stmt = conn.prepareStatement(SELECT_BY_CREDIT);
            stmt.setInt(1, credit);
            var set = stmt.executeQuery();
            while (set.next())
                list.add(new Module(set.getInt(1), set.getString(2), set.getInt(3)));
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
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