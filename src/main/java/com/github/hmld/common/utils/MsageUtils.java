package com.github.hmld.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;

import com.github.hmld.common.core.emnu.SysDefaultParam;
import com.github.hmld.common.core.text.CharsetKit;
/**
 * 多语言支持工具
 * @author hmld
 *
 */
public class MsageUtils {
  /** 多语资源配置*/
  private static Properties msgProperties = null;
  private static String propertiesUrl = "";
  /**
   * 获取多语资源值
   * @param key 多语资源键值
   * @return 多语资源值
   */
  public static String getMsg(String key) {
    Properties msgPro = loadAddressMsage(MsageUtils.class);
    return msgPro.getProperty(key);
  }
  /**
   * 获取多语资源值(多参数格式化)
   * @param key 多语资源键值
   * @param args 参数
   * @return 多语资源值
   */
  public static String getMsg(String key,Object... args) {
    Properties msgPro = loadAddressMsage(MsageUtils.class);
    try {
      return String.format(msgPro.getProperty(key), args);
    } catch (Exception e) {
      StringBuilder parameters = new StringBuilder();
      parameters.append("[");
      for (int i = 0; i < args.length; i++) {
        if (i==args.length-1) {
          parameters.append(args[i].toString());
        }else {
          parameters.append(args[i].toString()+",");
        }
      }
      parameters.append("]");
      String msg = "The parameters in the language resource do not match. key:"+key+",value:"+msgPro.getProperty(key)+",parameters:"+parameters.toString();
      LoggerUtil.errorMsg(MsageUtils.class, msg);
      return msg;
    }
  }
  /**
   * 加载多语资源文件
   * @param clazz
   * @return 多语资源配置
   */
  private static Properties loadAddressMsage(final Class<?> clazz) {
    try {
      if (msgProperties==null) {
        String msagefilepath = SysDefaultParam.MSG_FILE_FLODER+"/"+SysDefaultParam.MSG_FILE_START_NAME+"_"+Locale.getDefault()+"."+SysDefaultParam.MSG_FILE_SUFFIX;
        String filepath = MsageUtils.class.getClassLoader().getResource(msagefilepath).getPath();
        File msgFile = new File(filepath);
        if (!msgFile.exists()) {
          msagefilepath = SysDefaultParam.MSG_FILE_FLODER+"/"+SysDefaultParam.MSG_FILE_START_NAME+"."+SysDefaultParam.MSG_FILE_SUFFIX;
          msgFile = new File(filepath);
        }
        propertiesUrl = msagefilepath;
        InputStream in = new FileInputStream(msgFile);
        InputStreamReader reader = new InputStreamReader(in,CharsetKit.CHARSET_UTF_8);
        msgProperties = new Properties();
        msgProperties.load(reader);
      }
      return msgProperties;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      LoggerUtil.getManager(MsageUtils.class).error(e.getMessage());
      return new Properties();
    } catch (IOException e) {
      e.printStackTrace();
      LoggerUtil.getManager(MsageUtils.class).error(e.getMessage());
      return new Properties();
    }
  }
  
  /**
   * 获得多语资源配置
   * @return
   */
	public static Properties getMsgProperties() {
		loadAddressMsage(MsageUtils.class);
		return msgProperties;
	}
	/**
	 * 获得多语资源配置
	 * @return
	 */
	public static String getPropertiesUrl() {
		loadAddressMsage(MsageUtils.class);
		return propertiesUrl;
	}
}
