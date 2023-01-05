package com.knights_move.controller;

import com.knights_move.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.SimpleStyleableObjectProperty;
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
        private int scoreOfPlayer;
        private LocalDate dateOfGame;


        public NewGame(int gameID, String userName, int scoreOfPlayer, LocalDate dateOfGame) {
            this.gameID = gameID;
            this.userName = userName;
            this.scoreOfPlayer = scoreOfPlayer;
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

        public int getScoreOfPlayer() {
            return scoreOfPlayer;
        }

        public void setScoreOfPlayer(int scoreOfPlayer) {
            this.scoreOfPlayer = scoreOfPlayer;
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
    private TableColumn<NewGame,Integer> scoreCol;

    @FXML
    private TableColumn<NewGame,String> userNameCol;

    @FXML
    private TableColumn<NewGame, Date> dateCol;

    @FXML
    private TableColumn<NewGame, Integer> gameIdCol;
    @FXML
    private TableColumn<NewGame, result> statusCol;
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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SysData.getInstance().DesJsonGame();
        ArrayList<NewGame> listOfGame= new ArrayList<>();
        HashMap<Player,ArrayList<Game>> map= new HashMap<>();
        map=SysData.getInstance().getPlayerAndgames();
        if(map!=null)
        {
            Player p= new Player(SysData.getInstance().getUsername());

            if(map.containsKey(p))
            {
                System.out.println(p.getUserName());
                int count=1;
                for(Game g:map.get(p))
                {
                   // p.setScoreInGame(g,10);
                    listOfGame.add(new NewGame(count,p.getUserName(),g.getTotalScoreInGame(),g.getDateOfGame()));
                    count++;
                }
            }
            gameIdCol.setCellValueFactory(new PropertyValueFactory<>("gameID"));
            userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
            scoreCol.setCellValueFactory(new PropertyValueFactory<>("scoreOfPlayer"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfGame"));

            ObservableList<NewGame> observableList = FXCollections.observableArrayList(listOfGame);
            historTbl.setItems(observableList);
        }

    }

    public static void add(Game game)
    {
        boolean b=false;
        HashMap<Player,ArrayList<Game>> map = SysData.getInstance().getPlayerAndgames();
        Player p= new Player(SysData.getInstance().getUsername());
        ArrayList<Game> list= new ArrayList<>();
        if(map==null)
        {
            System.out.println("empty");
            map=new HashMap<>();
            list.add(game);
            map.put(p,list);
        }
        else {

            for(Player player:map.keySet()) {
                if (player.getUserName().equals(p.getUserName())) {
                    b=true;
                    map.get(p).add(game);
                }
            }
            if(!b)
            {
                list.add(game);
                map.put(p, list);
            }
        }
        SysData.getInstance().setPlayerAndgames(map);
        SysData.getInstance().serJsonGames();
    }


}

