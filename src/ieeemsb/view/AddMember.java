package ieeemsb.view;

import static ieeemsb.IEEEHRAPP.css;

import ieeemsb.models.Member;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddMember {

	private static ComboBox<String> committeeComboBox;
	private static TextField textFieldInfo;
	private static TextField textFieldPhone;
	private static TextField textFieldMail;
	private static TextField textFieldAcadimicYear;
	private static TextField textFieldName;
	private static TextField textFieldId;

	public static Member addMember() {
		Member member  = new Member();;

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10));
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		Button addBtnConfirm = new Button("Add Member");

		Label labelId = new Label("Id: ");
		Label labelName = new Label("Name: ");
		Label labelAcadimicYear = new Label("Acadimic Year: ");
		Label labelMail = new Label("Mail: ");
		Label labelPhone = new Label("Phone: ");
		Label labelCommittee = new Label("Committee: ");
		Label labelInfo = new Label("info: ");

		GridPane.setConstraints(labelId, 0, 0);
		GridPane.setConstraints(labelName, 0, 1);
		GridPane.setConstraints(labelAcadimicYear, 0, 2);
		GridPane.setConstraints(labelMail, 0, 3);
		GridPane.setConstraints(labelPhone, 0, 4);
		GridPane.setConstraints(labelCommittee, 0, 5);
		GridPane.setConstraints(labelInfo, 0, 6);

		textFieldId = new TextField();
		textFieldId.setPromptText(" Id");
		textFieldName = new TextField();
		textFieldName.setPromptText(" Name");
		textFieldAcadimicYear = new TextField();
		textFieldAcadimicYear.setPromptText(" Acadimic Year");
		textFieldMail = new TextField();
		textFieldMail.setPromptText(" Mail");
		textFieldPhone = new TextField();
		textFieldPhone.setPromptText(" Phone");
		textFieldInfo = new TextField();
		textFieldInfo.setPromptText("Info");

		committeeComboBox = new ComboBox<>();
		committeeComboBox.setPromptText("Committee");
		committeeComboBox.getItems().addAll("PR", "HR", "Media", "SocialMedia", "Operation", "CS", "RAS", "BIO", "EMBS",
				"Board");

		GridPane.setConstraints(textFieldId, 1, 0);
		GridPane.setConstraints(textFieldName, 1, 1);
		GridPane.setConstraints(textFieldAcadimicYear, 1, 2);
		GridPane.setConstraints(textFieldMail, 1, 3);
		GridPane.setConstraints(textFieldPhone, 1, 4);
		GridPane.setConstraints(committeeComboBox, 1, 5);
		GridPane.setConstraints(textFieldInfo, 1, 6);
		GridPane.setConstraints(addBtnConfirm, 1, 7, 2, 1);

		addBtnConfirm.setOnAction(e -> {
			try {
				setMemberData(member);
				window.close();
			} catch (Exception exception) {
				AlertBox.display("Inputs Error", "\tError!!\nData is invalid",
						"Ok", 420);
				exception.printStackTrace();
			}

		});

		gridPane.getChildren().addAll(labelId, labelName, labelAcadimicYear, labelMail, labelPhone, labelCommittee,
				labelInfo, textFieldId, textFieldName, textFieldAcadimicYear, textFieldMail, textFieldPhone,
				committeeComboBox, textFieldInfo, addBtnConfirm);

		Scene scene = new Scene(gridPane, 320, 300);
		scene.getStylesheets().add(css);

		window.setMaxHeight(340);
		window.setMaxWidth(345);
		window.setMinHeight(340);
		window.setMinWidth(345);
		window.setTitle("Add New  Member");
		window.setScene(scene);
		window.showAndWait();

		return member;
	}

	private static void setMemberData(Member member) {
		
		int id = Integer.parseInt(textFieldId.getText());
		if (id < 1) {
			throw new RuntimeException("invalid id");
		}
		
		String name = textFieldName.getText();
		int acadimicYear = Integer.parseInt(textFieldAcadimicYear.getText());
		String mail = textFieldMail.getText();
		String phone = textFieldPhone.getText();
		String committee = committeeComboBox.getValue();
		String info = textFieldInfo.getText();
		
		
		member.setId(id);
		member.setName(name);
		member.setAcadimicYear(acadimicYear);
		member.setMail(mail);
		member.setPhone(phone);
		member.setCommittee(committee);
		member.setInfo(info);
	}
}