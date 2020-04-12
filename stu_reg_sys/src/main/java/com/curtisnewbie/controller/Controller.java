package com.curtisnewbie.controller;

import java.util.ArrayList;
import java.util.List;

import com.curtisnewbie.dao.*;
import com.curtisnewbie.model.Faculty;
import com.curtisnewbie.model.School;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.util.Callback;

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
 * This Controller is mainly responsible for the interactions between the
 * high-level components (tabs and {@link Controller#commonLv}). The control of
 * each tab is delegated to each sub-controller, e.g.,
 * {@code FacultyTabController}.
 * </p>
 * 
 * @see {@link FacultyTabController}
 * @see {@link SchoolTabController}
 * @see {@link CourseTabController}
 * @see {@link ModuleTabController}
 * @see {@link LecturerTabController}
 * @see {@link StudentTabController}
 * 
 */
public class Controller {

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
	private ListView<String> facSchLv; /* Type of items in ListView should be determined */
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
	private ListView<String> schCouLv;

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
	private ListView<String> couMouLv; /* Type of items in ListView should be determined */
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
	private TextField mouByPositionTf;
	@FXML
	private TextField mouIdTf;
	@FXML
	private TextField mouNameTf;
	@FXML
	private TextField mouCreditTf;
	@FXML
	private ListView<String> mouCouLv; /* Type of items in ListView should be determined */
	@FXML
	private ListView<String> mouStuLv; /* Type of items in ListView should be determined */
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
	private ListView<String> lecCouLv; /* Type of items in ListView should be determined */
	@FXML
	private ListView<String> lecMouLv; /* Type of items in ListView should be determined */
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
	private ListView<String> stuModLv; /* Type of items in ListView should be determined */
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

	@FXML
	public void initialize() {
		this.facultyTab = new FacultyTabController();
		this.schoolTab = new SchoolTabController();
		this.courseTab = new CourseTabController();
		this.addTabSelectionEventHandler();
		this.setContextMenuToCommonLv();
	}

	private void addTabSelectionEventHandler() {
		// tab changed
		this.tabpane.getSelectionModel().selectedItemProperty().addListener((ov, prev, curr) -> {
			var index = this.tabpane.getSelectionModel().getSelectedIndex();
			switch (index) {
				case 0:
					displayAll(facuDao.getAll());
					break;
				case 1:
					displayAll(schoDao.getAll());
					break;
				case 2:
					displayAll(courDao.getAll());
					break;
				case 3:
					displayAll(moduDao.getAll());
					break;
				case 4:
					displayAll(lectDao.getAll());
					break;
				case 5:
					displayAll(studDao.getAll());
					break;
			}
		});
	}

	/*
	 * ------------------------------------
	 * 
	 * setContextMenuToCommonLv() not yet finished
	 * 
	 * ------------------------------------
	 */
	/**
	 * Set a context menu to the {@code CommonLv}
	 * 
	 */
	private void setContextMenuToCommonLv() {
		// create context menu
		MenuItem selectItem = new MenuItem("Select");
		MenuItem deleteItem = new MenuItem("Delete");
		ContextMenu ctxMenu = new ContextMenu();
		selectItem.setOnAction(e1 -> {
			var item = commonLv.getSelectionModel().getSelectedItem();
			if (item instanceof Faculty)
				facultyTab.displayContentOf(((Faculty) item).getId());
		});
		deleteItem.setOnAction(e2 -> {
			var item = commonLv.getSelectionModel().getSelectedItem();
			if (item instanceof Faculty) {
				facuDao.deleteById(((Faculty) item).getId());
				displayAll(facuDao.getAll());
			}
		});
		ctxMenu.getItems().addAll(selectItem, deleteItem);
		this.commonLv.setContextMenu(ctxMenu);
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
	private class FacultyTabController implements TabController {
		private Controller ctrler = Controller.this;

		FacultyTabController() {
			addFindByIdEventHandler();
			addFindByNameEventHandler();
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

		/**
		 * Display the content of a {@code Faculty}
		 */
		public void displayContentOf(int facultyId) {
			var faculty = ctrler.facuDao.findById(facultyId);
			if (faculty != null) {
				Platform.runLater(() -> {
					ctrler.facIdTf.setText(faculty.getId() + "");
					ctrler.facNameTf.setText(faculty.getName() == null ? "" : faculty.getName());
				});
				displaySchoolsInFaculty(facultyId);
			}
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
					if (faculty != null) {
						Platform.runLater(() -> {
							ctrler.facIdTf.setText(faculty.getId() + "");
							ctrler.facNameTf.setText(faculty.getName() == null ? "" : faculty.getName());
						});
						displaySchoolsInFaculty(faculty.getId());
					}
				}
			});
		}

		private void displaySchoolsInFaculty(int facultyId) {
			if (facultyId >= 0) {
				var list = comDao.getAllSchoInFacu(facultyId);
				var strlist = new ArrayList<String>();
				for (var sch : list)
					strlist.add(sch.toString());
				Platform.runLater(() -> {
					ctrler.facSchLv.setItems(FXCollections.observableList(strlist));
				});
			}
		}
	}

	/**
	 * Controller for School Tab
	 */
	private class SchoolTabController implements TabController {
		private Controller ctrler = Controller.this;

		SchoolTabController() {
			addFindByIdEventHandler();
			addFindByNameEventHandler();
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
				if (sch != null)
					displayContentOf(sch.getId());
			});
		}

		/**
		 * Display the content of a {@code School}
		 * 
		 * @param schoolId
		 */
		public void displayContentOf(int schoolId) {
			if (schoolId >= 0) {
				var school = schoDao.findById(schoolId);
				if (school != null) {
					Platform.runLater(() -> {
						ctrler.schIdTf.setText(school.getId() + "");
						ctrler.schNameTf.setText(school.getName());
					});
					displayCoursesInSchool(schoolId);
					displayFacultyOfSchool(school.getFacultyFk());
				}
			}
		}

		private void displayCoursesInSchool(int schoolId) {
			if (schoolId >= 0) {
				var list = comDao.getAllSchoInFacu(schoolId);
				var strlist = new ArrayList<String>();
				for (var cou : list)
					strlist.add(cou.toString());
				Platform.runLater(() -> {
					ctrler.schCouLv.setItems(FXCollections.observableList(strlist));
				});
			}
		}

		private void displayFacultyOfSchool(int facultyId) {
			var faculty = facuDao.findById(facultyId);
			if (faculty != null) {
				Platform.runLater(() -> {
					ctrler.schFacIdTf.setText(faculty.getId() + "");
					ctrler.schFacNameTf.setText(faculty.getName());
				});
			}
		}
	}

	/**
	 * Controller for Course Tab
	 */
	private class CourseTabController implements TabController {
		private Controller ctrler = Controller.this;

		CourseTabController() {
			addFindByIdEventHandler();
			addFindByNameEventHandler();
			addFindByCreditEventHandler();
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
					displayContentOf(course.getId());
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
				if (course != null) {
					Platform.runLater(() -> {
						ctrler.couIdTf.setText("" + course.getId());
						ctrler.couNameTf.setText(course.getName());
						ctrler.couCreditTf.setText(course.getCredit() + "");
					});
					displayCourseLeader(course.getLecturerFk());
					displayModulesInCourse(course.getId());
					displaySchoolOfCourse(course.getSchoolFk());
				}
			}
		}

		private void displayCourseLeader(int lecturerId) {
			var lecturer = lectDao.findById(lecturerId);
			if (lecturer != null) {
				Platform.runLater(() -> {
					ctrler.couLeaIdTf.setText(lecturer.getId() + "");
					ctrler.couLeaNameTf.setText(lecturer.getLastname() + " " + lecturer.getFirstname());
				});
			}
		}

		private void displayModulesInCourse(int courseId) {
			var list = comDao.getAllModuInCour(courseId);
			var strlist = new ArrayList<String>();
			for (var mou : list)
				strlist.add(mou.toString());
			Platform.runLater(() -> {
				ctrler.couMouLv.setItems(FXCollections.observableList(strlist));
			});
		}

		private void displaySchoolOfCourse(int schoolId) {
			var school = schoDao.findById(schoolId);
			if (school != null) {
				Platform.runLater(() -> {
					ctrler.couSchIdTf.setText(school.getId() + "");
					ctrler.couSchNameTf.setText(school.getName());
				});
			}
		}
	}

	/**
	 * Controller for Module Tab
	 */
	private class ModuleTabController implements TabController {
		private Controller ctrler = Controller.this;

		@Override
		public void displayContentOf(int id) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * Controller for Lecturer Tab
	 */
	private class LecturerTabController implements TabController {
		private Controller ctrler = Controller.this;

		@Override
		public void displayContentOf(int id) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * Controller for Student Tab
	 */
	private class StudentTabController implements TabController {
		private Controller ctrler = Controller.this;

		@Override
		public void displayContentOf(int id) {
			// TODO Auto-generated method stub

		}

	}
}