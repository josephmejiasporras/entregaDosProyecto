
package ui;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.*;


public class ConfirmBox {
    
    static boolean answer;
    
    public static boolean display(String title, String message){
        
        
        Stage window = new Stage(); 
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(200);
        Label label = new Label();
        label.setText(message);
        
        Button yes = new Button("Si");
        Button no = new Button("No");
        
        yes.setOnAction(e->{
            
            answer=true;
            window.close();
            
        });
        
        no.setOnAction(e->{
            
            answer=false;
            window.close();
            
        });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yes, no);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style4.css");
        window.setScene(scene);
        window.showAndWait();
        
        return answer;
    }
    
}
