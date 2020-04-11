package com.curtisnewbie.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.curtisnewbie.model.Course;
import com.curtisnewbie.model.Lecturer;
import com.curtisnewbie.model.Module;
import com.curtisnewbie.model.School;
import com.curtisnewbie.model.Student;
import com.curtisnewbie.util.LoggerProducer;
import com.curtisnewbie.util.LoggerWrapper;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Implementation of CommonDao interface
 * </p>
 * 
 * @see {@link com.curtisnewbie.dao.CommonDao}
 */
public class CommonDaoImpl implements CommonDao {

    private final String ADD_LECU_TO_MODU = "INSERT INTO lecturer_module VALUES (?,?)";
    private final String REMOVE_LECT_FROM_MODU = "DELETE FROM lecturer_module WHERE lec_fk = ? AND mod_fk = ?";
    private final String ADD_MODU_TO_COUR = "INSERT INTO course_module VALUES (?,?)";
    private final String REMOVE_MODU_FROM_COUR = "DELETE FROM course_module WHERE cou_fk = ? AND mod_fk = ?";
    private final String ADD_STUD_TO_MODU = "INSERT INTO student_module VALUES (?,?)";
    private final String REMOVE_STUD_FROM_MODU = "DELETE FROM student_module WHERE stu_fk = ? AND mod_fk = ?";
    private final String GET_MODU_IN_COUR = "SELECT m.* FROM course c INNER JOIN course_module cm on c.id = cm.cou_fk INNER JOIN module m on m.id = cm.mod_fk WHERE c.id = ?";
    private final String GET_MODU_OF_LECT = "SELECT m.* FROM module m INNER JOIN lecturer_module lm ON m.id = lm.mod_fk WHERE lm.lec_fk = ?";
    private final String GET_STUD_IN_MODU = "SELECT s.* FROM student s INNER JOIN student_module sm ON s.id = sm.stu_fk WHERE sm.mod_fk = ?";
    private final String GET_MODU_OF_STUD = "SELECT m.* FROM module m INNER JOIN student_module sm ON m.id = sm.mod_fk WHERE sm.stu_fk = ?";
    private final String GET_COUR_OF_MODU = "SELECT c.* FROM course c INNER JOIN course_module cm on c.id = cm.cou_fk WHERE cm.mod_fk = ?";
    private final String GET_LECT_IN_MODU = "SELECT l.* FROM lecturer l INNER JOIN lecturer_module lm on l.id = lm.lec_fk WHERE lm.mod_fk = ?";
    private final String GET_SCHO_IN_FAC = "SELECT s.* FROM school s WHERE s.fac_fk = ?";
    private final String GET_COUR_IN_SCHO = "SELECT c.* FROM course c WHERE c.sch_fk = ?";

    private final LoggerWrapper logger = LoggerProducer.getLogger(this);
    private final Connection conn = DBManager.getDBManager().getConnection();

    @Override
    public boolean addLectToModu(int moduleId, int lecturerId) {
        logger.info(String.format("Add lecturer: %d to module: %d", lecturerId, moduleId));
        return execUpdate(ADD_LECU_TO_MODU, lecturerId, moduleId);
    }

    @Override
    public boolean removeLectFromModu(int moduleId, int lecturerId) {
        logger.info(String.format("Remove lecturer: %d from module: %d", lecturerId, moduleId));
        return execUpdate(REMOVE_LECT_FROM_MODU, lecturerId, moduleId);
    }

    @Override
    public boolean addModuToCour(int courseId, int moduleId) {
        logger.info(String.format("Add module: %d to course: %d", moduleId, courseId));
        return execUpdate(ADD_MODU_TO_COUR, courseId, moduleId);
    }

    @Override
    public boolean removeModuFromCour(int courseId, int moduleId) {
        logger.info(String.format("Remove course: %d from module: %d", courseId, moduleId));
        return execUpdate(REMOVE_MODU_FROM_COUR, courseId, moduleId);
    }

    @Override
    public List<Module> getAllModuInCour(int courseId) {
        logger.info(String.format("Get all modules in course: %d", courseId));
        return execGetModule(GET_MODU_IN_COUR, courseId);
    }

