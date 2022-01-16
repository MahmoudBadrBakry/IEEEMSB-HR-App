package ieeemsb.view;

import ieeemsb.IEEEHRAPP;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static String Error = IEEEHRAPP.class.getResource("Error.css").toExternalForm();
    public static void display(String title, String message , String buttonText,int width){
        Stage window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(width);
        window.setMinHeight(170);
        window.setMaxHeight(170);
        window.setMaxWidth(width);
        
        Label messageLabel = new Label(message);
        Button btn = new Button(buttonText);
        btn.setOnAction(e -> window.close());
        
        VBox layout = new VBox(20);
        layout.getChildren().addAll(messageLabel , btn);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(Error);
        
        window.setScene(scene);
        window.showAndWait();
    }
}