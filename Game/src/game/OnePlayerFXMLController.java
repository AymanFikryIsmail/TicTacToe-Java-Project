/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.teamdev.jxcapture.Codec;
import com.teamdev.jxcapture.EncodingParameters;
import com.teamdev.jxcapture.VideoCapture;
import com.teamdev.jxcapture.video.Desktop;
import static game.DifficultyFXMLController.isDifficult;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ayman
 */
public class OnePlayerFXMLController implements Initializable {

    private static int loc;
    private int movement = 1;
    private boolean isFirst = true;
    private MediaPlayer gameMediaPlayer, failMediaPlayer;

    private Boolean place1 = true, place2 = true, place3 = true, place4 = true, place5 = true, place6 = true,
            place7 = true, place8 = true, place9 = true, stopPress = true;
    private ArrayList<Integer> firstPlayerTockens, secondPlayerTockens;
    // Every possibilities of winning
    private ArrayList<Integer> firstWinPossibility, secondWinPossibility,
            thirdWinPossibility, forthWinPossibility, fifthWinPossibility,
            sixthWinPossibility, seventhWinPossibility, eighthWinPossibility;
    private VideoCapture videoCapture;

    // Places where AI can aI_Prop
    private ArrayList<Integer> aI_Prop1, aI_Prop2, aI_Prop3, aI_Prop4, aI_Prop5, aI_Prop6, aI_Prop7, aI_Prop8, aI_Prop9, aI_Prop10, aI_Prop11,
            aI_Prop12, aI_Prop13, aI_Prop14, aI_Prop15, aI_Prop16, aI_Prop17, aI_Prop18, aI_Prop19, aI_Prop20, aI_Prop21, aI_Prop22, aI_Prop23,
            aI_Prop24;
    private Boolean isAIChecked1 = true, isAIChecked2 = true, isAIChecked3 = true, isAIChecked4 = true, isAIChecked5 = true, isAIChecked6 = true, isAIChecked7 = true,
            isAIChecked8 = true, isAIChecked9 = true, isAIChecked10 = true, isAIChecked11 = true, isAIChecked12 = true, isAIChecked13 = true, isAIChecked14 = true,
            isAIChecked15 = true, isAIChecked16 = true, isAIChecked17 = true, isAIChecked18 = true, isAIChecked19 = true, isAIChecked20 = true, isAIChecked21 = true,
            isAIChecked22 = true, isAIChecked23 = true, isAIChecked24 = true, isAIChecked25 = true, isAIChecked26 = true, isAIChecked27 = true, isAIChecked28 = true,
            isAIChecked29 = true, isAIChecked30 = true, isAIChecked31 = true, isAIChecked32 = true, isAIChecked33 = true, isAIChecked34 = true, isAIChecked35 = true;

    private int player1Score, player2Score, tieScore;

    // Stores if someone won or tied
    private Boolean win = false, tie = false;
    @FXML
    private AnchorPane onePlayerPane;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Button backBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Label firstPlayerScore;
    @FXML
    private Label secondPlayerScore;
    @FXML
    private Label firstPlayerName;
    @FXML
    private Label secondPlayerName;
    @FXML
    private Button recordBtn;
    @FXML
    private Button stopBtn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        firstPlayerName.setText("You");
        secondPlayerName.setText("Computer");

        firstPlayerTockens = new ArrayList<Integer>();
        secondPlayerTockens = new ArrayList<Integer>();
        // Defines every possibilities of winning
        firstWinPossibility = new ArrayList<Integer>();
        secondWinPossibility = new ArrayList<Integer>();
        thirdWinPossibility = new ArrayList<Integer>();
        forthWinPossibility = new ArrayList<Integer>();
        fifthWinPossibility = new ArrayList<Integer>();
        sixthWinPossibility = new ArrayList<Integer>();
        seventhWinPossibility = new ArrayList<Integer>();
        eighthWinPossibility = new ArrayList<Integer>();

        firstWinPossibility.add(1);
        firstWinPossibility.add(2);
        firstWinPossibility.add(3);
        secondWinPossibility.add(4);
        secondWinPossibility.add(5);
        secondWinPossibility.add(6);
        thirdWinPossibility.add(7);
        thirdWinPossibility.add(8);
        thirdWinPossibility.add(9);
        forthWinPossibility.add(1);
        forthWinPossibility.add(4);
        forthWinPossibility.add(7);
        fifthWinPossibility.add(2);
        fifthWinPossibility.add(5);
        fifthWinPossibility.add(8);
        sixthWinPossibility.add(3);
        sixthWinPossibility.add(6);
        sixthWinPossibility.add(9);
        seventhWinPossibility.add(1);
        seventhWinPossibility.add(5);
        seventhWinPossibility.add(9);
        eighthWinPossibility.add(3);
        eighthWinPossibility.add(5);
        eighthWinPossibility.add(7);

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
     * When click on first cell
     *
     * @param event
     */
    @FXML
    private void cell00Click(ActionEvent event) {

        if (place1 && !win && !tie) {
            place1 = false;
            movement++;
            loc = 1;
            btn1.setText(drawX());
            firstPlayerTockens.add(loc);
            computerTurn();
        }
    }

