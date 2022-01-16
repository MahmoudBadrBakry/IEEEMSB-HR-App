/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ieeemsb.view;

import ieeemsb.IEEEHRAPP;
import ieeemsb.db.SQLDB;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author mbadr
 */
public class PointSystem {

	public static String css = IEEEHRAPP.class.getResource("myCss.css").toExternalForm();

	public static void pointscene(int memberid, String membername, int memberpoints) {

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

		Button plusBtn = new Button("Bonus");
		Button minusBtn = new Button("Minus");
		Button backBtn = new Button("Back");

		Label idLabel = new Label("Id:");
		Label nameLabel = new Label("Name:");
		Label pointsLabel = new Label("Points:");

		TextField idTextField = new TextField(memberid + "");
		TextField nameTextField = new TextField(membername);
		TextField pointsTextField = new TextField(memberpoints + "");

		plusBtn.setOnAction(e -> {
			int memberPoints = Integer.parseInt(pointsTextField.getText());
			memberPoints++;
			pointsTextField.setText(memberPoints + "");
			SQLDB.updatePoints(memberPoints, memberid);
		});

		minusBtn.setOnAction(e -> {
			int memberPoints = Integer.parseInt(pointsTextField.getText());
			memberPoints--;
			pointsTextField.setText(memberPoints + "");
			SQLDB.updatePoints(memberPoints, memberid);
		});

		backBtn.setOnAction(e -> window.close());

		idTextField.setEditable(false);
		nameTextField.setEditable(false);
		pointsTextField.setEditable(false);

		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10));
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(minusBtn, backBtn);

		gridPane.setConstraints(idLabel, 0, 0);
		gridPane.setConstraints(nameLabel, 0, 1);
		gridPane.setConstraints(pointsLabel, 0, 2);
		gridPane.setConstraints(idTextField, 1, 0);
		gridPane.setConstraints(nameTextField, 1, 1);
		gridPane.setConstraints(pointsTextField, 1, 2);
		gridPane.setConstraints(plusBtn, 0, 4);
		gridPane.setConstraints(hBox, 1, 4);
		gridPane.getChildren().addAll(idLabel, nameLabel, pointsLabel, idTextField, nameTextField, pointsTextField,
				plusBtn, hBox);

		Scene scene = new Scene(gridPane, 300, 160);
		scene.getStylesheets().add(css);

		window.setMaxHeight(210);
		window.setMaxWidth(280);
		window.setMinHeight(210);
		window.setMinWidth(280);
		window.setTitle("Points Page");
		window.setScene(scene);
		window.showAndWait();
	}
}