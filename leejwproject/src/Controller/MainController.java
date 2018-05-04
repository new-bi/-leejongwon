package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.print.attribute.standard.MediaSize.Other;

import Model.MenuOrder;
import Model.Order;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainController implements Initializable {
	// 고기 식사 음료
	@FXML
	private Button btMeat;
	@FXML
	private Button btMeal;
	@FXML
	private Button btDrink;
	// 추가 수정 삭제
	@FXML
	private Button btAdd;
	@FXML
	private Button btSet;
	@FXML
	private Button btDelete;
	// 결제 초기화 닫기
	@FXML
	private Button btPayment;
	@FXML
	private Button btReset;
	@FXML
	private Button btClose;
	// 좌석
	@FXML
	private Button btSit1;
	@FXML
	private Button btSit2;
	@FXML
	private Button btSit3;
	@FXML
	private Button btSit4;
	@FXML
	private Button btSit5;
	@FXML
	private Button btSit6;
	@FXML
	private Button btSit7;
	@FXML
	private Button btSit8;
	@FXML
	private Button btSit9;
	@FXML
	private Button btSit10;
	@FXML
	private Button btSit11;
	@FXML
	private Button btSit12;

	// 메뉴 테이블
	@FXML
	private TableView<MenuOrder> tvManu = new TableView<>();

	@FXML
	private TableView<Order> tvOrderList = new TableView<>();

	MenuOrder manuorder = new MenuOrder();
	ObservableList<MenuOrder> data = FXCollections.observableArrayList();
	
	
	ObservableList<Order> orderData = FXCollections.observableArrayList();

	// 관리자로그인
	@FXML
	private TextField txtID;
	@FXML
	private PasswordField txtPass;
	@FXML
	private Button btLogin;

	private String selectSit = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tvManu.setEditable(false);
		//
		TableColumn colName = new TableColumn("명칭");
		colName.setPrefWidth(120);
		colName.setMaxWidth(120);
		colName.setMinWidth(120);
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn colPrice = new TableColumn("가격");
		colPrice.setPrefWidth(120);
		colPrice.setMaxWidth(120);
		colPrice.setMinWidth(120);
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

		TableColumn colList = new TableColumn("목록");
		colList.setPrefWidth(120);
		colList.setMaxWidth(120);
		colList.setMinWidth(120);
		colList.setCellValueFactory(new PropertyValueFactory<>("list"));

		TableColumn colCount = new TableColumn("갯수");
		colCount.setPrefWidth(120);
		colCount.setMaxWidth(120);
		colCount.setMinWidth(120);
		colCount.setCellValueFactory(new PropertyValueFactory<>("count"));

		totalList();
		tvManu.setItems(data);
		tvManu.getColumns().addAll(colName, colPrice, colList, colCount);
		
		//@@@@@@@@@@@@@@@@@@2
		TableColumn colOrderSit = new TableColumn("좌석번호");
		colOrderSit.setPrefWidth(70);
		colOrderSit.setMaxWidth(70);
		colOrderSit.setMinWidth(70);
		colOrderSit.setCellValueFactory(new PropertyValueFactory<>("orderSit"));

		TableColumn colOrderName = new TableColumn("메뉴명");
		colOrderName.setPrefWidth(100);
		colOrderName.setMaxWidth(100);
		colOrderName.setMinWidth(100);
		colOrderName.setCellValueFactory(new PropertyValueFactory<>("orderName"));

		TableColumn colOrderPrice = new TableColumn("가격");
		colOrderPrice.setPrefWidth(90);
		colOrderPrice.setMaxWidth(90);
		colOrderPrice.setMinWidth(90);
		colOrderPrice.setCellValueFactory(new PropertyValueFactory<>("orderPrice"));

		TableColumn colOrderList = new TableColumn("목록");
		colOrderList.setPrefWidth(70);
		colOrderList.setMaxWidth(70);
		colOrderList.setMinWidth(70);
		colOrderList.setCellValueFactory(new PropertyValueFactory<>("orderList"));

		TableColumn colOrderCount = new TableColumn("갯수");
		colOrderCount.setPrefWidth(50);
		colOrderCount.setMaxWidth(50);
		colOrderCount.setMinWidth(50);
		colOrderCount.setCellValueFactory(new PropertyValueFactory<>("orderCount"));
		tvOrderList.getColumns().addAll(colOrderSit, colOrderName, colOrderPrice, colOrderList, colOrderCount);
		//@@@@@@@@@@@@@@@@@@@3
		TableColumn colPaymentSit = new TableColumn("좌석번호");
		colPaymentSit.setPrefWidth(70);
		colPaymentSit.setMaxWidth(70);
		colPaymentSit.setMinWidth(70);
		colPaymentSit.setCellValueFactory(new PropertyValueFactory<>("orderSit"));

		TableColumn colPaymentName = new TableColumn("메뉴명");
		colPaymentName.setPrefWidth(100);
		colPaymentName.setMaxWidth(100);
		colPaymentName.setMinWidth(100);
		colPaymentName.setCellValueFactory(new PropertyValueFactory<>("orderName"));

		TableColumn colPaymentPrice = new TableColumn("가격");
		colPaymentPrice.setPrefWidth(90);
		colPaymentPrice.setMaxWidth(90);
		colPaymentPrice.setMinWidth(90);
		colPaymentPrice.setCellValueFactory(new PropertyValueFactory<>("orderPrice"));

		TableColumn colPaymentList = new TableColumn("목록");
		colPaymentList.setPrefWidth(70);
		colPaymentList.setMaxWidth(70);
		colPaymentList.setMinWidth(70);
		colPaymentList.setCellValueFactory(new PropertyValueFactory<>("orderList"));

		TableColumn colPaymentCount = new TableColumn("갯수");
		colPaymentCount.setPrefWidth(50);
		colPaymentCount.setMaxWidth(50);
		colPaymentCount.setMinWidth(50);
		colPaymentCount.setCellValueFactory(new PropertyValueFactory<>("orderCount"));
		tvOrderList.getColumns().addAll(colPaymentSit, colPaymentName, colPaymentPrice, colPaymentList, colPaymentCount);
		//@@@@@@@@@@@@@@@@@@@@@@@@@
		btMeat.setOnAction(event -> handlerBtMeatActoion(event));
		//
		btLogin.setOnAction(event -> handlerBtLoginActoion(event));
		//
		btAdd.setOnAction(event -> handlerBtAddActoion(event));
		btSet.setOnAction(event -> handlerBtSetActoion(event));
		btDelete.setOnAction(event -> handlerBtDeleteActoion(event));
		//
		btPayment.setOnAction(event -> handlerBtPaymentActoion(event));
		btReset.setOnAction(event -> handlerBtResetActoion(event));
		btClose.setOnAction(event -> handlerBtCloseActoion(event));
		//
		btSit1.setOnAction(event -> handlerBtSit1Actoion(event));
		btSit2.setOnAction(event -> handlerBtSit2Actoion(event));
		btSit3.setOnAction(event -> handlerBtSit3Actoion(event));
		btSit4.setOnAction(event -> handlerBtSit4Actoion(event));
		btSit5.setOnAction(event -> handlerBtSit5Actoion(event));
		btSit6.setOnAction(event -> handlerBtSit6Actoion(event));
		btSit7.setOnAction(event -> handlerBtSit7Actoion(event));
		btSit8.setOnAction(event -> handlerBtSit8Actoion(event));
		btSit9.setOnAction(event -> handlerBtSit9Actoion(event));
		btSit10.setOnAction(event -> handlerBtSit10Actoion(event));
		btSit11.setOnAction(event -> handlerBtSit11Actoion(event));
		btSit12.setOnAction(event -> handlerBtSit12Actoion(event));

	}


	// 검색 버튼 고기류
	private void handlerBtMeatActoion(ActionEvent event) {

	}

	// 검색 버튼 식사류
	private void handlerBtMealActoion(ActionEvent event) {

	}

	// 검색 버튼 주류
	private void handlerBtDrinkActoion(ActionEvent event) {

	}

	// 전체 리스트
	public void totalList() {
		MenuDAO sDao = new MenuDAO();
		MenuOrder sVo;
		ArrayList<MenuOrder> list;
		list = sDao.getMenuTotal();
		int count = list.size();
		for (int i = 0; i < count; i++) {
			sVo = list.get(i);
			data.add(sVo);
		}
	}
	
	
	public void orderList() {
		MainDAO oDao = new MainDAO();
		Order oVo;
		ArrayList<Order> orderList;
		orderList = oDao.orderListMenu();
		int count = orderList.size();
		for (int i = 0; i < count; i++) {
			oVo = orderList.get(i);
			//data.add(oVo);
		}
	}
	
	

	// 삭제
	private void handlerBtDeleteActoion(ActionEvent event) {

	}

	// 초기화
	private void handlerBtResetActoion(ActionEvent event) {

	}

	// 수정
	private void handlerBtSetActoion(ActionEvent event) {

	}

	// 메뉴추가 버튼
	private void handlerBtAddActoion(ActionEvent event) {
		
		if(selectSit != null){	Order oVo = new Order(selectSit, tvManu.getSelectionModel().getSelectedItem().getName(),
				tvManu.getSelectionModel().getSelectedItem().getPrice(), tvManu.getSelectionModel().getSelectedItem().getList()
				, tvManu.getSelectionModel().getSelectedItem().getCount());

		
		
		
		/*oVo.setOrderSit(selectSit);
		oVo.setOrderName(tvManu.getSelectionModel().getSelectedItem().getName());
		oVo.setOrderPrice(tvManu.getSelectionModel().getSelectedItem().getPrice());
		oVo.setOrderList(tvManu.getSelectionModel().getSelectedItem().getList());
		oVo.setOrderCount(tvManu.getSelectionModel().getSelectedItem().getCount());*/
		
		
		
		orderData.addAll(oVo);
		tvOrderList.setItems(orderData);}
		else {
			System.out.println("다시");
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("오류");
			alert.setHeaderText("좌석 미선택");
			alert.setContentText("좌석을 선택바랍니다.");
			alert.setResizable(false);
			alert.showAndWait();
			return;
		}
		
	}
	//결제
	private void handlerBtPaymentActoion(ActionEvent event) {
	System.out.println(orderData); 
	
	
	
	}


	// 닫기
	private void handlerBtCloseActoion(ActionEvent event) {
		Platform.exit();
	}

	// 좌석 테이블
	// @@@@@@@@@@@@
	// 좌석 번호
	public void handlerBtSit1Actoion(ActionEvent event) {
		selectSit = "1";
		System.out.println(selectSit);
	}

	public void handlerBtSit2Actoion(ActionEvent event) {
		selectSit = "2";
		System.out.println(selectSit);
	}

	public void handlerBtSit3Actoion(ActionEvent event) {
		selectSit = "3";
		System.out.println(selectSit);
	}

	public void handlerBtSit4Actoion(ActionEvent event) {
		selectSit = "4";
		System.out.println(selectSit);
	}

	public void handlerBtSit5Actoion(ActionEvent event) {
		selectSit = "5";
		System.out.println(selectSit);
	}

	public void handlerBtSit6Actoion(ActionEvent event) {
		selectSit = "6";
		System.out.println(selectSit);
	}

	public void handlerBtSit7Actoion(ActionEvent event) {
		selectSit = "7";
		System.out.println(selectSit);
	}

	public void handlerBtSit8Actoion(ActionEvent event) {
		selectSit = "8";
		System.out.println(selectSit);
	}

	public void handlerBtSit9Actoion(ActionEvent event) {
		selectSit = "9";
		System.out.println(selectSit);
	}

	public void handlerBtSit10Actoion(ActionEvent event) {
		selectSit = "10";
		System.out.println(selectSit);
	}

	public void handlerBtSit11Actoion(ActionEvent event) {
		selectSit = "11";
		System.out.println(selectSit);
	}

	public void handlerBtSit12Actoion(ActionEvent event) {
		selectSit = "12";
		System.out.println(selectSit);
	}

	// 관리자 텝
	// @@@@@@@@@@@@@@@@@@@@@@@
	// 관리자 로그인 버튼 ->메뉴관리페이지열기
	public void handlerBtLoginActoion(ActionEvent event) {
		Alert alert;
		if (txtID.getText().equals("") || txtPass.getText().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("로그인실패");
			alert.setHeaderText("아이디와 비밀번호 미입력");
			alert.setContentText("아이디와 비밀번호중 입력하지 않은 항목이있습니다." + "\n다시 제대로 입력하시오.");
			alert.setResizable(false);
			alert.showAndWait();
			return;
		}
		if (txtID.getText().equals("admin") && txtPass.getText().equals("1234")) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/admin.fxml"));

				Parent mainView = (Parent) loader.load();

				Scene scane = new Scene(mainView);
				Stage mainMtage = new Stage();
				mainMtage.setResizable(false);
				mainMtage.setScene(scane);
				Stage oldStage = (Stage) btLogin.getScene().getWindow();
				oldStage.close();
				mainMtage.show();

			} catch (IOException e) {
				System.out.println("오류" + e);
			}
		} else {
			// 아이디패스워드 확인하라는 창
			alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("로그인 실패");
			alert.setContentText("아이디와 비밀번호가 일치하지 않습니다." + "\n다시 제대로 입력하시오.");
			alert.showAndWait();
		}

	}

}
