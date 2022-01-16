package ieeemsb;

import ieeemsb.db.SQLDB;
import ieeemsb.models.Member;
import ieeemsb.view.AddMember;
import ieeemsb.view.AlertBox;
import ieeemsb.view.DeleteMember;
import ieeemsb.view.EditPassword;
import ieeemsb.view.ModifyMember;
import ieeemsb.view.PointSystem;
import ieeemsb.view.SignIn;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author IEEE JAVA TEAM ^_^
 */
public class IEEEHRAPP extends Application {
	private Button selectBtn, editBtn, addBtn, projectsBtn, modifyBtn, deleteBtn, backBtnSelectScene, points;
	private Scene selectScene, modifyScene;
	private TableView<Member> table;
	private ComboBox<String> committeeComboBox;
	private Label committeeLabel;
	private boolean tableIntialized = false;
	private BorderPane mainP;
	private Pane mainPage, selectPage, modifyPage;
	private ObservableList<Member> selectedToModify, allMembers;
	private static boolean closing = false;

	public static void setClosing(boolean closing) {
		IEEEHRAPP.closing = closing;
	}

	public static String css = IEEEHRAPP.class.getResource("myCss.css").toExternalForm();

	@Override
	public void start(Stage window) {

		SignIn.startPage();
		window.setTitle("IEEE MSB HR APP");

		mainPage = new Pane();

		////// Buttons Configuration
		selectBtn = new Button("Select Member");
		addBtn = new Button("Add Member");
		editBtn = new Button("Edit Password");

		addBtn.setMinSize(90, 30);
		addBtn.layoutXProperty().bind(mainPage.widthProperty().divide(10).multiply(4.3));
		addBtn.layoutYProperty().bind(mainPage.heightProperty().divide(10).multiply(8));

		selectBtn.setMinSize(90, 30);
		selectBtn.layoutXProperty().bind(mainPage.widthProperty().divide(10).multiply(1));
		selectBtn.layoutYProperty().bind(mainPage.heightProperty().divide(10).multiply(8));

		editBtn.setMinSize(90, 30);
		editBtn.layoutXProperty().bind(mainPage.widthProperty().divide(10).multiply(7.4));
		editBtn.layoutYProperty().bind(mainPage.heightProperty().divide(10).multiply(8));

		///// Buttons Actions
		addBtn.setOnAction(e -> {
			Member newMember = AddMember.addMember();
			if (newMember != null) {
				SQLDB.insertMember(newMember);
				updateTableData();
			}
		});
		
		selectBtn.setOnAction(e -> {
			window.setScene(selectScene);
		});

		editBtn.setOnAction(e -> {
			EditPassword.edit();
		});

		///// IEEE logo
		ImageView ieeeLogo = new ImageView(new Image("ieeemsb/IEEEMSB Logo.JPG"));
		ieeeLogo.setFitHeight(280);
		ieeeLogo.setFitWidth(250);
		ieeeLogo.layoutXProperty().bind(mainPage.widthProperty().divide(10).multiply(3.6));
		ieeeLogo.layoutYProperty().bind(mainPage.heightProperty().divide(10).multiply(1));

		////// Pane view
		mainPage.getChildren().addAll(editBtn, addBtn, selectBtn, ieeeLogo);

		Scene scene = new Scene(mainPage, 810, 520);

/////////////////////////////////////////////////////////////////////       
//////////////////////////////select scene///////////////////////////

		try {
			///////// pointscolumn
			TableColumn<Member, Integer> pointsColumn = new TableColumn<>("Points");
			pointsColumn.setMinWidth(20);
			pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

			/// idColumn
			TableColumn<Member, Integer> idColumn = new TableColumn<>("ID");
			idColumn.setMinWidth(20);
			idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

			/// nameColumn
			TableColumn<Member, String> nameColumn = new TableColumn<>("Name");
			nameColumn.setMinWidth(120);
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

			/// acadimicYearColumn
			TableColumn<Member, Integer> acadimicYearColumn = new TableColumn<>("Acadimic Year");
			acadimicYearColumn.setMinWidth(20);
			acadimicYearColumn.setCellValueFactory(new PropertyValueFactory<>("acadimicYear"));

			/// mailColumn
			TableColumn<Member, String> mailColumn = new TableColumn<>("Mail");
			mailColumn.setMinWidth(150);
			mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));

			/// phoneColumn
			TableColumn<Member, String> phoneColumn = new TableColumn<>("Phone");
			phoneColumn.setMinWidth(100);
			phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

			/// committeeColumn
			TableColumn<Member, String> committeeColumn = new TableColumn<>("Committee");
			committeeColumn.setMinWidth(102);
			committeeColumn.setCellValueFactory(new PropertyValueFactory<>("committee"));

