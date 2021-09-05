package com.github.hmld.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.github.hmld.common.utils.LoggerUtil;
import com.github.hmld.common.utils.SqliteJDBCUtil;
import com.github.hmld.core.enity.DataPasswordEnity;
import com.github.hmld.core.mapper.DataPasswordMapper;
import com.github.hmld.core.service.IDataPasswordService;
/**
 * 密码管理 实现
 * @author hmld
 *
 */
public class DataPasswordServiceImpl implements IDataPasswordService {
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
	
}
