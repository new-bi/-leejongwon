package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.MenuOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminController implements Initializable {

	// 버튼
	@FXML
	private Button btAdminAdd;
	@FXML
	private Button btAdminSet;
	@FXML
	private Button btAdminDelete;
	@FXML
	private Button btAdminClose;
	// 테이블뷰
	@FXML
	private TableView<MenuOrder> tvManu = new TableView<>();

	MenuOrder manuorder = new MenuOrder();
	ObservableList<MenuOrder> data = FXCollections.observableArrayList();
	ObservableList<MenuOrder> selectManu;
	boolean editDelete = false;

	int no;// 삭제시 테이블에서 선택한 정보 저장

	int selectedIndex; // 테이블에서 선택한 학생 정보 인덱스 저장

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tvManu.setEditable(false);
		//
		TableColumn colNo = new TableColumn("no");
		colNo.setPrefWidth(30);
		colNo.setMaxWidth(30);
		colNo.setMinWidth(30);
		colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
		TableColumn colName = new TableColumn("명칭");
		colName.setPrefWidth(230);
		colName.setMaxWidth(230);
		colName.setMinWidth(230);
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn colPrice = new TableColumn("가격");
		colPrice.setPrefWidth(320);
		colPrice.setMaxWidth(320);
		colPrice.setMinWidth(320);
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn colList = new TableColumn("목록");
		colList.setPrefWidth(230);
		colList.setMaxWidth(230);
		colList.setMinWidth(230);
		colList.setCellValueFactory(new PropertyValueFactory<>("list"));
		TableColumn colCount = new TableColumn("갯수");
		colCount.setPrefWidth(220);
		colCount.setMaxWidth(220);
		colCount.setMinWidth(220);
		colCount.setCellValueFactory(new PropertyValueFactory<>("count"));
		totalList();
		tvManu.setItems(data);
		tvManu.getColumns().addAll(colNo, colName, colPrice, colList, colCount);

		btAdminAdd.setOnAction(event -> handlerBtAdminAddAction(event));// 추가 버튼
		btAdminSet.setOnAction(event -> handlerBtAdminSetAction(event));// 수정 버튼
		btAdminDelete.setOnAction(event -> handlerBtAdminDeleteAction(event));// 삭제 버튼
		btAdminClose.setOnAction(event -> handlerBtAdminCloseAction(event));// 닫기 버튼
		tvManu.setOnMouseClicked(event -> handlerBtnPieChartActoion(event));// 테이블 선택

	}

	// 테이블 선택
	private void handlerBtnPieChartActoion(MouseEvent event) {

		try {
			if (event.getClickCount() != 2) {
				selectManu = tvManu.getSelectionModel().getSelectedItems();
				no = selectManu.get(0).getNo();
				return;
			}
		} catch (Exception e) {

		}
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

	// 메뉴추가버튼
	public void handlerBtAdminAddAction(ActionEvent Event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/add.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btAdminAdd.getScene().getWindow());
			dialog.setTitle("메뉴추가");

			selectedIndex = tvManu.getSelectionModel().getSelectedIndex();
			Parent parentAdd = (Parent) loader.load();
			Button btAddAdd = (Button) parentAdd.lookup("#btAddAdd");
			Button btAddClose = (Button) parentAdd.lookup("#btAddClose");
			TextField txtName = (TextField) parentAdd.lookup("#txtAddName");
			TextField txtAddprice = (TextField) parentAdd.lookup("#txtAddprice");
			TextField txtAddList = (TextField) parentAdd.lookup("#txtAddList");
			TextField txtAddcount = (TextField) parentAdd.lookup("#txtAddcount");
			txtAddcount.setDisable(true);

			btAddAdd.setOnAction(event -> {

				try {
					data.removeAll(data);

					MenuOrder sVo = null;
					MenuDAO sDao = null;

					if (event.getSource().equals(btAddAdd)) {

						sVo = new MenuOrder(txtName.getText(), txtAddprice.getText(), txtAddList.getText(),
								txtAddcount.getText());
						sDao = new MenuDAO();
						sDao.getmanulistregiste(sVo);

						if (sDao != null) {
							totalList();
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("메뉴 추가");
							alert.setHeaderText(txtName.getText() + "성공적으로 추가되었습니다..");
							alert.setContentText("＃＃＃");
							alert.showAndWait();

							dialog.close();
						}
					}
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("메뉴 추가");
					alert.setHeaderText("정보를 정확히 입력하시오.");
					alert.setContentText("주의 바람");
					alert.showAndWait();
				}
			});

			btAddClose.setOnAction(e1 -> {

				dialog.close();
			});
			Scene scene = new Scene(parentAdd);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// 메뉴수정버튼
	public void handlerBtAdminSetAction(ActionEvent Event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/set.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btAdminSet.getScene().getWindow());
			dialog.setTitle("수정");

			Parent parentSet = (Parent) loader.load();
			MenuOrder Edit = tvManu.getSelectionModel().getSelectedItem();
			Button btSetAdd = (Button) parentSet.lookup("#btSetAdd");
			Button btSetClose = (Button) parentSet.lookup("#btSetClose");
			TextField txtSetName = (TextField) parentSet.lookup("#txtSetName");
			TextField txtSetprice = (TextField) parentSet.lookup("#txtSetprice");
			TextField txtSetList = (TextField) parentSet.lookup("#txtSetList");
			// 오류예상 1
			txtSetName.setText(Edit.getName() + "");
			txtSetprice.setText(Edit.getPrice() + "");
			txtSetList.setText(Edit.getList() + "");

			// 임시
			btSetAdd.setOnAction(event -> {
				data.remove(selectedIndex);
				MenuOrder sVo = null;
				MenuDAO sDao = null;
				try {

					sVo = new MenuOrder(txtSetName.getText(), txtSetprice.getText(), txtSetList.getText());
					sDao = new MenuDAO();

					sDao.getManuSetUpdate(sVo, sVo.getNo());
					// no = selectManu.get(0).getNo();
					System.out.println(no);

					// 대기
					sDao.getManuSetUpdate(sVo, no);

					data.removeAll(data);
					totalList();
					dialog.close();
				} catch (Exception e1) {
					e1.printStackTrace();
					System.out.println("dddd");
					System.out.println(e1);
				}
			});

			btSetClose.setOnAction(e -> {

				dialog.close();
			});

			Scene scene = new Scene(parentSet);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
		} catch (IOException e) {
			System.out.println(e.toString());
			System.out.println(e);
			e.getStackTrace();
		}

	}

	// 메뉴삭제버튼
	public void handlerBtAdminDeleteAction(ActionEvent event) {
		MenuDAO sDao = null;
		sDao = new MenuDAO();

		try {
			sDao.getManuDelete(no);

			data.removeAll(data);
			// 학생 전체 정보
			totalList();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	// 메뉴닫기버튼
	public void handlerBtAdminCloseAction(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/main.fxml"));
			Parent subView = (Parent) loader.load();
			Scene scane = new Scene(subView);
			Stage subMtage = new Stage();
			subMtage.setScene(scane);
			subMtage.setResizable(false);
			Stage oldStage = (Stage) btAdminClose.getScene().getWindow();
			oldStage.close();
			subMtage.show();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
