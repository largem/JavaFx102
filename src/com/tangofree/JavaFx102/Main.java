package com.tangofree.JavaFx102;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Date;

/**
 * Created by Megral on 6/3/2016.
 */
public class Main extends Application{

    Stage window;
    TableView<DriveItem> driveTable;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Plus10 Drive");
        window.setWidth(800);
        window.setHeight(600);

        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(10, 10, 10, 10));

        //Hbox on the top
        HBox hbox = new HBox();
        Button connectBtn = new Button("Connect to Drive");
        hbox.getChildren().addAll(connectBtn);
        mainPane.setTop(hbox);

        //Tree on the left
        TreeItem<String> root = new TreeItem<>("Plus10 Drive");
        root.setExpanded(true);
        TreeView<String> driveTree = new TreeView<>(root);
        //Add one child to root
        TreeItem<String> childNode = new TreeItem<>("Folder 1");
        root.getChildren().addAll(childNode);
        mainPane.setLeft(driveTree);

        //Table on the center (along with some buttons)
        HBox hbox1 = new HBox();
        hbox1.setPadding(new Insets(0, 10, 0, 0));
        hbox1.setSpacing(10);
        Button uploadBtn = new Button("Upload");
        Button downloadBtn = new Button("Download");
        Button propertyBtn = new Button("Property");
        Button previewBtn = new Button("Preview");
        hbox1.getChildren().addAll(uploadBtn, downloadBtn, propertyBtn, previewBtn);

        //create Table Columns
        TableColumn<DriveItem, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<DriveItem, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(80);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<DriveItem, Integer> sizeColumn = new TableColumn<>("Size");
        sizeColumn.setMinWidth(60);
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));

        driveTable = new TableView<>();
        driveTable.setItems(getDriveItems());
        driveTable.getColumns().addAll(nameColumn, dateColumn, sizeColumn);


        VBox vbox = new VBox();
        vbox.setVgrow(driveTable, Priority.ALWAYS);
        vbox.getChildren().addAll(hbox1, driveTable);

        mainPane.setCenter(vbox);

        Label status = new Label("Status");
        mainPane.setBottom(status);

        Scene scene = new Scene(mainPane);
        window.setScene(scene);
        window.show();
    }

    public ObservableList<DriveItem> getDriveItems() {
        ObservableList<DriveItem> items = FXCollections.observableArrayList();
        items.add(new DriveItem("Fake Picture.jpg", "2016-05-01", 100, "abcd"));
        items.add(new DriveItem("Fake Picture 2.jpg", "2016-05-01", 112, "abcd"));
        items.add(new DriveItem("Fake Picture 3.jpg", "2016-05-01", 1222, "abcd"));
        items.add(new DriveItem("Fake Picture 4.jpg", "2016-05-01", 1343433, "abcd"));

        return items;
    }
}