    /**
     * When click on second cell
     *
     * @param event
     */
    @FXML
    private void cell01Click(ActionEvent event) {
        if (place2 && !win && !tie) {
            loc = 2;
            movement++;

            place2 = false;
            btn2.setText(drawX());
            firstPlayerTockens.add(loc);
            computerTurn();
        }
    }

    /**
     * When click on third cell
     *
     * @param event
     */
    @FXML
    private void cell03Click(ActionEvent event) {
        if (place3 && !win && !tie) {
            loc = 3;
            movement++;

            place3 = false;
            btn3.setText(drawX());
            firstPlayerTockens.add(loc);
            computerTurn();
        }
    }

    /**
     * When click on forth cell
     *
     * @param event
     */
    @FXML
    private void cell10Click(ActionEvent event) {
        if (place4 && !win && !tie) {
            loc = 4;
            movement++;

            place4 = false;
            btn4.setText(drawX());
            firstPlayerTockens.add(loc);
            computerTurn();
        }
    }

    /**
     * When click on fifth cell
     *
     * @param event
     */
    @FXML
    private void cell11Click(ActionEvent event) {
        if (place5 && !win && !tie) {
            loc = 5;
            movement++;

            place5 = false;
            btn5.setText(drawX());
            firstPlayerTockens.add(loc);
            computerTurn();

        }
    }

    /**
     * When click on sixth cell
     *
     * @param event
     */
    @FXML
    private void cell12Click(ActionEvent event) {
        if (place6 && !win && !tie) {
            place6 = false;
            loc = 6;
            movement++;

            btn6.setText(drawX());
            firstPlayerTockens.add(loc);
            computerTurn();
        }
    }

    /**
     * When click on seventh cell
     *
     * @param event
     */
    @FXML
    private void cell20Click(ActionEvent event) {
        if (place7 && !win && !tie) {
            place7 = false;
            loc = 7;
            movement++;

            btn7.setText(drawX());
            firstPlayerTockens.add(loc);
            computerTurn();
        }
    }

    /**
     * When click on eighth cell
     *
     * @param event
     */
    @FXML
    private void cell21Click(ActionEvent event) {
        if (place8 && !win && !tie) {
            place8 = false;
            loc = 8;
            movement++;

            btn8.setText(drawX());
            firstPlayerTockens.add(loc);
            computerTurn();
        }
    }

    /**
     * When click on ninth cell
     *
     * @param event
     */
    @FXML
    private void cell22Click(ActionEvent event) {
        if (place9 && !win && !tie) {
            place9 = false;
            loc = 9;
            movement++;

            btn9.setText(drawX());
            firstPlayerTockens.add(loc);
            computerTurn();
        }
    }

