package com.github.hmld.view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.hmld.common.utils.LoggerUtil;
import com.github.hmld.common.utils.MsageUtils;
import com.github.hmld.common.utils.view.ViewUtil;
import com.github.hmld.core.enity.SysManagerEnity;
import com.github.hmld.core.service.ISysManagerService;
import com.github.hmld.core.service.impl.SysManagerServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainViewController implements Initializable{
	private ISysManagerService sysManagerService = new SysManagerServiceImpl();

  @FXML
  private PasswordField fieldLoginPassword;
  @FXML
  private PasswordField fieldLoginUsername;
  @FXML
  private Button buttonLogin;
  @FXML
  private TextField textFildRegisUserName;
  @FXML
  private TextField textFildRegisNickName;
  @FXML
  private TextField textFildRegisPassword;
  @FXML
  private TextField textFildRegisRetryPassword;
  @FXML
  private Button buttonRegis;
  @FXML
  private TextArea msgConsole;
  @FXML
  private Tab loginTab;
  @FXML
  private Tab regisTab;
  
  @FXML
  public void buttonLoginAction(ActionEvent e) {
  	msgConsole.clear();
    String userName = this.fieldLoginUsername.getText();
    String password = this.fieldLoginPassword.getText();
    if (userName==null || userName.equals("")) {
			LoggerUtil.warnMsgI18n(getClass(), "login.msg", MsageUtils.getMsg("login.view.lable.user_name")+"不能为空！");
			msgConsole.appendText(MsageUtils.getMsg("login.view.lable.user_name")+"不能为空！");
			return;
		}
		if (password==null || password.equals("")) {
			LoggerUtil.warnMsgI18n(getClass(), "login.msg", MsageUtils.getMsg("login.view.lable.password")+"不能为空！");
			msgConsole.appendText(MsageUtils.getMsg("login.view.lable.password")+"不能为空！");
			return;
		}
    if (sysManagerService.loginUser(userName, password)) {
  		LoggerUtil.warnMsgI18n(getClass(), "login.msg","登录成功!");
  		msgConsole.appendText("登录成功!");
    	Stage oldStage = (Stage)((Node)e.getSource()).getScene().getWindow();
    	ViewUtil.goToStage(getClass(), oldStage, "view/passwordmanager/pm.fxml");
		}
  }
  
  /**
   * 注册按钮
   * @param e
   */
  @FXML
  public void buttonRegisAction(ActionEvent e) {
  	msgConsole.clear();
    String usreName = this.textFildRegisUserName.getText();
    String nickName = this.textFildRegisNickName.getText();
    String pass = this.textFildRegisPassword.getText();
    String retryPass = this.textFildRegisRetryPassword.getText();
    // 注册用户
    if (
    		usreName != null && !usreName.equals("") && 
    		nickName != null && !nickName.equals("") && 
    		pass != null && !pass.equals("") && 
    		retryPass != null && !retryPass.equals("") && 
    		pass.equals(retryPass)
    ) {
    	if (sysManagerService.regisUser(new SysManagerEnity(usreName, nickName, pass))) {
    		LoggerUtil.warnMsgI18n(getClass(), "regis.msg","注册成功!");
    		msgConsole.appendText("注册成功!");
			}
		}
    else if (usreName == null || usreName.equals("")) {
    	LoggerUtil.warnMsgI18n(getClass(), "regis.msg","用户名不能为空！");
    	msgConsole.appendText(MsageUtils.getMsg("regis.view.lable.user_name")+"不能为空！");
		}
    else if (nickName == null || nickName.equals("")) {
    	LoggerUtil.warnMsgI18n(getClass(), "regis.msg","用户昵称不能为空！");
    	msgConsole.appendText(MsageUtils.getMsg("regis.view.lable.nick_name")+"不能为空！");
		}
		else if (pass == null || pass.equals("") || retryPass == null || retryPass.equals("") || pass.equals(retryPass)) {
			LoggerUtil.warnMsgI18n(getClass(), "regis.msg",MsageUtils.getMsg("regis.view.lable.password")+"不能为空不能为空或两次的"+MsageUtils.getMsg("regis.view.lable.password")+"不同！");
			msgConsole.appendText(MsageUtils.getMsg("regis.view.lable.password")+"不能为空不能为空或两次的"+MsageUtils.getMsg("regis.view.lable.password")+"不同！");
		}
  }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fieldLoginUsername.setPromptText(MsageUtils.getMsg("login.view.lable.user_name"));
		fieldLoginPassword.setPromptText(MsageUtils.getMsg("login.view.lable.password"));
	  textFildRegisUserName.setPromptText(MsageUtils.getMsg("regis.view.lable.user_name"));
	  textFildRegisNickName.setPromptText(MsageUtils.getMsg("regis.view.lable.nick_name"));
	  textFildRegisPassword.setPromptText(MsageUtils.getMsg("regis.view.lable.password"));
	  textFildRegisRetryPassword.setPromptText(MsageUtils.getMsg("regis.view.lable.retry_password"));
	}

}
