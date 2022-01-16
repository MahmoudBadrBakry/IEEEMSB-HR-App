
package ieeemsb.view;

import static ieeemsb.IEEEHRAPP.css;
import static ieeemsb.view.AlertBox.Error;

import ieeemsb.IEEEHRAPP;
import ieeemsb.db.SQLDB;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditPassword  {

    public static void edit() {
        Stage window = new Stage();
        
        Label check = new Label(" ");
        check.getStylesheets().add(Error);
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setOnCloseRequest(e -> {
            e.consume();
            IEEEHRAPP.setClosing(true);
            window.close();
                });
        
        PasswordField oldPassTextField = new PasswordField();
        oldPassTextField.setPromptText("Old Password");
        PasswordField newpassTextField = new PasswordField();
        newpassTextField.setPromptText("New Password");
        
        
        Button confirmBtn = new Button();
        confirmBtn.setText("Confirm");
        confirmBtn.setMinSize(40, 20);
        confirmBtn.setOnAction(e -> {
            if(buttonHandling(oldPassTextField.getText())){
                SQLDB.modifyPass(encryption(newpassTextField.getText()));
                window.close();
            }else {
                check.setText("Incorrect Password!!!");
            }
        });
        Label oldPass = new Label("Old password: ");
        Label newPass= new Label("New password: ");
        
        HBox bottom = new HBox(50);
        bottom.getChildren().addAll(confirmBtn , check);
        
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(15, 15,15,15));
        layout.getChildren().addAll(oldPass,oldPassTextField,newPass,newpassTextField,bottom);
        
        Scene scene = new Scene(layout, 270, 400);
        scene.getStylesheets().add(css);
        
        window.setMaxHeight(270);
        window.setMaxWidth(400);
        window.setMinHeight(270);
        window.setMinWidth(400);
        window.setScene(scene);
        window.setTitle("Changing Password");
        window.showAndWait();
    }    
    private static boolean buttonHandling(String oldPass){
        String checkPass = encryption(oldPass);
        String acutalPass = SQLDB.selectPass();
        if (!checkPass.equals(acutalPass))
            return false;
        return true;
    }
    
    private static String encryption(String plainString){
        StringBuilder encrypted = new StringBuilder();
        int count = 0;
        int count1 = 0;
        char character ;
        StringBuilder plain = new StringBuilder(plainString);
        StringBuilder lowerKey = new StringBuilder("happyencryption");
        StringBuilder upperKey = new StringBuilder("HAPPYENCRYPTION");
        StringBuilder digitKey = new StringBuilder("893423674");
       
        for (int i = 0; i < plain.length() ; i++) {
            if (count == lowerKey.length())
                count = 0 ;
            if (count1 == digitKey.length())
                count1 = 0 ;
            if(Character.isLowerCase(plain.charAt(i))){
                character = (char) (plain.charAt(i) + (lowerKey.charAt(count)-'a'));
                if (character > 'z')
                    character -= 26;
                encrypted.append((char)character);
                count++;
            }else if(Character.isUpperCase(plain.charAt(i))){
                character = (char) (plain.charAt(i) + (upperKey.charAt(count)-'A'));
                if (character > 'Z')
                    character -= 26;
                encrypted.append((char)character);
                count++;
            }else if(Character.isDigit(plain.charAt(i))){
                character = (char) (plain.charAt(i) + (digitKey.charAt(count1) - '0'));
                if (character > '9')
                    character -= 10;
                encrypted.append((char)character);
                count1++;
            }else{
                encrypted.append(plain.charAt(i));
            }
        }
        String returnedString = encrypted.toString();
        return returnedString;
    }
}