package com.github.hmld.view.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import com.github.hmld.common.utils.LoggerUtil;
import com.github.hmld.common.utils.MsageUtils;
import com.github.hmld.core.service.ISysManagerService;
import com.github.hmld.core.service.impl.SysManagerServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
/**
 * 登录窗口Controller
 * @author hmld
 *
 */
public class LoginController {
	private ISysManagerService sysManagerService = new SysManagerServiceImpl();
	@FXML
	private TextArea textAreaLoginMsg;
  @FXML
  private Label lableLoginPassword;
  @FXML
  private Label lableLoginUsername;
  @FXML
  private PasswordField fieldLoginPassword;
  @FXML
  private PasswordField fieldLoginUsername;
  @FXML
  private Button buttonRegis;
  @FXML
  private Button buttonLogin;
  
  @FXML
  public void buttonRegisAction(ActionEvent e) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(loader.getClassLoader().getResource("view/regis.fxml"));
      loader.setResources(ResourceBundle.getBundle(MsageUtils.getPropertiesUrl().replaceAll(".properties", "")));
      Scene scene = new Scene(loader.load());
      Stage loginView = (Stage)((Node)e.getSource()).getScene().getWindow();
      loginView.hide();
      loginView.setScene(scene);
      loginView.show();
      LoggerUtil.infoMsgI18n(getClass(), "system.jump.view","view/regis.fxml");
    } catch (IOException e1) {
      e1.printStackTrace();
      this.getTextAreaLoginMsg().setText(LoggerUtil.errorMsgI18n(getClass(), "system.log.error",e1.getMessage()));
    }
  }
  
  @FXML
  public void buttonLoginAction(ActionEvent e) {
    String userName = this.getFieldLoginUsername().getText();
    String password = this.getFieldLoginPassword().getText();
    if (sysManagerService.loginUser(this.getTextAreaLoginMsg(),userName, password)) {
    	
		}
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

  public PasswordField getFieldLoginPassword() {
		return fieldLoginPassword;
	}

	public void setFieldLoginPassword(PasswordField fieldLoginPassword) {
		this.fieldLoginPassword = fieldLoginPassword;
	}

	public PasswordField getFieldLoginUsername() {
		return fieldLoginUsername;
	}

	public void setFieldLoginUsername(PasswordField fieldLoginUsername) {
		this.fieldLoginUsername = fieldLoginUsername;
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

	public TextArea getTextAreaLoginMsg() {
		return textAreaLoginMsg;
	}

	public void setTextAreaLoginMsg(TextArea textAreaLoginMsg) {
		this.textAreaLoginMsg = textAreaLoginMsg;
	}

}
