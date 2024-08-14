package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("nagit.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setTitle("TO-DO List");
        stage.setScene(scene);
        stage.show();
        Task task = getData.getTask("32");
        System.out.println(task);
        //getData.getStats("2");
        //EditTask.editTask("4");
        //DeleteData.deleteTask("16");
        //DeleteData.deleteCategory("11");
        //AddData.addCategory("Sport", "2");
        //getData.getTasks();
        //AddData.addTask("Nějaký jméno", "String priority", "String note", "24-01-01", "15:00", "2", "2");
    }



    public static void main(String[] args) {
        launch();
    }
}