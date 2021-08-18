package com.github.hmld.view.controller;

import java.io.IOException;

import com.github.hmld.core.enity.SysManagerEnity;
import com.github.hmld.core.service.ISysManagerService;
import com.github.hmld.core.service.impl.SysManagerServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisController {
  private ISysManagerService sysManagerService = new SysManagerServiceImpl();
  @FXML
  private Label lableRegisUsername;
  @FXML
  private Label lableRegisPassword;
  @FXML
  private Label lableRetryRegisPassword;
  @FXML
  private TextField textFildRegisUserName;
  @FXML
  private TextField textFildRegisPassword;
  @FXML
  private TextField textFildRegisRetryPassword;
  @FXML
  private Button buttonBack;
  @FXML
  private Button buttonRegis;
  @FXML
  public void buttonBackAction(ActionEvent e) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(loader.getClassLoader().getResource("view/login.fxml"));
      Scene scene = new Scene(loader.load());
      Stage loginView = (Stage)((Node)e.getSource()).getScene().getWindow();
      loginView.hide();
      loginView.setScene(scene);
      loginView.show();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }
  @FXML
  public void buttonRegisAction(ActionEvent e) {
    this.getTextFildRegisUserName();
    this.getTextFildRegisPassword();
    this.getTextFildRegisRetryPassword();
    sysManagerService.regisUser(new SysManagerEnity());
  }
  public Label getLableRegisUsername() {
    return lableRegisUsername;
  }
  public void setLableRegisUsername(Label lableRegisUsername) {
    this.lableRegisUsername = lableRegisUsername;
  }
  public Label getLableRegisPassword() {
    return lableRegisPassword;
  }
  public void setLableRegisPassword(Label lableRegisPassword) {
    this.lableRegisPassword = lableRegisPassword;
  }
  public Label getLableRetryRegisPassword() {
    return lableRetryRegisPassword;
  }
  public void setLableRetryRegisPassword(Label lableRetryRegisPassword) {
    this.lableRetryRegisPassword = lableRetryRegisPassword;
  }
  public TextField getTextFildRegisUserName() {
    return textFildRegisUserName;
  }
  public void setTextFildRegisUserName(TextField textFildRegisUserName) {
    this.textFildRegisUserName = textFildRegisUserName;
  }
  public TextField getTextFildRegisPassword() {
    return textFildRegisPassword;
  }
  public void setTextFildRegisPassword(TextField textFildRegisPassword) {
    this.textFildRegisPassword = textFildRegisPassword;
  }
  public TextField getTextFildRegisRetryPassword() {
    return textFildRegisRetryPassword;
  }
  public void setTextFildRegisRetryPassword(TextField textFildRegisRetryPassword) {
    this.textFildRegisRetryPassword = textFildRegisRetryPassword;
  }
  public Button getButtonBack() {
    return buttonBack;
  }
  public void setButtonBack(Button buttonBack) {
    this.buttonBack = buttonBack;
  }
  public Button getButtonRegis() {
    return buttonRegis;
  }
  public void setButtonRegis(Button buttonRegis) {
    this.buttonRegis = buttonRegis;
  }
  
}
