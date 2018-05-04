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

	// ��ư
	@FXML
	private Button btAdminAdd;
	@FXML
	private Button btAdminSet;
	@FXML
	private Button btAdminDelete;
	@FXML
	private Button btAdminClose;
	// ���̺��
	@FXML
	private TableView<MenuOrder> tvManu = new TableView<>();

	MenuOrder manuorder = new MenuOrder();
	ObservableList<MenuOrder> data = FXCollections.observableArrayList();
	ObservableList<MenuOrder> selectManu;
	boolean editDelete = false;

	int no;// ������ ���̺��� ������ ���� ����

	int selectedIndex; // ���̺��� ������ �л� ���� �ε��� ����

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tvManu.setEditable(false);
		//
		TableColumn colNo = new TableColumn("no");
		colNo.setPrefWidth(30);
		colNo.setMaxWidth(30);
		colNo.setMinWidth(30);
		colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
		TableColumn colName = new TableColumn("��Ī");
		colName.setPrefWidth(230);
		colName.setMaxWidth(230);
		colName.setMinWidth(230);
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn colPrice = new TableColumn("����");
		colPrice.setPrefWidth(320);
		colPrice.setMaxWidth(320);
		colPrice.setMinWidth(320);
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn colList = new TableColumn("���");
		colList.setPrefWidth(230);
		colList.setMaxWidth(230);
		colList.setMinWidth(230);
		colList.setCellValueFactory(new PropertyValueFactory<>("list"));
		TableColumn colCount = new TableColumn("����");
		colCount.setPrefWidth(220);
		colCount.setMaxWidth(220);
		colCount.setMinWidth(220);
		colCount.setCellValueFactory(new PropertyValueFactory<>("count"));
		totalList();
		tvManu.setItems(data);
		tvManu.getColumns().addAll(colNo, colName, colPrice, colList, colCount);

		btAdminAdd.setOnAction(event -> handlerBtAdminAddAction(event));// �߰� ��ư
		btAdminSet.setOnAction(event -> handlerBtAdminSetAction(event));// ���� ��ư
		btAdminDelete.setOnAction(event -> handlerBtAdminDeleteAction(event));// ���� ��ư
		btAdminClose.setOnAction(event -> handlerBtAdminCloseAction(event));// �ݱ� ��ư
		tvManu.setOnMouseClicked(event -> handlerBtnPieChartActoion(event));// ���̺� ����

	}

	// ���̺� ����
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

	// ��ü ����Ʈ
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

	// �޴��߰���ư
	public void handlerBtAdminAddAction(ActionEvent Event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/add.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btAdminAdd.getScene().getWindow());
			dialog.setTitle("�޴��߰�");

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
							alert.setTitle("�޴� �߰�");
							alert.setHeaderText(txtName.getText() + "���������� �߰��Ǿ����ϴ�..");
							alert.setContentText("������");
							alert.showAndWait();

							dialog.close();
						}
					}
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("�޴� �߰�");
					alert.setHeaderText("������ ��Ȯ�� �Է��Ͻÿ�.");
					alert.setContentText("���� �ٶ�");
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

	// �޴�������ư
	public void handlerBtAdminSetAction(ActionEvent Event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/set.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btAdminSet.getScene().getWindow());
			dialog.setTitle("����");

			Parent parentSet = (Parent) loader.load();
			MenuOrder Edit = tvManu.getSelectionModel().getSelectedItem();
			Button btSetAdd = (Button) parentSet.lookup("#btSetAdd");
			Button btSetClose = (Button) parentSet.lookup("#btSetClose");
			TextField txtSetName = (TextField) parentSet.lookup("#txtSetName");
			TextField txtSetprice = (TextField) parentSet.lookup("#txtSetprice");
			TextField txtSetList = (TextField) parentSet.lookup("#txtSetList");
			// �������� 1
			txtSetName.setText(Edit.getName() + "");
			txtSetprice.setText(Edit.getPrice() + "");
			txtSetList.setText(Edit.getList() + "");

			// �ӽ�
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

					// ���
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

	// �޴�������ư
	public void handlerBtAdminDeleteAction(ActionEvent event) {
		MenuDAO sDao = null;
		sDao = new MenuDAO();

		try {
			sDao.getManuDelete(no);

			data.removeAll(data);
			// �л� ��ü ����
			totalList();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	// �޴��ݱ��ư
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
