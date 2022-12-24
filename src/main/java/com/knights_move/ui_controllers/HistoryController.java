package com.knights_move.ui_controllers;

import com.knights_move.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    public class NewGame{
        private int gameID;
        private String userName;
        private int positionOfPlayer;
        private LocalDate dateOfGame;

        public NewGame(int gameID, String userName, int positionOfPlayer, LocalDate dateOfGame) {
            this.gameID = gameID;
            this.userName = userName;
            this.positionOfPlayer = positionOfPlayer;
            this.dateOfGame = dateOfGame;
        }

        public int getGameID() {
            return gameID;
        }

        public void setGameID(int gameID) {
            this.gameID = gameID;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getPositionOfPlayer() {
            return positionOfPlayer;
        }

        public void setPositionOfPlayer(int positionOfPlayer) {
            this.positionOfPlayer = positionOfPlayer;
        }

        public LocalDate getDateOfGame() {
            return dateOfGame;
        }

        public void setDateOfGame(LocalDate dateOfGame) {
            this.dateOfGame = dateOfGame;
        }
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TableColumn<NewGame,Integer> positionCol;

    @FXML
    private TableColumn<NewGame,String> userNameCol;

    @FXML
    private TableColumn<NewGame, Date> dateCol;

    @FXML
    private TableColumn<NewGame, Integer> gameIdCol;

    @FXML
    private TableView<NewGame> historTbl;

    @FXML
    private Label label_title;

    @FXML
    private AnchorPane pnlHistory;

    @FXML
    private Button historyBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button logoBtn;

    @FXML
    private Button qaBtn;

    @FXML
    private Button rulesBtn;

    @FXML
    private Button playBtn;
    @FXML
    private Button homeBtn;

    @FXML
    private Button signInBtn;

    @FXML
    private Button BTRY;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<NewGame> listOfGames=new ArrayList<>();
        HashMap<Player,ArrayList<Game>> playerAndGame=SysData.getInstance().getPlayerAndgames();
        if(playerAndGame!=null) {
            for (Map.Entry<Player, ArrayList<Game>> entry : playerAndGame.entrySet()) {
                Player playerKey = entry.getKey();
                if (playerKey.getUserName().compareTo(SysData.getInstance().getUsername()) == 0) {
                    ArrayList<Game> gameValue = entry.getValue();
                    for (Game game : gameValue) {
                        listOfGames.add(new NewGame(game.getGameID(), playerKey.getUserName(), playerKey.getPositionInGame(game), game.getDateOfGame()));
                    }
                }
            }
        }
        gameIdCol.setCellValueFactory(new PropertyValueFactory<>("gameID"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("positionOfPlayer"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfGame"));

        ObservableList<NewGame> observableList = FXCollections.observableArrayList(listOfGames);
        historTbl.setItems(observableList);

        BTRY.setOnAction(e->{
                add();
        });
        }
        public void add()
        {
            Game g= new Game(3,LocalDate.now());
            HashMap<Player,ArrayList<Game>> map =SysData.getInstance().getPlayerAndgames();
            if(map!=null) {
                for(Player p:map.keySet())
                {
                    if(p.getUserName().compareTo(SysData.getInstance().getUsername())==0)
                    {
                        ArrayList<Game> gamePlayer=map.get(p);
                        gamePlayer.add(g);
                        SysData.getInstance().setPlayerAndgames(map);
                        if(p.getPositionInGame().get(g)==null)
                        {
                            p.setPositionInGame(g,5);
                        }
                        SysData.getInstance().serJsonGames();
                    }
                }
            }

        }

}