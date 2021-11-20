package com.github.hmld.view.passwordmanager.controller;
/**
 * 密码添加页面
 * @author hmld
 *
 */

import java.util.HashMap;
import java.util.Map;

import com.github.hmld.common.core.emnu.DelFlgEmnu;
import com.github.hmld.common.core.emnu.UseFlgEmnu;
import com.github.hmld.common.core.enity.base.BaseController;
import com.github.hmld.common.utils.DateUtils;
import com.github.hmld.common.utils.EncryptEngine;
import com.github.hmld.common.utils.LoggerUtil;
import com.github.hmld.common.utils.LoginPool;
import com.github.hmld.common.utils.StringUtils;
import com.github.hmld.core.enity.DataPasswordEnity;
import com.github.hmld.core.service.IDataPasswordService;
import com.github.hmld.core.service.impl.DataPasswordServiceImpl;
import com.github.hmld.pwm.enigine.PassWordEnigine;
import com.github.hmld.pwm.enigine.config.PassWordSetting;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditViewController extends BaseController{
	private IDataPasswordService dataSerivce = new DataPasswordServiceImpl();
	private DataPasswordEnity headerData;
	private int editNum = 0;
	private static String oldSalt="";
	@FXML
	private TextField textFieldAppName;
	@FXML
	private TextField textFieldAppUrl;
	@FXML
	private TextField textFieldAccountUserName;
	@FXML
	private TextField textFieldAccountNickName;
	@FXML
	private TextField textFieldAccountEmail;
	@FXML
	private TextField textFieldAccountPhoneNumber;
	@FXML
	private Slider sliderPasswordLength;
	@FXML
	private CheckBox checkBoxHaveNumber;
	@FXML
	private CheckBox checkBoxHaveSpecial;
	@FXML
	private CheckBox checkBoxHaveChinese;
	@FXML
	private TextArea textAreaAccountPassword;
	@FXML
	private Button buttonGenPassword;
	@FXML
	private Button buttonSave;
	
	public void buttonGenPasswordAction(ActionEvent e) {
		PassWordSetting passWordSetting = new PassWordSetting();
		passWordSetting.setHave_number(this.checkBoxHaveNumber.isSelected());
		passWordSetting.setHave_special(this.checkBoxHaveSpecial.isSelected());
		passWordSetting.setHave_chinese(this.checkBoxHaveChinese.isSelected());
		passWordSetting.setPassord_length(((Double)this.sliderPasswordLength.getValue()).intValue());
		PassWordEnigine enigine = new PassWordEnigine();
		this.textAreaAccountPassword.setText(enigine.getPassWord(passWordSetting));
	}
	
	public void buttonSaveAction(ActionEvent e) {
    try {
    	if (LoginPool.getLogin(getClass())==null || StringUtils.isEmpty(LoginPool.getLogin(getClass()))) {
    		LoggerUtil.errorMsgI18n(getClass(), "system.log.error","获取登录信息异常");
			}
			if (this.getHeaderData()==null || StringUtils.isEmpty(this.getHeaderData().getPasswordPk())) {
				return;
			}
			// 获取盐
	  	String salt = StringUtils.getSalt();
	  	this.getHeaderData().setAncestors("0");
	  	this.getHeaderData().setGrade(0);
	  	this.getHeaderData().setManagerUserPk(LoginPool.getLogin(getClass()));
	  	this.getHeaderData().setManagerNickName(getManager().getManagerNickName());
			this.getHeaderData().setAppName(this.textFieldAppName.getText());
			this.getHeaderData().setAppWebUrl(this.textFieldAppUrl.getText());
			this.getHeaderData().setAccountNickName(this.textFieldAccountNickName.getText());
			this.getHeaderData().setSalt(salt);
			String enitySalt = EncryptEngine.encode(salt.getBytes(), getSaltEncodeData(this.getHeaderData()), getManager().getSalt().getBytes());
			String accountUserName = EncryptEngine.encode(this.textFieldAccountUserName.getText().getBytes(), getEncodeData(this.getHeaderData()), salt.getBytes());
			String email = EncryptEngine.encode(this.textFieldAccountEmail.getText().getBytes(), getEncodeData(this.getHeaderData()), salt.getBytes());
			String phoneNumber = EncryptEngine.encode(this.textFieldAccountPhoneNumber.getText().getBytes(), getEncodeData(this.getHeaderData()), salt.getBytes());
			String password = EncryptEngine.encode(this.textAreaAccountPassword.getText().getBytes(), getEncodeData(this.getHeaderData()), salt.getBytes());
			this.getHeaderData().setSalt(enitySalt);
			this.getHeaderData().setAccountUserName(accountUserName);
			this.getHeaderData().setAccountEmail(email);
			this.getHeaderData().setAccountPhoneNumber(phoneNumber);
			this.getHeaderData().setAccountPassword(password);
			this.getHeaderData().setUpdateBy(getManager().getManagerUserName());
			this.getHeaderData().setUpdateTime(DateUtils.getNowDate().getTime());
			this.getHeaderData().setDelFlg(DelFlgEmnu.USE_TYPE);
			this.getHeaderData().setUseFlg(UseFlgEmnu.USE_TYPE);
			this.editNum = dataSerivce.editEnity(this.getHeaderData());
			LoggerUtil.infoMsgI18n(getClass(), "system.log.info","成功添加["+editNum+"]条");
			Stage thisStage = (Stage)((Node)e.getSource()).getScene().getWindow();
			thisStage.close();
    } catch (Exception ex) {
    	LoggerUtil.errorMsgI18n(getClass(), "system.log.error",ex.getMessage());
    } 
  }
	
	/**
	 * 构建加密密码
	 * @param manager
	 * @return
	 */
  private static Object getEncodeData(DataPasswordEnity enity) {
  	Map<String, String> data = new HashMap<String, String>();
    data.put("managerUserName", getManager().getManagerUserName());
    data.put("managerSalt", getManager().getSalt());
    data.put("salt", enity.getSalt());
		return data;
  }
	/**
	 * 构建加密密码
	 * @param manager
	 * @return
	 */
  private static Object getSaltEncodeData(DataPasswordEnity enity) {
  	Map<String, String> data = new HashMap<String, String>();
    data.put("managerUserName", getManager().getManagerUserName());
    data.put("managerSalt", getManager().getSalt());
    data.put("enity", enity.getPasswordPk());
		return data;
  }
  
	public int geteditNum() {
		return editNum;
	}
	
	public void initData(DataPasswordEnity data) {
		try {
			if (data!=null) {
				oldSalt = data.getSalt();
				this.headerData = data;
				this.textFieldAppName.setText(this.headerData.getAppName());
				this.textFieldAppUrl.setText(this.headerData.getAppWebUrl());
				this.textFieldAccountNickName.setText(this.headerData.getAccountNickName());
				String salt = EncryptEngine.decode(oldSalt.getBytes(), getSaltEncodeData(data), getManager().getSalt().getBytes());
				data.setSalt(salt);
				String accountUserName = EncryptEngine.decode(this.headerData.getAccountUserName().getBytes(), getEncodeData(data), salt.getBytes());
				String accountEmail = EncryptEngine.decode(this.headerData.getAccountEmail().getBytes(), getEncodeData(data), salt.getBytes());
				String accountPhoneNumber = EncryptEngine.decode(this.headerData.getAccountPhoneNumber().getBytes(), getEncodeData(data), salt.getBytes());
				String apssword = EncryptEngine.decode(this.headerData.getAccountPassword().getBytes(), getEncodeData(data), salt.getBytes());
				this.textFieldAccountUserName.setText(accountUserName);
				this.textFieldAccountEmail.setText(accountEmail);
				this.textFieldAccountPhoneNumber.setText(accountPhoneNumber);
				this.textAreaAccountPassword.setText(apssword);
			}
		} catch (Exception ex) {
	  	LoggerUtil.errorMsgI18n(getClass(), "system.log.error",ex.getMessage());
	  } 
	}
	public DataPasswordEnity getHeaderData() {
		return headerData;
	}
	public void setHeaderData(DataPasswordEnity headerData) {
		this.headerData = headerData;
	}
	
}