    /**
     * to back to main page
     *
     * @param event
     */
    @FXML
    private void onBack(ActionEvent event) {
        gameMediaPlayer.stop();
        failMediaPlayer.stop();

        try {
            Pane main = FXMLLoader.load(getClass().getResource("MainXML.fxml"));
            onePlayerPane.getChildren().setAll(main);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * to start new game
     *
     * @param event
     */
    @FXML
    private void onReset(ActionEvent event) {
        restart();

    }

    /**
     * to start record the game
     *
     * @param event
     */
    @FXML
    private void recordClick(ActionEvent event) throws IOException {
        stopPress = false;
        videoCapture = VideoCapture.create();
        videoCapture.setVideoSource(new Desktop());
        java.util.List<Codec> videoCodecs = videoCapture.getVideoCodecs();
        System.out.println(videoCodecs);
        Codec videoCodec = videoCodecs.get(0);
        EncodingParameters encodingParameters = new EncodingParameters(new File("Replays//ReplayOneVsComputer" + System.currentTimeMillis() + ".mp4"));
        encodingParameters.setSize(new Dimension(1000, 600));
        encodingParameters.setBitrate(800000);
        encodingParameters.setFramerate(10);
        encodingParameters.setCodec(videoCodec);
        videoCapture.setEncodingParameters(encodingParameters);
        videoCapture.start();
    }

    /**
     * to stop record the game
     *
     * @param event
     */
    @FXML
    private void stopClick(ActionEvent event) {
        if (!stopPress) {
            videoCapture.stop();
            String workingDir = System.getProperty("user.dir");
            //show video
            /* File f = new File(workingDir, "videoplayback.mp4");
            Media m = new Media(f.toURI().toString());
            MediaPlayer mp = new MediaPlayer(m);
            MediaView mv = new MediaView(mp);
            BorderPane borderPane = new BorderPane();
            borderPane.getChildren().add(mv);
            Scene scene = new Scene(borderPane, 600, 600);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("You won!");
            stage.show();
            mp.play();
             */
            stopPress = true;
        } else {
            JOptionPane.showMessageDialog(null, "Please start recording");
        }
    }

    /**
     * to check the tie
     *
     */
    public void checkTie() {
        if (win != true && tie == false) {
            tie = true;
            gameMediaPlayer.stop();
            failMediaPlayer.play();

            String st = "Tie";
            JOptionPane.showMessageDialog(null, st);
            tieScore = tieScore + 1;
        }
    }

    /**
     * to draw x
     *
     * @return player symbol
     */
    public String drawX() {
        return "X";

    }

    /**
     * to draw o
     *
     * @return computer player symbol
     */
    public String draw0() {
        return "O";
    }

    /**
     * easy computer movement (max - min) + 1) + min
     *
     */
    public void easyComputer() {
        if (movement < 9) {
            int rand = 1 + (int) (Math.random() * ((9 - 1) + 1));
            while (firstPlayerTockens.contains(rand) || secondPlayerTockens.contains(rand)) {
                rand = 1 + (int) (Math.random() * ((9 - 1) + 1));
            }
            loc = rand;
            if (loc == 1) {
                place1 = false;
                movement++;
                btn1.setText(draw0());
            } else if (loc == 2) {
                place2 = false;
                movement++;

                btn2.setText(draw0());
            } else if (loc == 3) {
                place3 = false;
                movement++;

                btn3.setText(draw0());
            } else if (loc == 4) {
                place4 = false;
                movement++;

                btn4.setText(draw0());
            } else if (loc == 5) {
                place5 = false;
                movement++;

                btn5.setText(draw0());
            } else if (loc == 6) {
                place6 = false;
                movement++;

                btn6.setText(draw0());
            } else if (loc == 7) {
                place7 = false;
                movement++;

                btn7.setText(draw0());
            } else if (loc == 8) {
                place8 = false;
                movement++;

                btn8.setText(draw0());
            } else if (loc == 9) {
                place9 = false;
                movement++;

                btn9.setText(draw0());
            }
            secondPlayerTockens.add(loc);

            if (movement >= 5) {
                checkWinning();
            }
            if (movement >= 9) {
                checkTie();
            }
        } else if (movement == 9) {
            checkWinning();
            checkTie();
        }

    }

    /**
     * computer level
     *
     */
    public void computerTurn() {
        if (isDifficult) {
            hardComputer();
        } else {
            easyComputer();
        }
    }

    /**
     * to check the winning
     *
     */
    public void checkWinning() {
        // Checks if someone wins
        if (firstPlayerTockens.containsAll(firstWinPossibility)) {
            win = true;
            String st = "you win";
            //JOptionPane.showMessageDialog(null, st);
            playVedio();

            player1Score = player1Score + 1;
        } else if (secondPlayerTockens.containsAll(firstWinPossibility)) {
            win = true;
            gameMediaPlayer.stop();
            failMediaPlayer.play();
            String st = "you lose";
            JOptionPane.showMessageDialog(null, st);
            player2Score = player2Score + 1;
        } else if (firstPlayerTockens.containsAll(secondWinPossibility)) {
            win = true;
            String st = "you win";
            //JOptionPane.showMessageDialog(null, st);
            playVedio();

            player1Score = player1Score + 1;
        } else if (secondPlayerTockens.containsAll(secondWinPossibility)) {
            win = true;
            gameMediaPlayer.stop();
            failMediaPlayer.play();

            String st = "you lose";
            JOptionPane.showMessageDialog(null, st);
            player2Score = player2Score + 1;
        } else if (firstPlayerTockens.containsAll(thirdWinPossibility)) {
            win = true;
            String st = "you win";
            //JOptionPane.showMessageDialog(null, st);
            playVedio();

            player1Score = player1Score + 1;
        } else if (secondPlayerTockens.containsAll(thirdWinPossibility)) {
            win = true;
            gameMediaPlayer.stop();
            failMediaPlayer.play();

            String st = "you lose";
            JOptionPane.showMessageDialog(null, st);
            player2Score = player2Score + 1;
        } else if (firstPlayerTockens.containsAll(forthWinPossibility)) {
            win = true;
            String st = "you win";
            // JOptionPane.showMessageDialog(null, st);
            playVedio();

            //winText.setText("win");
            player1Score = player1Score + 1;
        } else if (secondPlayerTockens.containsAll(forthWinPossibility)) {
            win = true;
            gameMediaPlayer.stop();
            failMediaPlayer.play();
            String st = "you lose";
            JOptionPane.showMessageDialog(null, st);

            player2Score = player2Score + 1;
        } else if (firstPlayerTockens.containsAll(fifthWinPossibility)) {
            win = true;
            String st = "you win";
            //JOptionPane.showMessageDialog(null, st);
            playVedio();

            player1Score = player1Score + 1;
        } else if (secondPlayerTockens.containsAll(fifthWinPossibility)) {
            win = true;
            gameMediaPlayer.stop();
            failMediaPlayer.play();

            String st = "you lose";
            JOptionPane.showMessageDialog(null, st);
            player2Score = player2Score + 1;
        } else if (firstPlayerTockens.containsAll(sixthWinPossibility)) {
            win = true;
            String st = "you win";
            //JOptionPane.showMessageDialog(null, st);
            playVedio();

            player1Score = player1Score + 1;
        } else if (secondPlayerTockens.containsAll(sixthWinPossibility)) {
            win = true;
            gameMediaPlayer.stop();
            failMediaPlayer.play();
            String st = "you lose";
            JOptionPane.showMessageDialog(null, st);
            player2Score = player2Score + 1;
        } else if (firstPlayerTockens.containsAll(seventhWinPossibility)) {
            win = true;
            String st = "you win";
            //JOptionPane.showMessageDialog(null, st);
            playVedio();

            player1Score = player1Score + 1;
        } else if (secondPlayerTockens.containsAll(seventhWinPossibility)) {
            win = true;
            gameMediaPlayer.stop();
            failMediaPlayer.play();

            String st = "you lose";
            JOptionPane.showMessageDialog(null, st);
            //winText.setText("win");
            player2Score = player2Score + 1;
        } else if (firstPlayerTockens.containsAll(eighthWinPossibility)) {
            win = true;
            String st = "you win";
            // JOptionPane.showMessageDialog(null, st);
            playVedio();

            //winText.setText("win");
            player1Score = player1Score + 1;
        } else if (secondPlayerTockens.containsAll(eighthWinPossibility)) {
            win = true;
            gameMediaPlayer.stop();
            failMediaPlayer.play();

            String st = "you lose";
            JOptionPane.showMessageDialog(null, st);
            player2Score = player2Score + 1;
        }

        if (win) {
            firstPlayerScore.setText("Score : " + String.valueOf(player1Score));
            secondPlayerScore.setText("Score : " + String.valueOf(player2Score));

            movement = 10;
        }

    }

    /**
     * to play the video when win
     *
     */
    public void playVedio() {
        String workingDir = System.getProperty("user.dir");
        URL url = getClass().getClassLoader().getResource("videoplayback.mp4");
        Media media = new Media(url.toString());;
        MediaPlayer mp = new MediaPlayer(media);
        MediaView mv = new MediaView(mp);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(mv);
        Scene scene = new Scene(borderPane, 500, 300);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("You won!");
        stage.show();
        mp.play();
        gameMediaPlayer.pause();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                mp.stop();
                gameMediaPlayer.play();
            }
        });

