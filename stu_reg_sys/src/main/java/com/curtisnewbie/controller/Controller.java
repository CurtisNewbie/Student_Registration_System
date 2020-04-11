package com.curtisnewbie.controller;

import java.util.ArrayList;
import java.util.List;

import com.curtisnewbie.dao.*;
import com.curtisnewbie.model.Faculty;

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
				facultyTab.displayById(((Faculty) item).getId());
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
	private class FacultyTabController {
		private Controller ctrler = Controller.this;

		FacultyTabController() {
			addFindByIdEventHandler();
			addFindByNameEventHandler();
		}

		/**
		 * Add EventHandler to {@link Controller#facByIdTf}. It internally calls
		 * {@link FacultyTabController#displayById(int)} and
		 * {@link FacultyTabController#displaySchoolsInFaculty(int)} to refresh the
		 * listview for displaying schools in this faculty.
		 */
		private void addFindByIdEventHandler() {
			ctrler.facByIdTf.setOnAction(e -> {
				int id = -1;
				try {
					id = Integer.parseInt(facByIdTf.getText());
				} catch (NumberFormatException ne) {
				}
				if (id != -1)
					displayById(id);
			});
		}

		/**
		 * Display the content of a faculty
		 */
		public void displayById(int id) {
			var faculty = ctrler.facuDao.findById(id);
			if (faculty != null) {
				Platform.runLater(() -> {
					ctrler.facIdTf.setText(faculty.getId() + "");
					ctrler.facNameTf.setText(faculty.getName() == null ? "" : faculty.getName());
				});
				displaySchoolsInFaculty(id);
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
	private class SchoolTabController {
		private Controller ctrler = Controller.this;

	}

	/**
	 * Controller for Course Tab
	 */
	private class CourseTabController {
		private Controller ctrler = Controller.this;

	}

	/**
	 * Controller for Module Tab
	 */
	private class ModuleTabController {
		private Controller ctrler = Controller.this;

	}

	/**
	 * Controller for Lecturer Tab
	 */
	private class LecturerTabController {
		private Controller ctrler = Controller.this;

	}

	/**
	 * Controller for Student Tab
	 */
	private class StudentTabController {
		private Controller ctrler = Controller.this;

	}
}