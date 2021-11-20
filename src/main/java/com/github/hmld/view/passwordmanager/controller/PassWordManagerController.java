package com.github.hmld.view.passwordmanager.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.github.hmld.common.core.emnu.UseFlgEmnu;
import com.github.hmld.common.core.enity.base.BaseController;
import com.github.hmld.common.utils.EncryptEngine;
import com.github.hmld.common.utils.MsageUtils;
import com.github.hmld.common.utils.view.ViewUtil;
import com.github.hmld.core.enity.DataPasswordEnity;
import com.github.hmld.core.service.IDataPasswordService;
import com.github.hmld.core.service.impl.DataPasswordServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
/**
 * 密码管理功能Controller
 * @author hmld
 *
 */
public class PassWordManagerController extends BaseController implements Initializable {
	private IDataPasswordService dataSerivce = new DataPasswordServiceImpl();
	private Integer pageSize = 50;
	private Integer pageCount = 0;
	private Integer pageIndex = 0;
	private DataPasswordEnity serchEmpty = new DataPasswordEnity();
	private IDataPasswordService passwordService = new DataPasswordServiceImpl();
	private ObservableList<DataPasswordEnity> list = FXCollections.observableArrayList();
	@FXML
	private Pagination pagebar;
	@FXML 
	private Button buttonAdd;
	@FXML
	private Button buttonEdit;
	@FXML
	private Button buttonDel;
	@FXML
	private Button buttonQuery;
	@FXML
	private TextField textFieldSerchApp;
	@FXML
	private TextField textFieldSerchUrl;
	@FXML
	private TextField textFieldSerchNickName;
	@FXML
	private TableView<DataPasswordEnity> pmDataTable;
	@FXML
	private TableColumn<DataPasswordEnity, String> colAppName; // 应用名
	@FXML
	private TableColumn<DataPasswordEnity, String> colAppWebUrl;// 应用网站
	@FXML
	private TableColumn<DataPasswordEnity, String> colAccountNickName;// 注册昵称
	@FXML
	private TableColumn<DataPasswordEnity, Integer> colUseFlg;// 使用状态
	@FXML
	private TableColumn<DataPasswordEnity, String> colActions;
	
