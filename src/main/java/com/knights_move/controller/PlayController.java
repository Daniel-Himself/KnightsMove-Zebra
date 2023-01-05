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
    private int kingSec = 0;
    private Timeline timeline;
    private Timeline timeKing;
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
            PlayAssistController.checkAnswerForRadio(question, 1, msgTxt, game, questionPane, answerRadio1, boardGrid);
        });
        answerRadio2.setOnAction(event -> {
            PlayAssistController.checkAnswerForRadio(question, 2, msgTxt, game, questionPane, answerRadio2, boardGrid);
        });
        answerRadio3.setOnAction(event -> {
            PlayAssistController.checkAnswerForRadio(question, 3, msgTxt, game, questionPane, answerRadio3, boardGrid);
        });
        answerRadio4.setOnAction(event -> {
            PlayAssistController.checkAnswerForRadio(question, 4, msgTxt, game, questionPane, answerRadio4, boardGrid);
        });

    }

    private void clearGrid() {
        game.setTotalScoreInGame(game.getTotalScoreInGame() + game.getCurrentLevelScore());
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
        king.setPosition(new Position(7,7));
    }

    public void setFiguresOnBoard() {
        msgTxt.setText("Game started");
        Button btnHorse = (Button)PlayAssistController.getNodeByRowColumnIndex(horse.getPosition().getX(),horse.getPosition().getY(), boardGrid);
        btnHorse.getStyleClass().add("vbox");
        btnHorse.setGraphic(horseImg);
        horseImg.setVisible(true);
        int level = game.getGameBoard().getBoardId();
        if(level == 1 || level == 2){
            Button btnQueen = (Button)PlayAssistController.getNodeByRowColumnIndex(queen.getPosition().getX(),queen.getPosition().getY(), boardGrid);
            btnQueen.setGraphic(queenImg);
            queenImg.setVisible(true);
        }
        else{
            if(level == 4){
                timeKing = initKingMoveTimer();
            }
            Button btnKing = (Button)PlayAssistController.getNodeByRowColumnIndex(king.getPosition().getX(),king.getPosition().getY(), boardGrid);
            btnKing.setGraphic(kingImg);
            kingImg.setVisible(true);
        }
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
        PlayAssistController.setNextQuestion(game, boardGrid);
        initialized = true;
    }

    private void initFigures(){
        Board board = new Board(1);
        game = new Game(1, board);
        game.setQuestion(SysData.getInstance().getQuestions());
        System.out.println("question array: "+game.getQuestion());
        List<Figure> figures = game.initFigureInStage1();
        horse = figures.get(0);
        queen = figures.get(1);
        king = figures.get(2);
        initGrid(game);
    }

    //todo -> figure out how queen/king kill horse
    //todo horse king cyclic

    private void moveFigure(Button button){
        Board board = game.getGameBoard();
        int level = board.getBoardId();
        System.out.println("last 3 tracked: "+board.getLastThreePositions()+"   score: "+board.getLastThreeScoreChange());
        if(turn == 1){
            List<Position> horseOptions = horse.horseOptions(horse.getPosition(), board);  // horse valid moves
            Position horseNewPos = new Position(GridPane.getRowIndex(button), GridPane.getColumnIndex(button));
            if(horseOptions.contains(horseNewPos)){
                if(level < 3 && queen.getPosition().equals(horseNewPos)){
                    endLevel(level + 1, true);
                }
                if(level > 2 && king.getPosition().equals(horseNewPos)){
                    endLevel(level + 1, true);
                    if(level == 4){
                        timeKing.stop();
                    }
                }
                int scoreChange = 0;
                Tile t = board.getTileByPosition(horseNewPos);
                if(!t.isVisited()){
                    t.setVisited(true);
                    button.getStyleClass().add("vbox");
                    if(t.getTileQuestion() != null){
                        t.setTileColor(Color.WHITE);
                        PlayAssistController.setNextQuestion(game, boardGrid);
                        boardGrid.setDisable(true);
                        Question q = setQuestionPane(t);
                        scoreChange = 0;  // already updated in check answer method scope
                        msgTxt.setText("Question tile: "+q.getLevel()+" points to score");
                    }
                    if(t.getType() == TypeTile.RANDOMPJUMP){
                        int x = PlayAssistController.generateRandomPosition();
                        int y = PlayAssistController.generateRandomPosition();
                        horseNewPos.setX(x);
                        horseNewPos.setY(y);
                        button = (Button)PlayAssistController.getNodeByRowColumnIndex(x,y, boardGrid);
                        button.getStyleClass().add("vbox");
                        Tile dest = board.getTileByPosition(horseNewPos);
                        scoreChange = dest.isVisited()? -1 : 1;     // score update according random destination tile
                        System.out.println("button new random position -> "+GridPane.getRowIndex(button)+" "+ GridPane.getColumnIndex(button));
                        button.setGraphic(horseImg);
                        msgTxt.setText("Random jump tile: new position "+horseNewPos.getX()+","+horseNewPos.getY());
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
                    PlayAssistController.disappear(msgTxt, null,2);
                    scoreChange = -1;
                }
                game.setCurrentLevelScore(game.getCurrentLevelScore() + scoreChange);
                board.updateLastThreeScoreChange(scoreChange);
                scoreLbl.setText(""+game.getCurrentLevelScore());    //todo -> instant score update in queston answer
                if(game.getCurrentLevelScore() >= 15){                     // case of success level passing the next level
                    endLevel(board.getBoardId() +1, true);     // board ID store level num in game
                    turn--;
                }
                turn++;
            }
            else {
                msgTxt.setText("Horse can't move that way");
                PlayAssistController.disappear(msgTxt, null, 2);
            }
            if(turn == 2){
                if(level == 1 || level == 2){
                    Position queenCurrPosition = queen.getPosition();
                    Position queenNextPosition = queen.move(horse.getPosition(), queenCurrPosition);
                    Boolean canAttack = queen.canAttack(horse.getPosition(), queenCurrPosition);
                    //kill horse case
                    if(canAttack){
                        queen.setPosition(queenNextPosition);
                        endLevel(board.getBoardId(), false);
                        msgTxt.setText("Queen Won! Game over");
                    }

                    /*queen.setPosition(queenNextPosition);
                    if(queenNextPosition.equals(horseNewPos)){   //kill horse case
                        endLevel(board.getBoardId(), false);
                        msgTxt.setText("Queen Won! Game over");
                    }*/
                    else{
                        Button nextNode = (Button)PlayAssistController.getNodeByRowColumnIndex(queenNextPosition.getX(),queenNextPosition.getY(), boardGrid);
                        nextNode.setGraphic(queenImg);
                    }
                }
                else if (level == 3){

                    Position kingCurrPosition = king.getPosition();
                    Position kingNextPosition = king.move(horse.getPosition(), kingCurrPosition);
                    System.out.println("king position ->" + kingNextPosition);
                    System.out.println("horse position ->" + horseNewPos);
                    System.out.println(horseNewPos.equals(kingNextPosition));
                    king.setPosition(kingNextPosition);
                    if(horseNewPos.equals(kingNextPosition)){   //kill horse case
                        msgTxt.setText("King Won! Game over");
                        endLevel(board.getBoardId(), false);
                    }
                    else{
                        Button nextNode = (Button)PlayAssistController.getNodeByRowColumnIndex(kingNextPosition.getX(),kingNextPosition.getY(), boardGrid);
                        nextNode.setGraphic(kingImg);
                    }
                }
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
        timeline.stop();
        if(success){
            clearGrid();
            //todo  -> fix after Noa will improve history -> award logic
            if(level > 4) {
                timeKing.stop();
                game.setCurrentQuestion(0);
                awardImg.setVisible(true);
                endGameBtn.setVisible(false);
                PlayAssistController.disappear(null, awardImg, 3);
                msgTxt.setText("Congratulations!!! You won "+game.getTotalScoreInGame()+" points!");
              //  HistoryController.NewGame()
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
            game.setCurrentQuestion(0);
          //  msgTxt.setText("Game Over");
            clearGrid();

        }
    }


    public void kingMoveOnSpeedIncrease(Position kingPosition, Position horsePosition){
        System.out.println("king position ->" + kingPosition);
        System.out.println("horse position ->" + horsePosition);
        System.out.println(horsePosition.equals(kingPosition));
        king.setPosition(kingPosition);
        if(horsePosition.equals(kingPosition)){   //kill horse case
            timeKing.stop();
            msgTxt.setText("King Won! Game over");
            endLevel(game.getGameBoard().getBoardId(), false);
        }
        else{
            Button nextNode = (Button)PlayAssistController.getNodeByRowColumnIndex(kingPosition.getX(),kingPosition.getY(), boardGrid);
            nextNode.setGraphic(kingImg);
        }
    }
    //assumption - king moves every
    // 50-60 0.5sec
    // 40-50 1sec
    // 30-40 1.5sec
    // 20-30 2sec
    // 10-20 3sec
    // 0-10  4sec
    public Timeline initKingMoveTimer(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
            kingSec++;
            int trigger = 0;
            if(kingSec < 60 && kingSec >= 50 ){
                if(kingSec%0.5 == 0){
                    System.out.println("trigger speed 6: "+trigger);
                    kingMoveOnSpeedIncrease(king.move(horse.getPosition(), king.getPosition()), horse.getPosition());
                }
            }
            else if(kingSec < 50 && kingSec >= 40 ){
                if(kingSec%1 == 0){
                    System.out.println("trigger speed 5: "+trigger);
                    kingMoveOnSpeedIncrease(king.move(horse.getPosition(), king.getPosition()), horse.getPosition());
                }
            }
            else if(kingSec < 40 && kingSec >= 30 ){
                if(kingSec%1.5 == 0){
                    System.out.println("trigger speed 4: "+trigger);
                    kingMoveOnSpeedIncrease(king.move(horse.getPosition(), king.getPosition()), horse.getPosition());
                }
            }
            else if(kingSec < 30 && kingSec >= 20 ){
                if(kingSec%2 == 0){
                    System.out.println("trigger speed 3: "+trigger);
                    kingMoveOnSpeedIncrease(king.move(horse.getPosition(), king.getPosition()), horse.getPosition());
                }
            }
            else if(kingSec < 20 && kingSec >= 10 ){
                if(kingSec%3 == 0){
                    System.out.println("trigger speed 2: "+trigger);
                    kingMoveOnSpeedIncrease(king.move(horse.getPosition(), king.getPosition()), horse.getPosition());
                }
            }
            else if(kingSec < 10 && kingSec >= 0 ){
                if(kingSec%4 == 0){
                    System.out.println("trigger speed 1: "+trigger);
                    kingMoveOnSpeedIncrease(king.move(horse.getPosition(), king.getPosition()), horse.getPosition());
                }
            }

        }));
        timeline.setCycleCount(60);
        timeline.play();
        return timeline;

    }

    public Question setQuestionPane(Tile t){
        questionPane.setVisible(true);
        question = t.getTileQuestion();
        questiotText.setText(question.getQuesId());
        answerRadio1.setText(question.getAnswers().get(0).toString());
        answerRadio2.setText(question.getAnswers().get(1).toString());
        answerRadio3.setText(question.getAnswers().get(2).toString());
        answerRadio4.setText(question.getAnswers().get(3).toString());
        return question;
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