package com.github.hmld.common.core.enity.base;

import com.github.hmld.common.utils.LoginPool;
import com.github.hmld.core.enity.SysManagerEnity;
import com.github.hmld.view.passwordmanager.controller.AddViewController;

public class BaseController {
	protected static SysManagerEnity headerManager;
	
	protected static SysManagerEnity getManager() {
  	if (headerManager==null) {
    	headerManager = LoginPool.getLoginUser(AddViewController.class);
		}
  	return headerManager;
  }
}
