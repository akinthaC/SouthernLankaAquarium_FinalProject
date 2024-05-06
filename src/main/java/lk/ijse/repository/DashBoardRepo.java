package lk.ijse.repository;

import javafx.application.Platform;
import lk.ijse.Db.DbConnection;

import java.sql.*;

public class DashBoardRepo {
    public static String getSaleCount(Date date) throws SQLException {
        String sql = "SELECT COUNT(*) FROM orders WHERE date = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setDate(1, date);

        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return String.valueOf(rs.getInt(1));
        } else {
            return "0";
        }
    }

    public static String getMostSaleFishWeekly() throws SQLException {
        String sql = "SELECT fishId,description, SUM(qty) AS total_quantity_sold\n" +
                "FROM fish_order\n" +
                "WHERE date BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 WEEK) AND CURDATE()\n" +
                "GROUP BY fishId,description \n" +
                "ORDER BY total_quantity_sold DESC\n" +
                "LIMIT 1;";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);



        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return rs.getString("description");
        } else {
            //Platform.runLater(() -> lblMostSoldFish.setText("No sales data available for this week."));
        }
        return "a";
    }

    public static String getEmployeeCount() throws SQLException {
        String sql = "SELECT COUNT(DISTINCT empId) AS employee_count FROM employee";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return rs.getString("employee_count");
        }
        return "0";
    }
}
