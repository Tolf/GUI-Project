
package com.anderstolf.summoners;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Anders
 */
public class MainApp extends Application {

    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage stage) throws IOException  {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Scene.fxml"));
        loader.setController(new MainWindowFXML());
        BorderPane pane = loader.load();
        
        Scene scene = new Scene(pane);
        scene.getStylesheets().add("styleSheet.css");
        stage.setTitle("Summoners Chart!");
        stage.setScene(scene);
        stage.show();
        
    }
    
    
}