	public void buttonAddAction(ActionEvent e) {
		Window win = ((Node)e.getSource()).getScene().getWindow();
		Map<String, Object> viewData = ViewUtil.openToStage(getClass(), "view/passwordmanager/addView/index.fxml");
		Scene scene = (Scene)viewData.get("scene");
		AddViewController accController = (AddViewController)viewData.get("controller");
		if (scene!=null) {
			Stage addStage = new Stage();
			addStage.initOwner(win);
			addStage.initModality(Modality.WINDOW_MODAL);
			addStage.setResizable(false);
			addStage.setScene(scene);
			addStage.setTitle(MsageUtils.getMsg("pwmanager.addview.title"));
			addStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					if (accController.getAddNum()>0) {
						doSearch();
					}
				}
			});
			addStage.show();
		}
	}
	
	public void buttonEditAction(ActionEvent e) {
		ObservableList<DataPasswordEnity> editEnitys = pmDataTable.getSelectionModel().getSelectedItems();
		if (editEnitys.size()>=1) {
			DataPasswordEnity editEnity = editEnitys.get(0);
			String oldSalt = editEnity.getSalt();
			Window win = ((Node)e.getSource()).getScene().getWindow();
			Map<String, Object> viewData = ViewUtil.openToStage(getClass(), "view/passwordmanager/editView/index.fxml");
			Scene scene = (Scene)viewData.get("scene");
			EditViewController editController = (EditViewController)viewData.get("controller");
			editController.initData(editEnity);
			if (scene!=null) {
				Stage editStage = new Stage();
				editStage.initOwner(win);
				editStage.initModality(Modality.WINDOW_MODAL);
				editStage.setResizable(false);
				editStage.setScene(scene);
				editStage.setTitle(MsageUtils.getMsg("pwmanager.editview.title"));
				editStage.show();
				editStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						if (editController.geteditNum()>0) {
							doSearch();
						}else {
							editEnity.setSalt(oldSalt);
						}
					}
				});
			}
		}
	}
	
	
	public void buttonDelAction(ActionEvent e) {
		ObservableList<DataPasswordEnity> delEnitys = pmDataTable.getSelectionModel().getSelectedItems();
		if (delEnitys.size()>0) {
			for (DataPasswordEnity delEmpty : delEnitys) {
				dataSerivce.delEnity(delEmpty);
			}
			doSearch();
		}
	}
	
	
	public void buttonQueryAction(ActionEvent e) {
		String app = textFieldSerchApp.getText();
		String url = textFieldSerchUrl.getText();
		String nickName = textFieldSerchNickName.getText();
		this.serchEmpty.setAppName(app);
		this.serchEmpty.setAppWebUrl(url);
		this.serchEmpty.setAccountNickName(nickName);
		this.doSearch();
		this.serchEmpty.setAppName(null);
		this.serchEmpty.setAppWebUrl(null);
		this.serchEmpty.setAccountNickName(null);
	}
	
	private void doSearch() {
		list.clear();
		Integer countNum = passwordService.queryCountNum(serchEmpty);
		if (countNum.equals(0)) {
			countNum = 1;
		}
		pageCount = countNum/pageSize;
		if (countNum%pageSize!=0) {
			pageCount++;
		}
		pagebar.setPageCount(pageCount);
		pagebar.setMaxPageIndicatorCount(10);
		pagebar.setCurrentPageIndex(pageIndex);
		serchEmpty.setPageSize(pageSize);
		serchEmpty.setPageIndex(pageIndex);
		list.addAll(passwordService.queryEnityList(serchEmpty));
		pmDataTable.setItems(list);
		pmDataTable.refresh();
	}

	/**
	 * 构建加密密码
	 * @param manager
	 * @return
	 */
  private static Object getSaltEncodeData(DataPasswordEnity enity) {
  	Map<String, String> data = new HashMap<String, String>();
    data.put("managerUserName", getManager().getManagerUserName());
    data.put("managerSalt", getManager().getSalt());
    data.put("enity", enity.getPasswordPk());
		return data;
  }
  
	/**
	 * 构建加密密码
	 * @param manager
	 * @return
	 */
  private static Object getEncodeData(DataPasswordEnity enity) {
  	Map<String, String> data = new HashMap<String, String>();
    data.put("managerUserName", getManager().getManagerUserName());
    data.put("managerSalt", getManager().getSalt());
    data.put("salt", enity.getSalt());
		return data;
  }
  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		textFieldSerchApp.setPromptText(MsageUtils.getMsg("pwmanager.mainview.tablecol.app_name"));
		textFieldSerchUrl.setPromptText(MsageUtils.getMsg("pwmanager.mainview.tablecol.app_name"));
		textFieldSerchNickName.setPromptText(MsageUtils.getMsg("pwmanager.mainview.tablecol.account_nickname"));
		colAppName.setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, String>("appName"));
		colAppName.setCellFactory(TextFieldTableCell.forTableColumn());
		colAppWebUrl.setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, String>("appWebUrl"));
		colAppWebUrl.setCellFactory(TextFieldTableCell.forTableColumn());
		colAccountNickName.setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, String>("accountNickName"));
		colAccountNickName.setCellFactory(TextFieldTableCell.forTableColumn());
		colUseFlg.setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, Integer>("useFlg"));
		colUseFlg.setCellFactory(new Callback<TableColumn<DataPasswordEnity,Integer>, TableCell<DataPasswordEnity,Integer>>() {
			@Override
			public TableCell<DataPasswordEnity, Integer> call(TableColumn<DataPasswordEnity, Integer> param) {
				TableCell<DataPasswordEnity, Integer> cell = new TableCell<DataPasswordEnity, Integer>(){
					@Override
					protected void updateItem(Integer item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null || empty==false) {
							AnchorPane actionPane = new AnchorPane();
							Label useLable = new Label();
							if (item.equals(UseFlgEmnu.USE_TYPE)) {
								useLable.setText("使用中");
								actionPane.getChildren().add(useLable);
							}
							if (item.equals(UseFlgEmnu.DEL_TYPE)) {
								useLable.setText("已删除");
								actionPane.getChildren().add(useLable);
							}
							this.setGraphic(actionPane);
						}
					}
				};
				return cell;
			}
		});
		colActions.setCellFactory(new Callback<TableColumn<DataPasswordEnity,String>, TableCell<DataPasswordEnity,String>>() {
			@Override
			public TableCell<DataPasswordEnity, String> call(TableColumn<DataPasswordEnity, String> param) {
				 TableCell<DataPasswordEnity, String> cell = new TableCell<DataPasswordEnity, String>(){
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null || empty==false) {
							AnchorPane actionPane = new AnchorPane();
							Button copyButton = new Button("复制密码");
							copyButton.setLayoutX(0);
							copyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent event) {
									try {
										DataPasswordEnity editEnity = pmDataTable.getItems().get(getIndex());
										String oldSalt = editEnity.getSalt();
										String salt = EncryptEngine.decode(oldSalt.getBytes(), getSaltEncodeData(editEnity), getManager().getSalt().getBytes());
										editEnity.setSalt(salt);
										String apssword = EncryptEngine.decode(editEnity.getAccountPassword().getBytes(), getEncodeData(editEnity), salt.getBytes());
										editEnity.setSalt(oldSalt);
										Clipboard clip = Clipboard.getSystemClipboard();
										if (clip.hasString()) {
											Map<DataFormat, Object> content = new HashMap<DataFormat, Object>();
											content.put(DataFormat.PLAIN_TEXT,apssword);
											if (clip.setContent(content)) {
												System.out.println("复制成功");
											}else {
												System.out.println("复制失败");
											}
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							
							Button editButton = new Button("修改");
							editButton.setLayoutX(69);
							editButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent event) {
									DataPasswordEnity editEnity = pmDataTable.getItems().get(getIndex());
									String oldSalt = editEnity.getSalt();
									Window win = ((Node)event.getSource()).getScene().getWindow();
									Map<String, Object> viewData = ViewUtil.openToStage(getClass(), "view/passwordmanager/editView/index.fxml");
									Scene scene = (Scene)viewData.get("scene");
									EditViewController editController = (EditViewController)viewData.get("controller");
									editController.initData(editEnity);
									if (scene!=null) {
										Stage editStage = new Stage();
										editStage.initOwner(win);
										editStage.initModality(Modality.WINDOW_MODAL);
										editStage.setResizable(false);
										editStage.setScene(scene);
										editStage.setTitle("修改密码");
										editStage.show();
										editStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
											@Override
											public void handle(WindowEvent event) {
												if (editController.geteditNum()>0) {
													doSearch();
												}else {
													editEnity.setSalt(oldSalt);
												}
											}
										});
									}
								}
							});
							Button delButton = new Button("删除");
							delButton.setLayoutX(115);
							delButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent event) {
									DataPasswordEnity editEnity = pmDataTable.getItems().get(getIndex());
									int delNum = passwordService.delEnity(editEnity);
									if (delNum>0) {
										doSearch();
									}
								}
							});
							actionPane.getChildren().addAll(copyButton,editButton,delButton);
							this.setGraphic(actionPane);
						}
					}
				};
				return cell;
			}
		});
		pmDataTable.setEditable(true);
		
		pagebar.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer param) {
				pageIndex = param;
				doSearch();
				return pmDataTable;
			}
		});
	}
}
