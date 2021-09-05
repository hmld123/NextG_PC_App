package com.github.hmld.core.service;
/**
 * 密码管理 接口
 * @author hmld
 *
 */

import java.util.List;

import com.github.hmld.core.enity.DataPasswordEnity;

public interface IDataPasswordService {
	/**
	 * 查询数据
	 * @param enity
	 * @return
	 */
	public List<DataPasswordEnity> queryEnityList(DataPasswordEnity enity);
}
