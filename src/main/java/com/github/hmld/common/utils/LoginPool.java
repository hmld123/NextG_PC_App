package com.github.hmld.common.utils;

import org.apache.ibatis.session.SqlSession;

import com.github.hmld.core.enity.SysManagerEnity;
import com.github.hmld.core.mapper.SysManagerMapper;
import com.github.hmld.view.passwordmanager.controller.AddViewController;

/**
 * 登录池 支支持一个账户
 * @author hmld
 *
 */
public class LoginPool {
	
	public static final ThreadLocal<String> loginThread = new ThreadLocal<String>();
	
	public static SysManagerEnity getLoginUser(final Class<?> clazz){
  		SqlSession session = SqliteJDBCUtil.getCurrentSqlSession();
    	SysManagerMapper mapper = session.getMapper(SysManagerMapper.class);
    	SysManagerEnity manager = mapper.queryOne(LoginPool.getLogin(AddViewController.class));
    	LoggerUtil.infoMsgI18n(clazz, "system.log.info","获取登录用户");
    	return manager;
	}
	
	public static String getLogin(final Class<?> clazz) {
		ThreadLocal<String> login = getLoginthread(null);
		if (!StringUtils.isEmpty(login.get())) {
			return login.get();
		}else {
			LoggerUtil.errorMsgI18n(clazz, "system.log.error","未获取到登录信息！");
			return null;
		}
	}
	
	public static void loginOut() {
		getLoginthread(null).set("");
	}
	
	public static void loginIn(final Class<?> clazz,String pk) {
		if (!StringUtils.isEmpty(pk) && StringUtils.isEmpty(getLoginthread(null).get())) {
			getLoginthread(pk);
		}else {
			LoggerUtil.errorMsgI18n(clazz, "system.log.error","登录信息不能为空！");
		}
	}
	
	public static ThreadLocal<String> getLoginthread(String pk) {
		String loginPk = loginThread.get();
		if (!StringUtils.isEmpty(pk)) {
			if (loginPk!=null&&!StringUtils.isEmpty(loginPk)) {
				LoggerUtil.errorMsgI18n(LoginPool.class, "system.log.error","已有用户登录！");
			}else {
				loginThread.set(pk);
			}
		}
		return loginThread;
	}
}
