package controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.DownloadPage;
import model.H2DB;


public class Result {
    ObservableList<URL> list = FXCollections.observableArrayList();


    @FXML
    private Button downloadButton;

    @FXML
    private Button addToDB;

    @FXML
    private ListView<String> ResultList;

    @FXML
    void initialize(){

        //вывод на экран
        addToResult();
        downloadButton.setOnAction(event -> {
            //скачиваем страницы
            DownloadPage downloadPage = new DownloadPage();

            //номер добавляемый перед скачиванием
            int iter = 0;
            for (URL link: Controller.getResult()){
                iter++;
                downloadPage.DownloadWebPage(link,iter);
            }
        });
        addToDB.setOnAction(event -> {
            //создание ДБ
            H2DB h2DB = new H2DB();
            h2DB.createDb();
            for (URL link :Controller.getResult()) {
                h2DB.insertInDB(String.valueOf(link));
            }
        });
    }

    private void addToResult(){
        list.addAll(Controller.getResult());
        for (URL link :list){
            ResultList.getItems().add(String.valueOf(link));
        }
    }
}
