package com.github.hmld.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.github.hmld.common.core.emnu.DelFlgEmnu;
import com.github.hmld.common.core.emnu.UseFlgEmnu;
import com.github.hmld.common.utils.DateUtils;
import com.github.hmld.common.utils.LoggerUtil;
import com.github.hmld.common.utils.LoginPool;
import com.github.hmld.common.utils.SqliteJDBCUtil;
import com.github.hmld.common.utils.StringUtils;
import com.github.hmld.core.enity.DataPasswordEnity;
import com.github.hmld.core.enity.SysManagerEnity;
import com.github.hmld.core.mapper.DataPasswordMapper;
import com.github.hmld.core.mapper.SysManagerMapper;
import com.github.hmld.core.service.IDataPasswordService;
import com.github.hmld.view.passwordmanager.controller.EditViewController;
/**
 * 密码管理 实现
 * @author hmld
 *
 */
public class DataPasswordServiceImpl implements IDataPasswordService {
	private static SysManagerEnity headerManager;
	
	private static SysManagerEnity getManager() {
  	if (headerManager==null) {
  		SqlSession session = SqliteJDBCUtil.getCurrentSqlSession();
    	SysManagerMapper mapper = session.getMapper(SysManagerMapper.class);
    	SysManagerEnity manager = mapper.queryOne(LoginPool.getLogin(EditViewController.class));
    	headerManager = manager;
		}
  	return headerManager;
  }
	/**
	 * 查询总个数
	 */
	@Override
	public Integer queryCountNum(DataPasswordEnity enity) {
		try {
			SqlSession session = SqliteJDBCUtil.getCurrentSqlSession();
			DataPasswordMapper mapper = session.getMapper(DataPasswordMapper.class);
			return mapper.queryCountNum(enity);
		} catch (Exception e) {
			LoggerUtil.errorMsgI18n(getClass(), "system.log.error",e.getMessage());
			return 0;
		}
	}
	/**
	 * 查询数据
	 */
	@Override
	public List<DataPasswordEnity> queryEnityList(DataPasswordEnity enity) {
		List<DataPasswordEnity> list = new ArrayList<DataPasswordEnity>();
		try {
			SqlSession session = SqliteJDBCUtil.getCurrentSqlSession();
			DataPasswordMapper mapper = session.getMapper(DataPasswordMapper.class);
			list = mapper.queryList(enity);
		} catch (Exception e) {
			LoggerUtil.errorMsgI18n(getClass(), "system.log.error",e.getMessage());
		}
		return list;
	}
	
	/**
	 * 添加一条
	 */
	@Override
	public int addEnity(DataPasswordEnity enity) {
		int addNum = 0;
		if (this.retryEnity(enity)) {
			SqlSession session = SqliteJDBCUtil.getCurrentSqlSession();
			try {
				DataPasswordMapper mapper = session.getMapper(DataPasswordMapper.class);
				addNum = mapper.addOne(enity);
				session.commit();
			} catch (Exception ex) {
	      session.rollback();
	      LoggerUtil.errorMsgI18n(getClass(), "system.log.error",ex.getMessage());
			}
		}
		return addNum;
	}
	/**
	 * 修改一条
	 */
	@Override
	public int editEnity(DataPasswordEnity enity) {
		int addNum = 0;
		if (this.retryEnity(enity)) {
			SqlSession session = SqliteJDBCUtil.getCurrentSqlSession();
			try {
				DataPasswordMapper mapper = session.getMapper(DataPasswordMapper.class);
				addNum = mapper.updateOne(enity);
				session.commit();
			} catch (Exception ex) {
	      session.rollback();
	      LoggerUtil.errorMsgI18n(getClass(), "system.log.error",ex.getMessage());
			}
		}
		return addNum;
	}
	/**
	 * 修改一条
	 */
	@Override
	public int delEnity(DataPasswordEnity enity) {
		int addNum = 0;
		if (this.retryEnity(enity)) {
			SqlSession session = SqliteJDBCUtil.getCurrentSqlSession();
			try {
				DataPasswordMapper mapper = session.getMapper(DataPasswordMapper.class);
				enity.setUpdateBy(getManager().getManagerUserName());
				enity.setUpdateTime(DateUtils.getNowDate().getTime());
				addNum = mapper.delOne(enity);
				session.commit();
			} catch (Exception ex) {
	      session.rollback();
	      LoggerUtil.errorMsgI18n(getClass(), "system.log.error",ex.getMessage());
			}
		}
		return addNum;
	}
	
	private boolean retryEnity(DataPasswordEnity enity) {
		boolean isok = true;
		if (enity==null) {
			LoggerUtil.errorMsgI18n(getClass(), "system.log.error","保存的实体不能为空！");
			isok = false;
		}
		if (StringUtils.isEmpty(enity.getPasswordPk())) {
			LoggerUtil.errorMsgI18n(getClass(), "system.log.error","主键不能为空！");
			isok = false;
		}
		if (StringUtils.isEmpty(enity.getAncestors())) {
			enity.setAncestors("0");
		}
		if (enity.getGrade()==null) {
			enity.setGrade(0);
		}
		if (StringUtils.isEmpty(enity.getManagerUserPk())) {
			LoggerUtil.errorMsgI18n(getClass(), "system.log.error","管理用户主键不能为空不能为空！");
			isok = false;
		}
		if (StringUtils.isEmpty(enity.getManagerNickName())) {
			LoggerUtil.errorMsgI18n(getClass(), "system.log.error","管理用户昵称不能为空不能为空！");
			isok = false;
		}
		if (StringUtils.isEmpty(enity.getCreatBy())) {
			LoggerUtil.errorMsgI18n(getClass(), "system.log.error","创建人不能为空不能为空！");
			isok = false;
		}
		if (enity.getCreatTime()==null) {
			enity.setCreatTime(DateUtils.getNowDate().getTime());
		}
		if (enity.getDelFlg()==null) {
			enity.setDelFlg(DelFlgEmnu.USE_TYPE);
		}
		if (enity.getUseFlg()==null) {
			enity.setUseFlg(UseFlgEmnu.USE_TYPE);
		}
		return isok;
	}
	
}
