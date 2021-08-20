package com.github.hmld.view;

import java.util.ResourceBundle;

import org.apache.ibatis.session.SqlSession;

import com.github.hmld.common.utils.LoggerUtil;
import com.github.hmld.common.utils.MsageUtils;
import com.github.hmld.common.utils.SqliteJDBCUtil;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainView extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(loader.getClassLoader().getResource("view/login.fxml"));
    loader.setResources(ResourceBundle.getBundle(MsageUtils.getPropertiesUrl().replaceAll(".properties", "")));
    LoggerUtil.infoMsgI18n(getClass(), "system.log.info","加载 [view/login.fxml]");
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				SqlSession session = SqliteJDBCUtil.getCurrentSqlSession();
				session.close();
				LoggerUtil.infoMsgI18n(getClass(),"system.log.info", "Session is close!");
			}
		});
    stage.show();
  }

}
