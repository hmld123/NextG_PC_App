package com.github.hmld.view;

import org.springframework.stereotype.Component;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

@Component
public class LoginView extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    AnchorPane root = new AnchorPane();
    Scene scene = new Scene(root );
    stage.setScene(scene );
    stage.setTitle("title");
    stage.show();
  }

}
