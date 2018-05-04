package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.MenuOrder;
import Model.Order;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MainDAO {
	// 검색

	public MenuOrder getMenuCheck(String name) throws Exception {
		String dml = "select * from manulist where list = '고기류'";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MenuOrder retval = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retval = new MenuOrder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
			}
		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				if (rs != null)
					rs.close();
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

	public ArrayList<Order> orderListMenu() {
		ArrayList<Order> orderList = new ArrayList<Order>();
		String tml2 = "select * from orderlist";

		Connection con2 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		Order oVo = null;

		try {
			con2 = DBUtil.getConnection();
			pstmt2 = con2.prepareStatement(tml2);
			rs2 = pstmt2.executeQuery();
			while (rs2.next()) {
				oVo = new Order(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4),
						rs2.getString(5));
				orderList.add(oVo);
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs2 != null)
					rs2.close();
				if (pstmt2 != null)
					pstmt2.close();
				if (con2 != null)
					con2.close();
			} catch (SQLException se) {
			}
		}
		return orderList;
	}

}
