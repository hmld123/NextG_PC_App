package com.github.hmld.common.core.emnu;
/**
 * 使用状态枚举
 * @author hmld
 *
 */
public enum UseFlgEmnu {
  /** 删除状态*/
  DEL_TYPE(1),
  /** 使用状态*/
  USE_TYPE(0);
  
  private Integer value;
  
  public Integer getValue() {
    return value;
  }
  
  public void setValue(Integer value) {
    this.value = value;
  }
  
  private UseFlgEmnu(Integer value) {
    this.value = value;
  }
}
