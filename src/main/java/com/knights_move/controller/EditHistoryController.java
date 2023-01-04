package com.knights_move.controller;

import com.knights_move.controller.QuestionAnswerController;
import com.knights_move.model.Game;
import com.knights_move.model.Player;
import com.knights_move.model.STATUS;
import com.knights_move.model.SysData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class EditHistoryController implements Initializable {
    public static class  historyEdit{
        private String quesId;
        private STATUS type;
        private String changes;
        private LocalDate dateof;

        public historyEdit(String quesId, STATUS type, String changes, LocalDate dateof) {
            this.quesId = quesId;
            this.type = type;
            this.changes = changes;
            this.dateof = dateof;
        }

        public String getQuesId() {
            return quesId;
        }

        public void setQuesId(String quesId) {
            this.quesId = quesId;
        }

        public STATUS getType() {
            return type;
        }

        public void setType(STATUS type) {
            this.type = type;
        }

        public String getChanges() {
            return changes;
        }

        public void setChanges(String changes) {
            this.changes = changes;
        }

        public LocalDate getDateof() {
            return dateof;
        }

        public void setDateof(LocalDate dateof) {
            this.dateof = dateof;
        }

        @Override
        public String toString() {
            return "historyEdit" +
                    "quesId='" + quesId ;
        }
    }
    @FXML
    private TableColumn<EditHistoryController.historyEdit,LocalDateTime> DateCol;

    @FXML
    private TableColumn<EditHistoryController.historyEdit,String> QuestionIdCol;

    @FXML
    private TableColumn<EditHistoryController.historyEdit,STATUS> StatusCol;

    @FXML
    private TableColumn<EditHistoryController.historyEdit,String> changeFiledCol;

    @FXML
    private Label label_title;

    @FXML
    private Pane pane_top;

    @FXML
    private TableView<historyEdit> tableView_historyEdit;
    private static ArrayList<historyEdit> history;
    public  ArrayList<historyEdit> getHistory() {
        return history;
    }
    @FXML
    private Label lbDescribeRow;


    public  void setHistory(ArrayList<historyEdit> history) {
        this.history = history;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<historyEdit> observableList = null;
        if(SysData.getInstance().getListOfChange()!=null)
        {
            observableList = FXCollections.observableArrayList(SysData.getInstance().getListOfChange());
        }
        QuestionIdCol.setCellValueFactory(new PropertyValueFactory<>("quesId"));
        StatusCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        changeFiledCol.setCellValueFactory(new PropertyValueFactory<>("changes"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("dateof"));
        tableView_historyEdit.setItems(observableList);


        tableView_historyEdit.setRowFactory( tv -> {
            TableRow<EditHistoryController.historyEdit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    historyEdit rowData = row.getItem();
                    System.out.println("hey");
                    lbDescribeRow.setText(row.getItem().toString());
                }
            });
            return row;
        });
//        tableView_historyEdit
//        if(!(tableView_historyEdit.getSelectionModel().isEmpty())) {
//            System.out.println("yes");
//            lbDescribeRow.setText(String.valueOf(tableView_historyEdit.getSelectionModel().getSelectedItem().toString()));
//        }

    }
}
