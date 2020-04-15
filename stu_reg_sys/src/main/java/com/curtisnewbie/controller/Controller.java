package com.curtisnewbie.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.curtisnewbie.dao.CommonDao;
import com.curtisnewbie.dao.CommonDaoImpl;
import com.curtisnewbie.dao.CourseDao;
import com.curtisnewbie.dao.CourseRepository;
import com.curtisnewbie.dao.FacultyDao;
import com.curtisnewbie.dao.FacultyRepository;
import com.curtisnewbie.dao.LecturerDao;
import com.curtisnewbie.dao.LecturerRepository;
import com.curtisnewbie.dao.ModuleDao;
import com.curtisnewbie.dao.ModuleRepository;
import com.curtisnewbie.dao.SchoolDao;
import com.curtisnewbie.dao.SchoolRepository;
import com.curtisnewbie.dao.StudentDao;
import com.curtisnewbie.dao.StudentRepository;
import com.curtisnewbie.dao.UnitDao;
import com.curtisnewbie.model.Course;
import com.curtisnewbie.model.Faculty;
import com.curtisnewbie.model.Lecturer;
import com.curtisnewbie.model.School;
import com.curtisnewbie.model.Student;
import com.curtisnewbie.model.Module;
import static com.curtisnewbie.util.DateFormatter.*;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * ------------------------------------
 * 
 * Author: Yongjie Zhuang
 * 
 * ------------------------------------
 * <p>
 * Controller of JavaFX FXML GUI as a whole.
 * </p>
 * <p>
 * This {@code Controller} is mainly responsible for the interactions between
 * the high-level components. The control of each tab is delegated to each
 * {@code TabController}, e.g., {@code FacultyTabController}. These
 * {@code TabController}(s) are instantiated in method
 * {@link Controller#initialize()}, once these {@code TabController}(s) are
 * instantiated, the {@code EventHandler}(s) for all controls are registered.
 * </p>
 * <p>
 * The {@code ListView} that is independent on each tab is named
 * {@code commonLv}, which is located on the right-hand side of the screen. The
 * content of it is updated whenever the selection of tab is changed. E.g., if
 * the currently selected tab is 'faculty tab', the {@code commonLv} will be
 * displaying the {@code Faculty} items. Some functionalities will also take
 * advantage of this {@code ListView} as well when the result consists of more
 * than one item.
 * </p>
 * 
 * @see {@link FacultyTabController}
 * @see {@link SchoolTabController}
 * @see {@link CourseTabController}
 * @see {@link ModuleTabController}
 * @see {@link LecturerTabController}
 * @see {@link StudentTabController}
 * 
 * @see {@link Controller#initialize()}
 * @see {@link Controller#addTabSelectionEventHandler()}
 * @see {@link Controller#setContextMenuToCommonLv()}
 */
public class Controller {

	public static final String NOT_FOUND = "Not Found";
	public static final String NULL_VALUE = "Null";

	/*
	 * ------------------------------------
	 * 
	 * For Faculty Tab
	 * 
	 * ------------------------------------
	 */
	@FXML
	private TextField facByIdTf;
	@FXML
	private TextField facByNameTf;
	@FXML
	private TextField facIdTf;
	@FXML
	private TextField facNameTf;
	@FXML
	private ListView<School> facSchLv;
	@FXML
	private Button facBtn;

	/*
	 * ------------------------------------
	 * 
	 * For School Tab
	 * 
	 * ------------------------------------
	 */
	@FXML
	private TextField schByIdTf;
	@FXML
	private TextField schByNameTf;
	@FXML
	private TextField schIdTf;
	@FXML
	private TextField schNameTf;
	@FXML
	private TextField schFacIdTf;
	@FXML
	private TextField schFacNameTf;
	@FXML
	private Button schBtn;
	@FXML
	private ListView<Course> schCouLv;

	/*
	 * ------------------------------------
	 * 
	 * For Course Tab
	 * 
	 * ------------------------------------
	 */
	@FXML
	private TextField couByIdTf;
	@FXML
	private TextField couByNameTf;
	@FXML
	private TextField couByCreditTf;
	@FXML
	private TextField couIdTf;
	@FXML
	private TextField couNameTf;
	@FXML
	private TextField couCreditTf;
	@FXML
	private TextField couLeaIdTf;
	@FXML
	private TextField couLeaNameTf;
	@FXML
	private TextField couSchIdTf;
	@FXML
	private TextField couSchNameTf;
	@FXML
	private ListView<Module> couMouLv;
	@FXML
	private Button couBtn;

	/*
	 * ------------------------------------
	 * 
	 * For Module Tab
	 * 
	 * ------------------------------------
	 */
	@FXML
	private TextField mouByIdTf;
	@FXML
	private TextField mouByNameTf;
	@FXML
	private TextField mouByCreditTf;
	@FXML
	private TextField mouIdTf;
	@FXML
	private TextField mouNameTf;
	@FXML
	private TextField mouCreditTf;
	@FXML
	private ListView<Course> mouCouLv; /* Type of items in ListView should be determined */
	@FXML
	private ListView<Student> mouStuLv; /* Type of items in ListView should be determined */
	@FXML
	private Button mouBtn;

	/*
	 * ------------------------------------
	 * 
	 * For Lecturer Tab
	 * 
	 * ------------------------------------
	 */
	@FXML
	private TextField lecByIdTf;
	@FXML
	private TextField lecByFirstnameTf;
	@FXML
	private TextField lecByLastnameTf;
	@FXML
	private TextField lecByPositionTf;
	@FXML
	private TextField lecIdTf;
	@FXML
	private TextField lecFirstnameTf;
	@FXML
	private TextField lecLastnameTf;
	@FXML
	private TextField lecPositionTf;
	@FXML
	private ListView<Course> lecCouLv; /* Type of items in ListView should be determined */
	@FXML
	private ListView<Module> lecMouLv; /* Type of items in ListView should be determined */
	@FXML
	private Button lecBtn;

	/*
	 * ------------------------------------
	 * 
	 * For Student Tab
	 * 
	 * ------------------------------------
	 */
	@FXML
	private TextField stuByIdTf;
	@FXML
	private TextField stuByFirstnameTf;
	@FXML
	private TextField stuByLastnameTf;
	@FXML
	private TextField stuByDateTf;
	@FXML
	private TextField stuIdTf;
	@FXML
	private TextField stuFirstnameTf;
	@FXML
	private TextField stuLastnameTf;
	@FXML
	private TextField stuDateTf;
	@FXML
	private TextField stuCouIdTf;
	@FXML
	private TextField stuCouNameTf;
	@FXML
	private TextField stuCouCreditTf;
	@FXML
	private TextField stuSchIdTf;
	@FXML
	private TextField stuSchNameTf;
	@FXML
	private ListView<Module> stuModLv; /* Type of items in ListView should be determined */
	@FXML
	private Button stuBtn;

	/*
	 * ------------------------------------
	 * 
	 * Common ListView (on the right-hand side of the screen) and Tabpane
	 * 
	 * ------------------------------------
	 */
	@FXML
	private ListView<Object> commonLv; /* Type of items in ListView should be determined */
	@FXML
	private TabPane tabpane;
	@FXML
	private Label commonLvTitle;

	/*
	 * ------------------------------------
	 * 
	 * Controllers for each tab
	 * 
	 * ------------------------------------
	 */
	private FacultyTabController facultyTab;
	private SchoolTabController schoolTab;
	private CourseTabController courseTab;
	private ModuleTabController moduleTab;
	private LecturerTabController lecturerTab;
	private StudentTabController studentTab;
	private CommonLvController commonLvCtrler;

	/*
	 * ------------------------------------
	 * 
	 * DAOs
	 * 
	 * ------------------------------------
	 */
	final CommonDao comDao = new CommonDaoImpl();
	final CourseDao courDao = new CourseRepository();
	final FacultyDao facuDao = new FacultyRepository();
	final LecturerDao lectDao = new LecturerRepository();
	final ModuleDao moduDao = new ModuleRepository();
	final SchoolDao schoDao = new SchoolRepository();
	final StudentDao studDao = new StudentRepository();

	/**
	 * Initialise all {@code TabController}(s) and register EventHandlers
	 */
	@FXML
	public void initialize() {
		this.facultyTab = new FacultyTabController();
		this.schoolTab = new SchoolTabController();
		this.courseTab = new CourseTabController();
		this.moduleTab = new ModuleTabController();
		this.lecturerTab = new LecturerTabController();
		this.studentTab = new StudentTabController();
		this.commonLvCtrler = new CommonLvController();
		this.addTabSelectionEventHandler();

		// faculty tab is displayed immediately on start-up
		commonLvCtrler.setCommonLvTitle("FACULTY");
		displayAll(facuDao.getAll());
	}

	/**
	 * EventHandler for tab selection
	 */
	private void addTabSelectionEventHandler() {
		// tab changed
		this.tabpane.getSelectionModel().selectedItemProperty().addListener((ov, prev, curr) -> {
			commonLvCtrler.refreshCommonLv();
		});
	}

	/**
	 * Controller for {@code ListView commonLv}
	 */
	private class CommonLvController {

		CommonLvController() {
			setContextMenuToCommonLv();
			addF5EventHandler();
			addDoubleClickEventHandler();
		}

		/**
		 * EventHandler for F5 key to refresh the content in {@code commonLv}
		 */
		private void addF5EventHandler() {
			tabpane.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
				if (e.getCode().equals(KeyCode.F5)) {
					refreshCommonLv();
				}
			});
		}

		/**
		 * Refresh the content in {@code commonLv}
		 */
		public void refreshCommonLv() {
			var index = tabpane.getSelectionModel().getSelectedIndex();
			switch (index) {
				case 0:
					setCommonLvTitle("FACULTY");
					displayAll(facuDao.getAll());
					break;
				case 1:
					setCommonLvTitle("SCHOOL");
					displayAll(schoDao.getAll());
					break;
				case 2:
					setCommonLvTitle("COURSE");
					displayAll(courDao.getAll());
					break;
				case 3:
					setCommonLvTitle("MODULE");
					displayAll(moduDao.getAll());
					break;
				case 4:
					setCommonLvTitle("LECTURER");
					displayAll(lectDao.getAll());
					break;
				case 5:
					setCommonLvTitle("STUDENT");
					displayAll(studDao.getAll());
					break;
			}
		}

		/**
		 * Set the title of {@code commonLvTitle} to "List: " + {@code title}
		 * 
		 * @param title
		 */
		public void setCommonLvTitle(String title) {
			commonLvTitle.setText("List: " + title);
		}

		/**
		 * Set a context menu to the {@code commonLv}. The context menu is able to
		 * detect the type of the item. E.g., if the currently selected tab is 'faculty
		 * tab', the context menu will treat the items in the {@code commonLv} as
		 * {@code Faculty}
		 */
		private void setContextMenuToCommonLv() {
			// create context menu
			MenuItem selectItem = new MenuItem("Select");
			MenuItem deleteItem = new MenuItem("Delete");
			ContextMenu ctxMenu = new ContextMenu();
			selectItem.setOnAction(e1 -> {
				displaySelectedItem();
			});
			deleteItem.setOnAction(e2 -> {
				var item = commonLv.getSelectionModel().getSelectedItem();
				if (item instanceof Faculty) {
					facuDao.deleteById(((Faculty) item).getId());
					displayAll(facuDao.getAll());
				} else if (item instanceof School) {
					schoDao.deleteById(((School) item).getId());
					displayAll(schoDao.getAll());
				} else if (item instanceof Course) {
					courDao.deleteById(((Course) item).getId());
					displayAll(courDao.getAll());
				} else if (item instanceof Module) {
					moduDao.deleteById(((Module) item).getId());
					displayAll(moduDao.getAll());
				} else if (item instanceof Lecturer) {
					lectDao.deleteById(((Lecturer) item).getId());
					displayAll(lectDao.getAll());
				} else if (item instanceof Student) {
					studDao.deleteById(((Student) item).getId());
					displayAll(studDao.getAll());
				}
			});
			ctxMenu.getItems().addAll(selectItem, deleteItem);
			commonLv.setContextMenu(ctxMenu);
		}

		private void displaySelectedItem() {
			var item = commonLv.getSelectionModel().getSelectedItem();
			if (item instanceof Faculty)
				facultyTab.displayContentOf(((Faculty) item).getId());
			else if (item instanceof School)
				schoolTab.displayContentOf(((School) item).getId());
			else if (item instanceof Course)
				courseTab.displayContentOf(((Course) item).getId());
			else if (item instanceof Module)
				moduleTab.displayContentOf(((Module) item).getId());
			else if (item instanceof Lecturer)
				lecturerTab.displayContentOf(((Lecturer) item).getId());
			else if (item instanceof Student)
				studentTab.displayContentOf(((Student) item).getId());
		}

		private void addDoubleClickEventHandler() {
			commonLv.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
					displaySelectedItem();
				}
			});
		}
	}

	/**
	 * Display a list of objects on {@code commonLv} list view
	 * 
	 * @param list
	 */
	private void displayAll(List<? extends Object> list) {
		List<Object> items = new ArrayList<>();
		for (var i : list)
			items.add((Object) i);
		Platform.runLater(() -> {
			commonLv.setItems(FXCollections.observableList(items));
		});
	}

	/**
	 * Controller for Faculty Tab
	 */
	private class FacultyTabController implements TabController<Faculty> {
		private Controller ctrler = Controller.this;
		private int currFacultyId;

		FacultyTabController() {
			addFindByIdEventHandler();
			addFindByNameEventHandler();
			addUpdateEventHandler();
			setSchoolsContextMenu();
		}

		private void setSchoolsContextMenu() {
			ContextMenu ctxMenu = new ContextMenu();
			MenuItem removeItem = new MenuItem("Remove");
			removeItem.setOnAction(e -> {
				var schoolId = ctrler.facSchLv.getSelectionModel().getSelectedItem().getId();
				boolean removed = schoDao.updateFacultyFk(schoolId, UnitDao.NULL_INT);
				if (removed)
					displaySchoolsInFaculty(currFacultyId);
			});
			ctxMenu.getItems().add(removeItem);
			ctrler.facSchLv.setContextMenu(ctxMenu);
		}

		/**
		 * Add Update EventHandler
		 */
		private void addUpdateEventHandler() {
			ctrler.facBtn.setOnAction(e -> {
				try {
					var id = Integer.parseInt(ctrler.facIdTf.getText());
					var name = ctrler.facNameTf.getText().trim();
					if (!name.isEmpty() && id >= 0) {
						facuDao.updateName(id, name);
						commonLvCtrler.refreshCommonLv();
					}
				} catch (NumberFormatException e1) {
				}
			});
		}

		/**
		 * Add EventHandler to {@link Controller#facByIdTf}. It internally calls
		 * {@link FacultyTabController#displayContentOf(int)} and
		 * {@link FacultyTabController#displaySchoolsInFaculty(int)} to refresh the
		 * listview for displaying schools in this faculty.
		 */
		private void addFindByIdEventHandler() {
			ctrler.facByIdTf.setOnAction(e -> {
				try {
					int id = Integer.parseInt(facByIdTf.getText());
					displayContentOf(id);
				} catch (NumberFormatException ne) {
				}
			});
		}

		@Override
		public void displayContentOf(int facultyId) {
			var faculty = ctrler.facuDao.findById(facultyId);
			displayContentOf(faculty);
		}

		@Override
		public void displayContentOf(Faculty faculty) {
			if (faculty != null) {
				currFacultyId = faculty.getId();
				displayFaculty(faculty);
				displaySchoolsInFaculty(faculty.getId());
			} else {
				clearContent();
			}
		}

		private void displayFaculty(Faculty faculty) {
			Platform.runLater(() -> {
				ctrler.facIdTf.setText(faculty.getId() + "");
				ctrler.facNameTf.setText(faculty.getName() == null ? "" : faculty.getName());
			});
		}

		/**
		 * Add EventHandler to {@link Controller#facByNameTf}. It internally calls
		 * {@link FacultyTabController#displaySchoolsInFaculty(int)} to refresh the
		 * listview for displaying schools in this faculty.
		 */
		private void addFindByNameEventHandler() {
			ctrler.facByNameTf.setOnAction(e -> {
				String name = facByNameTf.getText();
				if (name != null && !name.isEmpty()) {
					var faculty = ctrler.facuDao.findByName(name);
					if (faculty != null)
						displayContentOf(faculty);
				}
			});
		}

		private void displaySchoolsInFaculty(int facultyId) {
			var list = comDao.getAllSchoInFacu(facultyId);
			Platform.runLater(() -> {
				ctrler.facSchLv.setItems(FXCollections.observableList(list));
			});
		}

		@Override
		public void clearContent() {
			Platform.runLater(() -> {
				ctrler.facIdTf.clear();
				ctrler.facNameTf.clear();
				ctrler.facSchLv.getItems().clear();
			});
		}
	}

	/**
	 * Controller for School Tab
	 */
	private class SchoolTabController implements TabController<School> {
		private Controller ctrler = Controller.this;
		private int currSchoolId;

		SchoolTabController() {
			addFindByIdEventHandler();
			addFindByNameEventHandler();
			addUpdateEventHandler();
			setCoursesContextMenu();
		}

		private void setCoursesContextMenu() {
			ContextMenu ctxMenu = new ContextMenu();
			MenuItem removeItem = new MenuItem("Remove");
			removeItem.setOnAction(e -> {
				var courseId = ctrler.schCouLv.getSelectionModel().getSelectedItem().getId();
				boolean removed = courDao.updateSchoolFk(courseId, UnitDao.NULL_INT);
				if (removed)
					displayCoursesInSchool(currSchoolId);
			});
			ctxMenu.getItems().add(removeItem);
			ctrler.schCouLv.setContextMenu(ctxMenu);
		}

		/**
		 * Add Update EventHandler
		 */
		private void addUpdateEventHandler() {
			ctrler.schBtn.setOnAction(e -> {
				try {
					var id = Integer.parseInt(ctrler.schIdTf.getText());
					var name = ctrler.schNameTf.getText().trim();
					var facultyIdTxt = ctrler.schFacIdTf.getText().trim();
					var facultyId = facultyIdTxt.isEmpty() ? UnitDao.NULL_INT : Integer.parseInt(facultyIdTxt);
					if (!name.isEmpty() && id > 0 && (facultyId == UnitDao.NULL_INT || facultyId > 0)) {
						schoDao.update(id, name, facultyId);
						displayContentOf(currSchoolId);
						commonLvCtrler.refreshCommonLv();
					}
				} catch (NumberFormatException e1) {
				}
			});
		}

		private void addFindByIdEventHandler() {
			ctrler.schByIdTf.setOnAction(e -> {
				try {
					int id = Integer.parseInt(ctrler.schByIdTf.getText());
					displayContentOf(id);
				} catch (NumberFormatException e1) {
				}
			});
		}

		private void addFindByNameEventHandler() {
			ctrler.schByNameTf.setOnAction(e -> {
				var sch = schoDao.findByName(schByNameTf.getText());
				displayContentOf(sch.getId());
			});
		}

		@Override
		public void displayContentOf(int schoolId) {
			if (schoolId > 0) {
				var school = schoDao.findById(schoolId);
				displayContentOf(school);
			}
		}

		@Override
		public void displayContentOf(School school) {
			if (school != null) {
				currSchoolId = school.getId();
				displaySchool(school);
				displayCoursesInSchool(school.getId());
				displayFacultyOfSchool(school.getFacultyFk());
			} else {
				clearContent();
			}
		}

		private void displaySchool(School school) {
			Platform.runLater(() -> {
				ctrler.schIdTf.setText(school.getId() + "");
				ctrler.schNameTf.setText(school.getName());
			});
		}

		private void displayCoursesInSchool(int schoolId) {
			if (schoolId >= 0) {
				var list = comDao.getAllCourInScho(schoolId);
				Platform.runLater(() -> {
					ctrler.schCouLv.setItems(FXCollections.observableList(list));
				});
			}
		}

		private void displayFacultyOfSchool(int facultyId) {
			// NULL in DBMS
			if (facultyId == 0) {
				displayFacultyOfSchool(NULL_VALUE, NULL_VALUE);
				return;
			}

			var faculty = facuDao.findById(facultyId);
			if (faculty != null) {
				displayFacultyOfSchool(faculty.getId() + "", faculty.getName());
			} else {
				displayFacultyOfSchool(NOT_FOUND, NOT_FOUND);
			}
		}

		private void displayFacultyOfSchool(String facultyId, String facultyName) {
			Platform.runLater(() -> {
				ctrler.schFacIdTf.setText(facultyId);
				ctrler.schFacNameTf.setText(facultyName);
			});
		}

		@Override
		public void clearContent() {
			Platform.runLater(() -> {
				ctrler.schIdTf.clear();
				ctrler.schNameTf.clear();
				ctrler.schFacIdTf.clear();
				ctrler.schFacNameTf.clear();
				ctrler.schCouLv.getItems().clear();
			});
		}
	}

	/**
	 * Controller for Course Tab
	 */
	private class CourseTabController implements TabController<Course> {
		private Controller ctrler = Controller.this;
		private int currCourseId;

		CourseTabController() {
			addFindByIdEventHandler();
			addFindByNameEventHandler();
			addFindByCreditEventHandler();
			addUpdateEventHandler();
			setModulesContextMenu();
		}

		private void setModulesContextMenu() {
			ContextMenu ctxMenu = new ContextMenu();
			MenuItem removeItem = new MenuItem("Remove");
			removeItem.setOnAction(e -> {
				var moduleId = ctrler.couMouLv.getSelectionModel().getSelectedItem().getId();
				boolean removed = comDao.removeModuFromCour(currCourseId, moduleId);
				if (removed)
					displayModulesInCourse(currCourseId);
			});
			ctxMenu.getItems().add(removeItem);
			ctrler.couMouLv.setContextMenu(ctxMenu);
		}

		/**
		 * Add Update EventHandler
		 */
		private void addUpdateEventHandler() {
			ctrler.couBtn.setOnAction(e -> {
				try {
					var id = Integer.parseInt(ctrler.couIdTf.getText());
					var name = ctrler.couNameTf.getText().trim();
					var credit = Integer.parseInt(ctrler.couCreditTf.getText());

					var couLeaIdTfTxt = ctrler.couLeaIdTf.getText().trim();
					var courseLeaderId = couLeaIdTfTxt.isEmpty() ? UnitDao.NULL_INT : Integer.parseInt(couLeaIdTfTxt);

					var couSchIdTfTxt = ctrler.couSchIdTf.getText().trim();
					var schoolId = couSchIdTfTxt.isEmpty() ? UnitDao.NULL_INT : Integer.parseInt(couSchIdTfTxt);

					if (!name.isEmpty() && id > 0 && credit >= 0
							&& (courseLeaderId == UnitDao.NULL_INT || courseLeaderId > 0)
							&& (schoolId == UnitDao.NULL_INT || schoolId > 0)) {
						courDao.update(id, name, credit, schoolId, courseLeaderId);
						commonLvCtrler.refreshCommonLv();
						displayContentOf(currCourseId);
					}
				} catch (NumberFormatException e1) {
				}
			});
		}

		private void addFindByIdEventHandler() {
			ctrler.couByIdTf.setOnAction(e -> {
				try {
					var id = Integer.parseInt(ctrler.couByIdTf.getText());
					displayContentOf(id);
				} catch (NumberFormatException e2) {
				}
			});
		}

		private void addFindByNameEventHandler() {
			ctrler.couByNameTf.setOnAction(e -> {
				var name = ctrler.couByNameTf.getText();
				if (name != null && !name.isEmpty()) {
					var course = courDao.findByName(name);
					if (course != null)
						displayContentOf(course);
				}
			});
		}

		private void addFindByCreditEventHandler() {
			ctrler.couByCreditTf.setOnAction(e -> {
				try {
					var credit = Integer.parseInt(ctrler.couByCreditTf.getText());
					var list = courDao.findByCredit(credit);
					displayAll(list);
				} catch (NumberFormatException e1) {
				}
			});
		}

		@Override
		public void displayContentOf(int courseId) {
			if (courseId > 0) {
				var course = courDao.findById(courseId);
				displayContentOf(course);
			}
		}

		@Override
		public void displayContentOf(Course course) {
			if (course != null) {
				currCourseId = course.getId();
				displayCourse(course);
				displayCourseLeader(course.getLecturerFk());
				displayModulesInCourse(course.getId());
				displaySchoolOfCourse(course.getSchoolFk());
			} else {
				clearContent();
			}
		}

		private void displayCourse(Course course) {
			Platform.runLater(() -> {
				ctrler.couIdTf.setText("" + course.getId());
				ctrler.couNameTf.setText(course.getName());
				ctrler.couCreditTf.setText(course.getCredit() + "");
			});
		}

		private void displayCourseLeader(int lecturerId) {
			// NULL in DBMS
			if (lecturerId == 0) {
				displayCourseLeader(NULL_VALUE, NULL_VALUE);
				return;
			}

			var lecturer = lectDao.findById(lecturerId);
			if (lecturer != null) {
				displayCourseLeader(lecturer.getId() + "", lecturer.getLastname() + " " + lecturer.getFirstname());
			} else {
				displayCourseLeader(NOT_FOUND, NOT_FOUND);
			}
		}

		private void displayCourseLeader(String lecturerId, String lecturerName) {
			Platform.runLater(() -> {
				ctrler.couLeaIdTf.setText(lecturerId);
				ctrler.couLeaNameTf.setText(lecturerName);
			});
		}

		private void displayModulesInCourse(int courseId) {
			var list = comDao.getAllModuInCour(courseId);
			Platform.runLater(() -> {
				ctrler.couMouLv.setItems(FXCollections.observableList(list));
			});
		}

		private void displaySchoolOfCourse(int schoolId) {
			// NULL in DBMS
			if (schoolId == 0) {
				displaySchoolOfCourse(NULL_VALUE, NULL_VALUE);
				return;
			}

			var school = schoDao.findById(schoolId);
			if (school != null) {
				displaySchoolOfCourse(school.getId() + "", school.getName());
			} else {
				displaySchoolOfCourse(NOT_FOUND, NOT_FOUND);
			}
		}

		private void displaySchoolOfCourse(String schoolId, String schoolName) {
			Platform.runLater(() -> {
				ctrler.couSchIdTf.setText(schoolId);
				ctrler.couSchNameTf.setText(schoolName);
			});
		}

		@Override
		public void clearContent() {
			Platform.runLater(() -> {
				ctrler.couIdTf.clear();
				ctrler.couNameTf.clear();
				ctrler.couCreditTf.clear();
				ctrler.couLeaIdTf.clear();
				ctrler.couNameTf.clear();
				ctrler.couSchIdTf.clear();
				ctrler.couSchNameTf.clear();
			});
		}
	}

	/**
	 * Controller for Module Tab
	 */
	private class ModuleTabController implements TabController<Module> {
		private Controller ctrler = Controller.this;
		private int currModuleId;

		ModuleTabController() {
			addFindByIdEventHandler();
			addFindByNameEventHandler();
			addFindByCreditEventHandler();
			addUpdateEventHandler();
			setCoursesContextMenu();
			setStudentsContextMenu();
		}

		private void setCoursesContextMenu() {
			ContextMenu ctxMenu = new ContextMenu();
			MenuItem removeItem = new MenuItem("Remove");
			removeItem.setOnAction(e -> {
				var courseId = ctrler.mouCouLv.getSelectionModel().getSelectedItem().getId();
				boolean removed = comDao.removeModuFromCour(courseId, currModuleId);
				if (removed)
					displayCourseOfModule(currModuleId);
			});
			ctxMenu.getItems().add(removeItem);
			ctrler.mouCouLv.setContextMenu(ctxMenu);
		}

		private void setStudentsContextMenu() {
			ContextMenu ctxMenu = new ContextMenu();
			MenuItem removeItem = new MenuItem("Remove");
			removeItem.setOnAction(e -> {
				var studentId = ctrler.mouStuLv.getSelectionModel().getSelectedItem().getId();
				boolean removed = comDao.removeStudFromModu(currModuleId, studentId);
				if (removed)
					displayStudentsInModule(currModuleId);
			});
			ctxMenu.getItems().add(removeItem);
			ctrler.mouStuLv.setContextMenu(ctxMenu);
		}

		/**
		 * Add Update EventHandler
		 */
		private void addUpdateEventHandler() {
			ctrler.mouBtn.setOnAction(e -> {
				try {
					var id = Integer.parseInt(ctrler.mouIdTf.getText());
					var name = ctrler.mouNameTf.getText().trim();
					var credit = Integer.parseInt(ctrler.mouCreditTf.getText());
					if (!name.isEmpty() && id >= 0 && credit >= 0) {
						moduDao.update(name, credit, id);
						commonLvCtrler.refreshCommonLv();
					}
				} catch (NumberFormatException e1) {
				}
			});
		}

		private void addFindByCreditEventHandler() {
			ctrler.mouByCreditTf.setOnAction(e -> {
				try {
					var credit = Integer.parseInt(ctrler.mouByCreditTf.getText());
					var list = moduDao.findByCredit(credit);
					displayAll(list);
				} catch (NumberFormatException e1) {
				}
			});
		}

		private void addFindByNameEventHandler() {
			ctrler.mouByNameTf.setOnAction(e -> {
				var name = ctrler.mouByNameTf.getText();
				if (name != null && !name.isEmpty()) {
					var mou = moduDao.findByName(name);
					if (mou != null)
						displayContentOf(mou);
				}
			});
		}

		private void addFindByIdEventHandler() {
			ctrler.mouByIdTf.setOnAction(e -> {
				try {
					var id = Integer.parseInt(ctrler.mouByIdTf.getText());
					displayContentOf(id);
				} catch (NumberFormatException e1) {
				}
			});
		}

		@Override
		public void displayContentOf(int id) {
			if (id >= 0) {
				var module = moduDao.findById(id);
				displayContentOf(module);
			}
		}

		@Override
		public void displayContentOf(Module module) {
			if (module != null) {
				currModuleId = module.getId();
				displayModule(module);
				displayCourseOfModule(module.getId());
				displayStudentsInModule(module.getId());
			} else {
				clearContent();
			}
		}

		private void displayModule(Module module) {
			Platform.runLater(() -> {
				ctrler.mouIdTf.setText(module.getId() + "");
				ctrler.mouNameTf.setText(module.getName());
				ctrler.mouCreditTf.setText(module.getCredit() + "");
			});
		}

		private void displayCourseOfModule(int moduleId) {
			var list = comDao.getAllCourOfModu(moduleId);
			Platform.runLater(() -> {
				ctrler.mouCouLv.setItems(FXCollections.observableList(list));
			});
		}

		private void displayStudentsInModule(int moduleId) {
			var list = comDao.getAllStudInModu(moduleId);
			Platform.runLater(() -> {
				ctrler.mouStuLv.setItems(FXCollections.observableList(list));
			});
		}

		@Override
		public void clearContent() {
			Platform.runLater(() -> {
				ctrler.mouIdTf.clear();
				ctrler.mouNameTf.clear();
				ctrler.mouCreditTf.clear();
				ctrler.mouCouLv.getItems().clear();
				ctrler.mouStuLv.getItems().clear();
			});
		}

	}

	/**
	 * Controller for Lecturer Tab
	 */
	private class LecturerTabController implements TabController<Lecturer> {
		private Controller ctrler = Controller.this;
		private int currLecturerId;

		LecturerTabController() {
			addFindByIdEventHandler();
			addFindByFirstnameEventHandler();
			addFindByLastnameEventHandler();
			addFindByPositionEventHandler();
			addUpdateEventHandler();
			setCoursesContextMenu();
			setModulesContextMenu();
		}

		private void setCoursesContextMenu() {
			ContextMenu ctxMenu = new ContextMenu();
			MenuItem removeItem = new MenuItem("Remove");
			removeItem.setOnAction(e -> {
				var courseId = ctrler.lecCouLv.getSelectionModel().getSelectedItem().getId();
				boolean removed = courDao.updateLecturerFk(courseId, UnitDao.NULL_INT);
				if (removed)
					displayCoursesOfLecturer(currLecturerId);
			});
			ctxMenu.getItems().add(removeItem);
			ctrler.lecCouLv.setContextMenu(ctxMenu);
		}

		private void setModulesContextMenu() {
			ContextMenu ctxMenu = new ContextMenu();
			MenuItem removeItem = new MenuItem("Remove");
			removeItem.setOnAction(e -> {
				var moduleId = ctrler.lecMouLv.getSelectionModel().getSelectedItem().getId();
				boolean removed = comDao.removeLectFromModu(moduleId, currLecturerId);
				if (removed)
					displayModulesOfLecturer(currLecturerId);
			});
			ctxMenu.getItems().add(removeItem);
			ctrler.lecMouLv.setContextMenu(ctxMenu);
		}

		/**
		 * Add Update EventHandler
		 */
		private void addUpdateEventHandler() {
			ctrler.lecBtn.setOnAction(e -> {
				try {
					var id = Integer.parseInt(ctrler.lecIdTf.getText());
					var fname = ctrler.lecFirstnameTf.getText().trim();
					var lname = ctrler.lecLastnameTf.getText().trim();
					var pos = ctrler.lecPositionTf.getText().trim();
					if (!fname.isEmpty() && !lname.isEmpty() && !pos.isEmpty() && id >= 0) {
						lectDao.update(fname, lname, pos, id);
						commonLvCtrler.refreshCommonLv();
					}
				} catch (NumberFormatException e1) {
				}
			});
		}

		private void addFindByIdEventHandler() {
			ctrler.lecByIdTf.setOnAction(e -> {
				try {
					var id = Integer.parseInt(ctrler.lecByIdTf.getText());
					displayContentOf(id);
				} catch (NumberFormatException e1) {
				}
			});
		}

		private void addFindByFirstnameEventHandler() {
			ctrler.lecByFirstnameTf.setOnAction(e -> {
				var fname = ctrler.lecByFirstnameTf.getText();
				if (fname != null & !fname.isEmpty()) {
					var list = lectDao.findByFirstname(fname);
					displayAll(list);
				}
			});
		}

		private void addFindByLastnameEventHandler() {
			ctrler.lecByLastnameTf.setOnAction(e -> {
				var lname = ctrler.lecByLastnameTf.getText();
				if (lname != null & !lname.isEmpty()) {
					var list = lectDao.findByLastname(lname);
					displayAll(list);
				}
			});
		}

		private void addFindByPositionEventHandler() {
			ctrler.lecByPositionTf.setOnAction(e -> {
				var pos = ctrler.lecByPositionTf.getText();
				if (pos != null & !pos.isEmpty()) {
					var list = lectDao.findByPosition(pos);
					displayAll(list);
				}
			});
		}

		@Override
		public void displayContentOf(int id) {
			if (id >= 0) {
				var lecturer = lectDao.findById(id);
				displayContentOf(lecturer);
			}
		}

		@Override
		public void displayContentOf(Lecturer lecturer) {
			if (lecturer != null) {
				currLecturerId = lecturer.getId();
				displayLecturer(lecturer);
				displayCoursesOfLecturer(lecturer.getId());
				displayModulesOfLecturer(lecturer.getId());
			} else {
				clearContent();
			}
		}

		private void displayLecturer(Lecturer lecturer) {
			Platform.runLater(() -> {
				ctrler.lecIdTf.setText("" + lecturer.getId());
				ctrler.lecFirstnameTf.setText(lecturer.getFirstname());
				ctrler.lecLastnameTf.setText(lecturer.getLastname());
				ctrler.lecPositionTf.setText(lecturer.getPosition());
			});
		}

		private void displayModulesOfLecturer(int lecturerId) {
			var list = comDao.getAllModuOfLect(lecturerId);
			Platform.runLater(() -> {
				ctrler.lecMouLv.setItems(FXCollections.observableList(list));
			});
		}

		private void displayCoursesOfLecturer(int lecturerId) {
			var list = comDao.getAllCourOflect(lecturerId);
			Platform.runLater(() -> {
				ctrler.lecCouLv.setItems(FXCollections.observableList(list));
			});
		}

		@Override
		public void clearContent() {
			Platform.runLater(() -> {
				ctrler.lecIdTf.clear();
				ctrler.lecFirstnameTf.clear();
				ctrler.lecLastnameTf.clear();
				ctrler.lecPositionTf.clear();
				ctrler.lecCouLv.getItems().clear();
				ctrler.lecMouLv.getItems().clear();
			});
		}
	}

	/**
	 * Controller for Student Tab
	 */
	private class StudentTabController implements TabController<Student> {
		private Controller ctrler = Controller.this;

		StudentTabController() {
			addFindByIdEventHandler();
			addFindByFirstnameEventHandler();
			addFindByLastnameEventHandler();
			addFindByDateEventHandler();
			addUpdateEventHandler();
		}

		/**
		 * Add Update EventHandler
		 */
		private void addUpdateEventHandler() {
			ctrler.stuBtn.setOnAction(e -> {
				try {
					var id = Integer.parseInt(ctrler.stuIdTf.getText());
					var fname = ctrler.stuFirstnameTf.getText().trim();
					var lname = ctrler.stuLastnameTf.getText().trim();
					var date = ctrler.stuDateTf.getText().trim();
					if (!fname.isEmpty() && !lname.isEmpty() && !date.isEmpty() && id >= 0) {
						studDao.update(fname, lname, LocalDate.parse(date), id);
						commonLvCtrler.refreshCommonLv();
					}
				} catch (NumberFormatException e1) {
				}
			});
		}

		private void addFindByIdEventHandler() {
			ctrler.stuByIdTf.setOnAction(e -> {
				try {
					int id = Integer.parseInt(ctrler.stuByIdTf.getText());
					displayContentOf(id);
				} catch (NumberFormatException e2) {
				}
			});
		}

		private void addFindByFirstnameEventHandler() {
			ctrler.stuByFirstnameTf.setOnAction(e -> {
				var list = studDao.findStusByFirstname(ctrler.stuByFirstnameTf.getText());
				displayAll(list);
			});
		}

		private void addFindByLastnameEventHandler() {
			ctrler.stuByLastnameTf.setOnAction(e -> {
				var list = studDao.findStusByLastname(ctrler.stuByLastnameTf.getText());
				displayAll(list);
			});
		}

		private void addFindByDateEventHandler() {
			ctrler.stuByDateTf.setOnAction(e -> {
				var datestr = parseDateStr(ctrler.stuByDateTf.getText());
				if (datestr != null) {
					var localDate = LocalDate.parse(datestr);
					if (localDate != null) {
						var list = studDao.findStusByDateOfReg(localDate);
						displayAll(list);
					}
				} else {
					clearContent();
				}
			});
		}

		@Override
		public void displayContentOf(int id) {
			if (id >= 0) {
				var student = studDao.findById(id);
				displayContentOf(student);
			}
		}

		@Override
		public void displayContentOf(Student student) {
			if (student != null) {
				displayStudent(student);
				displayRegisteredCourse(student.getCourseFk());
				displayRegisteredModules(student.getId());
				displaySchoolOfStudent(student.getCourseFk());
			} else {
				clearContent();
			}
		}

		private void displayStudent(Student student) {
			Platform.runLater(() -> {
				ctrler.stuIdTf.setText(student.getId() + "");
				ctrler.stuFirstnameTf.setText(student.getFirstname());
				ctrler.stuLastnameTf.setText(student.getLastname());
				ctrler.stuDateTf.setText(student.getDateOfRegi().toString());
			});
		}

		private void displayRegisteredCourse(int courseId) {
			// NULL in DBMS
			if (courseId == 0) {
				displayRegisteredCourse(NULL_VALUE, NULL_VALUE, NULL_VALUE);
				return;
			}

			var course = courDao.findById(courseId);
			Platform.runLater(() -> {
				if (course != null) {
					displayRegisteredCourse("" + course.getId(), course.getName(), "" + course.getCredit());
				} else {
					displayRegisteredCourse(NOT_FOUND, NOT_FOUND, NOT_FOUND);
				}
			});
		}

		private void displayRegisteredCourse(String courseId, String courseName, String courseCredit) {
			Platform.runLater(() -> {
				ctrler.stuCouIdTf.setText(courseId);
				ctrler.stuCouNameTf.setText(courseName);
				ctrler.stuCouCreditTf.setText(courseCredit);
			});
		}

		private void displayRegisteredModules(int studentId) {
			var list = comDao.getAllModuOfStud(studentId);
			Platform.runLater(() -> {
				ctrler.stuModLv.setItems(FXCollections.observableList(list));
			});
		}

		private void displaySchoolOfStudent(int courseId) {
			// NULL in DBMS
			if (courseId == 0) {
				displaySchoolOfStudent(NULL_VALUE, NULL_VALUE);
				return;
			}

			var course = courDao.findById(courseId);
			if (course != null) {
				var school = schoDao.findById(course.getId());
				if (school != null) {
					displaySchoolOfStudent(school.getId() + "", school.getName());
				} else {
					displaySchoolOfStudent(NOT_FOUND, NOT_FOUND);
				}
			}
		}

		private void displaySchoolOfStudent(String schoolId, String schoolName) {
			Platform.runLater(() -> {
				ctrler.stuSchIdTf.setText(schoolId);
				ctrler.stuSchNameTf.setText(schoolName);
			});
		}

		@Override
		public void clearContent() {
			Platform.runLater(() -> {
				ctrler.stuIdTf.clear();
				ctrler.stuFirstnameTf.clear();
				ctrler.stuLastnameTf.clear();
				ctrler.stuDateTf.clear();
				ctrler.stuCouIdTf.clear();
				ctrler.stuCouNameTf.clear();
				ctrler.stuCouCreditTf.clear();
				ctrler.stuSchIdTf.clear();
				ctrler.stuSchNameTf.clear();
				ctrler.stuModLv.getItems().clear();
			});
		}
	}
}