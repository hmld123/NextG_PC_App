package com.github.hmld.view;

import org.springframework.stereotype.Component;

import com.github.hmld.common.logger.Log;
import com.github.hmld.common.utils.MsageUtils;
import com.github.hmld.common.utils.spring.SpringUtils;
import com.github.hmld.core.service.ISysManagerService;

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
