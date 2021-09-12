package com.github.hmld.view.controller;
import com.github.hmld.common.utils.view.ViewUtil;
import com.github.hmld.core.service.ISysManagerService;
import com.github.hmld.core.service.impl.SysManagerServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
/**
 * 登录窗口Controller
 * @author hmld
 *
 */
public class LoginController {
	private ISysManagerService sysManagerService = new SysManagerServiceImpl();
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
  	Stage oldStage = (Stage)((Node)e.getSource()).getScene().getWindow();
  	ViewUtil.goToStage(getClass(), oldStage, "view/regis.fxml");
  }
  
  @FXML
  public void buttonLoginAction(ActionEvent e) {
    String userName = this.fieldLoginUsername.getText();
    String password = this.fieldLoginPassword.getText();
    if (sysManagerService.loginUser(userName, password)) {
    	Stage oldStage = (Stage)((Node)e.getSource()).getScene().getWindow();
    	ViewUtil.goToStage(getClass(), oldStage, "view/passwordmanager/pm.fxml");
		}
  }

}
