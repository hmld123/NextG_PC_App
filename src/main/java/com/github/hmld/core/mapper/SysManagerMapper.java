package com.github.hmld.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.hmld.core.enity.SysManagerEnity;

/**
 * 用户表 mapper
 * @author hmld
 *
 */
@Mapper
public interface SysManagerMapper {
  public List<SysManagerEnity> queryList(SysManagerEnity sysManagerEnity);
}
