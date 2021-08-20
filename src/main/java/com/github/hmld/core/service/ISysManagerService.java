package com.github.hmld.core.service;

import com.github.hmld.core.enity.SysManagerEnity;

import javafx.scene.control.TextArea;
/**
 * 用户管理 接口
 * @author hmld
 *
 */
public interface ISysManagerService {
	/**
	 * 注册用户
	 * @param sysManagerEnity 用户参数
	 * @return 注册结果
	 */
  public boolean regisUser(TextArea msgArea,SysManagerEnity sysManagerEnity);
  /**
   * 登录校验
   * @param userName 用户名
   * @param passWord 密码
   * @return 校验结果
   */
  public boolean loginUser(TextArea msgArea,String userName,String passWord);
}
