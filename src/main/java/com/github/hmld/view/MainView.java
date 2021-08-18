package com.github.hmld.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(loader.getClassLoader().getResource("view/login.fxml"));
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
    stage.show();
  }

}
