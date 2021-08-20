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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisController {
  private ISysManagerService sysManagerService = new SysManagerServiceImpl();
  @FXML
	private TextArea textAreaRegisMsg;
  @FXML
  private Label lableRegisUsername;
  @FXML
  private Label lableRegisNickName;
  @FXML
  private Label lableRegisPassword;
  @FXML
  private Label lableRetryRegisPassword;
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
  	ViewUtil.goToStage(this.getTextAreaRegisMsg(),getClass(), oldStage, "view/login.fxml");
  }
  /**
   * 注册按钮
   * @param e
   */
  @FXML
  public void buttonRegisAction(ActionEvent e) {
    String usreName = this.getTextFildRegisUserName().getText();
    String nickName = this.getTextFildRegisNickName().getText();
    String pass = this.getTextFildRegisPassword().getText();
    String retryPass = this.getTextFildRegisRetryPassword().getText();
    // 注册用户
    if (
    		usreName != null && !usreName.equals("") && 
    		nickName != null && !nickName.equals("") && 
    		pass != null && !pass.equals("") && 
    		retryPass != null && !retryPass.equals("") && 
    		pass.equals(retryPass)
    ) {
    	if (sysManagerService.regisUser(this.getTextAreaRegisMsg(),new SysManagerEnity(usreName, nickName, pass))) {
				this.buttonBackAction(e);
			}
		}
    else if (usreName == null || usreName.equals("")) {
    	this.getTextAreaRegisMsg().setText(LoggerUtil.warnMsgI18n(getClass(), "regis.msg","用户名不能为空！"));
		}
    else if (nickName == null || nickName.equals("")) {
    	this.getTextAreaRegisMsg().setText(LoggerUtil.warnMsgI18n(getClass(), "regis.msg","用户昵称不能为空！"));
		}
		else if (pass == null || pass.equals("") || retryPass == null || retryPass.equals("") || pass.equals(retryPass)) {
			this.getTextAreaRegisMsg().setText(LoggerUtil.warnMsgI18n(getClass(), "regis.msg","用户密码不能为空不能为空或两次的密码不同！"));
		}
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
	public ISysManagerService getSysManagerService() {
		return sysManagerService;
	}
	public void setSysManagerService(ISysManagerService sysManagerService) {
		this.sysManagerService = sysManagerService;
	}
	public Label getLableRegisNickName() {
		return lableRegisNickName;
	}
	public void setLableRegisNickName(Label lableRegisNickName) {
		this.lableRegisNickName = lableRegisNickName;
	}
	public TextField getTextFildRegisNickName() {
		return textFildRegisNickName;
	}
	public void setTextFildRegisNickName(TextField textFildRegisNickName) {
		this.textFildRegisNickName = textFildRegisNickName;
	}
	public TextArea getTextAreaRegisMsg() {
		return textAreaRegisMsg;
	}
	public void setTextAreaRegisMsg(TextArea textAreaRegisMsg) {
		this.textAreaRegisMsg = textAreaRegisMsg;
	}
  
}
