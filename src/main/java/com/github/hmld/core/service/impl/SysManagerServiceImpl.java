package com.github.hmld.core.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.hmld.common.core.emnu.DelFlgEmnu;
import com.github.hmld.common.core.emnu.SYSDEFAULT;
import com.github.hmld.common.utils.DateUtils;
import com.github.hmld.common.utils.EncryptEngine;
import com.github.hmld.common.utils.LoggerUtil;
import com.github.hmld.common.utils.SqliteJDBCUtil;
import com.github.hmld.common.utils.StringUtils;
import com.github.hmld.core.enity.SysManagerEnity;
import com.github.hmld.core.mapper.SysManagerMapper;
import com.github.hmld.core.service.ISysManagerService;

import javafx.scene.control.TextArea;
/**
 * 用户管理 实现
 * @author hmld
 *
 */
public class SysManagerServiceImpl implements ISysManagerService {
	/**
	 * 注册用户
	 */
  @Override
  public boolean regisUser(TextArea msgArea,SysManagerEnity sysManagerEnity) {
    long creatDate = DateUtils.getNowDate().getTime();
    SqlSession session = SqliteJDBCUtil.getCurrentSqlSession();
    try {
    	// 需要加密的内容
    	String userPassWord = sysManagerEnity.getManagerPassword();
      // 获取盐
    	String salt = StringUtils.getSalt();
      // 获取加密用密码
      sysManagerEnity.setSalt(salt);//盐
    	sysManagerEnity.setManagerUserPk(UUID.randomUUID().toString());
			sysManagerEnity.setManagerPassword(EncryptEngine.encode(userPassWord.getBytes(), getEncodeData(sysManagerEnity), salt.getBytes()));
      sysManagerEnity.setDelFlg(DelFlgEmnu.USE_TYPE);
      sysManagerEnity.setCreatBy(SYSDEFAULT.DEF_USER);
      sysManagerEnity.setCreatTime(creatDate);
    	SysManagerMapper mapper = session.getMapper(SysManagerMapper.class);
      mapper.addOne(sysManagerEnity);
      session.commit();
      msgArea.setText(LoggerUtil.infoMsgI18n(getClass(), "regis.msg","{" + sysManagerEnity.getManagerUserName() + "}注册成功！"));
    	return true;
    } catch (Exception e) {
      session.rollback();
      msgArea.setText(LoggerUtil.errorMsgI18n(getClass(), "system.log.error",e.getMessage()));
      return false;
    } 
  }
  
  /**
   * 登录校验
   */
	@Override
	public boolean loginUser(TextArea msgArea,String userName, String passWord) {
		if (userName==null || userName.equals("")) {
			msgArea.setText(LoggerUtil.warnMsgI18n(getClass(), "login.msg", "用户名不能为空！"));
			return false;
		}
		if (passWord==null || passWord.equals("")) {
			msgArea.setText(LoggerUtil.warnMsgI18n(getClass(), "login.msg", "密码不能为空！"));
			return false;
		}
		SqlSession session = SqliteJDBCUtil.getCurrentSqlSession();
		try {
			SysManagerMapper mapper = session.getMapper(SysManagerMapper.class);
			SysManagerEnity manager = mapper.queryOneByUserName(userName);
			if (manager==null ) {
				msgArea.setText(LoggerUtil.warnMsgI18n(getClass(), "login.msg", "{" + userName + "}未注册！"));
	    	return false;
			}
			if (EncryptEngine.decode(manager.getManagerPassword().getBytes(), getEncodeData(manager), manager.getSalt().getBytes()).equals(passWord)) {
				msgArea.setText(LoggerUtil.warnMsgI18n(getClass(), "login.msg","{" + userName + "}登录成功！"));
	    	return true;
			}
			else {
				msgArea.setText(LoggerUtil.warnMsgI18n(getClass(), "login.msg","{" + userName + "}登录失败！"));
	    	return false;
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			msgArea.setText(LoggerUtil.errorMsgI18n(getClass(), "system.log.error",e.getMessage()));
      return false;
		} catch (Exception e) {
			e.printStackTrace();
			msgArea.setText(LoggerUtil.errorMsgI18n(getClass(), "system.log.error",e.getMessage()));
      return false;
		}
	}
	
	/**
	 * 构建加密密码
	 * @param manager
	 * @return
	 */
  private static Object getEncodeData(SysManagerEnity manager) {
  	Map<String, String> data = new HashMap<String, String>();
    data.put("userName", manager.getManagerUserName());
    data.put("salt", manager.getSalt());
		return data;
  }
}
