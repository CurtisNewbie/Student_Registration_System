package com.curtisnewbie.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
 * high-level components (tabs and {@link Controller#generalLv}). The control of
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
	 * General ListView (on the right-hand side of the screen)
	 * 
	 * ------------------------------------
	 */
	@FXML
	private ListView<String> generalLv; /* Type of items in ListView should be determined */

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

	@FXML
	public void initialize() {
		this.facultyTab = new FacultyTabController();
	}

	/**
	 * Controller for Faculty Tab
	 */
	private class FacultyTabController {
		private Controller ctrler = Controller.this;

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