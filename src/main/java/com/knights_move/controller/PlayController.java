package com.knights_move.controller;

import com.knights_move.model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.knights_move.model.TypeTile.EMPTY;

public class PlayController {

    @FXML
    private Text questiotText;
    @FXML
    private RadioButton answerRadio1;

    @FXML
    private Pane questionPane;
    @FXML
    private RadioButton answerRadio2;
    @FXML
    private RadioButton answerRadio3;

    @FXML
    private RadioButton answerRadio4;

    @FXML
    private ImageView queenImg;

    @FXML
    private Label scoreTxt;

    @FXML
    private GridPane boardGrid;

    @FXML
    private Label levelLbl;

    @FXML
    private Button startBtn;

    @FXML
    private Button endGameBtn;

    @FXML
    private ImageView kingImg;

    @FXML
    private GridPane figureGrid;

    @FXML
    private Label scoreLbl;

    @FXML
    private Text timeArea;

    @FXML
    private Text msgTxt;

    @FXML
    private ImageView horseImg;
    @FXML
    private ImageView awardImg;
    private boolean initialized = false;
    private int turn = 1;

    private int sec = 60;
    private Timeline timeline;
    private Tile tile;
    private Game game;
    private Question question;
    private Figure horse;
    private Figure queen;
    private Figure king;
    @FXML
    void initialize() {
        awardImg.setVisible(false);
        questionPane.setVisible(false);
        visible(true, true,true, true, true, true, false);
        figureGrid.setVisible(false);

        startBtn.setOnAction(event -> {
            awardImg.setVisible(false);
            if(!initialized) {
                initFigures();
            }
            setFiguresOnBoard();
            visible(true,true,true,true,false,true,true);
            timeline = initTimer();
        });

        endGameBtn.setOnAction(event -> {
            game.getGameBoard().setBoardId(1);
            clearGrid();
            visible(true,true,true,true,true,true,false);
            initGrid(game);
            boardGrid.setVisible(false);
        });

        answerRadio1.setOnAction(event -> {
            PlayAssistController.checkAnswerForRadio(question, 1, msgTxt, game, questionPane, answerRadio1);
        });
        answerRadio2.setOnAction(event -> {
            PlayAssistController.checkAnswerForRadio(question, 2, msgTxt, game, questionPane, answerRadio1);
        });
        answerRadio3.setOnAction(event -> {
            PlayAssistController.checkAnswerForRadio(question, 3, msgTxt, game, questionPane, answerRadio1);
        });
        answerRadio4.setOnAction(event -> {
            PlayAssistController.checkAnswerForRadio(question, 4, msgTxt, game, questionPane, answerRadio1);
        });

    }

    private void clearGrid() {
        game.setCurrentLevelScore(0);
        scoreLbl.setText(""+game.getCurrentLevelScore());
        levelLbl.setText("Level "+game.getGameBoard().getBoardId());
        PlayAssistController.clearBoard(game.getGameBoard());
        boardGrid.getChildren().removeIf(Objects::nonNull);
        msgTxt.setText("Game level lasted "+(60-sec)+" seconds");
        if(timeline != null){
            timeline.stop();
            timeArea.setText("Timer");
            sec = 60;
        }
        horseImg.setVisible(false);
        queenImg.setVisible(false);
        horse.setPosition(new Position(0,0));
        queen.setPosition(new Position(0,7));
    }

    public void setFiguresOnBoard() {
        msgTxt.setText("Game started");
        Button btnHorse = (Button)PlayAssistController.getNodeByRowColumnIndex(horse.getPosition().getX(),horse.getPosition().getY(), boardGrid);
        btnHorse.getStyleClass().add("vbox");
        btnHorse.setGraphic(horseImg);
        Button btnQueen = (Button)PlayAssistController.getNodeByRowColumnIndex(queen.getPosition().getX(),queen.getPosition().getY(), boardGrid);
        btnQueen.setGraphic(queenImg);
        horseImg.setVisible(true);
        queenImg.setVisible(true);
    }

