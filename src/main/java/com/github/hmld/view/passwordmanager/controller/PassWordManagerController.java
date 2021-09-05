package com.github.hmld.view.passwordmanager.controller;

import com.github.hmld.common.core.emnu.UseFlgEmnu;
import com.github.hmld.common.utils.view.ViewUtil;
import com.github.hmld.core.enity.DataPasswordEnity;
import com.github.hmld.core.service.IDataPasswordService;
import com.github.hmld.core.service.impl.DataPasswordServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 * 密码管理功能Controller
 * @author hmld
 *
 */
public class PassWordManagerController {
	private IDataPasswordService passwordService = new DataPasswordServiceImpl();
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
	private TableColumn<DataPasswordEnity, String> colAccountUserName;// 注册用户名
	@FXML
	private TableColumn<DataPasswordEnity, String> colAccountNickName;// 注册昵称
	@FXML
	private TableColumn<DataPasswordEnity, String> colAccountEmail;// 注册邮箱
	@FXML
	private TableColumn<DataPasswordEnity, String> colAccountPhoneNumber;// 注册手机号
	@FXML
	private TableColumn<DataPasswordEnity, String> colAccountPassword;// 密码
	@FXML
	private TableColumn<DataPasswordEnity, Integer> colUseFlg;// 使用状态
	@FXML
	private TableColumn<DataPasswordEnity, String> colActions;
	@FXML
	public void buttonAddAction(ActionEvent e) {
		Scene scene = ViewUtil.openToStage(getClass(), "view/passwordmanager/addView/index.fxml");
		if (scene!=null) {
			Stage addStage = new Stage();
			addStage.setScene(scene);
			addStage.setTitle("新增密码");
			addStage.show();
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
		ObservableList<DataPasswordEnity> list = FXCollections.observableArrayList();
		list.addAll(passwordService.queryEnityList(new DataPasswordEnity()));
		this.getColAppName().setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, String>("appName"));
		this.getColAppName().setCellFactory(TextFieldTableCell.forTableColumn());
		this.getColAppWebUrl().setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, String>("appWebUrl"));
		this.getColAppWebUrl().setCellFactory(TextFieldTableCell.forTableColumn());
		this.getColAccountUserName().setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, String>("accountUserName"));
		this.getColAccountUserName().setCellFactory(TextFieldTableCell.forTableColumn());
		this.getColAccountNickName().setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, String>("accountNickName"));
		this.getColAccountNickName().setCellFactory(TextFieldTableCell.forTableColumn());
		this.getColAccountNickName().setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, String>("accountNickName"));
		this.getColAccountNickName().setCellFactory(TextFieldTableCell.forTableColumn());
		this.getColAccountEmail().setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, String>("accountEmail"));
		this.getColAccountEmail().setCellFactory(TextFieldTableCell.forTableColumn());
		this.getColAccountPhoneNumber().setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, String>("accountPhoneNumber"));
		this.getColAccountPhoneNumber().setCellFactory(TextFieldTableCell.forTableColumn());
		this.getColAccountPassword().setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, String>("accountPassword"));
		this.getColUseFlg().setCellValueFactory(new PropertyValueFactory<DataPasswordEnity, Integer>("useFlg"));
		this.getColUseFlg().setCellFactory(new Callback<TableColumn<DataPasswordEnity,Integer>, TableCell<DataPasswordEnity,Integer>>() {
			
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
		this.getColActions().setCellFactory(new Callback<TableColumn<DataPasswordEnity,String>, TableCell<DataPasswordEnity,String>>() {
		@Override
		public TableCell<DataPasswordEnity, String> call(TableColumn<DataPasswordEnity, String> param) {
			TableCell<DataPasswordEnity, String> cell = new TableCell<DataPasswordEnity, String>(){
				@Override
				protected void updateItem(String item, boolean empty) {
					// TODO Auto-generated method stub
					super.updateItem(item, empty);
					if (item != null || empty==false) {
						AnchorPane actionPane = new AnchorPane();
						Button genButton = new Button("配置密码");
						genButton.setLayoutX(0);
						Button editButton = new Button("修改");
						editButton.setLayoutX(69);
						Button delButton = new Button("删除");
						delButton.setLayoutX(115);
						actionPane.getChildren().addAll(genButton,editButton,delButton);
						this.setGraphic(actionPane);
					}
				}
			};
			return cell;
		}
	});
		this.getPmDataTable().setEditable(true);
		this.getPmDataTable().setItems(list);
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
	public TableColumn<DataPasswordEnity, String> getColAccountUserName() {
		return colAccountUserName;
	}
	public void setColAccountUserName(TableColumn<DataPasswordEnity, String> colAccountUserName) {
		this.colAccountUserName = colAccountUserName;
	}
	public TableColumn<DataPasswordEnity, String> getColAccountNickName() {
		return colAccountNickName;
	}
	public void setColAccountNickName(TableColumn<DataPasswordEnity, String> colAccountNickName) {
		this.colAccountNickName = colAccountNickName;
	}
	public TableColumn<DataPasswordEnity, String> getColAccountEmail() {
		return colAccountEmail;
	}
	public void setColAccountEmail(TableColumn<DataPasswordEnity, String> colAccountEmail) {
		this.colAccountEmail = colAccountEmail;
	}
	public TableColumn<DataPasswordEnity, String> getColAccountPhoneNumber() {
		return colAccountPhoneNumber;
	}
	public void setColAccountPhoneNumber(TableColumn<DataPasswordEnity, String> colAccountPhoneNumber) {
		this.colAccountPhoneNumber = colAccountPhoneNumber;
	}
	public TableColumn<DataPasswordEnity, String> getColAccountPassword() {
		return colAccountPassword;
	}
	public void setColAccountPassword(TableColumn<DataPasswordEnity, String> colAccountPassword) {
		this.colAccountPassword = colAccountPassword;
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
