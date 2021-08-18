package com.github.hmld.common.core.enity.base;

public class BaseEnity {
  /** 删除状态*/
  private Integer delFlg;
  /** 创建人*/
  private String creatBy;
  /** 创建时间*/
  private Long creatTime;
  /** 更新人*/
  private String updateBy;
  /** 更新时间*/
  private Long updateTime;
  /**
   * 获取删除状态
   * @return 删除状态
   */
  public Integer getDelFlg() {
    return delFlg;
  }
  /**
   * 设置删除状态
   * @param delFlg
   */
  public void setDelFlg(Integer delFlg) {
    this.delFlg = delFlg;
  }
  /**
   * 获取创建人
   * @return 创建人
   */
  public String getCreatBy() {
    return creatBy;
  }
  /**
   * 设置创建人
   * @param creatBy
   */
  public void setCreatBy(String creatBy) {
    this.creatBy = creatBy;
  }
  public Long getCreatTime() {
    return creatTime;
  }
  public void setCreatTime(Long creatTime) {
    this.creatTime = creatTime;
  }
  /**
   * 获取更新人
   * @return 更新人
   */
  public String getUpdateBy() {
    return updateBy;
  }
  /**
   * 设置更新人
   * @param updateBy
   */
  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }
  
  public Long getUpdateTime() {
    return updateTime;
  }
  public void setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
  }
  @Override
  public String toString() {
    return "BaseEnity [delFlg=" + delFlg + ", creatBy=" + creatBy + ", creatTime=" + creatTime + ", updateBy="
        + updateBy + ", updateTime=" + updateTime + "]";
  }
}
