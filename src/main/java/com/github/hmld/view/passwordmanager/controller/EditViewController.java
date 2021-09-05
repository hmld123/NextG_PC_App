package com.github.hmld.view.passwordmanager.controller;
/**
 * 密码添加页面
 * @author hmld
 *
 */

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.github.hmld.common.core.emnu.DelFlgEmnu;
import com.github.hmld.common.core.emnu.UseFlgEmnu;
import com.github.hmld.common.utils.DateUtils;
import com.github.hmld.common.utils.EncryptEngine;
import com.github.hmld.common.utils.LoggerUtil;
import com.github.hmld.common.utils.LoginPool;
import com.github.hmld.common.utils.SqliteJDBCUtil;
import com.github.hmld.common.utils.StringUtils;
import com.github.hmld.core.enity.DataPasswordEnity;
import com.github.hmld.core.enity.SysManagerEnity;
import com.github.hmld.core.mapper.SysManagerMapper;
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

public class EditViewController {
	private IDataPasswordService dataSerivce = new DataPasswordServiceImpl();
	private DataPasswordEnity headerData;
	private int editNum = 0;
	private static String oldSalt="";
	private static SysManagerEnity headerManager;
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
		passWordSetting.setHave_number(this.getCheckBoxHaveNumber().isSelected());
		passWordSetting.setHave_special(this.getCheckBoxHaveSpecial().isSelected());
		passWordSetting.setHave_chinese(this.getCheckBoxHaveChinese().isSelected());
		passWordSetting.setPassord_length(((Double)this.getSliderPasswordLength().getValue()).intValue());
		PassWordEnigine enigine = new PassWordEnigine();
		this.getTextAreaAccountPassword().setText(enigine.getPassWord(passWordSetting));
	}
	@FXML
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
			this.getHeaderData().setAppName(this.getTextFieldAppName().getText());
			this.getHeaderData().setAppWebUrl(this.getTextFieldAppUrl().getText());
			this.getHeaderData().setAccountNickName(this.getTextFieldAccountNickName().getText());
			this.getHeaderData().setSalt(salt);
			String enitySalt = EncryptEngine.encode(salt.getBytes(), getSaltEncodeData(this.getHeaderData()), getManager().getSalt().getBytes());
			String accountUserName = EncryptEngine.encode(this.getTextFieldAccountUserName().getText().getBytes(), getEncodeData(this.getHeaderData()), salt.getBytes());
			String email = EncryptEngine.encode(this.getTextFieldAccountEmail().getText().getBytes(), getEncodeData(this.getHeaderData()), salt.getBytes());
			String phoneNumber = EncryptEngine.encode(this.getTextFieldAccountPhoneNumber().getText().getBytes(), getEncodeData(this.getHeaderData()), salt.getBytes());
			String password = EncryptEngine.encode(this.getTextAreaAccountPassword().getText().getBytes(), getEncodeData(this.getHeaderData()), salt.getBytes());
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
    } catch (Exception ex) {
    	LoggerUtil.errorMsgI18n(getClass(), "system.log.error",ex.getMessage());
    } 
  }
	
	public TextField getTextFieldAppName() {
		return textFieldAppName;
	}
	public void setTextFieldAppName(TextField textFieldAppName) {
		this.textFieldAppName = textFieldAppName;
	}
	public TextField getTextFieldAppUrl() {
		return textFieldAppUrl;
	}
	public void setTextFieldAppUrl(TextField textFieldAppUrl) {
		this.textFieldAppUrl = textFieldAppUrl;
	}
	public TextField getTextFieldAccountUserName() {
		return textFieldAccountUserName;
	}
	public void setTextFieldAccountUserName(TextField textFieldAccountUserName) {
		this.textFieldAccountUserName = textFieldAccountUserName;
	}
	public TextField getTextFieldAccountNickName() {
		return textFieldAccountNickName;
	}
	public void setTextFieldAccountNickName(TextField textFieldAccountNickName) {
		this.textFieldAccountNickName = textFieldAccountNickName;
	}
	public TextField getTextFieldAccountEmail() {
		return textFieldAccountEmail;
	}
	public void setTextFieldAccountEmail(TextField textFieldAccountEmail) {
		this.textFieldAccountEmail = textFieldAccountEmail;
	}
	public TextField getTextFieldAccountPhoneNumber() {
		return textFieldAccountPhoneNumber;
	}
	public void setTextFieldAccountPhoneNumber(TextField textFieldAccountPhoneNumber) {
		this.textFieldAccountPhoneNumber = textFieldAccountPhoneNumber;
	}
	public Slider getSliderPasswordLength() {
		return sliderPasswordLength;
	}
	public void setSliderPasswordLength(Slider sliderPasswordLength) {
		this.sliderPasswordLength = sliderPasswordLength;
	}
	public CheckBox getCheckBoxHaveNumber() {
		return checkBoxHaveNumber;
	}
	public void setCheckBoxHaveNumber(CheckBox checkBoxHaveNumber) {
		this.checkBoxHaveNumber = checkBoxHaveNumber;
	}
	public CheckBox getCheckBoxHaveSpecial() {
		return checkBoxHaveSpecial;
	}
	public void setCheckBoxHaveSpecial(CheckBox checkBoxHaveSpecial) {
		this.checkBoxHaveSpecial = checkBoxHaveSpecial;
	}
	public CheckBox getCheckBoxHaveChinese() {
		return checkBoxHaveChinese;
	}
	public void setCheckBoxHaveChinese(CheckBox checkBoxHaveChinese) {
		this.checkBoxHaveChinese = checkBoxHaveChinese;
	}
	public TextArea getTextAreaAccountPassword() {
		return textAreaAccountPassword;
	}
	public void setTextAreaAccountPassword(TextArea textAreaAccountPassword) {
		this.textAreaAccountPassword = textAreaAccountPassword;
	}
	public Button getButtonGenPassword() {
		return buttonGenPassword;
	}
	public void setButtonGenPassword(Button buttonGenPassword) {
		this.buttonGenPassword = buttonGenPassword;
	}
	public Button getButtonSave() {
		return buttonSave;
	}
	public void setButtonSave(Button buttonSave) {
		this.buttonSave = buttonSave;
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
  
  private static SysManagerEnity getManager() {
  	if (headerManager==null) {
  		SqlSession session = SqliteJDBCUtil.getCurrentSqlSession();
    	SysManagerMapper mapper = session.getMapper(SysManagerMapper.class);
    	SysManagerEnity manager = mapper.queryOne(LoginPool.getLogin(EditViewController.class));
    	headerManager = manager;
		}
  	return headerManager;
  }
	public int geteditNum() {
		return editNum;
	}
	
	public void initData(DataPasswordEnity data) {
		try {
			if (data!=null) {
				oldSalt = data.getSalt();
				this.headerData = data;
				this.getTextFieldAppName().setText(this.headerData.getAppName());
				this.getTextFieldAppUrl().setText(this.headerData.getAppWebUrl());
				this.getTextFieldAccountNickName().setText(this.headerData.getAccountNickName());
				String salt = EncryptEngine.decode(oldSalt.getBytes(), getSaltEncodeData(data), getManager().getSalt().getBytes());
				data.setSalt(salt);
				String accountUserName = EncryptEngine.decode(this.headerData.getAccountUserName().getBytes(), getEncodeData(data), salt.getBytes());
				String accountEmail = EncryptEngine.decode(this.headerData.getAccountEmail().getBytes(), getEncodeData(data), salt.getBytes());
				String accountPhoneNumber = EncryptEngine.decode(this.headerData.getAccountPhoneNumber().getBytes(), getEncodeData(data), salt.getBytes());
				String apssword = EncryptEngine.decode(this.headerData.getAccountPassword().getBytes(), getEncodeData(data), salt.getBytes());
				this.getTextFieldAccountUserName().setText(accountUserName);
				this.getTextFieldAccountEmail().setText(accountEmail);
				this.getTextFieldAccountPhoneNumber().setText(accountPhoneNumber);
				this.getTextAreaAccountPassword().setText(apssword);
			}
		} catch (Exception ex) {
	  	LoggerUtil.errorMsgI18n(getClass(), "system.log.error",ex.getMessage());
	  } 
	}
}
