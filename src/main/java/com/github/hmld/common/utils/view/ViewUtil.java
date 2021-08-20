package com.github.hmld.common.utils.view;

import java.io.IOException;
import java.util.ResourceBundle;

import com.github.hmld.common.utils.LoggerUtil;
import com.github.hmld.common.utils.MsageUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
/**
 * 页面工具类
 * @author hmld
 *
 */
public class ViewUtil {
	/**
	 * 页面跳转方法
	 * @param msgArea 页面上的 log显示组件
	 * @param clazz
	 * @param oldStage
	 * @param viewUrl 页面url
	 */
	public static void goToStage(TextArea msgArea,final Class<?> clazz,Stage oldStage ,String viewUrl) {
		try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(loader.getClassLoader().getResource(viewUrl));
      loader.setResources(ResourceBundle.getBundle(MsageUtils.getPropertiesUrl().replaceAll(".properties", "")));
      Scene scene = new Scene(loader.load());
      oldStage.hide();
      oldStage.setScene(scene);
      oldStage.show();
      LoggerUtil.infoMsgI18n(clazz, "system.jump.view",viewUrl);
    } catch (IOException e1) {
      e1.printStackTrace();
      msgArea.setText(LoggerUtil.errorMsgI18n(clazz, "system.log.error",e1.getMessage()));
    }
	}
}
