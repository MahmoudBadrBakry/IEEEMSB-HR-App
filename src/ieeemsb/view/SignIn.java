package ieeemsb.view;

import static ieeemsb.IEEEHRAPP.css;
import static ieeemsb.view.AlertBox.Error;

import ieeemsb.IEEEHRAPP;
import ieeemsb.db.SQLDB;
import ieeemsb.utils.Encryptor;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SignIn  {

    public static void startPage() {
        Stage window = new Stage();
        Label check = new Label(" ");
        check.getStylesheets().add(Error);
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setOnCloseRequest(e -> {
            e.consume();
            IEEEHRAPP.setClosing(true);
            window.close();
                });
        
        TextField userInput = new TextField();
        userInput.setPromptText("Email");
        PasswordField passInput = new PasswordField();
        passInput.setPromptText("Password");
        
        
        Button signInBtn = new Button();
        signInBtn.setText("Sign in");
        signInBtn.setMinSize(40, 20);
        signInBtn.setOnAction(e -> {
            if(buttonHandling(userInput.getText(), passInput.getText())){
                window.close();
            }else {
                check.setText("Error mail or password!!!");
            }
        });
        
        Label emailLabel = new Label("Email: ");
        Label passwordLabel = new Label("Password: ");
        
        HBox bottom = new HBox(50);
        bottom.getChildren().addAll(signInBtn , check);
        
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(15,15,15,15));
        layout.getChildren().addAll(emailLabel,userInput,passwordLabel,passInput,bottom);
        
        Scene scene = new Scene(layout, 270, 400);
        scene.getStylesheets().add(css);
        
        window.setMaxHeight(270);
        window.setMaxWidth(400);
        window.setMinHeight(270);
        window.setMinWidth(400);
        window.setScene(scene);
        window.setTitle("Sign in");
        window.showAndWait();
    }    
    private static boolean buttonHandling(String mail,String pass){
        String Committee = SQLDB.selectCommittee(mail);
        if(!Committee.equals("HR") && !Committee.equals("Board"))
            return false;
        String checkPass = Encryptor.encrypt(pass);
        String acutalPass = SQLDB.selectPass();
        if (!checkPass.equals(acutalPass))
            return false;
        return true;
    }
    
}