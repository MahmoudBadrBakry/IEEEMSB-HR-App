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

public class ModifyMember {
    public static Member modifyMemeber(Member member){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

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

        TextField textFieldId = new TextField();
        TextField textFieldName = new TextField();
        TextField textFieldAcadimicYear = new TextField();
        TextField textFieldMail = new TextField();
        TextField textFieldPhone = new TextField();
        ComboBox committeeComboBox1 = new ComboBox<>();
        committeeComboBox1.getItems().addAll("PR","HR","Media"
            ,"SocialMedia","Operation","CS","RAS","BIO","EMBS","Board");
        TextField textFieldInfo = new TextField();
        
        textFieldId.setText(""+member.getId());
        textFieldId.setEditable(false);
        textFieldName.setText(""+member.getName());
        textFieldAcadimicYear.setText(""+member.getAcadimicYear());
        textFieldMail.setText(""+member.getMail());
        textFieldPhone.setText(""+member.getPhone());
        committeeComboBox1.setValue(""+member.getCommittee());
        textFieldInfo.setText(""+member.getInfo());

        GridPane.setConstraints(textFieldId, 1, 0);
        GridPane.setConstraints(textFieldName, 1, 1);
        GridPane.setConstraints(textFieldAcadimicYear, 1, 2);
        GridPane.setConstraints(textFieldMail , 1, 3);
        GridPane.setConstraints(textFieldPhone , 1, 4);
        GridPane.setConstraints(committeeComboBox1 , 1, 5);
        GridPane.setConstraints(textFieldInfo , 1, 6);


        Button modifyBtnConfirm = new Button("Modify");
        modifyBtnConfirm.setOnAction(e->{
            try{
                int id = Integer.parseInt(textFieldId.getText());
            String name = textFieldName.getText();
            int acadimicYear = Integer.parseInt(textFieldAcadimicYear.getText());
            String mail = textFieldMail.getText();
            String phone = textFieldPhone.getText();
            String committee = (String) committeeComboBox1.getValue();
            String info = textFieldInfo.getText();

            member.setId(id);
            member.setName(name);
            member.setAcadimicYear(acadimicYear);
            member.setMail(mail);
            member.setPhone(phone);
            member.setCommittee(committee);
            member.setInfo(info);
            window.close();
            }catch(Exception e1){
                e1.printStackTrace();
                AlertBox.display("Inputs Error", "\t\t\tError!!\nAcadimic year and Id must be Integer and Exist!!" , "Ok" , 420);
            }
            
        });

        GridPane.setConstraints(modifyBtnConfirm, 1, 7, 2, 1);

        gridPane.getChildren().addAll(labelId, labelName, labelAcadimicYear 
        , labelMail , labelPhone , labelCommittee , labelInfo
        , textFieldId, textFieldName, textFieldAcadimicYear 
        , textFieldMail , textFieldPhone , committeeComboBox1 , textFieldInfo
                , modifyBtnConfirm);

        Scene scene = new Scene(gridPane, 320, 300);
        scene.getStylesheets().add(css);
        window.setMaxHeight(340);
        window.setMaxWidth(345);
        window.setMinHeight(340);
        window.setMinWidth(345);
        window.setTitle("Modify");
        window.setScene(scene);
        window.showAndWait();
        return member;
    }    
}