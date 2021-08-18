package com.github.hmld.core.enity;

import com.github.hmld.common.core.enity.base.BaseEnity;
/**
 * 密码所属用户
 * @author hmld
 *
 */
public class SysManagerEnity extends BaseEnity{
  /** 所属用户主键*/
  private String managerUserPk;
  /** 所属用户名*/
  private String managerUserName;
  /** 所属用户昵称*/
  private String managerNickName;
  /** 注册密码*/
  private String managerPassword;
  /** 盐*/
  private String salt;
  /**
   * 获取所属用户主键
   * @return
   */
  public String getManagerUserPk() {
    return managerUserPk;
  }
  /**
   * 设置所属用户主键
   * @param managerUserPk
   */
  public void setManagerUserPk(String managerUserPk) {
    this.managerUserPk = managerUserPk;
  }
  /**
   * 获取所属用户昵称
   * @return 所属用户昵称
   */
  public String getManagerNickName() {
    return managerNickName;
  }
  /**
   * 设置所属用户昵称
   * @param managerNickName
   */
  public void setManagerNickName(String managerNickName) {
    this.managerNickName = managerNickName;
  }
  /**
   * 获取注册密码
   * @return 注册密码
   */
  public String getManagerPassword() {
    return managerPassword;
  }
  /**
   * 设置注册密码
   * @param managerPassword
   */
  public void setManagerPassword(String managerPassword) {
    this.managerPassword = managerPassword;
  }
  /**
   * 获取盐
   * @return 盐
   */
  public String getSalt() {
    return salt;
  }
  /**
   * 设置盐
   * @param salt
   */
  public void setSalt(String salt) {
    this.salt = salt;
  }
  /**
   * 获取用户名
   * @return 用户名
   */
  public String getManagerUserName() {
    return managerUserName;
  }
  /**
   * 设置用户名
   * @param managerUserName
   */
  public void setManagerUserName(String managerUserName) {
    this.managerUserName = managerUserName;
  }
  @Override
  public String toString() {
    return "SysManagerEnity [managerUserPk=" + managerUserPk + ", managerNickName=" + managerNickName
        + ", managerPassword=" + managerPassword + ", salt=" + salt + "]";
  }
}
