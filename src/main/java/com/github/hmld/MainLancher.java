package com.github.hmld;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.github.hmld.common.utils.LoggerUtil;
import com.github.hmld.common.utils.SqliteJDBCUtil;
import com.github.hmld.core.enity.SysManagerEnity;
import com.github.hmld.core.mapper.SysManagerMapper;
import com.github.hmld.view.MainView;

/**
 * 启动类
 * @author hmld
 *
 */
public class MainLancher {
  public static void main(String[] args) {
     SqlSession session = SqliteJDBCUtil.getCurrentSqlSession();
     SysManagerMapper mapper = session.getMapper(SysManagerMapper.class);
     List<SysManagerEnity> list = mapper.queryList(new SysManagerEnity());
     for (SysManagerEnity sysManagerEnity : list) {
      System.out.println(sysManagerEnity);
    }
     SqliteJDBCUtil.closeCurrentSession();
    MainView.launch(MainView.class, args);
    
  }
}