        /*
        
        String workingDir = System.getProperty("user.dir");
        File f = new File(workingDir, "src//game//music//videoplayback.mp4");
        Media m = new Media(f.toURI().toString());
        MediaPlayer mp = new MediaPlayer(m);
        MediaView mv = new MediaView(mp);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(mv);
        Scene scene = new Scene(borderPane, 600, 350);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("You won!");
        stage.show();
        mp.play();*/
    }

    /**
     * to restart the game
     *
     */
    public void restart() {
        failMediaPlayer.stop();
        gameMediaPlayer.play();

        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");

        win = false;
        tie = false;
        firstPlayerTockens.clear();
        secondPlayerTockens.clear();
        place1 = true;
        place2 = true;
        place3 = true;
        place4 = true;
        place5 = true;
        place6 = true;
        place7 = true;
        place8 = true;
        place9 = true;
        movement = 0;
        loc = 0;

        // au
        isAIChecked1 = true;
        isAIChecked2 = true;
        isAIChecked3 = true;
        isAIChecked4 = true;
        isAIChecked5 = true;
        isAIChecked6 = true;
        isAIChecked7 = true;
        isAIChecked8 = true;
        isAIChecked9 = true;
        isAIChecked10 = true;
        isAIChecked11 = true;
        isAIChecked12 = true;
        isAIChecked13 = true;
        isAIChecked14 = true;
        isAIChecked15 = true;
        isAIChecked16 = true;
        isAIChecked17 = true;
        isAIChecked18 = true;
        isAIChecked19 = true;
        isAIChecked20 = true;
        isAIChecked21 = true;
        isAIChecked22 = true;
        isAIChecked23 = true;
        isAIChecked24 = true;
        isAIChecked25 = true;
        isAIChecked26 = true;
        isAIChecked27 = true;
        isAIChecked28 = true;
        isAIChecked29 = true;
        isAIChecked30 = true;
        isAIChecked31 = true;
        isAIChecked32 = true;
        isAIChecked33 = true;
        isAIChecked34 = true;
        isAIChecked35 = true;
    }

    /**
     * to play in a hard game
     *
     */
    public void hardComputer() {
        aI_Prop1 = new ArrayList<Integer>();
        aI_Prop2 = new ArrayList<Integer>();
        aI_Prop3 = new ArrayList<Integer>();
        aI_Prop4 = new ArrayList<Integer>();
        aI_Prop5 = new ArrayList<Integer>();
        aI_Prop6 = new ArrayList<Integer>();
        aI_Prop7 = new ArrayList<Integer>();
        aI_Prop8 = new ArrayList<Integer>();
        aI_Prop9 = new ArrayList<Integer>();
        aI_Prop10 = new ArrayList<Integer>();
        aI_Prop11 = new ArrayList<Integer>();
        aI_Prop12 = new ArrayList<Integer>();
        aI_Prop13 = new ArrayList<Integer>();
        aI_Prop14 = new ArrayList<Integer>();
        aI_Prop15 = new ArrayList<Integer>();
        aI_Prop16 = new ArrayList<Integer>();
        aI_Prop17 = new ArrayList<Integer>();
        aI_Prop18 = new ArrayList<Integer>();
        aI_Prop19 = new ArrayList<Integer>();
        aI_Prop20 = new ArrayList<Integer>();
        aI_Prop21 = new ArrayList<Integer>();
        aI_Prop22 = new ArrayList<Integer>();
        aI_Prop23 = new ArrayList<Integer>();
        aI_Prop24 = new ArrayList<Integer>();

        aI_Prop1.add(1);
        aI_Prop1.add(2);
        aI_Prop2.add(4);
        aI_Prop2.add(5);
        aI_Prop3.add(7);
        aI_Prop3.add(8);
        aI_Prop4.add(2);
        aI_Prop4.add(3);
        aI_Prop5.add(5);
        aI_Prop5.add(6);
        aI_Prop6.add(8);
        aI_Prop6.add(9);
        aI_Prop7.add(7);
        aI_Prop7.add(4);
        aI_Prop8.add(8);
        aI_Prop8.add(5);
        aI_Prop9.add(9);
        aI_Prop9.add(6);
        aI_Prop10.add(1);
        aI_Prop10.add(4);
        aI_Prop11.add(2);
        aI_Prop11.add(5);
        aI_Prop12.add(3);
        aI_Prop12.add(6);
        aI_Prop13.add(5);
        aI_Prop13.add(9);
        aI_Prop14.add(1);
        aI_Prop14.add(5);
        aI_Prop15.add(7);
        aI_Prop15.add(5);
        aI_Prop16.add(5);
        aI_Prop16.add(3);
        aI_Prop17.add(1);
        aI_Prop17.add(7);
        aI_Prop18.add(2);
        aI_Prop18.add(8);
        aI_Prop19.add(3);
        aI_Prop19.add(9);
        aI_Prop20.add(1);
        aI_Prop20.add(3);
        aI_Prop21.add(4);
        aI_Prop21.add(6);
        aI_Prop22.add(7);
        aI_Prop22.add(9);
        aI_Prop23.add(1);
        aI_Prop23.add(9);
        aI_Prop24.add(7);
        aI_Prop24.add(3);

        // Puts computer token
        if (firstPlayerTockens.isEmpty() == false) {
            if (secondPlayerTockens.containsAll(aI_Prop1) && isAIChecked1 && place3) {
                isAIChecked1 = false;
                place3 = false;
                secondPlayerTockens.add(3);
                movement++;
                btn3.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop2) && isAIChecked2 && place6) {
                isAIChecked2 = false;
                place6 = false;
                secondPlayerTockens.add(6);
                movement++;
                btn6.setText(draw0());

            } else if (secondPlayerTockens.containsAll(aI_Prop3) && isAIChecked3 && place9) {
                isAIChecked3 = false;
                place9 = false;
                secondPlayerTockens.add(9);
                movement++;
                btn9.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop4) && isAIChecked4 && place1) {
                isAIChecked4 = false;
                place1 = false;
                secondPlayerTockens.add(1);
                movement++;
                btn1.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop5) && isAIChecked5 && place4) {
                isAIChecked5 = false;
                place4 = false;
                secondPlayerTockens.add(4);
                movement++;
                btn4.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop6) && isAIChecked6 && place7) {
                isAIChecked6 = false;
                place7 = false;
                secondPlayerTockens.add(7);
                movement++;
                btn7.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop7) && isAIChecked7 && place1) {
                isAIChecked7 = false;
                place1 = false;
                secondPlayerTockens.add(1);
                movement++;
                btn1.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop8) && isAIChecked8 && place2) {
                isAIChecked8 = false;
                place2 = false;
                secondPlayerTockens.add(2);
                movement++;
                btn2.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop9) && isAIChecked9 && place3) {
                isAIChecked9 = false;
                place3 = false;
                secondPlayerTockens.add(3);
                movement++;
                btn3.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop10) && isAIChecked10 && place7) {
                isAIChecked10 = false;
                place7 = false;
                secondPlayerTockens.add(7);
                movement++;
                btn7.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop11) && isAIChecked11 && place8) {
                isAIChecked11 = false;
                place8 = false;
                secondPlayerTockens.add(8);
                movement++;
                btn8.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop12) && isAIChecked12 && place9) {
                isAIChecked12 = false;
                place9 = false;
                secondPlayerTockens.add(9);
                movement++;
                btn9.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop13) && isAIChecked13 && place1) {
                isAIChecked13 = false;
                place1 = false;
                secondPlayerTockens.add(1);
                movement++;
                btn1.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop14) && isAIChecked14 && place9) {
                isAIChecked14 = false;
                place9 = false;
                secondPlayerTockens.add(9);
                movement++;
                btn9.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop15) && isAIChecked15 && place3) {
                isAIChecked15 = false;
                place3 = false;
                secondPlayerTockens.add(3);
                movement++;
                btn3.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop16) && isAIChecked16 && place7) {
                isAIChecked16 = false;
                place7 = false;
                secondPlayerTockens.add(7);
                movement++;
                btn7.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop17) && isAIChecked22 && place4) {
                isAIChecked22 = false;
                place4 = false;
                secondPlayerTockens.add(4);
                movement++;
                btn4.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop18) && isAIChecked23 && place5) {
                isAIChecked23 = false;
                place5 = false;
                secondPlayerTockens.add(5);
                movement++;
                btn5.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop19) && isAIChecked24 && place6) {
                isAIChecked24 = false;
                place6 = false;
                secondPlayerTockens.add(6);
                movement++;
                btn6.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop20) && isAIChecked25 && place2) {
                isAIChecked25 = false;
                place2 = false;
                secondPlayerTockens.add(2);
                movement++;
                btn2.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop21) && isAIChecked26 && place5) {
                isAIChecked26 = false;
                place5 = false;
                secondPlayerTockens.add(5);
                movement++;
                btn5.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop22) && isAIChecked27 && place8) {
                isAIChecked27 = false;
                place8 = false;
                secondPlayerTockens.add(8);
                movement++;
                btn8.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop23) && isAIChecked28 && place5) {
                isAIChecked28 = false;
                place5 = false;
                secondPlayerTockens.add(5);
                movement++;
                btn5.setText(draw0());
            } else if (secondPlayerTockens.containsAll(aI_Prop24) && isAIChecked29 && place5) {
                isAIChecked29 = false;
                place5 = false;
                secondPlayerTockens.add(5);
                movement++;
                btn5.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop1) && isAIChecked1 && place3) {
                isAIChecked1 = false;
                place3 = false;
                secondPlayerTockens.add(3);
                movement++;
                btn3.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop2) && isAIChecked2 && place6) {
                isAIChecked2 = false;
                place6 = false;
                secondPlayerTockens.add(6);
                movement++;
                btn6.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop3) && isAIChecked3 && place9) {
                isAIChecked3 = false;
                place9 = false;
                secondPlayerTockens.add(9);
                movement++;
                btn9.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop4) && isAIChecked4 && place1) {
                isAIChecked4 = false;
                place1 = false;
                secondPlayerTockens.add(1);
                movement++;
                btn1.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop5) && isAIChecked5 && place4) {
                isAIChecked5 = false;
                place4 = false;
                secondPlayerTockens.add(4);
                movement++;
                btn4.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop6) && isAIChecked6 && place7) {
                isAIChecked6 = false;
                place7 = false;
                secondPlayerTockens.add(7);
                movement++;
                btn7.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop7) && isAIChecked7 && place1) {
                isAIChecked7 = false;
                place1 = false;
                secondPlayerTockens.add(1);
                movement++;
                btn1.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop8) && isAIChecked8 && place2) {
                isAIChecked8 = false;
                place2 = false;
                secondPlayerTockens.add(2);
                movement++;
                btn2.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop9) && isAIChecked9 && place3) {
                isAIChecked9 = false;
                place3 = false;
                secondPlayerTockens.add(3);
            } else if (firstPlayerTockens.containsAll(aI_Prop10) && isAIChecked10 && place7) {
                isAIChecked10 = false;
                place7 = false;
                secondPlayerTockens.add(7);
                movement++;
                btn7.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop11) && isAIChecked11 && place8) {
                isAIChecked11 = false;
                place8 = false;
                secondPlayerTockens.add(8);
                movement++;
                btn8.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop12) && isAIChecked12 && place9) {
                isAIChecked12 = false;
                place9 = false;
                secondPlayerTockens.add(9);
                movement++;
                btn9.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop13) && isAIChecked13 && place1) {
                isAIChecked13 = false;
                place1 = false;
                secondPlayerTockens.add(1);
                movement++;
                btn1.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop14) && isAIChecked14 && place9) {
                isAIChecked14 = false;
                place9 = false;
                secondPlayerTockens.add(9);
                movement++;
                btn9.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop15) && isAIChecked15 && place3) {
                isAIChecked15 = false;
                place3 = false;
                secondPlayerTockens.add(3);
                movement++;
                btn3.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop16) && isAIChecked16 && place7) {
                isAIChecked16 = false;
                place7 = false;
                secondPlayerTockens.add(7);
                movement++;
                btn7.setText(draw0());
            } else if (firstPlayerTockens.contains(1) && isAIChecked17 && place5) {
                isAIChecked17 = false;
                place5 = false;
                secondPlayerTockens.add(5);
                movement++;
                btn5.setText(draw0());
            } else if (firstPlayerTockens.contains(3) && isAIChecked18 && place5) {
                isAIChecked18 = false;
                place5 = false;
                secondPlayerTockens.add(5);
                movement++;
                btn5.setText(draw0());
            } else if (firstPlayerTockens.contains(7) && isAIChecked19 && place5) {
                isAIChecked19 = false;
                place5 = false;
                secondPlayerTockens.add(5);
                movement++;
                btn5.setText(draw0());
            } else if (firstPlayerTockens.contains(9) && isAIChecked20 && place5) {
                isAIChecked20 = false;
                place5 = false;
                secondPlayerTockens.add(5);
                movement++;
                btn5.setText(draw0());
            } else if (firstPlayerTockens.contains(5) && isAIChecked21) {
                isAIChecked21 = false;
                if (place1) {
                    place1 = false;
                    secondPlayerTockens.add(1);
                    movement++;
                    btn1.setText(draw0());
                } else if (place3) {
                    place3 = false;
                    secondPlayerTockens.add(3);
                    movement++;
                    btn3.setText(draw0());
                } else if (place7) {
                    place7 = false;
                    secondPlayerTockens.add(7);
                    movement++;
                    btn7.setText(draw0());
                } else if (place9) {
                    place9 = false;
                    secondPlayerTockens.add(9);
                    movement++;
                    btn9.setText(draw0());
                }
            } else if (firstPlayerTockens.containsAll(aI_Prop17) && isAIChecked22 && place4) {
                isAIChecked22 = false;
                place4 = false;
                secondPlayerTockens.add(4);
                movement++;
                btn4.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop18) && isAIChecked23 && place5) {
                isAIChecked23 = false;
                place5 = false;
                secondPlayerTockens.add(5);
                movement++;
                btn5.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop19) && isAIChecked24 && place6) {
                isAIChecked24 = false;
                place6 = false;
                secondPlayerTockens.add(6);
                movement++;
                btn6.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop20) && isAIChecked25 && place2) {
                isAIChecked25 = false;
                place2 = false;
                secondPlayerTockens.add(2);
                movement++;
                btn2.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop21) && isAIChecked26 && place5) {
                isAIChecked26 = false;
                place5 = false;
                secondPlayerTockens.add(5);
                movement++;
                btn5.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop22) && isAIChecked27 && place8) {
                isAIChecked27 = false;
                place8 = false;
                secondPlayerTockens.add(8);
                movement++;
                btn8.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop23) && isAIChecked28 && place5) {
                isAIChecked28 = false;
                place5 = false;
                secondPlayerTockens.add(5);
                movement++;
                btn5.setText(draw0());
            } else if (firstPlayerTockens.containsAll(aI_Prop24) && isAIChecked29 && place5) {
                isAIChecked29 = false;
                place5 = false;
                secondPlayerTockens.add(5);
                movement++;
                btn5.setText(draw0());
            } else if (firstPlayerTockens.contains(2) && isAIChecked30) {
                isAIChecked30 = false;
                if (place1) {
                    place1 = false;
                    secondPlayerTockens.add(1);
                    movement++;
                    btn1.setText(draw0());
                } else if (place3) {
                    place3 = false;
                    secondPlayerTockens.add(3);
                    movement++;
                    btn3.setText(draw0());
                } else if (place7) {
                    place7 = false;
                    secondPlayerTockens.add(7);
                    movement++;
                    btn7.setText(draw0());
                } else if (place9) {
                    place9 = false;
                    secondPlayerTockens.add(9);
                    movement++;
                    btn9.setText(draw0());
                }
            } else if (firstPlayerTockens.contains(4) && isAIChecked31) {
                isAIChecked31 = false;
                if (place1) {
                    place1 = false;
                    secondPlayerTockens.add(1);
                    movement++;
                    btn1.setText(draw0());
                } else if (place3) {
                    place3 = false;
                    secondPlayerTockens.add(3);
                    movement++;
                    btn3.setText(draw0());
                } else if (place7) {
                    place7 = false;
                    secondPlayerTockens.add(7);
                    movement++;
                    btn7.setText(draw0());
                } else if (place9) {
                    place9 = false;
                    secondPlayerTockens.add(9);
                    movement++;
                    btn9.setText(draw0());
                }
            } else if (firstPlayerTockens.contains(8) && isAIChecked32) {
                isAIChecked32 = false;
                if (place1) {
                    place1 = false;
                    secondPlayerTockens.add(1);
                    movement++;
                    btn1.setText(draw0());
                } else if (place3) {
                    place3 = false;
                    secondPlayerTockens.add(3);
                    movement++;
                    btn3.setText(draw0());
                } else if (place7) {
                    place7 = false;
                    secondPlayerTockens.add(7);
                    movement++;
                    btn7.setText(draw0());
                } else if (place9) {
                    place9 = false;
                    secondPlayerTockens.add(9);
                    movement++;
                    btn9.setText(draw0());
                }
            } else if (firstPlayerTockens.contains(6) && isAIChecked33) {
                isAIChecked33 = false;
                if (place1) {
                    place1 = false;
                    secondPlayerTockens.add(1);
                    movement++;
                    btn1.setText(draw0());
                } else if (place3) {
                    place3 = false;
                    secondPlayerTockens.add(3);
                    movement++;
                    btn3.setText(draw0());
                } else if (place7) {
                    place7 = false;
                    secondPlayerTockens.add(7);
                    movement++;
                    btn7.setText(draw0());
                } else if (place9) {
                    place9 = false;
                    secondPlayerTockens.add(9);
                    movement++;
                    btn9.setText(draw0());
                }
            } else if (firstPlayerTockens.containsAll(aI_Prop24) && isAIChecked34) {
                isAIChecked34 = false;
                if (place2) {
                    place2 = false;
                    secondPlayerTockens.add(2);
                    movement++;
                    btn2.setText(draw0());
                } else if (place4) {
                    place4 = false;
                    secondPlayerTockens.add(4);
                    movement++;
                    btn4.setText(draw0());
                } else if (place6) {
                    place6 = false;
                    secondPlayerTockens.add(6);
                    movement++;
                    btn6.setText(draw0());
                } else if (place8) {
                    place8 = false;
                    secondPlayerTockens.add(8);
                    movement++;
                    btn8.setText(draw0());
                }
            } else if (firstPlayerTockens.containsAll(aI_Prop23) && isAIChecked35) {
                isAIChecked35 = false;
                if (place2) {
                    place2 = false;
                    secondPlayerTockens.add(2);
                    movement++;
                    btn2.setText(draw0());
                } else if (place4) {
                    place4 = false;
                    secondPlayerTockens.add(4);
                    movement++;
                    btn4.setText(draw0());
                } else if (place6) {
                    place6 = false;
                    secondPlayerTockens.add(6);
                    movement++;
                    btn6.setText(draw0());
                } else if (place8) {
                    place8 = false;
                    secondPlayerTockens.add(8);
                    movement++;
                    btn8.setText(draw0());
                }
            }
            if (movement >= 5) {
                checkWinning();
            }
            if (movement >= 9) {
                checkTie();
            }
        }
    }

}
