package com.github.hmld.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.hmld.core.enity.DataPasswordEnity;

/**
 * 密码数据 mapper
 * @author hmld
 *
 */
@Mapper
public interface DataPasswordMapper {
  /**
   * 查询所有
   * @param dataPasswordEnity 参数
   * @return 结果集
   */
  public List<DataPasswordEnity> queryList(DataPasswordEnity dataPasswordEnity);
  /**
   * 查询一条
   * @param passwordPk 主键
   * @return 结果
   */
  public DataPasswordEnity queryOne(String passwordPk);
  /**
   * 添加一条
   * @param dataPasswordEnity 参数
   * @return 结果数
   */
  public int addOne(DataPasswordEnity dataPasswordEnity);
  /**
   * 更新一条
   * @param dataPasswordEnity 参数
   * @return 结果数
   */
  public int updateOne(DataPasswordEnity dataPasswordEnity);
  /**
   * 删除一条
   * @param dataPasswordEnity 参数
   * @return 结果数
   */
  public int delOne(DataPasswordEnity dataPasswordEnity);
}
