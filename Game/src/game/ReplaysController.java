/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


/**
 * FXML Controller class
 *
 * @author Farah A.Moniem
 */
public class ReplaysController implements Initializable {

    @FXML
    private ListView<String> replays;
    private File[] listOfFiles;
    @FXML
    private Button back;
    private MediaPlayer gameMediaPlayer, failMediaPlayer; 
    @FXML
    private AnchorPane replayPane;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameMediaPlayer = new MediaPlayer(new Media(new File("src//game//music//backAudio.mp3").toURI().toString()));
        gameMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        gameMediaPlayer.play();
        failMediaPlayer = new MediaPlayer(new Media(new File("src//game//music//fail.mp3").toURI().toString()));
        failMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        File folder = new File("Replays");
        listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                replays.getItems().add(listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
            }
        }
    }

    @FXML
    private void openVideo(MouseEvent event) {
        if (replays.getSelectionModel().getSelectedItem() != null) {
            try {
                String path=replays.getSelectionModel().getSelectedItem().trim();
                Desktop.getDesktop().open(new File("Replays//"+path));
            } catch (IOException ex) {
                Logger.getLogger(ReplaysController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

     @FXML
    private void onBack(ActionEvent event) {
        gameMediaPlayer.stop();
        failMediaPlayer.stop();

        try {
            Pane main = FXMLLoader.load(getClass().getResource("MainXML.fxml"));
            replayPane.getChildren().setAll(main);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