    @Override
    public List<Lecturer> getAllLectInModu(int moduleId) {
        logger.info(String.format("Get all lecturers in module : %d", moduleId));
        List<Lecturer> list = new ArrayList<>();
        try {
            var stmt = conn.prepareStatement(GET_LECT_IN_MODU);
            stmt.setInt(1, moduleId);
            var set = stmt.executeQuery();
            while (set.next())
                list.add(new Lecturer(set.getInt(1), set.getString(2), set.getString(3), set.getString(4)));
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Module> getAllModuOfLect(int lecturerId) {
        logger.info(String.format("Get all modules of lecturer: %d", lecturerId));
        return execGetModule(GET_MODU_OF_LECT, lecturerId);
    }

    @Override
    public List<Course> getAllCourOfModu(int moduleId) {
        logger.info(String.format("Get all course of module : %d", moduleId));
        List<Course> list = new ArrayList<>();
        try {
            var stmt = conn.prepareStatement(GET_COUR_OF_MODU);
            stmt.setInt(1, moduleId);
            var set = stmt.executeQuery();
            while (set.next())
                list.add(new Course(set.getInt(1), set.getString(2), set.getInt(3), set.getInt(4), set.getInt(5)));
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
    }

    @Override
    public boolean addStudToModu(int moduleId, int studentId) {
        logger.info(String.format("Add student: %d to module: %d", studentId, moduleId));
        return execUpdate(ADD_STUD_TO_MODU, studentId, moduleId);
    }

    @Override
    public boolean removeStudFromModu(int moduleId, int studentId) {
        logger.info(String.format("Remove student: %d from module: %d", studentId, moduleId));
        return execUpdate(REMOVE_STUD_FROM_MODU, studentId, moduleId);
    }

    @Override
    public List<Student> getAllStudInModu(int moduleId) {
        logger.info(String.format("Get all students in module: %d", moduleId));
        List<Student> list = new ArrayList<>();
        try {
            var stmt = conn.prepareStatement(GET_STUD_IN_MODU);
            stmt.setInt(1, moduleId);
            var set = stmt.executeQuery();
            while (set.next())
                list.add(new Student(set.getInt(1), set.getString(2), set.getString(3), set.getDate(4), set.getInt(5)));
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Module> getAllModuOfStud(int studentId) {
        logger.info(String.format("Get all modules of student: %d", studentId));
        return execGetModule(GET_MODU_OF_STUD, studentId);
    }

    /**
     * Helper method that execute query to update db. Be aware that there is an
     * order of which the parameters are inserted into the query.
     * 
     * @param query parameterised sql query
     * @param param parameters that will be inserted into the sql query.
     * @return {@code True} if successful else {@code False}
     */
    private boolean execUpdate(final String query, final int... param) {
        try {
            var stmt = conn.prepareStatement(query);
            for (int i = 0; i < param.length; i++) {
                stmt.setInt(i + 1, param[i]);
            }
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    /**
     * Helper method that execute query to retrieves a list of Module
     * 
     * @param query parameterised sql query
     * @param param parameter that will be inserted in to the query
     * @param model the type of the object that is expected to be retrieved
     * @return A {@code List} of expected objects
     * 
     */
    private List<Module> execGetModule(final String query, final int param) {
        List<Module> list = new ArrayList<>();
        try {
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, param);
            var set = stmt.executeQuery();
            while (set.next())
                list.add(new Module(set.getInt(1), set.getString(2), set.getInt(3)));
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
    }

    @Override
    public List<School> getAllSchoInFacu(int facultyId) {
        logger.info(String.format("Get all schools in faculty : %d", facultyId));
        List<School> list = new ArrayList<>();
        try {
            var stmt = conn.prepareStatement(GET_SCHO_IN_FAC);
            stmt.setInt(1, facultyId);
            var set = stmt.executeQuery();
            while (set.next())
                list.add(new School(set.getInt(1), set.getString(2), set.getInt(3)));
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Course> getAllCourInScho(int schoolId) {
        logger.info(String.format("Get all courses in school : %d", schoolId));
        List<Course> list = new ArrayList<>();
        try {
            var stmt = conn.prepareStatement(GET_COUR_IN_SCHO);
            stmt.setInt(1, schoolId);
            var set = stmt.executeQuery();
            while (set.next())
                list.add(new Course(set.getInt(1), set.getString(2), set.getInt(3), set.getInt(4), set.getInt(5)));
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return list;
    }
}