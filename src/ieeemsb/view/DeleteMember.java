package ieeemsb.view;

import static ieeemsb.IEEEHRAPP.css;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeleteMember {
    
    public static boolean answer = false;
    
    public static Boolean delete( String message){
        Stage window = new Stage();
        window.setTitle("Deleting Member!");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(450);
        window.setMinHeight(140);
        window.setMaxHeight(140);
        window.setMaxWidth(450);
        window.setOnCloseRequest(e -> e.consume());
        
        Label qusetion = new Label("Are you Sure you want to delete " + message + " ?!");
        
        Button yesBtn = new Button("Yes");
        yesBtn.setFont(Font.font(12.0));
        yesBtn.setOnAction(e -> {
           answer = true;
           window.close();
        });
        
        Button noBtn = new Button("No");
        noBtn.setOnAction(e -> {
            answer = false;
            window.close();
        });
       
        HBox layoutBtns = new HBox(40);
        layoutBtns.setPadding(new Insets(10, 10, 10, 10));
        layoutBtns.setAlignment(Pos.CENTER);
        layoutBtns.getChildren().addAll(yesBtn , noBtn);
        
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(qusetion , layoutBtns );
        layout.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(css);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }    
}