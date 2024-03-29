/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import client.ClientImpl;
import static game.OnlineRoomFXMLController.player;
import game.model.Client;
import game.model.ClientDAO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.JOptionPane;
import service.RemoteServer;

/**
 * FXML Controller class
 *
 * @author ayman
 */
public class LoginFXMLController implements Initializable {

    private MediaPlayer gameMediaPlayer, failMediaPlayer;
    ClientImpl playerObj;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private Button login;
    @FXML
    private TextField UN;
    @FXML
    private Button signup;
    @FXML
    private PasswordField PW;

    public static String userName;
    @FXML
    private Button back;

    public static RemoteServer server;
    private Registry reg;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            reg = LocateRegistry.getRegistry("localhost", 6000);
            try {
                server = (RemoteServer) reg.lookup("service");
            } catch (NotBoundException ex) {
                ex.printStackTrace();
            } catch (AccessException ex) {
                ex.printStackTrace();
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
       URL url1 = getClass().getClassLoader().getResource("backAudio.mp3");
        URL url2 = getClass().getClassLoader().getResource("fail.mp3");
        URL url3 = getClass().getClassLoader().getResource("videoplayback.mp4");

        Media media = new Media(url1.toString());
        gameMediaPlayer = new MediaPlayer(media);
        gameMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        gameMediaPlayer.play();
        Media media2 = new Media(url2.toString());
        failMediaPlayer = new MediaPlayer(media2);
        failMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    /**
     * user login
     *
     * @param event
     */
    @FXML
    private void login(ActionEvent event) {
        if (UN.getText().trim().equals("")) {
            String st = "Please Enter your name";
            JOptionPane.showMessageDialog(null, st);
        } else if (PW.getText().trim().equals("")) {
            String st = "Please Enter your password";
            JOptionPane.showMessageDialog(null, st);
        } else {
            Client client = new Client(UN.getText().toString(), PW.getText().toString());
            /*  ClientDAO clientdtat=new ClientDAO();
                boolean isLogged=clientdtat.login(client);
             */
            boolean isLogged = false;
            try {
                playerObj = new ClientImpl(this);
                server.login(UN.getText().toString(), PW.getText().toString(), playerObj);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            /*  if(isLogged){
                    userName=UN.getText().toString();
                       try {
            Pane main = FXMLLoader.load(getClass().getResource("OnlineRoomFXML.fxml"));
            loginPane.getChildren().setAll(main);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
                }
                else{
                 String st = "this user doesn't  exist ";
              JOptionPane.showMessageDialog(null, st);
                }
             */
        }

    }

    /**
     * check if succesfully login
     *
     * @param isLogged
     */
    public void isLogin(boolean isLogged) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (isLogged) {
                    userName = UN.getText().toString();
                    try {
                        Pane main = FXMLLoader.load(getClass().getResource("OnlineRoomFXML.fxml"));
                        loginPane.getChildren().setAll(main);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    String st = "this user doesn't  exist ";
                    JOptionPane.showMessageDialog(null, st);
                }

            }
        });
    }

    /**
     * check if succesfully registered
     *
     * @param isRegistered
     */
    public void isRegistered(int isRegistered) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (isRegistered == 1) {
                    userName = UN.getText().toString();
                    try {
                        Pane main = FXMLLoader.load(getClass().getResource("OnlineRoomFXML.fxml"));
                        loginPane.getChildren().setAll(main);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    String st = "Registration failed ";
                    JOptionPane.showMessageDialog(null, st);
                }

            }
        });
    }

    /**
     * user sign up
     *
     * @param event
     */
    @FXML
    private void signup(ActionEvent event) {

        if (UN.getText().trim().equals("")) {
            String st = "Please Enter your name";
            JOptionPane.showMessageDialog(null, st);
        } else if (PW.getText().trim().equals("")) {
            String st = "Please Enter your password";
            JOptionPane.showMessageDialog(null, st);
        } else {
            try {
                playerObj = new ClientImpl(this);
                server.register(UN.getText().toString(), PW.getText().toString(), playerObj);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            /*
          Client client= new Client(UN.getText().toString(),PW.getText().toString());
                ClientDAO clientdtat=new ClientDAO();
                int isRegistered=clientdtat.create(client);
                if(isRegistered==1){
                }
                else{
                 String st = "Registration failed ";
              JOptionPane.showMessageDialog(null, st);
                }*/
        }

    }

    /**
     * back to menu
     *
     * @param event
     */
    @FXML
    private void onBack(ActionEvent event) {
        gameMediaPlayer.stop();
        failMediaPlayer.stop();

        try {
            Pane main = FXMLLoader.load(getClass().getResource("MainXML.fxml"));
            loginPane.getChildren().setAll(main);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
