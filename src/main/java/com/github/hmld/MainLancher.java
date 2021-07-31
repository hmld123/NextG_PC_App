package com.github.hmld;
import com.github.hmld.common.utils.LoggerUtil;
import com.github.hmld.view.MainView;

/**
 * 启动类
 * @author hmld
 *
 */
public class MainLancher {
  public static void main(String[] args) {
    LoggerUtil.infoMsgI18n(MainLancher.class, "sys.version",1);
    MainView.launch(MainView.class, args);
  }
}
