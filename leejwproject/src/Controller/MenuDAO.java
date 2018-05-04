package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.MenuOrder;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MenuDAO {

	// �߰�
	public MenuOrder getmanulistregiste(MenuOrder order) throws Exception {

		String dml = "insert into manulist " + "(no,name,price,list,count) " + "values "
				+ "(manulist_seq.nextval,?,?,?,?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		MenuOrder retval = null;

		try {
			con = DBUtil.getConnection();

			System.out.println(order.getName());
			System.out.println(Integer.parseInt(order.getPrice()));
			System.out.println(order.getList());
			System.out.println(Integer.parseInt(order.getCount()));

			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, order.getName());
			pstmt.setInt(2, Integer.parseInt(order.getPrice()));
			pstmt.setString(3, order.getList());
			pstmt.setInt(4, Integer.parseInt(order.getCount()));
			int i = pstmt.executeUpdate();

			if (i == 1) {

			}

			retval = new MenuOrder();

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		return retval;
	}
	// ����

	public MenuOrder getManuSetUpdate(MenuOrder sVo, int no) throws Exception {
		String dml = "update manulist set " + "name=?, price=?, list=? where no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		MenuOrder retval = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);

			pstmt.setString(1, sVo.getName());
			pstmt.setString(2, sVo.getPrice());
			pstmt.setString(3, sVo.getList());
			pstmt.setInt(4, no);
			// sql ó����� ����
			int i = pstmt.executeUpdate();

			// ��� ���

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
			e.getStackTrace();

		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
			e.getStackTrace();
		} finally {

			try {
				// �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����

				if (pstmt != null)
					pstmt.close();
				if (pstmt != null)
					pstmt.close();

			} catch (SQLException e) {
			}

		}
		return retval;

	}



	// ��ü ����Ʈ ���
	public ArrayList<MenuOrder> getMenuTotal() {
		ArrayList<MenuOrder> list = new ArrayList<MenuOrder>();
		String tml = "select * from manulist";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MenuOrder emVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(tml);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				emVo = new MenuOrder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				list.add(emVo);
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return list;
	}

	// ����
	public void getManuDelete(int no) throws Exception {
		// ������ ó���� ���� sql ��
		String dml = "delete from manulist where no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, no);

			int i = pstmt.executeUpdate();
			System.out.println(pstmt.executeUpdate());

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�޴� ����");
				alert.setHeaderText("���� ����");
				alert.setContentText("���� ����");
				alert.showAndWait();

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�޴� ����");
				alert.setHeaderText("���� ����");
				alert.setContentText("���� ����");
				alert.showAndWait();

			}

		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// �� �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {

			}
		}

	}

}
