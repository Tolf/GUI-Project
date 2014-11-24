package com.anderstolf.summoners;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Anders
 */
class MainWindowFXML implements Initializable {

    @FXML
    Button btnAdd;
    @FXML
    Button btnRemove;
    @FXML
    ListView<Summoner> listView;
    @FXML
    ListView listRightInfo;
    @FXML
    PieChart chartPie;
    @FXML
    TextArea textArea;

    private final ObservableList<PieChart.Data> pieList = FXCollections.observableArrayList();

    private final ObservableList<Summoner> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listView.setItems(list);
//        listView.setOnMouseClicked(this::selectFromList);
        btnAdd.setOnAction(this::addToList);
        listView.setCellFactory(view -> new SummonerNameCell());

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectFromList(newValue);
        }
        );

        btnRemove.setOnAction(this::removeFromList);

    }

    private void selectFromList(Summoner summoner) {

        pieList.clear();

        if (summoner != null) {
            
            textArea.textProperty().bind(summoner.description());
            chartPie.setTitle(summoner.getNick());
            pieList.add(new PieChart.Data("Wins", summoner.getWins()));
            pieList.add(new PieChart.Data("Losses", summoner.getLosses()));
            chartPie.setData(pieList);
        }
    }

    private void addToList(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            PopUpFXML dialog = new PopUpFXML();
            loader.setLocation(getClass().getResource("AddDialog.fxml"));
            loader.setController(dialog);
            GridPane pane = loader.load();

            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setTitle("Create new Summoner");
            stage.setScene(scene);
            stage.showAndWait();

            if (dialog.created()) {
                Summoner summoner = new Summoner();
                summoner.setLosses(Integer.parseInt(dialog.getTfieldLosses()));
                summoner.setWins(Integer.parseInt(dialog.getTfieldWins()));
                summoner.setNickname(dialog.getTfieldNick());
                list.add(summoner);
            }

        } catch (IOException ex) {
        }

    }

    private void removeFromList(ActionEvent event) {

        textArea.textProperty().unbind();
        textArea.clear();
        chartPie.setTitle(null);

        list.remove(listView.getSelectionModel().getSelectedItem());
    }
}

//            Dialog dialog = new Dialog(new Stage(), "Create new Summoner");
//            
//            GridPane grid = new GridPane();
//            grid.setVgap(10);
//            grid.setHgap(10);
//            grid.setPadding(new Insets(0,10, 0, 10));
//            
//            nickName.setPromptText("Nickname ");
//            wins.setPromptText("Wins");
//            losses.setPromptText("Losses");
//            
//            grid.add(new Label("Nickname: "), 0, 0);
//            grid.add(nickName, 1, 0);
//            grid.add(new Label("Wins: "), 0, 1);
//            grid.add(wins, 1, 1);
//            grid.add(new Label("Losses: "), 0, 2);
//            grid.add(losses, 1, 2);
//            
//            ButtonBar buttonBar = new ButtonBar();
//            
//            Button submitButton = new Button("Submit");
//            ButtonBar.setType(submitButton, ButtonBar.ButtonType.FINISH);
//            
//            Button cancelButton = new Button("Cancel");
//            ButtonBar.setType(cancelButton, ButtonBar.ButtonType.CANCEL_CLOSE);
//            
//            buttonBar.getButtons().addAll(submitButton, cancelButton);
//            
//            grid.add(buttonBar, 0, 3);
//            
//            submitButton.setOnAction(this::submitAction);
//            
//            dialog.setContent(grid);
//            dialog.show();
//    
//    private void submitAction(ActionEvent action){
//        
//        Summoner summoner = new Summoner();
//        summoner.setNickname(nickName.getText());
//        summoner.setLosses(Integer.parseInt(losses.getText()));
//        summoner.setWins(Integer.parseInt(wins.getText()));
//        list.add(summoner);
//        
//        
//        
//    }