    private void initGrid(Game game){
        int count = 0;
        double s = 38; // side of button in grid
        for (int i = 0; i < 8; i++) {
            count++;
            for (int j = 0; j < 8; j++) {
                tile = new Tile(new Position(i,j),EMPTY,Color.WHITE,null,false);
                Button button = new Button();
                button.setOnAction(event -> {
                    moveFigure(button);
                });
                game.getGameBoard().addEmptyTile(tile);
                game.getGameBoard().getTileList().add(tile);
                button.setPrefSize(s, s);
                button.getStyleClass().removeAll("button");
                if (count % 2 == 0) { button.getStyleClass().add("whiteTile");}
                else { button.getStyleClass().add("greenTile"); }
                boardGrid.add(button, j, i);
                count++;
            }
        }//todo in backend -> tiles with questions
        PlayAssistController.setSpecialTilesByLevel(game, boardGrid);
        game.setQuestionTiles();
        initialized = true;
    }

    private void initFigures(){
        Board board = new Board(1);
        game = new Game(1, board);
        game.setQuestion(SysData.getInstance().getQuestions());
        System.out.println("question array: "+game.getQuestion());
        List<Figure> figures = game.initFigureInStage();
        horse = figures.get(0);
        queen = figures.get(1);
       // king = figures.get(2);
        initGrid(game);
    }
//todo track visited (tile+board)+ timer/level
    //todo -> figure out how queen/king kill horse
    //todo -> blocked logic + colors
    //todo questions logic
    private void moveFigure(Button button){
        Board board = game.getGameBoard();
        System.out.println("last 3 tracked: "+board.getLastThreePositions()+"   score: "+board.getLastThreeScoreChange());
        if(turn == 1){
            List<Position> horseOptions = horse.horseOptions(horse.getPosition(), board);  // horse valid moves
            Position horseNewPos = new Position(GridPane.getRowIndex(button), GridPane.getColumnIndex(button));
            if(horseOptions.contains(horseNewPos)){
                int scoreChange = 0;
                Tile t = board.getTileByPosition(horseNewPos);
                if(!t.isVisited()){
                    t.setVisited(true);
                    button.getStyleClass().add("vbox");
                    if(t.getTileQuestion() != null){
                        setQuestionPane(t);
                    }
                    if(t.getType() == TypeTile.RANDOMPJUMP){
                        int x = PlayAssistController.generateRandomJumpPosition();
                        int y = PlayAssistController.generateRandomJumpPosition();
                        horseNewPos.setX(x);
                        horseNewPos.setY(y);
                        button = (Button)PlayAssistController.getNodeByRowColumnIndex(x,y, boardGrid);
                        button.getStyleClass().add("vbox");
                        Tile dest = board.getTileByPosition(horseNewPos);
                        scoreChange = dest.isVisited()? -1 : 1;     // score update according random destination tile
                        System.out.println("button new random position -> "+GridPane.getRowIndex(button)+" "+ GridPane.getColumnIndex(button));
                        button.setGraphic(horseImg);
                    }
                    else if(t.getType() == TypeTile.FORGOTTEN){
                        button.getStyleClass().remove("vbox");
                        for(int i: board.getLastThreeScoreChange()){
                            scoreChange += i;
                        }
                        Collections.reverse(board.getLastThreePositions());
                        for(Position p: board.getLastThreePositions()){
                            System.out.println("to clean : "+p);
                            button = (Button)PlayAssistController.getNodeByRowColumnIndex(p.getX(),p.getY(), boardGrid);
                            button.getStyleClass().remove("vbox");   // cleaning visited tiles
                            System.out.println("button forgotten position -> "+GridPane.getRowIndex(button)+" "+ GridPane.getColumnIndex(button));
                            Tile forgotten = board.getTileByPosition(p);
                            forgotten.setVisited(false);    // removing from visited list + visited false
                            board.getVisitedTile().remove(forgotten);
                            horseNewPos = p;
                        }
                        System.out.println("new destination after forgotten position : " +horseNewPos);
                        Collections.reverse(board.getLastThreePositions());
                        msgTxt.setText("Forgotten tile: "+scoreChange+" to score");
                        button.setGraphic(horseImg);
                    }
                    else{
                        scoreChange = 1;
                        button.setGraphic(horseImg);
                    }
                    horse.setPosition(horseNewPos);
                    board.updateLastThreePositions(horseNewPos); // updating last 3 horse positions
                    board.addVisitedTile(t);
                    board.removeEmptyTile(t);
                }
                else {
                    msgTxt.setText("Visited tile: -1 to score");
                    PlayAssistController.disappear(msgTxt, null,2, startBtn);
                    scoreChange = -1;
                }
                game.setCurrentLevelScore(game.getCurrentLevelScore() + scoreChange);
                board.updateLastThreeScoreChange(scoreChange);
                scoreLbl.setText(""+game.getCurrentLevelScore());
                if(game.getCurrentLevelScore() >= 10){                     // case of success level passing the next level
                    endLevel(board.getBoardId() +1, true);     // board ID store level num in game
                    turn--;
                }
                turn++;
            }
            else {
                msgTxt.setText("Horse can't move that way");
                PlayAssistController.disappear(msgTxt, null, 2, startBtn);
            }
            if(turn == 2){
                Position queenCurrPosition = queen.getPosition();
                Position queenNextPosition = queen.move(horse.getPosition(), queenCurrPosition);
                queen.setPosition(queenNextPosition);
                Button nextNode = (Button)PlayAssistController.getNodeByRowColumnIndex(queenNextPosition.getX(),queenNextPosition.getY(), boardGrid);
                msgTxt.setText("horse : " + horse.getPosition().getX()+" "+ horse.getPosition().getY()+
                        "  queen : "+queen.getPosition().getX()+" "+ queen.getPosition().getY());
                nextNode.setGraphic(queenImg);
                turn--;
            }
        }
    }

