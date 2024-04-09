package lk.ijse.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.Db.DbConnection;
import lk.ijse.model.Order;
import lk.ijse.model.OrderDetail;

import java.sql.*;
import java.util.List;

public class OrderRepo {
    public static boolean save(Order order) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?,?,?,?)";


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, order.getId());
        pstm.setObject(2, order.getDate());
        pstm.setObject(3, order.getHandOverDate());
        pstm.setObject(4, order.getCusId());


        return pstm.executeUpdate() > 0;

    }

    public static String getCurrentId() throws SQLException {
        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String ordId = resultSet.getString(1);
            return ordId;
        }
        return null;
    }


    public static List<String> getStatus() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        obList.add("Successes");
        obList.add("Pending");

        return obList;
    }

    public static boolean save(String id, Date date, Date handOverDate, int qty, String status, String cusId, String description) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?,?)";


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,id);
        pstm.setObject(2, date);
        pstm.setObject(3, handOverDate);
        pstm.setObject(4, qty);
        pstm.setObject(5,status);
        pstm.setObject(6,cusId );
        pstm.setObject(7, description);

        return pstm.executeUpdate() > 0;
    }
}
