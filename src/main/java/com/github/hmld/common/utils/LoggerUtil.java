package com.github.hmld.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Log 工具类
 * @author hmld
 *
 */
public class LoggerUtil {
  /** logger池 */
  private static Map<String, Logger> logPool = null;
  /**
   * 获取 logManager 
   * @param clazz 类
   * @return Logger
   */
  public static Logger getManager(final Class<?> clazz) {
    if (logPool==null) {
      logPool = new HashMap<String, Logger>();
    }
    Logger thLog = logPool.get(clazz.getName());
    if (thLog==null) {
      thLog = LogManager.getLogger(clazz);
      logPool.put(clazz.getName(), thLog);
      return thLog;
    }
    return thLog;
  }
  /**
   * 自定义info 级别日志处理
   * @param clazz
   * @param message 日志信息
   */
  public static String infoMsg(final Class<?> clazz,String message) {
    LoggerUtil.getManager(clazz).info(message);
    return message;
  }
  /**
   * 自定义info 级别日志处理(带参数)
   * @param clazz
   * @param message 日志信息
   * @param args 参数
   */
  public static String infoMsg(final Class<?> clazz,String message,Object... args) {
    LoggerUtil.getManager(clazz).info(message,args);
    return StringUtils.format(message,args);
  }
  /**
   * 自定义info 级别日志处理，多语支持
   * @param clazz
   * @param key 多语资源键值
   */
  public static String infoMsgI18n(final Class<?> clazz,String key) {
  	String msg = MsageUtils.getMsg(key);
    LoggerUtil.getManager(clazz).info(msg);
    return msg;
  }
  /**
   * 自定义info 级别日志处理，多语支持(带参数)
   * @param clazz
   * @param key 多语资源键值
   * @param args 参数
   */
  public static String infoMsgI18n(final Class<?> clazz,String key,Object... args) {
  	String msg = MsageUtils.getMsg(key,args);
    LoggerUtil.getManager(clazz).info(msg);
    return msg;
  }
  /**
   * 自定义 warn 级别日志处理
   * @param clazz
   * @param message 日志信息
   */
  public static String warnMsg(final Class<?> clazz,String message) {
    LoggerUtil.getManager(clazz).warn(message);
    return message;
  }
  /**
   * 自定义 warn 级别日志处理(带参数)
   * @param clazz
   * @param message 日志信息
   * @param args 参数
   */
  public static String warnMsg(final Class<?> clazz,String message,Object... args) {
    LoggerUtil.getManager(clazz).warn(message,args);
    return StringUtils.format(message,args);
  }
  /**
   * 自定义 warn 级别日志处理，多语支持
   * @param clazz
   * @param key 多语资源键值
   */
  public static String warnMsgI18n(final Class<?> clazz,String key) {
  	String msg = MsageUtils.getMsg(key);
    LoggerUtil.getManager(clazz).warn(msg);
    return msg;
  }
  /**
   * 自定义 warn 级别日志处理，多语支持(带参数)
   * @param clazz
   * @param key 多语资源键值
   * @param args 参数
   */
  public static String warnMsgI18n(final Class<?> clazz,String key,Object... args) {
  	String msg = MsageUtils.getMsg(key,args);
    LoggerUtil.getManager(clazz).warn(msg);
    return msg;
  }
  /**
   * 自定义 debug 级别日志处理
   * @param clazz
   * @param message 日志信息
   */
  public static String debugMsg(final Class<?> clazz,String message) {
    LoggerUtil.getManager(clazz).debug(message);
    return message;
  }
  /**
   * 自定义 debug 级别日志处理(带参数)
   * @param clazz
   * @param message 日志信息
   * @param args 参数
   */
  public static String debugMsg(final Class<?> clazz,String message,Object... args) {
    LoggerUtil.getManager(clazz).debug(message,args);
    return StringUtils.format(message,args);
  }
  /**
   * 自定义 debug 级别日志处理，多语支持
   * @param clazz
   * @param key 多语资源键值
   */
  public static String debugMsgI18n(final Class<?> clazz,String key) {
  	String msg = MsageUtils.getMsg(key);
    LoggerUtil.getManager(clazz).debug(msg);
    return msg;
  }
  /**
   * 自定义 debug 级别日志处理，多语支持(带参数)
   * @param clazz
   * @param key 多语资源键值
   * @param args 参数
   */
  public static String debugMsgI18n(final Class<?> clazz,String key,Object... args) {
  	String msg = MsageUtils.getMsg(key,args);
    LoggerUtil.getManager(clazz).debug(msg);
    return msg;
  }
  /**
   * 自定义 error 级别日志处理
   * @param clazz
   * @param message 日志信息
   */
  public static String errorMsg(final Class<?> clazz,String message) {
    LoggerUtil.getManager(clazz).error(message);
    return message;
  }
  /**
   * 自定义 error 级别日志处理(带参数)
   * @param clazz
   * @param message 日志信息
   * @param args 参数
   */
  public static String errorMsg(final Class<?> clazz,String message,Object... args) {
    LoggerUtil.getManager(clazz).error(message,args);
    return StringUtils.format(message,args);
  }
  /**
   * 自定义 error 级别日志处理，多语支持
   * @param clazz
   * @param key 多语资源键值
   */
  public static String errorMsgI18n(final Class<?> clazz,String key) {
  	String msg = MsageUtils.getMsg(key);
    LoggerUtil.getManager(clazz).error(msg);
    return msg;
  }
  /**
   * 自定义 error 级别日志处理，多语支持(带参数)
   * @param clazz
   * @param key 多语资源键值
   * @param args 参数
   */
  public static String errorMsgI18n(final Class<?> clazz,String key,Object... args) {
  	String msg = MsageUtils.getMsg(key,args);
    LoggerUtil.getManager(clazz).error(msg);
    return msg;
  }
}
