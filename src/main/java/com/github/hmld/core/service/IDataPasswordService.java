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
	/**
	 * 添加一条
	 * @param enity
	 * @return 
	 */
	public int addEnity(DataPasswordEnity enity);
	/**
	 * 修改一条
	 * @param enity
	 * @return
	 */
	public int editEnity(DataPasswordEnity enity);
	/**
	 * 删除一条
	 * @param enity
	 * @return
	 */
	public int delEnity(DataPasswordEnity enity);
}
