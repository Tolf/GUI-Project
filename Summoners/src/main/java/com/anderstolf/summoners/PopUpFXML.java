
package com.anderstolf.summoners;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Anders
 */
public class PopUpFXML implements Initializable{
    
    @FXML
    TextField tfieldNick;
    @FXML
    TextField tfieldWins;
    @FXML
    TextField tfieldLosses;
    @FXML
    Button btnSubmit;
    @FXML
    Button btnCancel;
    @FXML
    Tooltip tooltipWin;
    @FXML
    Tooltip tooltipLoss;
    @FXML
    Tooltip tooltipNickname;
    @FXML
    Label labelWins;
    @FXML
    Label labelLosses;
    @FXML
    Label labelNickname;
    
    private boolean created;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        btnSubmit.setOnAction(this::submitAction);
        btnSubmit.setDisable(true);
        btnCancel.setOnAction(this::cancelAction);
        
        tfieldNick.textProperty().addListener((observable, oldValue, newValue)->{
            if(newValue.length() >= 3){
                tfieldWins.setDisable(false);
                labelNickname.setGraphic(acceptableImageView());  
                tooltipNickname.hide();
                
            }else if(newValue.length() < 3){
                tooltipNickname.setText("Nickname must be atleast 3 characters!");
                tooltipNickname.show(tfieldNick.getScene().getWindow());
                labelNickname.setGraphic(null);
                
                tfieldWins.setDisable(true);
                tfieldLosses.setDisable(true);
                btnSubmit.setDisable(true);
                
                tooltipLoss.hide();
                tooltipWin.hide();
            }
        });
        
        tfieldWins.textProperty().addListener((observable, oldValue, newValue)-> {    
            if(!newValue.matches("\\d*") || newValue.isEmpty()){
                tooltipWin.setText("Not a valid number. Only use 0-9!");
                tooltipWin.show(tfieldWins.getScene().getWindow());
                labelWins.setGraphic(unacceptableImageView());
                tfieldLosses.setDisable(true);
                btnSubmit.setDisable(true);
                
            }else {
                tooltipWin.hide();
                labelWins.setGraphic(acceptableImageView());
                tfieldLosses.setDisable(false);
            }
        });
        
        tfieldLosses.textProperty().addListener((observable, oldValue, newValue)-> {    
            if(!newValue.matches("\\d*") || newValue.isEmpty()){
                tooltipLoss.setText("Not a valid number. Only use 0-9!");
                tooltipLoss.show(tfieldLosses.getScene().getWindow());
                labelLosses.setGraphic(unacceptableImageView());
                btnSubmit.setDisable(true);
                
            }else{
                btnSubmit.setDisable(false);
                tooltipLoss.hide();
                labelLosses.setGraphic(acceptableImageView());
            }    
            });
        }
    
    private ImageView acceptableImageView(){
        return new ImageView(new Image(getClass().getResourceAsStream("images\\acceptable.png")));
    }
    private ImageView unacceptableImageView(){
        return new ImageView(new Image(getClass().getResourceAsStream("images\\unacceptable.jpg")));
    }
    
    private void cancelAction(ActionEvent event){
        
         ((Node)(event.getSource())).getScene().getWindow().hide();
         created = false;
    }
    
    private void submitAction(ActionEvent event){
        
        ((Node)(event.getSource())).getScene().getWindow().hide();
        created = true;
    }
    
    public boolean created(){
        return created;
    }

    public String getTfieldLosses() {
        return tfieldLosses.getText();
    }

    public String getTfieldNick() {
        return tfieldNick.getText();
    }
    
    public String getTfieldWins(){
        return tfieldWins.getText();
    }
    
    
    
    
    
    
}
