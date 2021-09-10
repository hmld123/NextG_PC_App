package com.github.hmld.view.passwordmanager.controller;

import java.util.Map;

import com.github.hmld.common.core.emnu.UseFlgEmnu;
import com.github.hmld.common.utils.view.ViewUtil;
import com.github.hmld.core.enity.DataPasswordEnity;
import com.github.hmld.core.service.IDataPasswordService;
import com.github.hmld.core.service.impl.DataPasswordServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
public class PassWordManagerController {
	private IDataPasswordService passwordService = new DataPasswordServiceImpl();
	private ObservableList<DataPasswordEnity> list = FXCollections.observableArrayList();
	@FXML
	private Button buttonAdd;
	@FXML
	private Button buttonEdit;
	@FXML
	private Button buttonDel;
	@FXML
	private Button buttonQuery;
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
	@FXML
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
			addStage.setTitle("新增密码");
			addStage.show();
			addStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					if (accController.getAddNum()>0) {
						doSearch();
					}
				}
			});
		}
	}
	@FXML
	public void buttonEditAction(ActionEvent e) {
		
	}
	@FXML
	public void buttonDelAction(ActionEvent e) {
		
	}
	@FXML
	public void buttonQueryAction(ActionEvent e) {
		this.doSearch();
	}
	
	private void doSearch() {
		getColAppName().setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, String>("appName"));
		getColAppName().setCellFactory(TextFieldTableCell.forTableColumn());
		getColAppWebUrl().setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, String>("appWebUrl"));
		getColAppWebUrl().setCellFactory(TextFieldTableCell.forTableColumn());
		getColAccountNickName().setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, String>("accountNickName"));
		getColAccountNickName().setCellFactory(TextFieldTableCell.forTableColumn());
		getColAccountNickName().setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, String>("accountNickName"));
		getColAccountNickName().setCellFactory(TextFieldTableCell.forTableColumn());
		getColUseFlg().setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, Integer>("useFlg"));
		getColUseFlg().setCellFactory(new Callback<TableColumn<DataPasswordEnity,Integer>, TableCell<DataPasswordEnity,Integer>>() {
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
		getColActions().setCellFactory(new Callback<TableColumn<DataPasswordEnity,String>, TableCell<DataPasswordEnity,String>>() {
			@Override
			public TableCell<DataPasswordEnity, String> call(TableColumn<DataPasswordEnity, String> param) {
				 TableCell<DataPasswordEnity, String> cell = new TableCell<DataPasswordEnity, String>(){
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null || empty==false) {
							AnchorPane actionPane = new AnchorPane();
							Button genButton = new Button("配置密码");
							genButton.setLayoutX(0);
							Button editButton = new Button("修改");
							editButton.setLayoutX(69);
							editButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent event) {
									DataPasswordEnity editEnity = getPmDataTable().getItems().get(getIndex());
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
									DataPasswordEnity editEnity = getPmDataTable().getItems().get(getIndex());
									int delNum = passwordService.delEnity(editEnity);
									if (delNum>0) {
										doSearch();
									}
								}
							});
							actionPane.getChildren().addAll(genButton,editButton,delButton);
							this.setGraphic(actionPane);
						}
					}
				};
				return cell;
			}
		});
		getPmDataTable().setEditable(true);
		list.clear();
		list.addAll(passwordService.queryEnityList(new DataPasswordEnity()));
		getPmDataTable().setEditable(true);
		getPmDataTable().setItems(list);
	}
	
	public Button getButtonAdd() {
		return buttonAdd;
	}
	public void setButtonAdd(Button buttonAdd) {
		this.buttonAdd = buttonAdd;
	}
	public Button getButtonEdit() {
		return buttonEdit;
	}
	public void setButtonEdit(Button buttonEdit) {
		this.buttonEdit = buttonEdit;
	}
	public Button getButtonDel() {
		return buttonDel;
	}
	public void setButtonDel(Button buttonDel) {
		this.buttonDel = buttonDel;
	}
	public Button getButtonQuery() {
		return buttonQuery;
	}
	public void setButtonQuery(Button buttonQuery) {
		this.buttonQuery = buttonQuery;
	}
	public TableView<DataPasswordEnity> getPmDataTable() {
		return pmDataTable;
	}
	public void setPmDataTable(TableView<DataPasswordEnity> pmDataTable) {
		this.pmDataTable = pmDataTable;
	}
	public IDataPasswordService getPasswordService() {
		return passwordService;
	}
	public void setPasswordService(IDataPasswordService passwordService) {
		this.passwordService = passwordService;
	}
	public TableColumn<DataPasswordEnity, String> getColAppName() {
		return colAppName;
	}
	public void setColAppName(TableColumn<DataPasswordEnity, String> colAppName) {
		this.colAppName = colAppName;
	}
	public TableColumn<DataPasswordEnity, String> getColAppWebUrl() {
		return colAppWebUrl;
	}
	public void setColAppWebUrl(TableColumn<DataPasswordEnity, String> colAppWebUrl) {
		this.colAppWebUrl = colAppWebUrl;
	}
	public TableColumn<DataPasswordEnity, String> getColAccountNickName() {
		return colAccountNickName;
	}
	public void setColAccountNickName(TableColumn<DataPasswordEnity, String> colAccountNickName) {
		this.colAccountNickName = colAccountNickName;
	}
	public TableColumn<DataPasswordEnity, String> getColActions() {
		return colActions;
	}
	public void setColActions(TableColumn<DataPasswordEnity, String> colActions) {
		this.colActions = colActions;
	}
	public TableColumn<DataPasswordEnity, Integer> getColUseFlg() {
		return colUseFlg;
	}
	public void setColUseFlg(TableColumn<DataPasswordEnity, Integer> colUseFlg) {
		this.colUseFlg = colUseFlg;
	}
	
}
