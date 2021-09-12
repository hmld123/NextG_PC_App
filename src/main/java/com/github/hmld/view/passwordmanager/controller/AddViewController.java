package com.github.hmld.view.passwordmanager.controller;
/**
 * 密码添加页面
 * @author hmld
 *
 */

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddViewController extends BaseController{
	private IDataPasswordService dataSerivce = new DataPasswordServiceImpl();
	private DataPasswordEnity headerData;
	private int addNum = 0;
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
	@FXML
	public void buttonGenPasswordAction(ActionEvent e) {
		PassWordSetting passWordSetting = new PassWordSetting();
		passWordSetting.setHave_number(this.checkBoxHaveNumber.isSelected());
		passWordSetting.setHave_special(this.checkBoxHaveSpecial.isSelected());
		passWordSetting.setHave_chinese(this.checkBoxHaveChinese.isSelected());
		passWordSetting.setPassord_length(((Double)this.sliderPasswordLength.getValue()).intValue());
		PassWordEnigine enigine = new PassWordEnigine();
		this.textAreaAccountPassword.setText(enigine.getPassWord(passWordSetting));
	}
	@FXML
	public void buttonSaveAction(ActionEvent e) {
    try {
    	if (LoginPool.getLogin(getClass())==null || StringUtils.isEmpty(LoginPool.getLogin(getClass()))) {
    		LoggerUtil.errorMsgI18n(getClass(), "system.log.error","获取登录信息异常");
			}
			if (this.getHeaderData()==null) {
				this.setHeaderData(new DataPasswordEnity());
			}
			// 获取盐
	  	String salt = StringUtils.getSalt();
	  	this.getHeaderData().setPasswordPk(UUID.randomUUID().toString());
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
			this.getHeaderData().setCreatBy(getManager().getManagerUserName());
			this.getHeaderData().setCreatTime(DateUtils.getNowDate().getTime());
			this.getHeaderData().setDelFlg(DelFlgEmnu.USE_TYPE);
			this.getHeaderData().setUseFlg(UseFlgEmnu.USE_TYPE);
			this.addNum = dataSerivce.addEnity(this.getHeaderData());
			LoggerUtil.infoMsgI18n(getClass(), "system.log.info","成功添加["+addNum+"]条");
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
  
  
	public int getAddNum() {
		return addNum;
	}
}