			/// infoColumn
			TableColumn<Member, String> infoColumn = new TableColumn<>("Info");
			infoColumn.setMinWidth(160);
			infoColumn.setCellValueFactory(new PropertyValueFactory<>("info"));

			////// table config
			table = new TableView<>();
			table.getColumns().addAll(idColumn, nameColumn, acadimicYearColumn, phoneColumn, mailColumn,
					committeeColumn, infoColumn, pointsColumn);
			table.setLayoutX(0);
			table.setLayoutY(0);
			table.setMinSize(200, 170);
			table.setMaxSize(860, 460);
			updateTableData();

			/////////////// backBtnSelectScene
			backBtnSelectScene = new Button("Back");
			backBtnSelectScene.setLayoutX(500);
			backBtnSelectScene.setLayoutY(440);
			backBtnSelectScene.setMinSize(90, 30);
			backBtnSelectScene.setOnAction(e -> {
				window.setScene(mainPage.getScene());
			});

			///////// pointsbtn

			points = new Button("points");
			points.setMinSize(90, 30);
			points.setLayoutX(360);
			points.setLayoutY(440);
			points.setOnAction(e -> {
				try {
					int memberId = table.getSelectionModel().getSelectedItem().getId();
					String membername = table.getSelectionModel().getSelectedItem().getName();
					int memberpoints = table.getSelectionModel().getSelectedItem().getPoints();
					PointSystem.pointscene(memberId, membername, memberpoints);
					updateTableData();
				} catch (Exception e1) {
					AlertBox.display("Error", "Please Select a member !!!", "Ok", 270);
				}
			});

			/////////////// modifyBtn
			modifyBtn = new Button("Modify");
			modifyBtn.setLayoutX(60);
			modifyBtn.setLayoutY(440);
			modifyBtn.setMinSize(90, 30);
			modifyBtn.setOnAction(e -> {
				Member member = null;
				try {
					member = table.getSelectionModel().getSelectedItem();
					ModifyMember.modifyMemeber(member);
					SQLDB.modifyMember(member);
					updateTableData();
				} catch (Exception e1) {
					AlertBox.display("Error", "Please Select a member !!!", "Ok", 270);
				}
			});

			/////////////// deleteBtn
			deleteBtn = new Button("Delete");
			deleteBtn.setLayoutX(210);
			deleteBtn.setLayoutY(440);
			deleteBtn.setMinSize(90, 30);
			deleteBtn.setOnAction(e -> {
				try {
					if (DeleteMember.delete(table.getSelectionModel().getSelectedItem().getName())) {
						int memberId = table.getSelectionModel().getSelectedItem().getId();
						SQLDB.deleteMember(memberId);
						updateTableData();
					}
				} catch (Exception e1) {
					AlertBox.display("Error", "Please Select a member !!!", "Ok", 270);
				}
			});

			////////// committee combobox
			committeeLabel = new Label();
			committeeLabel.setText("Committee");
			committeeLabel.setLayoutX(665);
			committeeLabel.setLayoutY(415);

			committeeComboBox = new ComboBox<>();
			committeeComboBox.setLayoutX(660);
			committeeComboBox.setLayoutY(445);
			committeeComboBox.getItems().addAll("PR", "HR", "Media", "SocialMedia", "Operation", "CS", "RAS", "BIO",
					"EMBS", "Board", "All");
			committeeComboBox.setValue("All");
			committeeComboBox.setOnAction(e -> {
				updateTableData();
			});

			//////// selectPage
			selectPage = new AnchorPane();
			selectPage.getChildren().addAll(table, backBtnSelectScene, deleteBtn, modifyBtn, committeeComboBox,
					committeeLabel, points);
			selectScene = new Scene(selectPage, 820, 520);
			selectScene.getStylesheets().add(css);
		} catch (Exception e) {
			e.printStackTrace();
		}

///////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////

		scene.getStylesheets().add(css);
		window.setMaxHeight(520);
		window.setMaxWidth(860);
		window.setMinHeight(520);
		window.setMinWidth(860);
		window.setScene(scene);
		window.show();
		if (closing)
			window.close();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void updateTableData() {
		if (!tableIntialized || (committeeComboBox.getValue().equals("All"))) {
			table.setItems(SQLDB.selectAllMembers());
			tableIntialized = true;
		} else {
			ObservableList<Member> tableData = FXCollections.observableArrayList();
			for (int i = 0; i < SQLDB.selectAllMembers().size(); ++i) {
				if (SQLDB.selectAllMembers().get(i).getCommittee().contains(committeeComboBox.getValue())) {
					tableData.add(SQLDB.selectAllMembers().get(i));
				}
			}
			table.setItems(tableData);
		}
	}
}