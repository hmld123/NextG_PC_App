package com.github.hmld.view.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
/**
 * 登录窗口Controller
 * @author hmld
 *
 */
public class LoginController {
  @FXML
  private Label lableLoginPassword;
  @FXML
  private Label lableLoginUsername;
  @FXML
  private PasswordField buttonLoginPassword;
  @FXML
  private PasswordField buttonLoginUsername;
  @FXML
  private Button buttonRegis;
  @FXML
  private Button buttonLogin;
  
  @FXML
  public void buttonRegisAction(ActionEvent e) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(loader.getClassLoader().getResource("view/regis.fxml"));
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
  public void buttonLoginAction(ActionEvent e) {
    
  }

  public Label getLableLoginPassword() {
    return lableLoginPassword;
  }

  public void setLableLoginPassword(Label lableLoginPassword) {
    this.lableLoginPassword = lableLoginPassword;
  }

  public Label getLableLoginUsername() {
    return lableLoginUsername;
  }

  public void setLableLoginUsername(Label lableLoginUsername) {
    this.lableLoginUsername = lableLoginUsername;
  }

  public PasswordField getButtonLoginPassword() {
    return buttonLoginPassword;
  }

  public void setButtonLoginPassword(PasswordField buttonLoginPassword) {
    this.buttonLoginPassword = buttonLoginPassword;
  }

  public PasswordField getButtonLoginUsername() {
    return buttonLoginUsername;
  }

  public void setButtonLoginUsername(PasswordField buttonLoginUsername) {
    this.buttonLoginUsername = buttonLoginUsername;
  }

  public Button getButtonRegis() {
    return buttonRegis;
  }

  public void setButtonRegis(Button buttonRegis) {
    this.buttonRegis = buttonRegis;
  }

  public Button getButtonLogin() {
    return buttonLogin;
  }

  public void setButtonLogin(Button buttonLogin) {
    this.buttonLogin = buttonLogin;
  }
  
  
}
