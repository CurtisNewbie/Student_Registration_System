package com.curtisnewbie.dao;

import java.util.List;

import com.curtisnewbie.model.Course;
import com.curtisnewbie.model.Lecturer;
import com.curtisnewbie.model.Module;
import com.curtisnewbie.model.School;
import com.curtisnewbie.model.Student;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * CommonDao that specifies methods for handling data persistence throughout the
 * system. I.e., this CommonDao serves the whole system, for those that serves a
 * specific model, see {@link com.curtisnewbie.dao.UnitDao}.
 * </p>
 * <p>
 * Some relationships between tables are many-to-many relationship, determining
 * which one is the owner of the relationship is completely subjective or
 * random.
 * </p>
 * <p>
 * For example, the Module-Lecturer is a many-to-many relationship, we can have
 * method like: {@code addLecturerToModule()} or {@code addModuleToLecturer()},
 * this doesn't matter since we need their ids anyway.
 * </p>
 * 
 * @see {@link com.curtisnewbie.dao.UnitDao}
 * 
 */
public interface CommonDao {

    /**
     * Add a {@code Lecturer} to a {@code Module}
     * 
     * @param moduleId
     * @param lecturerId
     * 
     * @return {@code True} if successful else {@code False}
     */
    public boolean addLectToModu(int moduleId, int lecturerId);

    /**
     * Remove a {@code Lecturer} from a {@code Module}
     * 
     * @param moduleId
     * @param lecturerId
     * @return {@code True} if successful else {@code False}
     */
    public boolean removeLectFromModu(int moduleId, int lecturerId);

    /**
     * Get all {@code Lecturer}(s) in a {@code Module}
     * 
     * @param moduleId
     * @return A list of {@code Lecturer}(s) in this {@code Module}
     */
    public List<Lecturer> getAllLectInModu(int moduleId);

    /**
     * Get all {@code Module}(s) of a {@code Lecturer}
     * 
     * @param lecturerId
     * @return A list of {@code Module}(s) of this {@code Lecturer}
     */
    public List<Module> getAllModuOfLect(int lecturerId);

    /**
     * Add a {@code Module} to the {@code Course}
     * 
     * @param courseId
     * @param moduleId
     * @return {@code True} if successful else {@code False}
     */
    public boolean addModuToCour(int courseId, int moduleId);

    /**
     * remove a {@code Module} from a {@code Course}
     * 
     * @param courseId
     * @param moduleId
     * @return {@code True} if successful else {@code False}
     */
    public boolean removeModuFromCour(int courseId, int moduleId);

    /**
     * get all {@code Module}(s) in a {@code Course}
     * 
     * @param courseId
     * @return A list of {@code Module}(s) belong to this {@code Course}
     */
    public List<Module> getAllModuInCour(int courseId);

    /**
     * Get all {@code Course}(s) that this {@code Module} belongs to
     * 
     * @param moduleId
     * @return a list of {@code Course}(s) that this {@code Module} belongs to
     */
    public List<Course> getAllCourOfModu(int moduleId);

    /**
     * Add a {@code Student} to a {@code Module}
     * 
     * @param moduleId
     * @param studentId
     * 
     * @return {@code True} if successful else {@code False}
     */
    public boolean addStudToModu(int moduleId, int studentId);

    /**
     * Remove a {@code Student} from a {@code Module}
     * 
     * @param moduleId
     * @param studentId
     * @return {@code True} if successful else {@code False}
     */
    public boolean removeStudFromModu(int moduleId, int studentId);

    /**
     * Get all {@code Student}(s) in a {@code Module}
     * 
     * @param moduleId
     * @return A list of {@code Student}(s) in this {@code Module}
     */
    public List<Student> getAllStudInModu(int moduleId);

    /**
     * Get all {@code Module}(s) of a {@code Student}
     * 
     * @param studentId
     * @return A list of {@code Module}(s) of this {@code Student}
     */
    public List<Module> getAllModuOfStud(int studentId);

    /**
     * Get all {@code School}(s) in {@code Faculty}
     * 
     * @param facultyId
     * @return A list of {@code School}(s)
     */
    public List<School> getAllSchoInFacu(int facultyId);

    /**
     * Get all {@code Course}(s) in {@code School}
     * 
     * @param schoolId
     * @return A list of {@code Course}(s)
     */
    public List<Course> getAllCourInScho(int schoolId);

    /**
     * Get all {@code Course}(s) lead by the {@code Lecturer}
     * 
     * @param lecturerId
     * @return A list of {@code Course}(s)
     */
    public List<Course> getAllCourOfLect(int lecturerId);

}