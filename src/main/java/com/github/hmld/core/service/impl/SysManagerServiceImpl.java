package com.github.hmld.core.service.impl;

import org.apache.ibatis.session.SqlSession;

import com.github.hmld.common.utils.DateUtils;
import com.github.hmld.common.utils.SqliteJDBCUtil;
import com.github.hmld.core.enity.SysManagerEnity;
import com.github.hmld.core.mapper.SysManagerMapper;
import com.github.hmld.core.service.ISysManagerService;

public class SysManagerServiceImpl implements ISysManagerService {

  @Override
  public void regisUser(SysManagerEnity sysManagerEnity) {
    long creatDate = DateUtils.getNowDate().getTime();
    SqlSession session = SqliteJDBCUtil.getCurrentSqlSession();
    try {
      
      SysManagerMapper mapper = session.getMapper(SysManagerMapper.class);
      sysManagerEnity.setManagerUserPk("123");
      sysManagerEnity.setManagerUserName("a");
      sysManagerEnity.setManagerNickName("A");
      sysManagerEnity.setManagerPassword("1234856");
      sysManagerEnity.setSalt("12345678");
      sysManagerEnity.setDelFlg(0);
      sysManagerEnity.setCreatBy("sys");
      sysManagerEnity.setCreatTime(creatDate);
      mapper.addOne(sysManagerEnity);
      session.commit();
    } catch (Exception e) {
      session.rollback();
    } finally {
      session.close();
    }
  }

}