    private Timeline initTimer(){
        timeArea.setText("Time: "+sec);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
            sec--;
            if(sec < 10){ timeArea.setText("Time: 0"+sec); }
            else { timeArea.setText("Time: "+sec); }
            if(sec == 0){
                timeArea.setText("Game over!");
                endLevel(game.getGameBoard().getBoardId(), false);  // case of timeout -> triggers game over
            }
             //todo equals to  level num
        }));
        timeline.setCycleCount(60);
        timeline.play();
        return timeline;
    }
    // end level implements game over case or successfully passing to the next one
    private void endLevel(int level, boolean success) {
        //clearGrid();
        if(success){
            clearGrid();
            //todo  -> fix after Noa will improve history -> award logic
            if(level > 4) {
                awardImg.setVisible(true);
                endGameBtn.setVisible(false);
                PlayAssistController.disappear(null, awardImg, 3, startBtn);
                msgTxt.setText("Congratulations!!! You won "+game.getTotalScoreInGame()+" points!");
            }
            else {
                clearGrid();
                game.getGameBoard().setBoardId(level);
                initGrid(game);
                setFiguresOnBoard();
                timeline = initTimer();
                System.out.println("board id: " + game.getGameBoard());
            }
        }
        else {
          //  HistoryController.add(game);
          //  Player p = new Player(SysData.getInstance().getUsername());
        }
    }

    public void setQuestionPane(Tile t){
        questionPane.setVisible(true);
        question = t.getTileQuestion();
        questiotText.setText(question.getQuesId());
        answerRadio1.setText(question.getAnswers().get(0).toString());
        answerRadio2.setText(question.getAnswers().get(1).toString());
        answerRadio3.setText(question.getAnswers().get(2).toString());
        answerRadio4.setText(question.getAnswers().get(3).toString());
    }
    public void visible(boolean scoreLbl1, boolean timeArea1, boolean levelLbl1, boolean scoreTxt1, boolean startBtn1, boolean boardGrid1, boolean endGameBtn1) {
        scoreLbl.setVisible(scoreLbl1);
        timeArea.setVisible(timeArea1);
        levelLbl.setVisible(levelLbl1);
        scoreTxt.setVisible(scoreTxt1);
        startBtn.setVisible(startBtn1);
        boardGrid.setVisible(boardGrid1);
        endGameBtn.setVisible(endGameBtn1);
    }

}