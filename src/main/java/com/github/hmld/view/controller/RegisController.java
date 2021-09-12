package com.github.hmld.view.controller;

import com.github.hmld.common.utils.LoggerUtil;
import com.github.hmld.common.utils.view.ViewUtil;
import com.github.hmld.core.enity.SysManagerEnity;
import com.github.hmld.core.service.ISysManagerService;
import com.github.hmld.core.service.impl.SysManagerServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisController {
  private ISysManagerService sysManagerService = new SysManagerServiceImpl();
  @FXML
  private TextField textFildRegisUserName;
  @FXML
  private TextField textFildRegisNickName;
  @FXML
  private TextField textFildRegisPassword;
  @FXML
  private TextField textFildRegisRetryPassword;
  @FXML
  private Button buttonBack;
  @FXML
  private Button buttonRegis;
  /**
   * 返回按钮
   */
  @FXML
  public void buttonBackAction(ActionEvent e) {
    Stage oldStage = (Stage)((Node)e.getSource()).getScene().getWindow();
  	ViewUtil.goToStage(getClass(), oldStage, "view/login.fxml");
  }
  /**
   * 注册按钮
   * @param e
   */
  @FXML
  public void buttonRegisAction(ActionEvent e) {
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
				this.buttonBackAction(e);
			}
		}
    else if (usreName == null || usreName.equals("")) {
    	LoggerUtil.warnMsgI18n(getClass(), "regis.msg","用户名不能为空！");
		}
    else if (nickName == null || nickName.equals("")) {
    	LoggerUtil.warnMsgI18n(getClass(), "regis.msg","用户昵称不能为空！");
		}
		else if (pass == null || pass.equals("") || retryPass == null || retryPass.equals("") || pass.equals(retryPass)) {
			LoggerUtil.warnMsgI18n(getClass(), "regis.msg","用户密码不能为空不能为空或两次的密码不同！");
		}
  }

  
}
