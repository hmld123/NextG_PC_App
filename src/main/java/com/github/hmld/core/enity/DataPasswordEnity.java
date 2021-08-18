package com.github.hmld.core.enity;

import com.github.hmld.common.core.enity.base.BaseEnity;
/**
 * 密码数据
 * @author hmld
 * 
 */
public class DataPasswordEnity extends BaseEnity{
  /** 主键*/
  private String passwordPk;
  /** 父节点主键*/
  private String parentPk;
  /** 祖籍列表*/
  private String ancestors;
  /** 级次*/
  private Integer grade;
  /** 所属用户主键*/
  private String managerUserPk;
  /** 所属用户名称*/
  private String managerNickName;
  /** 应用名*/
  private String appName;
  /** 应用网站*/
  private String appWebUrl;
  /** 注册用户名*/
  private String accountUserName;
  /** 注册用户昵称*/
  private String accountNickName;
  /** 注册用户Email*/
  private String accountEmail;
  /** 注册用户手机号*/
  private String accountPhoneNumber;  
  /** 注册密码*/
  private String accountPassword;
  /** 盐*/
  private String salt;
  /** 密码使用状态*/
  private Integer useFlg;
  /**
   * 获取主键
   * @return 主键
   */
  public String getPasswordPk() {
    return passwordPk;
  }
  /**
   * 设置主键
   * @param passwordPk
   */
  public void setPasswordPk(String passwordPk) {
    this.passwordPk = passwordPk;
  }
  /**
   * 获取父节点主键
   * @return 父节点主键
   */
  public String getParentPk() {
    return parentPk;
  }
  /**
   * 设置父节点主键
   * @param parentPk
   */
  public void setParentPk(String parentPk) {
    this.parentPk = parentPk;
  }
  /**
   * 获取祖籍列表
   * @return 祖籍列表
   */
  public String getAncestors() {
    return ancestors;
  }
  /**
   * 设置祖籍列表
   * @param ancestors
   */
  public void setAncestors(String ancestors) {
    this.ancestors = ancestors;
  }
  /**
   * 获取级次
   * @return 级次
   */
  public Integer getGrade() {
    return grade;
  }
  /**
   * 设置级次
   * @param grade
   */
  public void setGrade(Integer grade) {
    this.grade = grade;
  }
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
   * 获取所属用户名称
   * @return 所属用户名称
   */
  public String getManagerNickName() {
    return managerNickName;
  }
  /**
   * 设置所属用户名称
   * @param managerNickName
   */
  public void setManagerNickName(String managerNickName) {
    this.managerNickName = managerNickName;
  }
  /**
   * 获取应用名
   * @return 应用名
   */
  public String getAppName() {
    return appName;
  }
  /**
   * 设置应用名
   * @param appName
   */
  public void setAppName(String appName) {
    this.appName = appName;
  }
  /**
   * 获取应用网站
   * @return
   */
  public String getAppWebUrl() {
    return appWebUrl;
  }
  /**
   * 设置应用网站
   * @param appWebUrl
   */
  public void setAppWebUrl(String appWebUrl) {
    this.appWebUrl = appWebUrl;
  }
  /**
   * 获取注册用户名
   * @return 注册用户名
   */
  public String getAccountUserName() {
    return accountUserName;
  }
  /**
   * 设置注册用户名
   * @param accountUserName
   */
  public void setAccountUserName(String accountUserName) {
    this.accountUserName = accountUserName;
  }
  /**
   * 获取注册用户昵称
   * @return 注册用户昵称
   */
  public String getAccountNickName() {
    return accountNickName;
  }
  /**
   * 设置注册用户昵称
   * @param accountNickName
   */
  public void setAccountNickName(String accountNickName) {
    this.accountNickName = accountNickName;
  }
  /**
   * 获取注册用户Email
   * @return 注册用户Email
   */
  public String getAccountEmail() {
    return accountEmail;
  }
  /**
   * 设置注册用户Email
   * @param accountEmail
   */
  public void setAccountEmail(String accountEmail) {
    this.accountEmail = accountEmail;
  }
  /**
   * 获取注册用户手机号
   * @return 注册用户手机号
   */
  public String getAccountPhoneNumber() {
    return accountPhoneNumber;
  }
  /**
   * 设置注册用户手机号
   * @param accountPhoneNumber
   */
  public void setAccountPhoneNumber(String accountPhoneNumber) {
    this.accountPhoneNumber = accountPhoneNumber;
  }
  /**
   * 获取注册密码
   * @return 注册密码
   */
  public String getAccountPassword() {
    return accountPassword;
  }
  /**
   * 设置注册密码
   * @param accountPassword
   */
  public void setAccountPassword(String accountPassword) {
    this.accountPassword = accountPassword;
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
   * 获取密码使用状态
   * @return 密码使用状态
   */
  public Integer getUseFlg() {
    return useFlg;
  }
  /**
   * 设置密码使用状态
   * @param useFlg
   */
  public void setUseFlg(Integer useFlg) {
    this.useFlg = useFlg;
  }
  @Override
  public String toString() {
    return "DataPasswordEnity [passwordPk=" + passwordPk + ", parentPk=" + parentPk + ", ancestors=" + ancestors
        + ", grade=" + grade + ", managerUserPk=" + managerUserPk + ", managerNickName=" + managerNickName
        + ", appName=" + appName + ", appWebUrl=" + appWebUrl + ", accountUserName=" + accountUserName
        + ", accountNickName=" + accountNickName + ", accountEmail=" + accountEmail + ", accountPhoneNumber="
        + accountPhoneNumber + ", accountPassword=" + accountPassword + ", salt=" + salt + ", useFlg=" + useFlg + "]";
  }
}
